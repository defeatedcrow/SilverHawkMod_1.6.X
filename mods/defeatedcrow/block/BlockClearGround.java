package mods.defeatedcrow.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import mods.defeatedcrow.common.SilverHawkCore;

public class BlockClearGround extends Block{

	@SideOnly(Side.CLIENT)
	private Icon oreIcon[]; 
	
	private static String[] stoneName = {"dirt", "sand", "clay", "gravel"};
	
	public BlockClearGround(int blockid)
	{
		super(blockid, Material.ground);
		this.setHardness(1.0F);
		this.setResistance(1.0F);
		this.setStepSound(Block.soundGrassFootstep);
		this.setLightValue(0.0F);
		this.setLightOpacity(0);
	}
	
	public int quantityDropped(Random random)
	{
		return 1;
	}
	
	protected boolean canSilkHarvest()
    {
        return true;
    }
	
	@Override
	public int idDropped(int metadata, Random rand, int fortune)
	{
		return 0;
	}
	
	@Override 
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = super.getBlockDropped(world, x, y, z, metadata, fortune);
        int f = fortune > 0 ? world.rand.nextInt(fortune) : 0;

        switch (metadata)
		{
		case 0:
			ret.add(new ItemStack(this.blockID, 1, 0));
			break;
		case 1:
			ret.add(new ItemStack(this.blockID, 1, 2));
			break;
		case 2:
			ret.add(new ItemStack(Item.clay, world.rand.nextInt(4) + f, 0));
			break;
		case 3:
			ret.add(new ItemStack(this.blockID, 1, 4));
			ret.add(new ItemStack(Item.flint, world.rand.nextInt(1 + f), 0));
			break;
		default:
			break;
		}
        return ret;
    }
	
	@SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    { 
		if (par2 > 5) par2 = 5;
		switch (par2)
		{
		case 0:
			return Block.dirt.getBlockTextureFromSide(1);
		case 1:
			return Block.sand.getBlockTextureFromSide(1);
		case 2:
			return Block.blockClay.getBlockTextureFromSide(1);
		case 3:
			return Block.gravel.getBlockTextureFromSide(1);
		default:
			break;
		}
		return this.oreIcon[par2];
    }
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("crowsdefeat:orefluorite");
	}

}