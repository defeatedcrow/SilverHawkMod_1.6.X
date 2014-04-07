package mods.defeatedcrow.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TrajectoryCalculater {
	
	public static float initialRotetionYaw (EntityNormalGatling proj, EntityLivingBase par2Entity, EntityLivingBase par3Entity, float par4)
	{
		if (par2Entity != null)
		{
			if (proj.thisType() == OrbitType.SNIPE && par3Entity != null)
			{
		        double d0 = par3Entity.posX - par2Entity.posX;
		        double d2 = par3Entity.posZ - par2Entity.posZ;
		        double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);

		        if (d3 >= 1.0E-7D)
		        {
		            float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
		            return f2;
		        }
		        return 0.0F;
			}
			else
			{
		        return par2Entity.rotationYaw;
			}
		}
		
		return 0.0F;
	}
	
	public static float initialRotetionPitch (EntityNormalGatling proj, EntityLivingBase par2Entity, EntityLivingBase par3Entity, float par4)
	{
		if (par2Entity != null)
		{
			if (proj.thisType() == OrbitType.SNIPE && par3Entity != null)
			{
				double posY = par2Entity.posY + (double)par2Entity.getEyeHeight() - 0.10000000149011612D;
		        double d0 = par3Entity.posX - par2Entity.posX;
		        double d1 = par3Entity.boundingBox.minY + (double)(par3Entity.height / 3.0F) - posY;
		        double d2 = par3Entity.posZ - par2Entity.posZ;
		        double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);

		        if (d3 >= 1.0E-7D)
		        {
		            float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));
		            return f3;
		        }
		        return 0.0F;
			}
			else
			{
		        return par2Entity.rotationPitch;
			}
		}
		
		return 0.0F;
	}

}
