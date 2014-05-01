package mods.defeatedcrow.entity.projectile;

import mods.defeatedcrow.util.DCsDamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntitySmallWave extends EntityNormalGatling{

	public EntitySmallWave(World par1World)
    {
        super(par1World);
        this.renderDistanceWeight = 10.0D;
        this.setSize(1.0F, 0.2F);
        this.damage = 2.0D;
    }
	
	public EntitySmallWave(World par1World, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase, float speed, float speed2, float ajustX, float ajustZ, float ajustY)
    {
		super(par1World, par2EntityLivingBase, par3EntityLivingBase, speed, speed2, ajustX, ajustZ, ajustY);
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
    	return DCsDamageSource.Energy(entity);
    }
    
    /** ブロック貫通 */
	@Override
    public boolean isPenetrateBlock()
    {
    	return true;
    }
    
    /** エンティティ貫通 */
	@Override
    public boolean isPenetrateEntity()
    {
    	return true;
    }

}
