package mods.defeatedcrow.common;

import java.io.*;
import java.util.logging.Level;

import org.lwjgl.input.Keyboard;

import mods.defeatedcrow.common.SHPacketHandler;
import mods.defeatedcrow.block.*;
import mods.defeatedcrow.entity.*;
import mods.defeatedcrow.entity.projectile.*;
import mods.defeatedcrow.event.CDEventResister;
import mods.defeatedcrow.event.NewSoundEvent;
import mods.defeatedcrow.item.*;
import mods.defeatedcrow.plugin.*;
import mods.defeatedcrow.util.*;
import mods.defeatedcrow.world.WorldProviderReverse;
import mods.defeatedcrow.world.biome.BiomeGenBaseReverse;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.src.*;
import net.minecraft.util.DamageSource;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(
		modid = "DCsSilverHawk",
		name = "SilverHawkMod",
		version = "1.6.4_0.2D"
		)
@NetworkMod(
		clientSideRequired = true,
		serverSideRequired = false,
		channels = {"FlyKey", "SneakKey"}, packetHandler = SHPacketHandler.class
		)

public class SilverHawkCore {
	
	@SidedProxy(clientSide = "mods.defeatedcrow.client.ClientProxy", serverSide = "mods.defeatedcrow.common.CommonProxy")
	public static CommonProxy proxy;
	
	@Instance("DCsSilverHawk")
    public static SilverHawkCore instance;
	
	public static final CreativeTabs crowsdefeat = new CreativeTabCrow("SilverHawkMod");
	
	public static Block Ores;
	public static Block FluoriteLight;
	public static Item  Fluorite;
	public static Block FluoroGlass;
	public static Block CDLantern;
	public static Block GlowButton;
	public static Block GlowMoss;
	public static Block DarkGlass;
	
	public static Block SHPortal;
	public static Block GameMachine;
	
	public static Block NeoBedrock;
	public static Block ClearStone;
	public static Block ClearGround;
	public static Block ClearGrass;
	public static Block ClearOre;
	
	public static Item  BlackEgg;
	public static Item  BlackFeather;
	public static Item  armBFMet, armBFPlate, armBFLegs, armBFBoots;
	public static Item  armHKMet, armHKPlate, armHKLegs, armHKBoots;
	public static Item  armSWMet, armSWPlate, armSWLegs, armSWBoots;
	public static Item  fluoroSword;
	public static Item  blueSword;
	public static Item  crimsonSword;
	public static Item  dustSilver;
	public static Item  ingotSilver;
	public static Item  ingotLead;
	public static Item  flighterCore;
 
	public int blockIdOre = 711;
	public int blockIdFLight = 712;
	public int blockIdFGlass = 713;
	public int blockIdDGlass = 714;
	public int blockIdCDL = 715;
	public int blockIdGButton = 716;
	public int blockIdGMoss = 717;
	public int blockIdGameMachine = 720;
	public int blockIdGamePortal = 721;
	
	public int blockIdNeoBedrock = 210;
	public int blockIdCLStone = 211;
	public int blockIdCLGround = 212;
	public int blockIdCLOre = 214;
	public int blockIdCLGrass = 213;
	public int blockIdRPlants = 215;
	public int blockIdRSapling = 216;
	
	public int fluidIdRWater = 217;
	public int fluidIdRLava = 219;
	
	public int itemIdFluorite = 7011;
	public int itemIdBegg = 7013;
	public int itemIdBfeather = 7012;
	public int itemIdFSword = 7017;
	public int itemIdDustS = 7014;
	public int itemIdingotS = 7015;
	public int itemIdingotL = 7016;
	public int itemIdBlueSword = 7018;
	public int itemIdCrimsonSword = 7019;
	public int armIdBFM = 7020;
	public int armIdBFP = 7021;
	public int armIdBFL = 7022;
	public int armIdBFB = 7023;
	public int armIdHKM = 7024;
	public int armIdHKP = 7025;
	public int armIdHKL = 7026;
	public int armIdHKB = 7027;
	public int armIdSWM = 7028;
	public int armIdSWP = 7029;
	public int armIdSWL = 7030;
	public int armIdSWB = 7031;
	public int itemIdFlighterCore = 7032;
	public int itemIdChangeCore = 7033;
	public int itemIdBossDrops = 7035;
	public int itemIdRWaterBucket = 7040;
	public int itemIdRLavaBucket = 7041;
	
	//ID of Living Entity
	public static int entityIdCrow = 250;
	public static int entityIdSHawk = 251;
	public static int entityIdSChicken = 252;
	public static int entityIdRgray = 253;
	public static int entityIdKerberos = 254;
	public static int entityIdFireleo = 255;
	public static int entityIdToxicviper = 256;
	
	//ID of projectiles
	public static int projIdShifter = 270;
	public int projIdNormal = 0;
	public int projIdSLaser = 1;
	public int projIdSWave = 2;
	public int projIdLWave = 3;
	
