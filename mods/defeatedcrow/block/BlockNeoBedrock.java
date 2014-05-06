package mods.defeatedcrow.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import mods.defeatedcrow.common.SilverHawkCore;

public class BlockNeoBedrock extends Block{
	
	public BlockNeoBedrock(int blockid)
	{
		super(blockid,  Material.rock);
		this.setHardness(16.0F);
		this.setResistance(6000000.0F);
		this.setStepSound(Block.soundStoneFootstep);
		this.setLightValue(0.0F);
		this.setLightOpacity(0);
		this.setBlockUnbreakable();
	}
	
	public int quantityDropped(Random random)
	{
		return 1;
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
        return true;
    }
	
	@SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        int i1 = par1IBlockAccess.getBlockId(par2, par3, par4);
        return i1 == this.blockID ? false : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
    }
	
	@Override
	public int getRenderType()
	{
		return SilverHawkCore.modelInvisible;
	}
	
	//エンティティが着地した時
	@Override
	public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		if (par5Entity instanceof EntityLiving)
		{
			EntityLiving living = (EntityLiving)par5Entity;
			living.fallDistance = 0.0F;
		}
	}
	
	@Override
	public void onFallenUpon(World par1World, int par2, int par3, int par4, Entity par5Entity, float par6)
	{
		if (par5Entity instanceof EntityLiving)
		{
			EntityLiving living = (EntityLiving)par5Entity;
			living.fallDistance = 0.0F;
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = Block.bedrock.getBlockTextureFromSide(1);
	}

}
