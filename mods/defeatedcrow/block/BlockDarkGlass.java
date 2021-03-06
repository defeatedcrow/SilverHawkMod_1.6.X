package mods.defeatedcrow.block;

import net.minecraft.block.Block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.src.*;
import mods.defeatedcrow.common.*;

public class BlockDarkGlass extends BlockBreakable{
	
	public BlockDarkGlass  (int blockid, Material material, boolean flag)
	{
		super(blockid, "defeatedcrow.darkGlass", material, flag);
		this.setUnlocalizedName("defeatedcrow.darkGlass");
		this.setHardness(0.3F);
		this.setResistance(2.0F);
		this.setStepSound(Block.soundGlassFootstep);
		this.setLightValue(0.0F);
		this.setLightOpacity(225);
	}
	
	@Override
	public int idDropped(int metadata, Random rand, int fortune)
	{
		return this.blockID;
	}
	
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass()
    {
        return 0;
    }
	
	@Override
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	@Override
	public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("crowsdefeat:darkglass");
		
	}

}
