package mods.defeatedcrow.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.common.*;
import mods.defeatedcrow.item.ItemFluorite;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.src.*;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;


public class EntityCrow extends EntityTameable{
	
	public float wingA = 0.0F;
    public float dPos = 0.0F;
    public float wingC;
    public float wingB;
    public float destB = 1.0F;
    protected int soundRand;
    
    protected boolean isFlying;
    
	
	public EntityCrow(World par1World)
	{
		super (par1World);
		this.setSize(0.4F, 0.7F);
		this.soundRand = this.rand.nextInt(6000) + 6000;
		float f = 0.3F;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(5, new EntityAIFollowOwnerCrow(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(6, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntityZombie.class, 100, false));
        this.setTamed(false);
	}

	//entity strength
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.30000001192092896D);

        if (this.isTamed())
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(20.0D);
        }
        else
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(10.0D);
        }
    }
	
	public int getTotalArmorValue()
	{
		return 2;
		
	}
	
	//entity AI
	public boolean isAIEnabled()
	{
		return true;
	}
	
	protected void updateAITick()
    {
        this.dataWatcher.updateObject(18, Float.valueOf(this.getHealth()));
    }
	
	protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(18, new Float(this.getHealth()));
        this.dataWatcher.addObject(19, new Byte((byte)0));
    }
	
	public void setAttackTarget(EntityLiving par1EntityLiving)
    {
        super.setAttackTarget(par1EntityLiving);
    }
	
	//entity sound
	protected float getSoundVolume()
    {
        return 0.6F;
    }
	
	protected String getLivingSound()
    {
        return "crowsdefeat:crowcry";
    }
	
	protected String getHurtSound()
    {
        return "mob.bat.hurt";
    }
	
	protected String getDeathSound()
    {
        return "mob.bat.hurt";
    }
	
	//entity NBT
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
    }
	
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
    }
	
	//atack
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
            Entity entity = par1DamageSource.getEntity();
            this.aiSit.setSitting(false);

            if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow))
            {
                par2 = (par2 + 1) / 2;
            }

            return super.attackEntityFrom(par1DamageSource, par2);
        }
    }
	
	public boolean attackEntityAsMob(Entity par1Entity)
    {
        int i = this.isTamed() ? 8 : 5;
        return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float)i);
    }
	
	//entity living event
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		this.wingB = this.wingA;
        this.wingC = this.dPos;
        this.dPos = (float)((double)this.dPos + (double)(this.onGround ? -1 : 4) * 0.3D);
		
        if (this.dPos < 0.0F)
        {
            this.dPos = 0.0F;
        }

        if (this.dPos > 1.0F)
        { 
            this.dPos = 1.0F;
        }

        if (!this.onGround && this.destB < 1.0F)
        {
            this.destB = 1.0F;
        }

        this.destB = (float)((double)this.destB * 0.9D);
        
		if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.6D;
        }
		
		this.wingA += this.destB * 2.0F;
		
		if (!this.isChild() && !this.worldObj.isRemote && --this.soundRand <= 0)
        {
			this.playSound("mob.chicken.plop", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            this.dropItem(SilverHawkCore.BlackFeather.itemID, 1);
            this.soundRand = this.rand.nextInt(6000) + 6000;
        }
		
		if (!this.onGround && !this.inWater)
		{
			this.isFlying = true;
		}
		else
		{
			this.isFlying = false;
		}
		
	}
	
	public void setTamed(boolean par1)
    {
        super.setTamed(par1);

        if (par1)
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(20.0D);
        }
        else
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(10.0D);
        }
    }
	
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
		
		if (this.isTamed())
		{
	            if (itemstack != null)
	            {
	                if (Item.itemsList[itemstack.itemID] instanceof ItemFood)
	                {
	                    ItemFood itemfood = (ItemFood)Item.itemsList[itemstack.itemID];

	                    if (itemfood.isWolfsFavoriteMeat() && this.dataWatcher.getWatchableObjectFloat(18) < 20.0F)
	                    {
	                        if (!par1EntityPlayer.capabilities.isCreativeMode)
	                        {
	                            --itemstack.stackSize;
	                        }

	                        this.heal(itemfood.getHealAmount());

	                        if (itemstack.stackSize <= 0)
	                        {
	                            par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
	                        }

	                        return true;
	                    }
	                }
	                else if (itemstack.itemID == SilverHawkCore.Fluorite.itemID && !this.worldObj.isRemote)
                    {
                    	this.setTamed(false);
                        this.setPathToEntity((PathEntity)null);
                        this.setAttackTarget((EntityLiving)null);
                        this.aiSit.setSitting(false);
                        this.setHealth(20.0F);
                        this.playTameEffect(false);
                        this.worldObj.setEntityState(this, (byte)6);
                    }
	            }
	            if (par1EntityPlayer.getCommandSenderName().equalsIgnoreCase(this.getOwnerName()) &&!this.worldObj.isRemote && !this.isBreedingItem(itemstack))
	            {
	                this.aiSit.setSitting(!this.isSitting());
	                this.isJumping = false;
	                this.setPathToEntity((PathEntity)null);
	                this.setTarget((Entity)null);
	                this.setAttackTarget((EntityLivingBase)null);
	            }
		}
		else if (itemstack != null && itemstack.itemID == SilverHawkCore.Fluorite.itemID)
        {
            boolean k;
            String name = this.getOwnerName();
            if (name != par1EntityPlayer.getCommandSenderName() && !par1EntityPlayer.capabilities.isCreativeMode)
            {
            	--itemstack.stackSize;
            }
            if (itemstack.stackSize <= 0)
            {
                par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
            }

            if (!this.worldObj.isRemote)
            {
            	this.setTamed(true);
                this.setPathToEntity((PathEntity)null);
                this.setAttackTarget((EntityLiving)null);
                this.aiSit.setSitting(true);
                this.setHealth(20.0F);
                this.setOwner(par1EntityPlayer.getCommandSenderName());
                this.playTameEffect(true);
                this.worldObj.setEntityState(this, (byte)7);
            }

            return true;
        }
		
		return super.interact(par1EntityPlayer);
	}
	
	@SideOnly(Side.CLIENT)
    public boolean getCrowFlying()
    {
        return this.isFlying;
    }
	
	@SideOnly(Side.CLIENT)
    public float getWingAngle(float par1)
	{
		float f2 = 0.3F;
		f2 = this.isFlying ? 0.3F : 0.0F;
		return (float)Math.PI*f2;
	}
	
	//other setting
	protected int getDropItemId()
    {
        return SilverHawkCore.BlackFeather.itemID;
    }
	
	protected void fall(float par1) {}
	
	public EntityCrow spawnBabyAnimal(EntityAgeable par1EntityAgeable)
    {
        EntityCrow entitycrow = new EntityCrow(this.worldObj);
        String s = this.getOwnerName();

        if (s != null && s.trim().length() > 0)
        {
            entitycrow.setOwner(s);
            entitycrow.setTamed(true);
        }
		return entitycrow;
    }
	
	@SideOnly(Side.CLIENT)
    public void handleHealthUpdate(byte par1)
	{
		super.handleHealthUpdate(par1);
	}
	
	public boolean isBreedingItem(ItemStack par1ItemStack)
    {
        return par1ItemStack == null ? false : (!(Item.itemsList[par1ItemStack.itemID] instanceof ItemFood) ? false : ((ItemFood)Item.itemsList[par1ItemStack.itemID]).isWolfsFavoriteMeat());
    }
	
	public boolean canMateWith(EntityAnimal par1EntityAnimal)
    {
        if (par1EntityAnimal == this)
        {
            return false;
        }
        else if (!this.isTamed())
        {
            return false;
        }
        else if (!(par1EntityAnimal instanceof EntityCrow))
        {
            return false;
        }
        else
        {
            EntityCrow entitycrow = (EntityCrow)par1EntityAnimal;
            return !entitycrow.isTamed() ? false : (entitycrow.isSitting() ? false : this.isInLove() && entitycrow.isInLove());
        }
    }

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
		
		return this.spawnBabyAnimal(entityageable);
	}
	
	

}
