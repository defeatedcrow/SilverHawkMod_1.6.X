package mods.defeatedcrow.entity.projectile;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.common.SilverHawkCore;
import mods.defeatedcrow.util.DCsDamageSource;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet70GameEvent;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityNormalGatling extends Entity implements IProjectile{
	
	/** 地中判定に使うもの */
	protected int xTile = -1;
    protected int yTile = -1;
    protected int zTile = -1;
    protected int inTile;
    protected int inData;
    protected boolean inGround;

    /** 撃ったエンティティ */
    public Entity shootingEntity;
    
    /** 地中・空中にいる時間 */
    protected int ticksInGround;
    protected int ticksInAir;
    
    /** Seems to be some sort of timer for animating an arrow. */
    public int arrowShake;
    
    /** ダメージの大きさ */
    protected double damage = 3.0D;
    
    /** ターゲットのエンティティ */
    public Entity targetEntity;

    /** ノックバックの大きさ */
    protected int knockbackStrength = 0;

    public EntityNormalGatling(World par1World)
    {
        super(par1World);
        this.renderDistanceWeight = 10.0D;
        this.setSize(0.2F, 0.2F);
        this.damage = 2.5D;
    }
    
    /** 恐らくメインで使用する定義用メソッド。他は順次消したい。
     * @param par1World this :world
     * @param par2EntityLivingBase :owner of this
     * @param par3EntityLivingBase :target of this*/
    public EntityNormalGatling(World par1World, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase, float speed, float speed2, float ajustX, float ajustZ, float ajustY)
    {
    	super(par1World);
        this.renderDistanceWeight = 10.0D;
        this.shootingEntity = par2EntityLivingBase;
        this.targetEntity = par3EntityLivingBase;
        this.yOffset = 0.0F;
        this.setSize(0.2F, 0.2F);
        
        //初期方角の決定
        float yaw = TrajectoryCalculater.initialRotetionYaw(this, par2EntityLivingBase, par2EntityLivingBase, speed);
        float pitch = TrajectoryCalculater.initialRotetionPitch(this, par2EntityLivingBase, par2EntityLivingBase, speed);
        this.setLocationAndAngles(par2EntityLivingBase.posX, par2EntityLivingBase.posY + (double)par2EntityLivingBase.getEyeHeight(), par2EntityLivingBase.posZ, yaw, pitch);
        
        //位置の調整
        this.posX += -(double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * (1.0F + ajustZ))
        		- (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * ajustX);
        this.posY += 0.05000000149011612D + ajustY;
        this.posZ += (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * (1.0F + ajustZ))
        		- (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * ajustX);
        this.setPosition(this.posX, this.posY, this.posZ);
        
        //初速度
        this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
        this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
        this.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI));
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, speed * 1.5F, speed2);
    }

    protected void entityInit()
    {
        this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
    }

    /**
     * Similar to setArrowHeading, it's point the throwable entity to a x, y, z direction.
     * 速度にそって先端の向きを変えているらしい
     */
    public void setThrowableHeading(double par1, double par3, double par5, float par7, float par8)
    {
        float f2 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
        par1 /= (double)f2;
        par3 /= (double)f2;
        par5 /= (double)f2;
        par1 += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)par8;
        par3 += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)par8;
        par5 += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)par8;
        par1 *= (double)par7;
        par3 *= (double)par7;
        par5 *= (double)par7;
        this.motionX = par1;
        this.motionY = par3;
        this.motionZ = par5;
        float f3 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(par1, par5) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(par3, (double)f3) * 180.0D / Math.PI);
        this.ticksInGround = 0;
    }

    @SideOnly(Side.CLIENT)
    /**
     * Sets the position and rotation. Only difference from the other one is no bounding on the rotation. Args: posX,
     * posY, posZ, yaw, pitch
     * バウンディングボックスのグリッド線が出ない。らしい。
     */
    public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9)
    {
        this.setPosition(par1, par3, par5);
        this.setRotation(par7, par8);
    }

    @SideOnly(Side.CLIENT)

    /**
     * Sets the velocity to the args. Args: x, y, z
     * 速度の設定。
     */
    public void setVelocity(double par1, double par3, double par5)
    {
        this.motionX = par1;
        this.motionY = par3;
        this.motionZ = par5;

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(par1, par5) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(par3, (double)f) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch;
            this.prevRotationYaw = this.rotationYaw;
            this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
            this.ticksInGround = 0;
        }
    }

    /**
     * Called to update the entity's position/logic.
     * 更新関係
     */
    public void onUpdate()
    {
        super.onUpdate();

        //直前のパラメータと新パラメータを一致させている。
        //各成分の移動速度に合わせて向きを直しているところ。
        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, (double)f) * 180.0D / Math.PI);
        }

        //激突したブロックを確認している
        int i = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);

        //空気じゃない
        if (i > 0 && !this.isPenetrateBlock())
        {
            Block.blocksList[i].setBlockBoundsBasedOnState(this.worldObj, this.xTile, this.yTile, this.zTile);
            AxisAlignedBB axisalignedbb = Block.blocksList[i].getCollisionBoundingBoxFromPool(this.worldObj, this.xTile, this.yTile, this.zTile);

            //当たり判定に接触しているかどうか
            if (axisalignedbb != null && axisalignedbb.isVecInside(this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ)))
            {
                this.inGround = true;
            }
        }

        //空気じゃないブロックに当たった
        if (this.inGround)
        {
            int j = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);
            int k = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);

            //別メソッドで確認した（恐らくラグでのズレか何か）埋まりブロックのIDとメタを照合している。違ったら埋まり状態を解除、埋まり状態5tick継続でDead
            if (j == this.inTile && k == this.inData)
            {
            	++this.ticksInGround;
            	int limit = this.isPenetrateBlock() ? 20 : 2;

                if (this.ticksInGround > limit)
                {
                    this.setDead();
                }
            }
            else
            {
                this.inGround = false;
                this.motionX *= (double)(this.rand.nextFloat() * 0.1F);
                this.motionY *= (double)(this.rand.nextFloat() * 0.1F);
                this.motionZ *= (double)(this.rand.nextFloat() * 0.1F);
                this.ticksInGround = 0;
                this.ticksInAir = 0;
            }
        }
        else//別に埋まってない時。速度の更新。
        {
            ++this.ticksInAir;
            //ブロックとの衝突判定
            Vec3 vec3 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
            Vec3 vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks_do_do(vec3, vec31, false, true);
            vec3 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
            vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            
            //ブロック貫通がONの場合、ブロック衝突判定をスキップ
            if (this.isPenetrateBlock())
            {
            	movingobjectposition = null;
            }
            
            //ブロックに当たった
            if (movingobjectposition != null)
            {
                vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
            }

            //Entityとの衝突判定。
            Entity entity = null;
            List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;
            int l;
            float f1;

            //同じブロック範囲内にいるエンティティ全てに対して繰り返す
            for (l = 0; l < list.size(); ++l)
            {
                Entity entity1 = (Entity)list.get(l);

                //発射物自身or発射後5tick以外だとすりぬける
                if (entity1.canBeCollidedWith() && (entity1 != this.shootingEntity || this.ticksInAir >= 5))
                {
                    f1 = 0.3F;
                    AxisAlignedBB axisalignedbb1 = entity1.boundingBox.expand((double)f1, (double)f1, (double)f1);
                    MovingObjectPosition movingobjectposition1 = axisalignedbb1.calculateIntercept(vec3, vec31);

                    if (movingobjectposition1 != null)
                    {
                        double d1 = vec3.distanceTo(movingobjectposition1.hitVec);

                        if (d1 < d0 || d0 == 0.0D)
                        {
                            entity = entity1;
                            d0 = d1;
                        }
                    }
                }
            }

            if (entity != null)
            {
                movingobjectposition = new MovingObjectPosition(entity);
            }

            //対象によってはダメージがない
            if (movingobjectposition != null && movingobjectposition.entityHit != null)
            {
                if (movingobjectposition.entityHit instanceof EntityPlayer) 
                {
                	EntityPlayer entityplayer = (EntityPlayer)movingobjectposition.entityHit;

                    if (entityplayer.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(entityplayer))
                    {
                        movingobjectposition = null;
                    }
                    else if (entityplayer == this.shootingEntity)
                    {
                    	movingobjectposition = null;
                    }
                }
                else if (movingobjectposition.entityHit instanceof EntityTameable || movingobjectposition.entityHit instanceof EntityHorse)
                {
                	if (SilverHawkCore.disableDamageForTameable)
                	{
                		movingobjectposition = null;
                	}
                }
                else if (movingobjectposition.entityHit instanceof EntityVillager)
                {
                	EntityVillager villager = (EntityVillager)movingobjectposition.entityHit;
                	
                	if (SilverHawkCore.disableDamageForVillager)
                	{
                		movingobjectposition = null;
                	}
                }
            }

            float f2;
            float f3;

            //当たったあとの処理
            if (movingobjectposition != null)
            {
            	//エンティティに当たった
                if (movingobjectposition.entityHit != null)
                {
                    f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                    int i1 = MathHelper.ceiling_double_int((double)f2 * this.damage);
                    i1 += this.rand.nextInt(3);//強制クリティカル処理

                    DamageSource damagesource = null;

                    //独自のダメージソース
                    if (this.shootingEntity != null)
                    {
                        damagesource = this.thisDamageSource(this.shootingEntity);
                    }
                    else
                    {
                        damagesource = DamageSource.magic;
                    }

                    //バニラ矢と同様、燃えているフラグがONなら着火することも出来る
                    if (this.isBurning() && !(movingobjectposition.entityHit instanceof EntityEnderman))
                    {
                        movingobjectposition.entityHit.setFire(5);
                    }

                    if (movingobjectposition.entityHit.attackEntityFrom(damagesource, (float)i1))
                    {
                        if (movingobjectposition.entityHit instanceof EntityLivingBase)
                        {
                            EntityLivingBase entitylivingbase = (EntityLivingBase)movingobjectposition.entityHit;

                            if (this.knockbackStrength > 0)
                            {
                                f3 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);

                                if (f3 > 0.0F)
                                {
                                    movingobjectposition.entityHit.addVelocity(this.motionX * (double)this.knockbackStrength * 0.6000000238418579D / (double)f3, 0.1D, this.motionZ * (double)this.knockbackStrength * 0.6000000238418579D / (double)f3);
                                }
                            }
                            else
                            {
                            	movingobjectposition.entityHit.hurtResistantTime = 0;
                            }

                            if (this.shootingEntity != null)
                            {
                                EnchantmentThorns.func_92096_a(this.shootingEntity, entitylivingbase, this.rand);
                            }

                            if (this.shootingEntity != null && movingobjectposition.entityHit != this.shootingEntity && movingobjectposition.entityHit instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP)
                            {
                                ((EntityPlayerMP)this.shootingEntity).playerNetServerHandler.sendPacketToPlayer(new Packet70GameEvent(6, 0));
                            }
                        }

                        this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));

                        //当たったあと、弾を消去する。エンティティ貫通がONの弾種はそのまま残す。
                        if (!(movingobjectposition.entityHit instanceof EntityEnderman) && !this.isPenetrateEntity())
                        {
                            this.setDead();
                        }
                    }
                }
                else if (!this.isPenetrateBlock())  //エンティティには当たってない。ブロックに当たった。
                {
                    this.xTile = movingobjectposition.blockX;
                    this.yTile = movingobjectposition.blockY;
                    this.zTile = movingobjectposition.blockZ;
                    this.inTile = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);
                    this.inData = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
                    this.motionX = (double)((float)(movingobjectposition.hitVec.xCoord - this.posX));
                    this.motionY = (double)((float)(movingobjectposition.hitVec.yCoord - this.posY));
                    this.motionZ = (double)((float)(movingobjectposition.hitVec.zCoord - this.posZ));
                    f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                    this.posX -= this.motionX / (double)f2 * 0.05000000074505806D;
                    this.posY -= this.motionY / (double)f2 * 0.05000000074505806D;
                    this.posZ -= this.motionZ / (double)f2 * 0.05000000074505806D;
                    //this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
                    this.setIsCritical(false);
                    this.inGround = true;
                    
                    if (this.inTile != 0)
                    {
                        Block.blocksList[this.inTile].onEntityCollidedWithBlock(this.worldObj, this.xTile, this.yTile, this.zTile, this);
                    }
                }
            }

            //ターゲット追尾の場合の計算式
            if (this.targetEntity != null)
            {
            	if (this.thisType() == OrbitType.SNIPE);
            }
            
            
            //改めてポジションに速度を加算。向きも更新。
            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
            this.rotationPitch = (float)(Math.atan2(this.motionY, (double)f2) * 180.0D / Math.PI);
            
            while ( this.rotationPitch - this.prevRotationPitch < -180.0F)
            {
            	this.prevRotationPitch -= 360.0F;
            }

            while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
            {
                this.prevRotationPitch += 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw < -180.0F)
            {
                this.prevRotationYaw -= 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
            {
                this.prevRotationYaw += 360.0F;
            }

            this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
            this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
            float f4 = 0.99F;
            
            //重力落下
            f1 = this.fallSpeed();

            if (this.isInWater())
            {
                for (int j1 = 0; j1 < 4; ++j1)
                {
                    f3 = 0.25F;
                    this.worldObj.spawnParticle("bubble", this.posX - this.motionX * (double)f3, this.posY - this.motionY * (double)f3, this.posZ - this.motionZ * (double)f3, this.motionX, this.motionY, this.motionZ);
                }

                f4 = 0.8F;
            }

            this.motionX *= (double)f4;
            this.motionY *= (double)f4;
            this.motionZ *= (double)f4;
            this.motionY -= (double)f1;
            
            //遅くなってきたら消える
            if (this.worldObj.isRemote && this.motionX * this.motionX + this.motionZ * this.motionZ < 0.001D)
            {
            	this.setDead();
            }
            
            
            this.setPosition(this.posX, this.posY, this.posZ);
            this.doBlockCollisions();
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setShort("xTile", (short)this.xTile);
        par1NBTTagCompound.setShort("yTile", (short)this.yTile);
        par1NBTTagCompound.setShort("zTile", (short)this.zTile);
        par1NBTTagCompound.setByte("inTile", (byte)this.inTile);
        par1NBTTagCompound.setByte("inData", (byte)this.inData);
        par1NBTTagCompound.setByte("shake", (byte)this.arrowShake);
        par1NBTTagCompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
        par1NBTTagCompound.setDouble("damage", this.damage);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        this.xTile = par1NBTTagCompound.getShort("xTile");
        this.yTile = par1NBTTagCompound.getShort("yTile");
        this.zTile = par1NBTTagCompound.getShort("zTile");
        this.inTile = par1NBTTagCompound.getByte("inTile") & 255;
        this.inData = par1NBTTagCompound.getByte("inData") & 255;
        this.arrowShake = par1NBTTagCompound.getByte("shake") & 255;
        this.inGround = par1NBTTagCompound.getByte("inGround") == 1;

        if (par1NBTTagCompound.hasKey("damage"))
        {
            this.damage = par1NBTTagCompound.getDouble("damage");
        }
    }

    /**
     * Called by a player entity when they collide with an entity
     */
    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer)
    {
    	
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 0.0F;
    }

    public void setDamage(double par1)
    {
        this.damage = par1;
    }

    public double getDamage()
    {
        return this.damage;
    }

    /**
     * Sets the amount of knockback the arrow applies when it hits a mob.
     */
    public void setKnockbackStrength(int par1)
    {
        this.knockbackStrength = par1;
    }

    /**
     * If returns false, the item will not inflict any damage against entities.
     */
    public boolean canAttackWithItem()
    {
        return false;
    }

    /**
     * Whether the arrow has a stream of critical hit particles flying behind it.
     */
    public void setIsCritical(boolean par1)
    {
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);

        if (par1)
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 | 1)));
        }
        else
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 & -2)));
        }
    }

    /**
     * Whether the arrow has a stream of critical hit particles flying behind it.
     */
    public boolean getIsCritical()
    {
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);
        return (b0 & 1) != 0;
    }
    
    
    //以下、当MOD用のパラメータ定義部分
    
    /** 軌道タイプ */
    public OrbitType thisType()
    {
    	return OrbitType.STRAIGHT;
    }
    
    /** 落下速度 */
    public float fallSpeed()
    {
    	return 0.0F;
    }
    
    /** ダメージソースのタイプ */
    public DamageSource thisDamageSource(Entity entity)
    {
    	return entity != null ? DCsDamageSource.NormalGutling(entity) : DamageSource.magic;
    }
    
    /** ブロック貫通 */
    public boolean isPenetrateBlock()
    {
    	return false;
    }
    
    /** エンティティ貫通 */
    public boolean isPenetrateEntity()
    {
    	return false;
    }

}
