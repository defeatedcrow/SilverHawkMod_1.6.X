package mods.defeatedcrow.world.biome;

import mods.defeatedcrow.common.SilverHawkCore;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenBaseReverse extends BiomeGenBase{
	
	//バイオームの登録
	public static BiomeGenBaseReverse[] reverseBiomeList = new BiomeGenBaseReverse[32];
	
	/**Reverse biomes
	 * 逆さま大地。基本バイオーム*/
    public static final BiomeGenBaseReverse reversePlains = (BiomeGenBaseReverse) (new BiomeGenReversePlain(0, SilverHawkCore.biomeIdRPlain))
            .setColor(9286496).setBiomeName("ReversePlains").setTemperatureRainfall(0.8F, 0.4F);
    public static final BiomeGenBaseReverse reverseForest = (BiomeGenBaseReverse) (new BiomeGenReverseForest(1, SilverHawkCore.biomeIdRForest))
            .setColor(353825).setBiomeName("ReverseForest").setTemperatureRainfall(0.7F, 0.5F).setMinMaxHeight(0.3F, 0.6F);
    public static final BiomeGenBaseReverse reverseOcean = (BiomeGenBaseReverse) (new BiomeGenReverseOcean(2, SilverHawkCore.biomeIdROcean))
            .setColor(9286496).setBiomeName("ReverseOcean").setTemperatureRainfall(0.6F, 0.6F);
    public static final BiomeGenBaseReverse reverseMountains = (BiomeGenBaseReverse) (new BiomeGenReverseMountain(3, SilverHawkCore.biomeIdRMountain))
            .setColor(6316128).setBiomeName("ReverseMountains").setTemperatureRainfall(0.4F, 0.3F).setMinMaxHeight(0.3F, 2.0F);
    /**Reverse sub biomes
	 * Reverse系のサブバイオーム。*/
    public static final BiomeGenBaseReverse reverseBeach = (BiomeGenBaseReverse) (new BiomeGenReverseBeach(4, SilverHawkCore.biomeIdRBeach))
            .setColor(16440917).setBiomeName("ReverseBeach").setTemperatureRainfall(0.7F, 0.5F);
    public static final BiomeGenBaseReverse reverseMountainEdge = (BiomeGenBaseReverse) (new BiomeGenReverseMountainEdge(7, SilverHawkCore.biomeIdRMountainEdge))
            .setColor(7501978).setBiomeName("ReverseMountainEdge").setTemperatureRainfall(0.6F, 0.4F).setMinMaxHeight(0.2F, 0.9F);
    /**Double biomes
	 * サブバイオーム。地面と天井が有る。*/
    public static final BiomeGenBaseReverse doublePlains = (BiomeGenBaseReverse) (new BiomeGenDoublePlain(5, SilverHawkCore.biomeIdDplain))
            .setColor(9286496).setBiomeName("DoublePlains").setTemperatureRainfall(0.8F, 0.4F);
    /**Curious biomes
	 * サブバイオーム。浮島が豊富な変則的天地。実際は高めに作った山に穴を開けて作る。*/
    public static final BiomeGenBaseReverse curiousMountains = (BiomeGenBaseReverse) (new BiomeGenCuriousMountain(6, SilverHawkCore.biomeIdDmountain))
            .setColor(6316128).setBiomeName("CuriousMountains").setTemperatureRainfall(0.4F, 0.3F).setMinMaxHeight(0.3F, 2.5F);
    /**Curious Inferno 
     * レアバイオーム。周囲にはBeachにあたるReverseMine（石で出来たビーチ）を生成する。*/
    public static final BiomeGenBaseReverse curiousInferno= (BiomeGenBaseReverse) (new BiomeGenCuriousInferno(6, SilverHawkCore.biomeIdDLava))
            .setColor(16711680).setBiomeName("CuriousMountains").setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(-0.3F, 2.5F);
    public static final BiomeGenBaseReverse ReverseMine = (BiomeGenBaseReverse) (new BiomeGenReverseMine(6, SilverHawkCore.biomeIdRmine))
            .setColor(13786898).setBiomeName("CuriousMountains").setTemperatureRainfall(1.4F, 0.0F).setMinMaxHeight(0.0F, 0.8F);

    //チャンク生成に使うサブ以外のバイオーム
    public static BiomeGenBaseReverse[] decorationBiomes = new BiomeGenBaseReverse[]{
    	reversePlains, reverseOcean, reverseMountains, reverseForest};
    
    //バイオームの飾り付け用定数：現在未定

	public BiomeGenBaseReverse(int par1, int par2) {
		super(par2);
		this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.topBlock = (byte)SilverHawkCore.ClearGrass.blockID;
        this.fillerBlock = (byte)SilverHawkCore.ClearGround.blockID;
        
        this.minHeight = 0.1F;
        this.maxHeight = 0.3F;
	}

}
