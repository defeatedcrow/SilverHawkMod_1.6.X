package mods.defeatedcrow.world.biome;

import java.awt.Color;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.common.SilverHawkCore;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenCuriousInferno extends BiomeGenBaseReverse implements CustomBiome{
	
	private WorldGenerator reverseWorldGenerator;
	private BiomeDecoratorReverse reverseBiomeDeco;

	public BiomeGenCuriousInferno(int par1, int par2) {
		super(par1, par2);
		this.theBiomeDecorator = new BiomeDecoratorReverse(this);
		this.reverseBiomeDeco = (BiomeDecoratorReverse) this.theBiomeDecorator;
		this.topBlock = (byte)SilverHawkCore.ClearStone.blockID;
        this.fillerBlock = (byte)SilverHawkCore.ClearStone.blockID;
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
	}
	
	@SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float par1)
    {
		par1 /= 3.0F;

        if (par1 < -1.0F)
        {
            par1 = -1.0F;
        }

        if (par1 > 1.0F)
        {
            par1 = 1.0F;
        }

        return Color.getHSBColor(0.62222224F - par1 * 0.05F, 0.5F + par1 * 0.1F, 1.0F).getRGB();
    }

	@Override
	public CustomBiomeType thisType() {
		return CustomBiomeType.CURIOUS;
	}

}