	//ID of Dimension and Biomes
	public static int DCsDimID = 11;
	public static int biomeIdShifter = 90;
	public static int biomeIdRPlain = 0;
	public static int biomeIdRMountain = 1;
	public static int biomeIdRForest = 2;
	public static int biomeIdROcean = 3;
	public static int biomeIdRmine = 4;
	public static int biomeIdDplain = 5;
	public static int biomeIdDmountain = 6;
	public static int biomeIdDLava = 7;
	public static int biomeIdRBeach = 8;
	public static int biomeIdRMountainEdge = 9;
	
	public static boolean useEXRecipe = false;
	public static boolean addDungeonRootCD = true;
	public static boolean disableDamageForTameable = true;
	public static boolean disableDamageForVillager = true;

	public static int modelLantern;
	public static int modelInvisible;
	
	public static int cfgFlyKeySet = Keyboard.KEY_SPACE;
	public static int cfgSneakKeySet = Keyboard.KEY_LSHIFT;
	
	public static EnumArmorMaterial enumArmorMaterialCrow;
	public static EnumToolMaterial enumToolMaterialCrow;
	public static EnumToolMaterial enumToolMaterialCrow2;
	
	public static boolean isDebugMode = false;
	public static boolean visibleBlock = false;
	
	public static boolean loaded_IC2 = false;
	public static boolean loaded_RC = false;
	public static boolean loaded_TE3 = false;
	
