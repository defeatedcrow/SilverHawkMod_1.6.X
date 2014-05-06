package mods.defeatedcrow.world;

import java.util.ArrayList;
import java.util.List;

import mods.defeatedcrow.world.biome.BiomeGenBaseReverse;
import net.minecraft.util.LongHashMap;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeCacheReverse {
	
	private final WorldChunkManagerReverse chunkManager;
	private long lastCleanupTime = 0L;
	
	private LongHashMap cacheMap = new LongHashMap();

    /** The list of cached BiomeCacheBlocks */
    private List cache = new ArrayList();
	
    public BiomeCacheReverse(WorldChunkManagerReverse par1WorldChunkManager)
    {
    	this.chunkManager = par1WorldChunkManager;
    }
    
    public BiomeCacheBlockReverse getBiomeCacheBlock(int par1, int par2)
    {
      par1 >>= 4;
      par2 >>= 4;
      long k = par1 & 0xFFFFFFFF | (par2 & 0xFFFFFFFF) << 32;
      BiomeCacheBlockReverse block = (BiomeCacheBlockReverse)this.cacheMap.getValueByKey(k);

      if (block == null)
      {
        block = new BiomeCacheBlockReverse(this, par1, par2);
        this.cacheMap.add(k, block);
        this.cache.add(block);
      }

      block.lastAccessTime = System.currentTimeMillis();
      return block;
    }
    
    public BiomeGenBase getBiomeGenAt(int par1, int par2)
    {
      return getBiomeCacheBlock(par1, par2).getBiomeGenAt(par1, par2);
    }

    public void cleanupCache()
    {
      long i = System.currentTimeMillis();
      long j = i - this.lastCleanupTime;

      if ((j > 7500L) || (j < 0L))
      {
        this.lastCleanupTime = i;

        for (int k = 0; k < this.cache.size(); k++)
        {
          BiomeCacheBlockReverse block = (BiomeCacheBlockReverse)this.cache.get(k);
          long l = i - block.lastAccessTime;

          if ((l > 30000L) || (l < 0L))
          {
            this.cache.remove(k--);
            long i1 = block.xPosition & 0xFFFFFFFF | (block.zPosition & 0xFFFFFFFF) << 32;
            this.cacheMap.remove(i1);
          }
        }
      }
    }

    public BiomeGenBase[] getCachedBiomes(int par1, int par2)
    {
      return getBiomeCacheBlock(par1, par2).biomes;
    }

    static WorldChunkManagerReverse getChunkManager(BiomeCacheReverse par0BiomeCache)
    {
      return par0BiomeCache.chunkManager;
    }

}
