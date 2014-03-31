package mods.defeatedcrow.block;

import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.WEST;
import static net.minecraftforge.common.ForgeDirection.DOWN;
import static net.minecraftforge.common.ForgeDirection.UP;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.*;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Direction;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import mods.defeatedcrow.common.SilverHawkCore;

public class BlockGlowMoss extends Block{
	
	public BlockGlowMoss(int blockid)
	{
		super(blockid, Material.vine);
		this.setTickRandomly(true);
		this.setLightValue(0.4F);
	}
	
	public void setBlockBoundsForItemRender()
    {
        float f = 0.0625F;
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
    }
	
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return false;
    }
	
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        float f = 0.0625F;
        float f1 = 0.0F;
        float f2 = 0.0F;
        float f3 = 0.0F;
        float f4 = 1.0F;
        float f5 = f;
        float f6 = 1.0F;

        this.setBlockBounds(f1, f2, f3, f4, f5, f6);
    }
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }
	
	public boolean canPlaceBlockOnSide(World par1World, int par2, int par3, int par4)
    {
		return this.canBePlacedOn(par1World.getBlockId(par2, par3 - 1, par4));
    }
	
	private boolean canBePlacedOn(int par1)
    {
        if (par1 == 0)
        {
            return false;
        }
        else
        {
            Block block = Block.blocksList[par1];
            return block.renderAsNormalBlock() && block.blockMaterial.blocksMovement();
        }
    }
	
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
		if (!par1World.isRemote && !this.canBePlacedOn(par1World.getBlockId(par2, par3 - 1, par4)))
        {
            this.dropBlockAsItem(par1World, par2, par3, par4, 0, 0);
            par1World.setBlockToAir(par2, par3, par4);
        }
    }
    	
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote && par1World.rand.nextInt(6) == 0)
        {
        	int i1 = par1World.rand.nextInt(6);
            int X1 = par2;
            int Y1 = par3;
            int Z1 = par4;
        	
            if (i1 < 0)
            {
            	
            }
            else
            {
            	X1 = par2 + this.sideX(i1);
            	Y1 = par3 + this.sideY(i1);
            	Z1 = par4 + this.sideZ(i1);
            	
            	if (par1World.isAirBlock(X1, Y1, Z1) && (par1World.getBlockLightValue(X1, Y1, Z1) < 13 || !par1World.isDaytime()))
            	{
            		if (this.canPlaceBlockOnSide(par1World, X1, Y1, Z1))
            		{
            			par1World.setBlock(X1, Y1, Z1, this.blockID);
            		}
            		else if (par1World.isAirBlock(X1, Y1 - 1, Z1) || this.canPlaceBlockOnSide(par1World, X1, Y1 - 1, Z1))
            		{
            			par1World.setBlock(X1, Y1 - 1, Z1, this.blockID);
            		}
            	}
            }
        }
    }
	
	public int sideX (int par1)
	{
		if (par1 == 4) return -1;
		else if (par1 == 5) return 1;
		else return 0;
	}
	
	public int sideY (int par1)
	{
		if (par1 == 0) return -1;
		else if (par1 == 1) return 1;
		else return 0;
	}
	
	public int sideZ (int par1)
	{
		if (par1 == 2) return -1;
		else if (par1 == 3) return 1;
		else return 0;
	}
	
	@Override
	public int idDropped(int metadata, Random rand, int fortune)
	{
		return this.blockID;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("crowsdefeat:glowmoss");
	}

}