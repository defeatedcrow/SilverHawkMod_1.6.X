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

public class BlockOreFluorite extends Block{

	@SideOnly(Side.CLIENT)
	private Icon oreIcon[]; 
	
	private static String[] oreName = {"fluorite", "silver", "crocoite"};
	
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
		return 0;
	}
	
	@Override 
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = super.getBlockDropped(world, x, y, z, metadata, fortune);
        int f = fortune > 0 ? world.rand.nextInt(fortune) : 0;

        if (metadata == 0)
        {
        	ret.add(new ItemStack(SilverHawkCore.Fluorite, 1 + f, 0));
        }
        else if (metadata == 1)
        {
        	ret.add(new ItemStack(this.blockID, 1, 1));
        }
        else if (metadata == 2)
        {
        	ret.add(new ItemStack(SilverHawkCore.Fluorite, 1 + f, 1));
        }
        return ret;
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
		if (par2 > 2) par2 = 2;
		return this.oreIcon[par2];
    }
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
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