	private final String BR = System.getProperty("line.separator");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		//set configuration file
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try
		{
			cfg.load();
			Property blockOre = cfg.getBlock("OreBlock", blockIdOre);
			Property blockFLight = cfg.getBlock("FluoriteLight", blockIdFLight);
			Property blockFGlass = cfg.getBlock("FluoroGlass", blockIdFGlass);
			Property blockDGlass = cfg.getBlock("DarkGlass", blockIdDGlass);
			Property blockCDL = cfg.getBlock("FluoriteLantern", blockIdCDL);
			Property blockGButton = cfg.getBlock("GlowingButton", blockIdGButton);
			Property blockGMoss = cfg.getBlock("GlowingMoss", blockIdGMoss);
			Property blockPortal = cfg.getBlock("PortalBlock", blockIdGamePortal);
			
			Property blockCLStone = cfg.getTerrainBlock("block_terrain", "TransparentStones", blockIdCLStone, 
					"This block are used in terrain generation. please set the ID 255 or less.");
			Property blockCLGround = cfg.getTerrainBlock("block_terrain", "TransparentGrounds", blockIdCLStone, 
					"This block are used in terrain generation. please set the ID 255 or less.");
			Property blockCLGrass = cfg.getTerrainBlock("block_terrain", "TransparentGrass", blockIdCLGround, 
					"This block are used in terrain generation. please set the ID 255 or less.");
			Property blockCLOre = cfg.getTerrainBlock("block_terrain", "TransparentOre", blockIdCLGrass, 
					"This block are used in terrain generation. please set the ID 255 or less.");
			Property blockNeoBR = cfg.getTerrainBlock("block_terrain", "TransparentBedrock", blockIdNeoBedrock, 
					"This block are used in terrain generation. please set the ID 255 or less.");
			Property blockRWater = cfg.getTerrainBlock("block_fluid", "ReverseWater", fluidIdRWater, 
					"This block are used in terrain generation, and this block are fluid."
					+ BR +  "The ID should be 255 or less, and the next ID should be vacant.");
			Property blockRLava = cfg.getTerrainBlock("block_fluid", "ReverseLava", fluidIdRLava, 
					"This block are used in terrain generation, and this block are fluid."
					+ BR +  "The ID should be 255 or less, and the next ID should be vacant.");
			
			Property itemFluorite  = cfg.getItem("Fluorite", itemIdFluorite);
			Property itemBegg = cfg.getItem("CraftingItems", itemIdBegg);
			Property itemBfeather = cfg.getItem("Blackfeather", itemIdBfeather);
			Property itemFSword = cfg.getItem("FluoroSword", itemIdFSword);
			Property itemBSword = cfg.getItem("DeepblueSword", itemIdBlueSword);
			Property itemCSword = cfg.getItem("CrimsonSword", itemIdCrimsonSword);
			Property itemdustS = cfg.getItem("SilverDust", itemIdDustS);
			Property itemingotS = cfg.getItem("SilverIngot", itemIdingotS);
			Property itemingotL = cfg.getItem("LeadIngot", itemIdingotL);
			Property itemFlighterCore = cfg.getItem("FlighterCore", itemIdFlighterCore);
			Property itemChangeCore = cfg.getItem("ChangingCore", itemIdChangeCore);
			Property itemBossDrops = cfg.getItem("BossDrops", itemIdBossDrops);
			Property itemRWBucket = cfg.getItem("BucketofR_Water", itemIdRWaterBucket);
			Property itemRLBucket = cfg.getItem("BucketOfR_Lava", itemIdRLavaBucket);
			
			Property armBFmet = cfg.getItem("CrowHelmet", armIdBFM);
			Property armBFPlate = cfg.getItem("CrowPlate", armIdBFP);
			Property armBFLegs = cfg.getItem("CrowLeggins", armIdBFL);
			Property armBFBoots = cfg.getItem("CrowBoots", armIdBFB);
			Property armHKMet = cfg.getItem("HawkHelmet", armIdHKM);
			Property armHKPlate = cfg.getItem("HawkPlate", armIdHKP);
			Property armHKLegs = cfg.getItem("HawkLeggins", armIdHKL);
			Property armHKBoots = cfg.getItem("HawkBoots", armIdHKB);
			Property armSWMet = cfg.getItem("SwanHelmet", armIdSWM);
			Property armSWPlate = cfg.getItem("SwanPlate", armIdSWP);
			Property armSWLegs = cfg.getItem("SwanLeggins", armIdSWL);
			Property armSWBoots = cfg.getItem("SwanBoots", armIdSWB);
			
			Property setFlyKey = cfg.get("keyCode", "FlyKey", cfgFlyKeySet, "This key is used for starting flight, and for the rise.");
			Property setSneakKey = cfg.get("keyCode", "SneakKey", cfgSneakKeySet, "This key is used for swimming in water.");
			
			Property entityCBCrow = cfg.get("entity", "CrowID", entityIdCrow);
			Property entitySHawk = cfg.get("entity", "SilverHawkID", entityIdSHawk);
			Property entitySChicken = cfg.get("entity", "SilverChickenID", entityIdSChicken);
			
			Property recipeBoolean = cfg.get("Setting", "Use Extra Recipe", useEXRecipe, "Add recipe for crafting blackfeather.");
			Property chestBoolean = cfg.get("Setting", "Add Custom Dungeon Root", addDungeonRootCD, "Not add custom dungeon root.");
			Property disDamageTameable = cfg.get("Setting", "Disable Damage for Tamable Entity", disableDamageForTameable,
					"Disable the bullet damage given to the animal.");
			Property disDamageVillager = cfg.get("Setting", "Disable Damage for Villager", disableDamageForVillager,
					"Disable the bullet damage given to the villager.");
			Property visible = cfg.get("Setting", "Visible Transparent Bedrock", visibleBlock,
					"Enable the invisible blocks visible mode. (for example, the Transparent Bedrock)");
			
			Property projId = cfg.get("entity_projectile", "Projectiles ID Shift", projIdShifter,
					"Shift ID number of the projectiles of this MOD.");
			Property biomeId = cfg.get("world_biome", "Biomes ID Shift", biomeIdShifter,
					"Shift ID number of the biomes of this MOD.");
			Property DimId = cfg.get("world", "Dimension ID", DCsDimID,
					"ID number of the dimension of this MOD.");
			
			blockIdOre = blockOre.getInt();
			blockIdFLight = blockFLight.getInt();
			blockIdFGlass = blockFGlass.getInt();
			blockIdDGlass = blockDGlass.getInt();
			blockIdCDL = blockCDL.getInt();
			blockIdGButton = blockGButton.getInt();
			blockIdGMoss = blockGMoss.getInt();
			blockIdGamePortal = blockPortal.getInt();
			
			blockIdCLStone = blockCLStone.getInt();
			blockIdCLGround = blockCLGround.getInt();
			blockIdCLGrass = blockCLGrass.getInt();
			blockIdCLOre = blockCLOre.getInt();
			blockIdNeoBedrock = blockNeoBR.getInt();
			
			fluidIdRWater = blockRWater.getInt();
			fluidIdRLava = blockRLava.getInt();
			
			itemIdFluorite  = itemFluorite.getInt();
			itemIdBegg  = itemBegg.getInt();
			itemIdBfeather  = itemBfeather.getInt();
			itemIdFSword  = itemFSword.getInt();
			itemIdBlueSword = itemBSword.getInt();
			itemIdCrimsonSword = itemCSword.getInt();
			itemIdDustS  = itemdustS.getInt();
			itemIdingotS  = itemingotS.getInt();
			itemIdingotL = itemingotL.getInt();
			itemIdFlighterCore = itemFlighterCore.getInt();
			itemIdChangeCore = itemChangeCore.getInt();
			itemIdBossDrops = itemBossDrops.getInt();
			itemIdRWaterBucket = itemRWBucket.getInt();
			itemIdRLavaBucket = itemRLBucket.getInt();
			
			armIdBFM  = armBFmet.getInt();
			armIdBFP  = armBFPlate.getInt();
			armIdBFL  = armBFLegs.getInt();
			armIdBFB  = armBFBoots.getInt();
			armIdHKM  = armHKMet.getInt();
			armIdHKP  = armHKPlate.getInt();
			armIdHKL  = armHKLegs.getInt();
			armIdHKB  = armHKBoots.getInt();
			armIdSWM  = armSWMet.getInt();
			armIdSWP  = armSWPlate.getInt();
			armIdSWL  = armSWLegs.getInt();
			armIdSWB  = armSWBoots.getInt();
			
			cfgFlyKeySet = setFlyKey.getInt();
			cfgSneakKeySet = setSneakKey.getInt();
			entityIdCrow = entityCBCrow.getInt();
			
			useEXRecipe = recipeBoolean.getBoolean(false);
			addDungeonRootCD = chestBoolean.getBoolean(true);
			disableDamageForTameable = disDamageTameable.getBoolean(true);
			disableDamageForVillager = disDamageVillager.getBoolean(true);
			visibleBlock = visible.getBoolean(false);
			
			projIdShifter = projId.getInt();
			projIdNormal = projIdShifter + 0;
			projIdSLaser = projIdShifter + 1;
			projIdSWave = projIdShifter + 2;
			projIdLWave = projIdShifter + 3;
			
			DCsDimID = DimId.getInt();
			
			biomeIdShifter = biomeId.getInt();
			biomeIdRPlain = 0 + biomeIdShifter;
			biomeIdRMountain = 1 + biomeIdShifter;
			biomeIdRForest = 2 + biomeIdShifter;
			biomeIdROcean = 3 + biomeIdShifter;
			biomeIdRmine = 4 + biomeIdShifter;
			biomeIdDplain = 5 + biomeIdShifter;
			biomeIdDmountain = 6 + biomeIdShifter;
			biomeIdDLava = 7 + biomeIdShifter;
			biomeIdRBeach = 8 + biomeIdShifter;
			biomeIdRMountainEdge = 9 + biomeIdShifter;

		}
		catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "Error Message");

		}
		finally
		{
			cfg.save();
		}
	    
		//sound load event
		MinecraftForge.EVENT_BUS.register(new NewSoundEvent());
		
		//Resister materials
		//blocks
		Ores = (new BlockOreFluorite(blockIdOre)).
				setUnlocalizedName("defeatedcrow.ores").
				setCreativeTab(SilverHawkCore.crowsdefeat);

		FluoriteLight = (new BlockFluoriteLight(blockIdFLight)).
				setUnlocalizedName("defeatedcrow.fluoriteLight").
				setCreativeTab(SilverHawkCore.crowsdefeat);
		
		FluoroGlass = (new BlockFluoroGlass(blockIdFGlass, Material.glass, false)).
				setCreativeTab(SilverHawkCore.crowsdefeat);
		
		DarkGlass = (new BlockDarkGlass(blockIdDGlass, Material.glass, false)).
				setCreativeTab(SilverHawkCore.crowsdefeat);
		
		CDLantern = (new BlockCDLantern(blockIdCDL)).
				setUnlocalizedName("defeatedcrow.CDLantern").
				setCreativeTab(SilverHawkCore.crowsdefeat);
		
		GlowButton = (new BlockGlowButton(blockIdGButton)).
				setUnlocalizedName("defeatedcrow.glowingButton").
				setCreativeTab(SilverHawkCore.crowsdefeat);
				
		GlowMoss = (new BlockGlowMoss(blockIdGMoss)).
				setUnlocalizedName("defeatedcrow.glowingMoss").
				setCreativeTab(SilverHawkCore.crowsdefeat);
		
		//teleporter
		SHPortal = (new BlockSHPortal(blockIdGamePortal)).
				setUnlocalizedName("defeatedcrow.portalBlock");
		
		//terrain blocks
		ClearStone = (new BlockClearStone(blockIdCLStone)).
				setUnlocalizedName("defeatedcrow.clearStones").
				setCreativeTab(SilverHawkCore.crowsdefeat);
		
		ClearGround = (new BlockClearGround(blockIdCLGround)).
				setUnlocalizedName("defeatedcrow.clearGrounds").
				setCreativeTab(SilverHawkCore.crowsdefeat);
		
		ClearGrass = (new BlockClearGrass(blockIdCLGrass)).
				setUnlocalizedName("defeatedcrow.clearGrass").
				setCreativeTab(SilverHawkCore.crowsdefeat);
		
		ClearOre = (new BlockClearOre(blockIdCLOre)).
				setUnlocalizedName("defeatedcrow.clearOres").
				setCreativeTab(SilverHawkCore.crowsdefeat);
		
		NeoBedrock = (new BlockNeoBedrock(blockIdNeoBedrock)).
				setUnlocalizedName("defeatedcrow.clearBedrock").
				setCreativeTab(SilverHawkCore.crowsdefeat);

		//items
		Fluorite = (new ItemFluorite(itemIdFluorite - 256)).
				setUnlocalizedName("defeatedcrow.fluorite").
				setCreativeTab(SilverHawkCore.crowsdefeat);

		BlackEgg  = (new ItemCraftingMaterial(itemIdBegg - 256)).
				setUnlocalizedName("defeatedcrow.materials").
				setCreativeTab(SilverHawkCore.crowsdefeat);

		BlackFeather  = (new ItemBlackFeather(itemIdBfeather - 256)).
				setUnlocalizedName("defeatedcrow.blackFeather").
				setCreativeTab(SilverHawkCore.crowsdefeat);
		
		dustSilver  = (new ItemDustSilver(itemIdDustS - 256)).
				setUnlocalizedName("defeatedcrow.dustSilver").
				setCreativeTab(SilverHawkCore.crowsdefeat);
		
		ingotSilver  = (new ItemIngotSilver(itemIdingotS - 256)).
				setUnlocalizedName("defeatedcrow.ingotSilver").
				setCreativeTab(SilverHawkCore.crowsdefeat);
		
		ingotLead  = (new ItemIngotLead(itemIdingotL - 256)).
				setUnlocalizedName("defeatedcrow.ingotLead").
				setCreativeTab(SilverHawkCore.crowsdefeat);
		
		flighterCore  = (new ItemFlighterCore(itemIdFlighterCore - 256)).
				setUnlocalizedName("defeatedcrow.flighterCore").
				setCreativeTab(SilverHawkCore.crowsdefeat);
		
		enumArmorMaterialCrow = EnumHelper.addArmorMaterial("CROW", 15, new int[]{3, 4, 5, 2}, 15);
		enumToolMaterialCrow = EnumHelper.addToolMaterial("DCSILVER", 3, 192, 8.0F, 3.0F, 18);
		enumToolMaterialCrow2 = EnumHelper.addToolMaterial("DCLEAD", 1, 127, 4.0F, 8.0F, 8);
		enumArmorMaterialCrow.customCraftingMaterial = this.BlackFeather;
		enumToolMaterialCrow.customCraftingMaterial = this.ingotSilver;
		enumToolMaterialCrow2.customCraftingMaterial = this.ingotLead;
		
		
		fluoroSword  = (new ItemFluoroSword(itemIdFSword - 256,enumToolMaterialCrow)).
				setUnlocalizedName("defeatedcrow.fluorosword").
				setCreativeTab(SilverHawkCore.crowsdefeat);
		
		blueSword  = (new ItemBlueSword(itemIdBlueSword - 256,enumToolMaterialCrow)).
				setUnlocalizedName("defeatedcrow.deepbluesword").
				setCreativeTab(SilverHawkCore.crowsdefeat);
		
		crimsonSword  = (new ItemCrimsonSword(itemIdCrimsonSword - 256,enumToolMaterialCrow2)).
				setUnlocalizedName("defeatedcrow.crimsonsword").
				setCreativeTab(SilverHawkCore.crowsdefeat);
		
		armBFMet  = (new ItemArmorCrow(armIdBFM - 256, enumArmorMaterialCrow, SilverHawkCore.proxy.addArmor("crowarmor"), 0)).
				setUnlocalizedName("defeatedcrow.crowHelmet").
				setCreativeTab(SilverHawkCore.crowsdefeat);
		armBFPlate  = (new ItemArmorCrow(armIdBFP - 256, enumArmorMaterialCrow, SilverHawkCore.proxy.addArmor("crowarmor"), 1)).
				setUnlocalizedName("defeatedcrow.crowSuits").
				setCreativeTab(SilverHawkCore.crowsdefeat);
		armBFLegs  = (new ItemArmorCrow(armIdBFL - 256, enumArmorMaterialCrow, SilverHawkCore.proxy.addArmor("crowarmor"), 2)).
				setUnlocalizedName("defeatedcrow.crowLeggins").
				setCreativeTab(SilverHawkCore.crowsdefeat);
		armBFBoots  = (new ItemArmorCrow(armIdBFB - 256, enumArmorMaterialCrow, SilverHawkCore.proxy.addArmor("crowarmor"), 3)).
				setUnlocalizedName("defeatedcrow.crowBoots").
				setCreativeTab(SilverHawkCore.crowsdefeat);
		
		armHKMet  = (new ItemArmorHawk(armIdHKM - 256, enumArmorMaterialCrow, SilverHawkCore.proxy.addArmor("hawkarmor"), 0)).
				setUnlocalizedName("defeatedcrow.hawkHelmet").
				setCreativeTab(SilverHawkCore.crowsdefeat);
		armHKPlate  = (new ItemArmorHawk(armIdHKP - 256, enumArmorMaterialCrow, SilverHawkCore.proxy.addArmor("hawkarmor"), 1)).
				setUnlocalizedName("defeatedcrow.hawkSuits").
				setCreativeTab(SilverHawkCore.crowsdefeat);
		armHKLegs  = (new ItemArmorHawk(armIdHKL - 256, enumArmorMaterialCrow, SilverHawkCore.proxy.addArmor("hawkarmor"), 2)).
				setUnlocalizedName("defeatedcrow.hawkLeggins").
				setCreativeTab(SilverHawkCore.crowsdefeat);
		armHKBoots  = (new ItemArmorSpecial(armIdHKB - 256, enumArmorMaterialCrow, SilverHawkCore.proxy.addArmor("hawkarmor"), 3)).
				setUnlocalizedName("defeatedcrow.hawkBoots").
				setCreativeTab(SilverHawkCore.crowsdefeat);
		
		armSWMet  = (new ItemArmorSwan(armIdSWM - 256, enumArmorMaterialCrow, SilverHawkCore.proxy.addArmor("swanarmor"), 0)).
				setUnlocalizedName("defeatedcrow.swanHelmet").
				setCreativeTab(SilverHawkCore.crowsdefeat);
		armSWPlate  = (new ItemArmorSwan(armIdSWP - 256, enumArmorMaterialCrow, SilverHawkCore.proxy.addArmor("swanarmor"), 1)).
				setUnlocalizedName("defeatedcrow.swanSuits").
				setCreativeTab(SilverHawkCore.crowsdefeat);
		armSWLegs  = (new ItemArmorSwan(armIdSWL - 256, enumArmorMaterialCrow, SilverHawkCore.proxy.addArmor("swanarmor"), 2)).
				setUnlocalizedName("defeatedcrow.swanLeggins").
				setCreativeTab(SilverHawkCore.crowsdefeat);
		armSWBoots  = (new ItemArmorSwan(armIdSWB - 256, enumArmorMaterialCrow, SilverHawkCore.proxy.addArmor("swanarmor"), 3)).
				setUnlocalizedName("defeatedcrow.swanBoots").
				setCreativeTab(SilverHawkCore.crowsdefeat);
				
		GameRegistry.registerBlock(Ores, ItemOreFluorite.class, "defeatedcrow.ores");
		GameRegistry.registerBlock(FluoriteLight, "defeatedcrow.fluoriteLight");
		GameRegistry.registerBlock(FluoroGlass, "defeatedcrow.fluoroGlass");
		GameRegistry.registerBlock(DarkGlass, "defeatedcrow.darkGlass");
		GameRegistry.registerBlock(CDLantern, "defeatedcrow.CDLantern");
		GameRegistry.registerBlock(GlowButton, "defeatedcrow.glowingButton");
		GameRegistry.registerBlock(GlowMoss, "defeatedcrow.glowingMoss");
		GameRegistry.registerBlock(SHPortal, "defeatedcrow.portalBlock");
		
		GameRegistry.registerBlock(ClearStone, ItemClearStone.class, "defeatedcrow.clearStone");
		GameRegistry.registerBlock(ClearGround, ItemClearGrounds.class, "defeatedcrow.clearGround");
		GameRegistry.registerBlock(ClearOre, ItemClearOres.class, "defeatedcrow.clearOre");
		GameRegistry.registerBlock(ClearGrass, "defeatedcrow.clearGrass");
		GameRegistry.registerBlock(NeoBedrock, "defeatedcrow.clearBedrock");
		
		GameRegistry.registerItem(Fluorite, "defeatedcrow.fluorite");
		GameRegistry.registerItem(BlackEgg, "defeatedcrow.materials");
		GameRegistry.registerItem(BlackFeather, "defeatedcrow.blackfeather");
		GameRegistry.registerItem(dustSilver, "defeatedcrow.silverDust");
		GameRegistry.registerItem(ingotSilver, "defeatedcrow.silverIngot");
		GameRegistry.registerItem(ingotLead, "defeatedcrow.leadIngot");
		GameRegistry.registerItem(flighterCore, "defeatedcrow.flighterCore");
		
		GameRegistry.registerItem(fluoroSword, "defeatedcrow.fluoroSword");
		GameRegistry.registerItem(blueSword, "defeatedcrow.deepblueSword");
		GameRegistry.registerItem(crimsonSword, "defeatedcrow.crimsonSword");
		
		GameRegistry.registerItem(armBFMet, "defeatedcrow.crowHelmet");
		GameRegistry.registerItem(armBFPlate, "defeatedcrow.crowSuits");
		GameRegistry.registerItem(armBFLegs, "defeatedcrow.crowLeggins");
		GameRegistry.registerItem(armBFBoots, "defeatedcrow.crowBoots");
		
		GameRegistry.registerItem(armHKMet, "defeatedcrow.hawkHelmet");
		GameRegistry.registerItem(armHKPlate, "defeatedcrow.hawkSuits");
		GameRegistry.registerItem(armHKLegs, "defeatedcrow.hawkLeggins");
		GameRegistry.registerItem(armHKBoots, "defeatedcrow.hawkBoots");
		
		GameRegistry.registerItem(armSWMet, "defeatedcrow.swanHelmet");
		GameRegistry.registerItem(armSWPlate, "defeatedcrow.swanSuits");
		GameRegistry.registerItem(armSWLegs, "defeatedcrow.swanLeggins");
		GameRegistry.registerItem(armSWBoots, "defeatedcrow.swanBoots");
		
		
	    //Ore Dictionary
	    OreDictionary.registerOre("oreFluorite", new ItemStack(this.Ores, 1, 0));
	    OreDictionary.registerOre("gemFluorite", new ItemStack(this.Fluorite, 1, 0));
	    OreDictionary.registerOre("oreSilver", new ItemStack(this.Ores, 1, 1));
	    OreDictionary.registerOre("ingotSilver", new ItemStack(this.ingotSilver, 1, 0));
	    OreDictionary.registerOre("dustSilver", new ItemStack(this.dustSilver, 1, 0));
	    OreDictionary.registerOre("oreCrocoite", new ItemStack(this.Ores, 1, 2));
	    OreDictionary.registerOre("gemCrocoite", new ItemStack(this.Fluorite, 1, 1));
	    OreDictionary.registerOre("ingotLead", new ItemStack(this.ingotLead, 1, 0));
	    OreDictionary.registerOre("dustLead", new ItemStack(this.dustSilver, 1, 1));
	    OreDictionary.registerOre("dustChromeVI", new ItemStack(this.dustSilver, 1, 2));
	    
	    OreDictionary.registerOre("oreCoal", new ItemStack(this.ClearOre, 1, 0));
	    OreDictionary.registerOre("oreIron", new ItemStack(this.ClearOre, 1, 1));
	    OreDictionary.registerOre("oreGold", new ItemStack(this.ClearOre, 1, 2));
	    OreDictionary.registerOre("oreDiamond", new ItemStack(this.ClearOre, 1, 3));
	    OreDictionary.registerOre("oreRedstone", new ItemStack(this.ClearOre, 1, 4));
	    OreDictionary.registerOre("oreEmerald", new ItemStack(this.ClearOre, 1, 5));
	    OreDictionary.registerOre("oreFluorite", new ItemStack(this.ClearOre, 1, 6));
	    OreDictionary.registerOre("oreSilver", new ItemStack(this.ClearOre, 1, 7));
	    OreDictionary.registerOre("oreCrocoite", new ItemStack(this.ClearOre, 1, 8));
	    
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
	      
	    //Registering entity
	    if(entityIdCrow == 0) {
	    	
	    	EntityRegistry.registerGlobalEntityID(EntityCrow.class, "crow", EntityRegistry.findGlobalUniqueEntityId(), 0x000000, 0xB000CC);
	    }
	    else {
	    	EntityRegistry.registerGlobalEntityID(EntityCrow.class, "crow", entityIdCrow, 0x000000, 0xB000CC);
	    }
	    
	    //mobs
	    if (entityIdCrow == 0){
	    	EntityRegistry.registerModEntity(EntityCrow.class, "crow", EntityRegistry.findGlobalUniqueEntityId(), this, 250, 5, true);
	    }
	    else{
	    	EntityRegistry.registerModEntity(EntityCrow.class, "crow", entityIdCrow, this, 250, 5, true);
	    }
	    
	    if (entityIdSHawk == 0){
	    	EntityRegistry.registerModEntity(EntitySilverHawk.class, "silverHawk", EntityRegistry.findGlobalUniqueEntityId(), this, 250, 5, true);
	    }
	    else{
	    	EntityRegistry.registerModEntity(EntitySilverHawk.class, "silverHawk", entityIdSHawk, this, 250, 5, true);
	    }
	    
