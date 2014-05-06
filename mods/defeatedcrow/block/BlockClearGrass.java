package mods.defeatedcrow.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Random;

import mods.defeatedcrow.common.SilverHawkCore;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockClearGrass extends Block
{
    @SideOnly(Side.CLIENT)
    private Icon iconGrassTop;
    @SideOnly(Side.CLIENT)
    private Icon iconSnowSide;
    @SideOnly(Side.CLIENT)
    private Icon iconGrassSideOverlay;

    public BlockClearGrass(int par1)
    {
        super(par1, Material.grass);
        this.setTickRandomly(true);
        this.setStepSound(soundGrassFootstep);
        this.setLightOpacity(0);
    }
    
    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
            if (par1World.getBlockLightValue(par2, par3 + 1, par4) < 4 && par1World.getBlockLightOpacity(par2, par3 + 1, par4) > 2)
            {
                par1World.setBlock(par2, par3, par4, SilverHawkCore.ClearGround.blockID);
            }
            else if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9)
            {
                for (int l = 0; l < 4; ++l)
                {
                    int i1 = par2 + par5Random.nextInt(3) - 1;
                    int j1 = par3 + par5Random.nextInt(5) - 3;
                    int k1 = par4 + par5Random.nextInt(3) - 1;
                    int l1 = par1World.getBlockId(i1, j1 + 1, k1);

                    if (par1World.getBlockId(i1, j1, k1) == SilverHawkCore.ClearGround.blockID && par1World.getBlockMetadata(i1, j1, k1) == 0 && par1World.getBlockLightValue(i1, j1 + 1, k1) >= 4 && par1World.getBlockLightOpacity(i1, j1 + 1, k1) <= 2)
                    {
                        par1World.setBlock(i1, j1, k1, this.blockID);
                    }
                }
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    {
        return par1 == 0 ? this.iconGrassTop : (par1 == 1 ? Block.dirt.getBlockTextureFromSide(par1) : this.blockIcon);
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        if (par5 == 0)
        {
            return this.iconGrassTop;
        }
        else if (par5 == 1)
        {
            return Block.dirt.getBlockTextureFromSide(par5);
        }
        else
        {
            Material material = par1IBlockAccess.getBlockMaterial(par2, par3 + 1, par4);
            return material != Material.snow && material != Material.craftedSnow ? this.blockIcon : this.iconSnowSide;
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("crowsdefeat:cleargrass_side");
        this.iconGrassTop = par1IconRegister.registerIcon("crowsdefeat:cleargrass_top");
        this.iconSnowSide = par1IconRegister.registerIcon("crowsdefeat:cleargrass_side_snowed");
        this.iconGrassSideOverlay = par1IconRegister.registerIcon("grass_side_overlay");
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return SilverHawkCore.ClearGround.idDropped(0, par2Random, par3);
    }
}
