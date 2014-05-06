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

public class BlockClearOre extends Block{

	@SideOnly(Side.CLIENT)
	private Icon oreIcon[]; 
	
	private static String[] oreName = {"fluorite", "silver", "crocoite"};
	
	public BlockClearOre(int blockid)
	{
		super(blockid, Material.rock);
		this.setHardness(1.5F);
		this.setResistance(2.0F);
		this.setStepSound(Block.soundStoneFootstep);
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
			ret.add(new ItemStack(Item.coal, 1 + f, 0));
			break;
		case 1:
			ret.add(new ItemStack(this.blockID, 1, 1));
			break;
		case 2:
			ret.add(new ItemStack(this.blockID, 1, 2));
			break;
		case 3:
			ret.add(new ItemStack(Item.diamond, 1 + f, 0));
			break;
		case 4:
			ret.add(new ItemStack(Item.redstone, world.rand.nextInt(4) + f, 0));
			break;
		case 5:
			ret.add(new ItemStack(Item.emerald, 1 + f, 0));
			break;
		case 6:
			ret.add(new ItemStack(SilverHawkCore.Fluorite, 1 + f, 0));
			break;
		case 7:
			ret.add(new ItemStack(this.blockID, 1, 7));
			break;
		case 8:
			ret.add(new ItemStack(SilverHawkCore.Fluorite, 1 + f, 1));
			break;
		default:
			break;
		}
        return ret;
    }
	
	@SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    { 
		if (par2 > 8) par2 = 8;
		switch (par2)
		{
		case 0:
			return Block.oreCoal.getBlockTextureFromSide(1);
		case 1:
			return Block.oreIron.getBlockTextureFromSide(1);
		case 2:
			return Block.oreGold.getBlockTextureFromSide(1);
		case 3:
			return Block.oreDiamond.getBlockTextureFromSide(1);
		case 4:
			return Block.oreRedstone.getBlockTextureFromSide(1);
		case 5:
			return Block.oreEmerald.getBlockTextureFromSide(1);
		case 6:
			return this.oreIcon[0];
		case 7:
			return this.oreIcon[1];
		case 8:
			return this.oreIcon[2];
		default:
			break;
		}
		return this.oreIcon[0];
    }
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
        par3List.add(new ItemStack(par1, 1, 4));
        par3List.add(new ItemStack(par1, 1, 5));
        par3List.add(new ItemStack(par1, 1, 6));
        par3List.add(new ItemStack(par1, 1, 7));
        par3List.add(new ItemStack(par1, 1, 8));
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.oreIcon = new Icon[3];
		for (int i = 0; i < 3; ++i)
        {
			this.oreIcon[i] = par1IconRegister.registerIcon("crowsdefeat:ore" + this.oreName[i]);
        }
		
		this.blockIcon = par1IconRegister.registerIcon("crowsdefeat:orefluorite");
	}

}