//	    EntityRegistry.registerModEntity(EntitySilverChicken.class, "silverChicken", entityIdSChicken, this, 250, 5, true);
	    
	    //proj
	    EntityRegistry.registerModEntity(EntityNormalGatling.class, "DCGatling", projIdNormal, this, 128, 5, true);
	    EntityRegistry.registerModEntity(EntityShortLaser.class, "DCSLaser", projIdSLaser, this, 128, 5, true);
	    EntityRegistry.registerModEntity(EntitySmallWave.class, "DCSWave", projIdSWave, this, 128, 5, true);
	    EntityRegistry.registerModEntity(EntityLargeWave.class, "DCLWave", projIdLWave, this, 128, 5, true);
	      
	      
	    EntityRegistry.addSpawn(EntityCrow.class, 20, 1, 4, EnumCreatureType.creature, BiomeGenBase.forest);
	    EntityRegistry.addSpawn(EntityCrow.class, 20, 1, 4, EnumCreatureType.creature, BiomeGenBase.jungle);
	    
	    //registering renderer
	    this.modelLantern = proxy.getRenderID();
	    this.modelInvisible = proxy.getRenderID();
	    proxy.registerRenderers();
	      
	    LanguageRegistry.instance().addStringLocalization("entity.crow.name", "en_US", "Crow");
	    LanguageRegistry.instance().addStringLocalization("entity.crow.name", "ja_JP", "カラス");
	    
	    LanguageRegistry.instance().addStringLocalization("entity.silverHawk.name", "en_US", "Silver Hawk");
	    LanguageRegistry.instance().addStringLocalization("entity.silverHawk.name", "ja_JP", "シルバーホーク");
	    
	    LanguageRegistry.instance().addStringLocalization("entity.silverChicken.name", "en_US", "Silver Chicken");
	    LanguageRegistry.instance().addStringLocalization("entity.silverChicken.name", "ja_JP", "ギンケイ");
	      
	      
	    //Registering recipe
	    (new RecipeRegister()).addRecipe();
	    if (this.useEXRecipe)
	    {
	    	(new RecipeRegister()).addEXRecipe();
	    }
	      
	    //Registering language
	    (new LangRegister()).registerLang();
	      
	    //Registering events
	    GameRegistry.registerWorldGenerator(new GeneratingFOre());
	    (new CDEventResister()).registerEvent();
	      
	    //ChestGenHooks
	    if (this.addDungeonRootCD)
	    {
	    	(new AddChestGen()).addChestItems();
	    }
	    
	    //registering new dimension
