package mods.defeatedcrow.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.*;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import mods.defeatedcrow.common.SilverHawkCore;

public class BlockOreFluorite extends Block{
	
	public static int droporefluorite;

	@SideOnly(Side.CLIENT)
	private Icon oreIcon; 
	
	public BlockOreFluorite(int blockid)
	{
		super(blockid, Material.rock);
		this.setHardness(1.5F);
		this.setResistance(2.0F);
		this.setStepSound(Block.soundStoneFootstep);
		this.setLightValue(0.0F);
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
		return metadata == 0 ? SilverHawkCore.Fluorite.itemID : this.blockID;
	}
	
	public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        int l = world.getBlockMetadata(x, y, z);
        boolean flag = (l == 0);
        return flag ? 8 : 0;
    }
	
	@SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    { 
		if (par2 == 0) {
			return this.oreIcon;
		}
		else {
			return this.blockIcon;
		}
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.oreIcon = par1IconRegister.registerIcon("crowsdefeat:orefluorite");
		this.blockIcon = par1IconRegister.registerIcon("crowsdefeat:oresilver");
	}

}
