package mods.defeatedcrow.world.gen;

import mods.defeatedcrow.util.SHLogger;
import mods.defeatedcrow.world.biome.BiomeGenBaseReverse;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

/**
 * サブバイオームの生成。バイオームの外周にできるもの。
 * Ocean、Lava、Mountainの3種にサブバイオームが有る。
 * */
public class GenLayerRShore extends GenLayerReverse{
	
	public GenLayerRShore(long par1, GenLayerReverse par3GenLayer)
    {
        super(par1);
        this.parent = par3GenLayer;
    }

    /**
     * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
     * amounts, or biomeList[] indices based on the particular GenLayer subclass.
     */
    public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int[] aint = this.parent.getInts(par1 - 1, par2 - 1, par3 + 2, par4 + 2);
        int[] aint1 = IntCache.getIntCache(par3 * par4);

        for (int i1 = 0; i1 < par4; ++i1)
        {
            for (int j1 = 0; j1 < par3; ++j1)
            {
                this.initChunkSeed((long)(j1 + par1), (long)(i1 + par2));
                int k1 = aint[j1 + 1 + (i1 + 1) * (par3 + 2)];
                int l1;
                int i2;
                int j2;
                int k2;
                
                //隣接バイオームのチェック
                if (k1 == BiomeGenBaseReverse.curiousInferno.biomeID)
                {
                    l1 = aint[j1 + 1 + (i1 + 1 - 1) * (par3 + 2)];
                    i2 = aint[j1 + 1 + 1 + (i1 + 1) * (par3 + 2)];
                    j2 = aint[j1 + 1 - 1 + (i1 + 1) * (par3 + 2)];
                    k2 = aint[j1 + 1 + (i1 + 1 + 1) * (par3 + 2)];

                    if (l1 == BiomeGenBaseReverse.curiousInferno.biomeID && i2 == BiomeGenBaseReverse.curiousInferno.biomeID && j2 == BiomeGenBaseReverse.curiousInferno.biomeID && k2 == BiomeGenBaseReverse.curiousInferno.biomeID)
                    {
                        aint1[j1 + i1 * par3] = k1;
                    }
                    else
                    {
                        aint1[j1 + i1 * par3] = BiomeGenBaseReverse.reverseMine.biomeID;
                    }
                }
                else if (k1 != BiomeGenBaseReverse.reverseOcean.biomeID && k1 != BiomeGenBaseReverse.reverseMountains.biomeID)
                {
                    l1 = aint[j1 + 1 + (i1 + 1 - 1) * (par3 + 2)];
                    i2 = aint[j1 + 1 + 1 + (i1 + 1) * (par3 + 2)];
                    j2 = aint[j1 + 1 - 1 + (i1 + 1) * (par3 + 2)];
                    k2 = aint[j1 + 1 + (i1 + 1 + 1) * (par3 + 2)];

                    if (l1 != BiomeGenBaseReverse.reverseOcean.biomeID && i2 != BiomeGenBaseReverse.reverseOcean.biomeID && j2 != BiomeGenBaseReverse.reverseOcean.biomeID && k2 != BiomeGenBaseReverse.reverseOcean.biomeID)
                    {
                        aint1[j1 + i1 * par3] = k1;
                    }
                    else
                    {
                        aint1[j1 + i1 * par3] = BiomeGenBaseReverse.reverseBeach.biomeID;
                    }
                }
                else if (k1 == BiomeGenBaseReverse.reverseMountains.biomeID)
                {
                    l1 = aint[j1 + 1 + (i1 + 1 - 1) * (par3 + 2)];
                    i2 = aint[j1 + 1 + 1 + (i1 + 1) * (par3 + 2)];
                    j2 = aint[j1 + 1 - 1 + (i1 + 1) * (par3 + 2)];
                    k2 = aint[j1 + 1 + (i1 + 1 + 1) * (par3 + 2)];

                    if (l1 == BiomeGenBaseReverse.reverseMountains.biomeID && i2 == BiomeGenBaseReverse.reverseMountains.biomeID && j2 == BiomeGenBaseReverse.reverseMountains.biomeID && k2 == BiomeGenBaseReverse.reverseMountains.biomeID)
                    {
                        aint1[j1 + i1 * par3] = k1;
                    }
                    else
                    {
                        aint1[j1 + i1 * par3] = BiomeGenBaseReverse.reverseMountainEdge.biomeID;
                    }
                }
                else
                {
                    aint1[j1 + i1 * par3] = k1;
                }
            }
        }

        SHLogger.debugInfo("check9 : " + aint1.length);
        return aint1;
    }
}
