package mods.defeatedcrow.world;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.common.SilverHawkCore;
import mods.defeatedcrow.world.gen.ChunkProviderReverse;
import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderReverse extends WorldProvider
{

	  public void registerWorldChunkManager()
	  {
		  this.worldChunkMgr = new WorldChunkManagerReverse();
		  this.dimensionId = SilverHawkCore.DCsDimID;
	  }

	  @Override
	  public String getDimensionName()
	  {
	  	return "Reverse World";
	  }

	  public boolean isSurfaceWorld()
	  {
		  return false;
	  }

	  public double getMovementFactor()
	  {
		  return 8.0D;
	  }

	  public float calculateCelestialAngle(long par1, float par3)
	  {
		  return 1.0F;
	  }

	  public float getCloudHeight()
	  {
		  return 64.0F;
	  }

	  public boolean darkenSkyDuringRain()
	  {
		  return false;
	  }

	  public boolean canCoordinateBeSpawn(int par1, int par2)
	  {
		  int var3 = this.worldObj.getFirstUncoveredBlock(par1, par2);
		  return var3 == SilverHawkCore.ClearStone.blockID;
	  }

	  public ChunkCoordinates getEntrancePortalLocation()
	  {
		  return new ChunkCoordinates(100, 50, 0);
	  }

	  public int getAverageGroundLevel()
	  {
		  return 20;
	  }

	  public double getHorizon()
	  {
		  return 0.0D;
	  }

	  @SideOnly(Side.CLIENT)
	  public boolean hasVoidParticles(boolean var1)
	  {
		  return false;
	  }

	  @SideOnly(Side.CLIENT)
	  public boolean isSkyColored()
	  {
		  return true;
	  }

	  public double getVoidFogYFactor()
	  {
		  return 1.0D;
	  }

	  @SideOnly(Side.CLIENT)
	  public Vec3 getFogColor(float par1, float par2)
	  {
		  float var3 = MathHelper.cos(par1 * 3.141593F * 2.0F) * 2.0F + 0.5F;
		  
		  if (var3 < 0.0F)
		    {
		      var3 = 0.0F;
		    }

		    if (var3 > 1.0F)
		    {
		      var3 = 1.0F;
		    }

		    float var4 = 1.0F;
		    float var5 = 0.9176471F;
		    float var6 = 0.4F;
		    var4 *= (var3 * 3.94F + 0.06F);
		    var5 *= (var3 * 0.94F + 0.06F);
		    var6 *= (var3 * 0.91F + 0.09F);
		    return this.worldObj.getWorldVec3Pool().getVecFromPool(var4, var5, var6);
	  }

	  public void setAllowedSpawnTypes(boolean allowHostile, boolean allowPeaceful)
	  {
		  allowPeaceful = true;
	  }

	  public String getWelcomeMessage()
	  {
		  return "Entering the Reverse World";
	  }

	  public String getDepartMessage()
	  {
		  return "Leaving the Reverse World";
	  }

	  public IChunkProvider createChunkGenerator()
	  {
		  return new ChunkProviderReverse(this.worldObj, this.worldObj.getSeed());
	  }
}
