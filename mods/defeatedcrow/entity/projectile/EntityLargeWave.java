package mods.defeatedcrow.entity.projectile;

import mods.defeatedcrow.util.DCsDamageSource;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityLargeWave extends EntityNormalGatling{

	public EntityLargeWave(World par1World)
    {
        super(par1World);
        this.renderDistanceWeight = 10.0D;
        this.setSize(1.2F, 0.2F);
    }
	
	public EntityLargeWave(World par1World, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase, float speed, float speed2, float ajustX, float ajustZ)
    {
		super(par1World, par2EntityLivingBase, par3EntityLivingBase, speed, speed2, ajustX, ajustZ);
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
    public DamageSource thisDamageSource()
    {
    	return DCsDamageSource.Energy(this.shootingEntity);
    }

}
