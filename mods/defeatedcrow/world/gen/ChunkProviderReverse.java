package mods.defeatedcrow.world.gen;

import java.util.List;
import java.util.Random;

import mods.defeatedcrow.common.SilverHawkCore;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class ChunkProviderReverse implements IChunkProvider{
	
	//乱数
	private Random reverseRNG;
	//ノイズ
    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    public NoiseGeneratorOctaves noiseGen4;
    public NoiseGeneratorOctaves noiseGen5;
    //ワールド
    private World reverseWorld;
    //これもノイズな気がする
    private double[] densities;
    //たまに地表に石が露出しているとこ
    private double[] stoneNoise = new double[256];

    /** The biomes that are used to generate the chunk */
    private BiomeGenBase[] biomesForGeneration;
    double[] noiseData1;
    double[] noiseData2;
    double[] noiseData3;
    double[] noiseData4;
    double[] noiseData5;
    int[][] field_73203_h = new int[32][32];
    
    /**
     * Used to store the 5x5 parabolic field that is used during terrain generation.
     */
    float[] parabolicField;
	
	public ChunkProviderReverse(World par1World, long par2)
	{
		this.reverseWorld = par1World;
        this.reverseRNG = new Random(par2);
        this.noiseGen1 = new NoiseGeneratorOctaves(this.reverseRNG, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(this.reverseRNG, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.reverseRNG, 8);
        this.noiseGen4 = new NoiseGeneratorOctaves(this.reverseRNG, 10);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.reverseRNG, 16);

        NoiseGeneratorOctaves[] noiseGens = {noiseGen1, noiseGen2, noiseGen3, noiseGen4, noiseGen5};
        noiseGens = TerrainGen.getModdedNoiseGenerators(par1World, this.reverseRNG, noiseGens);
        this.noiseGen1 = noiseGens[0];
        this.noiseGen2 = noiseGens[1];
        this.noiseGen3 = noiseGens[2];
        this.noiseGen4 = noiseGens[3];
        this.noiseGen5 = noiseGens[4];
	}
	
	/**
     * まず石を使って地形を作る段階。特定の深さを水で満たしているのもココらしい。
     * <br>ReverseWorldの場合、ワールド高さは128のため、0~16、96~128を基本にして、ノイズに合わせた地形を作る。
     * <br>0~16はバニラの石、96~128はリバースストーン。Curiousバイオームの場合、間にも浮遊島が出来る。
     */
    public void generateTerrain(int par1, int par2, byte[] par3ArrayOfByte)
    {
        byte b0 = 4;
        byte b1 = 16;
        byte b2 = 63;
        int k = b0 + 1;//5
        byte b3 = 17;
        int l = b0 + 1;//5
        this.biomesForGeneration = this.reverseWorld.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, par1 * 4 - 2, par2 * 4 - 2, k + 5, l + 5);
        this.densities = this.initializeNoiseField(this.densities, par1 * b0, 0, par2 * b0, k, b3, l);

        for (int i1 = 0; i1 < b0; ++i1)//0~3
        {
            for (int j1 = 0; j1 < b0; ++j1)//0~3
            {
                for (int k1 = 0; k1 < b1; ++k1)//0~15(たぶん縦チャンクの0~15ブロックじゃないかなぁ)
                {
                    double d0 = 0.125D;
                    double d1 = this.densities[((i1 + 0) * l + j1 + 0) * b3 + k1 + 0];//((3*5+3)*17+15)=321
                    double d2 = this.densities[((i1 + 0) * l + j1 + 1) * b3 + k1 + 0];//((3*5+4)*17+15)=338
                    double d3 = this.densities[((i1 + 1) * l + j1 + 0) * b3 + k1 + 0];//((4*5+3)*17+15)=406
                    double d4 = this.densities[((i1 + 1) * l + j1 + 1) * b3 + k1 + 0];//((4*5+4)*17+15)=423
                    double d5 = (this.densities[((i1 + 0) * l + j1 + 0) * b3 + k1 + 1] - d1) * d0;//なんか1/8してるぽい
                    double d6 = (this.densities[((i1 + 0) * l + j1 + 1) * b3 + k1 + 1] - d2) * d0;
                    double d7 = (this.densities[((i1 + 1) * l + j1 + 0) * b3 + k1 + 1] - d3) * d0;
                    double d8 = (this.densities[((i1 + 1) * l + j1 + 1) * b3 + k1 + 1] - d4) * d0;

                    for (int l1 = 0; l1 < 8; ++l1)//0~7
                    {
                        double d9 = 0.25D;
                        double d10 = d1;//返ってきたノイスデータ
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int i2 = 0; i2 < 4; ++i2)//0~3
                        {
                            int j2 = i2 + i1 * 4 << 11 | 0 + j1 * 4 << 7 | k1 * 8 + l1;
                            //(3+12<<11|12<<7|15*8+7)=(15*2048+12*128+127)
                            //下位バイトはたぶん高さ情報だけど、上位バイトの中身が分からない
                            short short1 = 128;
                            j2 -= short1;
                            double d14 = 0.25D;
                            double d15 = (d11 - d10) * d14;//中身がわからず触れられない感
                            double d16 = d10 - d15;

                            for (int k2 = 0; k2 < 4; ++k2)//0~3
                            {
                                if ((d16 += d15) > 0.0D)
                                {
                                    par3ArrayOfByte[j2 += short1] = (byte)SilverHawkCore.ClearStone.blockID;
                                }
                                else if (k1 * 8 + l1 < b2)//ki*8+liって地表上の空間のことなのかな？
                                {
                                    par3ArrayOfByte[j2 += short1] = 0;//(byte)Block.waterStill.blockID;いずれ逆さ水を作ってから
                                }
                                else
                                {
                                    par3ArrayOfByte[j2 += short1] = 0;
                                }
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        //周回時に1/8を足している。8回繰り返すと2倍に増える。
                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }
    
    /**
     * バイオームに合わせた適切なブロックに置換する。岩盤もココでおいているようだ
     */
    public void replaceBlocksForBiome(int par1, int par2, byte[] par3ArrayOfByte, BiomeGenBase[] par4ArrayOfBiomeGenBase)
    {
        ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, par1, par2, par3ArrayOfByte, par4ArrayOfBiomeGenBase);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == Result.DENY) return;

        byte b0 = 63;
        double d0 = 0.03125D;
        this.stoneNoise = this.noiseGen4.generateNoiseOctaves(this.stoneNoise, par1 * 16, par2 * 16, 0, 16, 16, 1, d0 * 2.0D, d0 * 2.0D, d0 * 2.0D);

        for (int k = 0; k < 16; ++k)
        {
            for (int l = 0; l < 16; ++l)
            {
                BiomeGenBase biomegenbase = par4ArrayOfBiomeGenBase[l + k * 16];
                float f = biomegenbase.getFloatTemperature();
                int i1 = (int)(this.stoneNoise[k + l * 16] / 3.0D + 3.0D + this.reverseRNG.nextDouble() * 0.25D);
                int j1 = -1;
                byte b1 = biomegenbase.topBlock;
                byte b2 = biomegenbase.fillerBlock;

                for (int k1 = 127; k1 >= 0; --k1)//0~127で回してる
                {
                    int l1 = (l * 16 + k) * 128 + k1;//lは16倍されてる。バイトデータで保存するためかな

                    if (k1 == 0 || k1 == 127)//天井と床をネオ岩盤で蓋しとく
                    {
                        par3ArrayOfByte[l1] = (byte)Block.bedrock.blockID;
                    }
                    else
                    {
                        byte b3 = par3ArrayOfByte[l1];

                        if (b3 == 0)//空気ブロックなら何もしない
                        {
                            j1 = -1;
                        }
                        else if (b3 == SilverHawkCore.ClearStone.blockID)//石だった
                        {
                            if (j1 == -1)
                            {
                                if (i1 <= 0)//球形のストーンノイズ。地表高さに食い込んでいたら置換
                                {
                                    b1 = 0;
                                    b2 = (byte)SilverHawkCore.ClearStone.blockID;
                                }
                                else if (k1 >= b0 - 4 && k1 <= b0 + 1)//地表用ブロックに変える。b0は63
                                {
                                    b1 = biomegenbase.topBlock;
                                    b2 = biomegenbase.fillerBlock;
                                }

                                if (k1 < b0 && b1 == 0)//63以下の空気ブロック
                                {
                                    if (f < 0.15F)//バイオームの温度
                                    {
                                        b1 = (byte)Block.ice.blockID;
                                    }
                                    else
                                    {
                                        b1 = (byte)Block.waterStill.blockID;
                                    }
                                }

                                j1 = i1;//ストーンノイズ計算で変化している

                                if (k1 >= b0 - 1)//1ブロックだけ草などの地表用ブロックに、ソレ以下を土などフィルターブロックに置換
                                {
                                    par3ArrayOfByte[l1] = b1;
                                }
                                else
                                {
                                    par3ArrayOfByte[l1] = b2;
                                }
                            }
                            else if (j1 > 0)
                            {
                                --j1;
                                par3ArrayOfByte[l1] = b2;

                                //砂岩設置判定かな？
                                if (j1 == 0 && b2 == SilverHawkCore.ClearGround.blockID)
                                {
                                    j1 = this.reverseRNG.nextInt(4);
                                    b2 = (byte)SilverHawkCore.ClearStone.blockID;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    /**
     * マップとチャンクのシード値から構成ブロックを生成する処理。
     */
    @Override
    public Chunk provideChunk(int par1, int par2)
    {
        this.reverseRNG.setSeed((long)par1 * 341873128712L + (long)par2 * 132897987541L);
        byte[] abyte = new byte[32768];
        this.generateTerrain(par1, par2, abyte);
        this.biomesForGeneration = this.reverseWorld.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
        this.replaceBlocksForBiome(par1, par2, abyte, this.biomesForGeneration);
//        this.caveGenerator.generate(this, this.reverseWorld, par1, par2, abyte);
//        this.ravineGenerator.generate(this, this.reverseWorld, par1, par2, abyte);
//
//        if (this.mapFeaturesEnabled)
//        {
//            this.mineshaftGenerator.generate(this, this.reverseWorld, par1, par2, abyte);
//            this.villageGenerator.generate(this, this.reverseWorld, par1, par2, abyte);
//            this.strongholdGenerator.generate(this, this.reverseWorld, par1, par2, abyte);
//            this.scatteredFeatureGenerator.generate(this, this.reverseWorld, par1, par2, abyte);
//        }

        Chunk chunk = new Chunk(this.reverseWorld, abyte, par1, par2);
        byte[] abyte1 = chunk.getBiomeArray();

        for (int k = 0; k < abyte1.length; ++k)
        {
            abyte1[k] = (byte)this.biomesForGeneration[k].biomeID;
        }

        chunk.generateSkylightMap();
        return chunk;
    }
    
    /**
     * generates a subset of the level's terrain data. Takes 7 arguments: the [empty] noise array, the position, and the
     * size.
     */
    private double[] initializeNoiseField(double[] par1ArrayOfDouble, int par2, int par3, int par4, int par5, int par6, int par7)
    {
        ChunkProviderEvent.InitNoiseField event = new ChunkProviderEvent.InitNoiseField(this, par1ArrayOfDouble, par2, par3, par4, par5, par6, par7);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == Result.DENY) return event.noisefield;

        if (par1ArrayOfDouble == null)
        {
            par1ArrayOfDouble = new double[par5 * par6 * par7];
        }

        if (this.parabolicField == null)
        {
            this.parabolicField = new float[25];

            for (int k1 = -2; k1 <= 2; ++k1)
            {
                for (int l1 = -2; l1 <= 2; ++l1)//-2~+2の範囲で円形になにかやっているぽい
                {
                    float f = 10.0F / MathHelper.sqrt_float((float)(k1 * k1 + l1 * l1) + 0.2F);
                    this.parabolicField[k1 + 2 + (l1 + 2) * 5] = f;
                }
            }
        }

        //以下、バニラのコピペ
        double d0 = 684.412D;
        double d1 = 684.412D;
        this.noiseData4 = this.noiseGen4.generateNoiseOctaves(this.noiseData4, par2, par4, par5, par7, 1.121D, 1.121D, 0.5D);
        this.noiseData5 = this.noiseGen5.generateNoiseOctaves(this.noiseData5, par2, par4, par5, par7, 200.0D, 200.0D, 0.5D);
        this.noiseData3 = this.noiseGen3.generateNoiseOctaves(this.noiseData3, par2, par3, par4, par5, par6, par7, d0 / 80.0D, d1 / 160.0D, d0 / 80.0D);
        this.noiseData1 = this.noiseGen1.generateNoiseOctaves(this.noiseData1, par2, par3, par4, par5, par6, par7, d0, d1, d0);
        this.noiseData2 = this.noiseGen2.generateNoiseOctaves(this.noiseData2, par2, par3, par4, par5, par6, par7, d0, d1, d0);
        boolean flag = false;
        boolean flag1 = false;
        int i2 = 0;
        int j2 = 0;

        for (int k2 = 0; k2 < par5; ++k2)
        {
            for (int l2 = 0; l2 < par7; ++l2)
            {
                float f1 = 0.0F;
                float f2 = 0.0F;
                float f3 = 0.0F;
                byte b0 = 2;
                //0~4 + 2 + (0~4 + 2) * (5 + 5)なので最小22、最大66。
                BiomeGenBase biomegenbase = this.biomesForGeneration[k2 + 2 + (l2 + 2) * (par5 + 5)];

                for (int i3 = -b0; i3 <= b0; ++i3)
                {
                    for (int j3 = -b0; j3 <= b0; ++j3)
                    {
                    	//さらに16パターン繰り返してる。こんどは-2~+2の範囲でやっている
                        BiomeGenBase biomegenbase1 = this.biomesForGeneration[k2 + i3 + 2 + (l2 + j3 + 2) * (par5 + 5)];
                        //地形を放物線状に作っていると思う。上の計算式だと、10/(2.8~0.44) 程度の範囲だった 
                        float f4 = this.parabolicField[i3 + 2 + (j3 + 2) * 5] / (biomegenbase1.minHeight + 2.0F);
                        

                        if (biomegenbase1.minHeight > biomegenbase.minHeight)//???
                        {
                            f4 /= 2.0F;
                        }

                        //バイオームに合わせた地形の高さ
                        //地形の高さの振れ幅な気もする
                        f1 += biomegenbase1.maxHeight * f4;//バニラでは0.1F*f4
                        f2 += biomegenbase1.minHeight * f4;//バニラでは0.3F*f4
                        f3 += f4;
                    }
                }

                f1 /= f3;//掛けたのにまた割るの？
                f2 /= f3;
                f1 = f1 * 0.9F + 0.1F;//0.2F前後になっているはず
                f2 = (f2 * 4.0F - 1.0F) / 8.0F;//こちらはなんか相当小さくなってないか
                double d2 = this.noiseData5[j2] / 8000.0D;//何が入っているのかはよくわかっていない

                if (d2 < 0.0D)
                {
                    d2 = -d2 * 0.3D;
                }

                d2 = d2 * 3.0D - 2.0D;

                if (d2 < 0.0D)
                {
                    d2 /= 2.0D;

                    if (d2 < -1.0D)
                    {
                        d2 = -1.0D;
                    }

                    d2 /= 1.4D;
                    d2 /= 2.0D;
                }
                else
                {
                    if (d2 > 1.0D)
                    {
                        d2 = 1.0D;
                    }

                    d2 /= 8.0D;
                }

                ++j2;

                for (int k3 = 0; k3 < par6; ++k3)
                {
                    double d3 = (double)f2;
                    double d4 = (double)f1;
                    d3 += d2 * 0.2D;
                    d3 = d3 * (double)par6 / 16.0D;
                    double d5 = (double)par6 / 2.0D + d3 * 4.0D;
                    double d6 = 0.0D;
                    double d7 = ((double)k3 - d5) * 12.0D * 128.0D / 128.0D / d4;

                    if (d7 < 0.0D)
                    {
                        d7 *= 4.0D;
                    }

                    double d8 = this.noiseData1[i2] / 512.0D;
                    double d9 = this.noiseData2[i2] / 512.0D;
                    double d10 = (this.noiseData3[i2] / 10.0D + 1.0D) / 2.0D;

                    if (d10 < 0.0D)
                    {
                        d6 = d8;
                    }
                    else if (d10 > 1.0D)
                    {
                        d6 = d9;
                    }
                    else
                    {
                        d6 = d8 + (d9 - d8) * d10;
                    }

                    d6 -= d7;

                    if (k3 > par6 - 4)
                    {
                        double d11 = (double)((float)(k3 - (par6 - 4)) / 3.0F);
                        d6 = d6 * (1.0D - d11) + -10.0D * d11;
                    }

                    par1ArrayOfDouble[i2] = d6;
                    ++i2;
                }
            }
        }

        return par1ArrayOfDouble;
    }

	@Override
	public boolean chunkExists(int i, int j) {
		return true;
	}

	@Override
	public Chunk loadChunk(int i, int j) {
		return this.provideChunk(i, j);
	}

	//デコレーションしたり建物とか鉱石とか生成したりするやつ
	@Override
	public void populate(IChunkProvider ichunkprovider, int i, int j) {
		
		BlockSand.fallInstantly = true;
        int k = i * 16;
        int l = j * 16;
        BiomeGenBase biomegenbase = this.reverseWorld.getBiomeGenForCoords(k + 16, l + 16);
        this.reverseRNG.setSeed(this.reverseWorld.getSeed());
        long i1 = this.reverseRNG.nextLong() / 2L * 2L + 1L;
        long j1 = this.reverseRNG.nextLong() / 2L * 2L + 1L;
        this.reverseRNG.setSeed((long)i * i1 + (long)j * j1 ^ this.reverseWorld.getSeed());
        boolean flag = false;
        
        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(ichunkprovider, reverseWorld, reverseRNG, i, j, flag));
		
        //ここでいろいろと生成する。
        
        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(ichunkprovider, reverseWorld, reverseRNG, i, j, flag));

        BlockSand.fallInstantly = false;
	}

	@Override
	public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate) {
		return true;
	}

	@Override
	public boolean unloadQueuedChunks() {
		return false;
	}

	@Override
	public boolean canSave() {
		return true;
	}

	@Override
	public String makeString() {
		return "SilverhawkRandomLevelSource";
	}

	//スポーン可能なMOB
	@Override
	public List getPossibleCreatures(EnumCreatureType enumcreaturetype, int i, int j, int k) {
		BiomeGenBase biomegenbase = this.reverseWorld.getBiomeGenForCoords(i, k);
		return biomegenbase == null ? null : biomegenbase.getSpawnableList(enumcreaturetype);
	}

	//バニラだと要塞を探す処理になってる
	@Override
	public ChunkPosition findClosestStructure(World world, String s, int i,
			int j, int k) {
		return null;
	}

	@Override
	public int getLoadedChunkCount() {
		return 0;
	}

	@Override
	public void recreateStructures(int i, int j) {
		
	}

	@Override
	public void saveExtraData() {
		
	}

}