//	    DimensionManager.registerProviderType(DCsDimID, WorldProviderReverse.class, false);
//	    DimensionManager.registerDimension(DCsDimID, DCsDimID);
	    
//	    if (BiomeGenBaseReverse.registerBiome())
//	    	SHLogger.debugInfo("Succeed to register biomes.");
	    
	    //biome dictionary
	    BiomeDictionary.registerBiomeType(BiomeGenBaseReverse.reversePlains, BiomeDictionary.Type.PLAINS);
	    BiomeDictionary.registerBiomeType(BiomeGenBaseReverse.reverseForest, BiomeDictionary.Type.FOREST);
	    BiomeDictionary.registerBiomeType(BiomeGenBaseReverse.reverseOcean, BiomeDictionary.Type.WATER);
	    BiomeDictionary.registerBiomeType(BiomeGenBaseReverse.reverseMountains, BiomeDictionary.Type.MOUNTAIN);
	    BiomeDictionary.registerBiomeType(BiomeGenBaseReverse.reverseBeach, BiomeDictionary.Type.BEACH);
	    BiomeDictionary.registerBiomeType(BiomeGenBaseReverse.reverseMountainEdge, BiomeDictionary.Type.MOUNTAIN);
	    BiomeDictionary.registerBiomeType(BiomeGenBaseReverse.reverseMine, BiomeDictionary.Type.WASTELAND);
	    BiomeDictionary.registerBiomeType(BiomeGenBaseReverse.doublePlains, BiomeDictionary.Type.PLAINS);
	    BiomeDictionary.registerBiomeType(BiomeGenBaseReverse.curiousMountains, BiomeDictionary.Type.MOUNTAIN);
	    BiomeDictionary.registerBiomeType(BiomeGenBaseReverse.curiousInferno, BiomeDictionary.Type.WASTELAND);

	}
	
	@EventHandler
	public void IMC(FMLInterModComms event)
	{
		if (Loader.isModLoaded("ThermalExpansion"))
	    {
	    	SHLogger.loadingModInfo("ThermalExpansion");
	    	try
	        {
	          this.loaded_TE3 = true;
	          (new TE3Plugin()).load();
	          SHLogger.loadedModInfo("ThermalExpansion");
	        }
	        catch (Exception e) {
	        	SHLogger.failLoadingModInfo("ThermalExpansion");
	          e.printStackTrace(System.err);
	        }
	    }
	}
	
	@EventHandler
    public void postInit(FMLPostInitializationEvent event) {
		
		(new EvolutionList()).registerEvol();
		
		if (Loader.isModLoaded("IC2"))
	    {
	    	SHLogger.loadingModInfo("IC2");
	    	try
	        {
	          this.loaded_IC2 = true;
	          (new IC2Plugin()).load();
	          SHLogger.loadedModInfo("IC2");
	        }
	        catch (Exception e) {
	        	SHLogger.failLoadingModInfo("IC2");
	          e.printStackTrace(System.err);
	        }
	    }
		
		if (Loader.isModLoaded("Railcraft"))
	    {
	    	SHLogger.loadingModInfo("Railcraft");
	    	try
	        {
	          this.loaded_RC = true;
	          (new RCPlugin()).load();
	          SHLogger.loadedModInfo("Railcraft");
	        }
	        catch (Exception e) {
	        	SHLogger.failLoadingModInfo("Railcraft");
	          e.printStackTrace(System.err);
	        }
	    }
	}

}
