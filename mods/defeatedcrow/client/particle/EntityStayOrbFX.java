package mods.defeatedcrow.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
 
 
@SideOnly(Side.CLIENT)
public class EntityStayOrbFX extends EntityFX {
 
	// 発生地点のY座標。消滅条件に利用する
	double orginalPosY;
 
	public EntityStayOrbFX(World par1World, double par2, double par4,
			double par6, double par8, double par10, double par12) {
		super(par1World, par2, par4, par6);
		this.orginalPosY = par4;
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
	}
	
	@Override
	public void setAlphaF(float par1)
    {
        this.particleAlpha = 0.5F;
    }
 
	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
 
		// 小さくする。一定以上小さくなると消滅
		if (this.particleScale > 0.05F) {
			this.particleScale = this.particleScale - 0.02F;
		}
		else{
			this.setDead();
		}
	}
 
	@Override
	public int getFXLayer() {
		return 2;
	}
 
}
