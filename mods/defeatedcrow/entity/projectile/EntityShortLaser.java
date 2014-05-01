package mods.defeatedcrow.entity.projectile;

import java.util.List;

import mods.defeatedcrow.common.SilverHawkCore;
import mods.defeatedcrow.util.DCsDamageSource;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet70GameEvent;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityShortLaser extends EntityNormalGatling{

	public EntityShortLaser(World par1World)
    {
        super(par1World);
        this.renderDistanceWeight = 10.0D;
        this.setSize(0.2F, 1.0F);
        this.damage = 3.5D;
    }
	
	public EntityShortLaser(World par1World, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase, float speed, float speed2, float ajustX, float ajustZ, float ajustY)
    {
		super(par1World, par2EntityLivingBase, par3EntityLivingBase, speed, speed2, ajustX, ajustZ, ajustY);
    }
	
    //以下、当MOD用のパラメータ定義部分
    
    /** 軌道タイプ */
	@Override
    public OrbitType thisType()
    {
    	return OrbitType.STRAIGHT;
    }
    
    /** 落下速度 */
	@Override
    public float fallSpeed()
    {
    	return 0.0F;
    }
    
    /** ダメージソースのタイプ */
	@Override
    public DamageSource thisDamageSource(Entity entity)
    {
    	return DCsDamageSource.Laser(entity);
    }
	
	/** ブロック貫通 */
	@Override
    public boolean isPenetrateBlock()
    {
    	return false;
    }
    
    /** エンティティ貫通 */
	@Override
    public boolean isPenetrateEntity()
    {
    	return true;
    }

}
