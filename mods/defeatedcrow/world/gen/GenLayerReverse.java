package mods.defeatedcrow.world.gen;

import mods.defeatedcrow.util.SHLogger;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.WorldTypeEvent;

public abstract class GenLayerReverse {
	
	/** seed from World#getWorldSeed that is used in the LCG prng */
    private long worldGenSeed;

    /** parent GenLayer that was provided via the constructor */
    protected GenLayerReverse parent;

    /**
     * final part of the LCG prng that uses the chunk X, Z coords along with the other two seeds to generate
     * pseudorandom numbers
     */
    private long chunkSeed;

    /** base seed to the LCG prng provided via the constructor */
    private long baseSeed;

    /**
     * the first array item is a linked list of the bioms, the second is the zoom function, the third is the same as the
     * first.
     */
    public static GenLayerReverse initializeAllBiomeGenerators(long par0, WorldType par2WorldType)
    {
    	//GenLayerを順に適用していくところ
        GenLayerIslandsReverse genlayerisland = new GenLayerIslandsReverse(1L);
        GenLayerFuzzyReverse genlayerfuzzyzoom = new GenLayerFuzzyReverse(2000L, genlayerisland);
        GenLayerAddIslandsReverse genlayeraddisland = new GenLayerAddIslandsReverse(1L, genlayerfuzzyzoom);
        GenLayerZoomReverse genlayerzoom = new GenLayerZoomReverse(2001L, genlayeraddisland);
        genlayeraddisland = new GenLayerAddIslandsReverse(2L, genlayerzoom);
        genlayerzoom = new GenLayerZoomReverse(2003L, genlayeraddisland);
        genlayeraddisland = new GenLayerAddIslandsReverse(4L, genlayerzoom);
        
        //レアバイオーム（CuriousInferno）の生成
        GenLayerCuriousLava genlayercuriouslava = new GenLayerCuriousLava(5L, genlayeraddisland);
        
        byte b0 = 4;

        if (par2WorldType == WorldType.LARGE_BIOMES)
        {
            b0 = 6;
        }
        b0 = getModdedBiomeSize(par2WorldType, b0);
        
        GenLayerReverse genlayer = GenLayerZoomReverse.magnify(1000L, genlayercuriouslava, 0);
        GenLayerSmoothReverse genlayersmooth = new GenLayerSmoothReverse(1000L, genlayer);
        GenLayerReverse genlayer1 = GenLayerZoomReverse.magnify(1000L, genlayercuriouslava, 0);
        
        //バイオームの適用
        GenLayerBiomeReverse genlayerbiome = new GenLayerBiomeReverse(200L, genlayersmooth, par2WorldType);
        genlayer1 = GenLayerZoomReverse.magnify(1000L, genlayerbiome, 2);
        
        //GenLayerHillsと同様の役目
        Object object = new GenLayerRSub(1000L, genlayer1);

        for (int j = 0; j < b0; ++j)
        {
            object = new GenLayerZoomReverse((long)(1000 + j), (GenLayerReverse)object);

            if (j == 0)
            {
                object = new GenLayerAddIslandsReverse(3L, (GenLayerReverse)object);
            }

            if (j == 1)
            {
                object = new GenLayerRShore(1000L, (GenLayerReverse)object);
            }
        }

        GenLayerSmoothReverse genlayersmooth1 = new GenLayerSmoothReverse(1000L, (GenLayerReverse)object);
        genlayersmooth1.initWorldGenSeed(par0);
        return genlayersmooth1;
    }

    public GenLayerReverse(long par1)
    {
        this.baseSeed = par1;
        this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        this.baseSeed += par1;
        this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        this.baseSeed += par1;
        this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        this.baseSeed += par1;
    }

    /**
     * Initialize layer's local worldGenSeed based on its own baseSeed and the world's global seed (passed in as an
     * argument).
     */
    public void initWorldGenSeed(long par1)
    {
        this.worldGenSeed = par1;

        if (this.parent != null)
        {
            this.parent.initWorldGenSeed(par1);
        }

        this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
        this.worldGenSeed += this.baseSeed;
        this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
        this.worldGenSeed += this.baseSeed;
        this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
        this.worldGenSeed += this.baseSeed;
    }

    /**
     * Initialize layer's current chunkSeed based on the local worldGenSeed and the (x,z) chunk coordinates.
     */
    public void initChunkSeed(long par1, long par3)
    {
        this.chunkSeed = this.worldGenSeed;
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += par1;
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += par3;
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += par1;
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += par3;
    }

    /**
     * returns a LCG pseudo random number from [0, x). Args: int x
     */
    protected int nextInt(int par1)
    {
        int j = (int)((this.chunkSeed >> 24) % (long)par1);

        if (j < 0)
        {
            j += par1;
        }

        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += this.worldGenSeed;
        return j;
    }

    /**
     * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
     * amounts, or biomeList[] indices based on the particular GenLayer subclass.
     */
    public abstract int[] getInts(int i, int j, int k, int l);

    public static byte getModdedBiomeSize(WorldType worldType, byte original)
    {
        WorldTypeEvent.BiomeSize event = new WorldTypeEvent.BiomeSize(worldType, original);
        MinecraftForge.TERRAIN_GEN_BUS.post(event);
        return event.newSize;
    }

}
