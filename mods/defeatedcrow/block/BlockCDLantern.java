package mods.defeatedcrow.block;

import static net.minecraftforge.common.ForgeDirection.DOWN;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.src.*;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import mods.defeatedcrow.client.RenderCDLBlock;
import mods.defeatedcrow.common.*;

public class BlockCDLantern extends Block{
	
	@SideOnly(Side.CLIENT)
    private Icon itemIconCDL;
	@SideOnly(Side.CLIENT)
	private Icon frameCDL;
	@SideOnly(Side.CLIENT)
	private Icon glowCDL;
	
	public BlockCDLantern (int blockid)
	{
		super(blockid, Material.glass);
		this.setHardness(0.2F);
		this.setResistance(2.0F);
		this.setStepSound(Block.soundStoneFootstep);
		this.setLightValue(1.0F);
	}
	
	@Override
	public int idDropped(int metadata, Random rand, int fortune)
	{
		return this.blockID;
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}
 
	public boolean renderAsNormalBlock() 
	{
		return false;
	}
	
	@Override
	public int getRenderType()
	{
		return SilverHawkCore.modelLantern;
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
    }
	
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
    }
	
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        this.CDLanternBoundingBox(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
    }
	
	public void CDLanternBoundingBox (int par1)
	{
		float f = 0.25F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
	}
	
	@SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    {
        return par1 == 0 ? this.frameCDL : (par1 == 1 ? this.itemIconCDL : this.glowCDL);
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.itemIconCDL = par1IconRegister.registerIcon("crowsdefeat:itemCDlantern");
		this.frameCDL = par1IconRegister.registerIcon("crowsdefeat:CDlantern_frame");
		this.glowCDL = par1IconRegister.registerIcon("crowsdefeat:CDlantern_side");
	}

}
