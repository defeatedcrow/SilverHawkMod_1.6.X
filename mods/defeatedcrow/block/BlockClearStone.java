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
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import mods.defeatedcrow.common.SilverHawkCore;

public class BlockClearStone extends Block{

	@SideOnly(Side.CLIENT)
	private Icon oreIcon[]; 
	
	private static String[] stoneName = {"stone", "cobble", "moss", "stoneblick"};
	
	public BlockClearStone(int blockid)
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
		return this.blockID;
	}
	
	public int damageDropped(int par1)
    {
        return par1;
    }
	
	@SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    { 
		if (par2 > 4) par2 = 4;
		switch (par2)
		{
		case 0:
			return Block.stone.getBlockTextureFromSide(1);
		case 1:
			return Block.cobblestone.getBlockTextureFromSide(1);
		case 2:
			return Block.cobblestoneMossy.getBlockTextureFromSide(1);
		case 3:
			return Block.stoneBrick.getBlockTextureFromSide(1);
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
