package mods.defeatedcrow.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class GeneratingFOre implements IWorldGenerator {
	
	private int genDim1 = 0;

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		
		genDim1 = world.provider.dimensionId;
		
		int chunk2X = chunkX << 4;
        int chunk2Z = chunkZ << 4;         
        
    	if ((genDim1 == 0) || (genDim1 > 1) || (genDim1 < -1))
    	{
    	    	for(int i = 0; i < 5; i++)
    			{
    				int randomPosX = chunk2X + random.nextInt(16);
    				int randomPosY = random.nextInt(30);
    				int randomPosZ = chunk2Z + random.nextInt(16);
    				(new WorldGenMinable(SilverHawkCore.Ores.blockID, 0, 6, Block.stone.blockID)).generate(world, random, randomPosX, randomPosY, randomPosZ);
    			}
    	    	
    	    	for(int j = 0; j < 5; j++)
    			{
    				int randomPosX = chunk2X + random.nextInt(16);
    				int randomPosY = random.nextInt(30);
    				int randomPosZ = chunk2Z + random.nextInt(16);
    				(new WorldGenMinable(SilverHawkCore.Ores.blockID, 1, 5, Block.stone.blockID)).generate(world, random, randomPosX, randomPosY, randomPosZ);
    			}
    	    	
    	    	for(int j = 0; j < 3; j++)
    			{
    				int randomPosX = chunk2X + random.nextInt(16);
    				int randomPosY = random.nextInt(40);
    				int randomPosZ = chunk2Z + random.nextInt(16);
    				(new WorldGenMinable(SilverHawkCore.Ores.blockID, 2, 1, Block.stone.blockID)).generate(world, random, randomPosX, randomPosY, randomPosZ);
    			}
    	    	
    	    	for(int j = 0; j < 3; j++)
    			{
    				int randomPosX = chunk2X + random.nextInt(16);
    				int randomPosY = random.nextInt(20);
    				int randomPosZ = chunk2Z + random.nextInt(16);
    				(new WorldGenMinable(SilverHawkCore.Ores.blockID, 2, 5, Block.stone.blockID)).generate(world, random, randomPosX, randomPosY, randomPosZ);
    			}
    	}

	}

}
