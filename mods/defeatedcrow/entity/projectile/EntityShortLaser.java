package mods.defeatedcrow.entity.projectile;

import mods.defeatedcrow.util.DCsDamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityShortLaser extends EntityNormalGatling{

	public EntityShortLaser(World par1World)
    {
        super(par1World);
        this.renderDistanceWeight = 10.0D;
        this.setSize(0.2F, 1.0F);
        this.damage = 3.0D;
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

}
