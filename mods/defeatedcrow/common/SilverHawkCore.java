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
		version = "1.6.4_0.2B"
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
	public int blockIdCDL = 715;
	public int blockIdGButton = 716;
	public int blockIdGMoss = 717;
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
	public static int entityIdCrow = 250;
	public static int entityIdSHawk = 251;
	public static int entityIdSChicken = 252;
	public static int entityIdRgray = 253;
	public static int entityIdKerberos = 254;
	public static int entityIdFireleo = 255;
	public static int entityIdToxicviper = 256;
	
	//id of projectiles
	public static int projIdShifter = 270;
	public int projIdNormal = 0;
	public int projIdSLaser = 1;
	public int projIdSWave = 2;
	public int projIdLWave = 3;
	
	public static boolean useEXRecipe = false;
	public static boolean addDungeonRootCD = true;
	public static boolean disableDamageForTameable = true;
	public static boolean disableDamageForVillager = true;

	public static int modelLantern;
	
	public static int cfgFlyKeySet = Keyboard.KEY_SPACE;
	public static int cfgSneakKeySet = Keyboard.KEY_LSHIFT;
	
	public static EnumArmorMaterial enumArmorMaterialCrow;
	public static EnumToolMaterial enumToolMaterialCrow;
	public static EnumToolMaterial enumToolMaterialCrow2;
	
	public static boolean isDebugMode = true;
	
	public static boolean loaded_IC2 = false;
	public static boolean loaded_RC = false;
	public static boolean loaded_TE3 = false;
	
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
			Property blockCDL = cfg.getBlock("FluoriteLantern", blockIdCDL);
			Property blockGButton = cfg.getBlock("GlowingButton", blockIdGButton);
			Property blockGMoss = cfg.getBlock("GlowingMoss", blockIdGMoss);
			
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
			Property projId = cfg.get("entity", "Projectiles ID Shift", projIdShifter,
					"Shift ID number of the projectiles of this MOD.");
			
			blockIdOre = blockOre.getInt();
			blockIdFLight = blockFLight.getInt();
			blockIdFGlass = blockFGlass.getInt();
			blockIdCDL = blockCDL.getInt();
			blockIdGButton = blockGButton.getInt();
			blockIdGMoss = blockGMoss.getInt();
			
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
			
			projIdShifter = projId.getInt();
			projIdNormal = projIdShifter + 0;
			projIdSLaser = projIdShifter + 1;
			projIdSWave = projIdShifter + 2;
			projIdLWave = projIdShifter + 3;

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
		Ores = (new BlockOreFluorite(blockIdOre)).
				setUnlocalizedName("defeatedcrow.ores").
				setCreativeTab(SilverHawkCore.crowsdefeat);

		FluoriteLight = (new BlockFluoriteLight(blockIdFLight)).
				setUnlocalizedName("defeatedcrow.fluoriteLight").
				setCreativeTab(SilverHawkCore.crowsdefeat);
		
		FluoroGlass = (new BlockFluoroGlass(blockIdFGlass, Material.glass, false)).
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
		GameRegistry.registerBlock(CDLantern, "defeatedcrow.CDLantern");
		GameRegistry.registerBlock(GlowButton, "defeatedcrow.glowingButton");
		GameRegistry.registerBlock(GlowMoss, "defeatedcrow.glowingMoss");
		
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
	    
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
	      
	    //Registering entity
	    if(entityIdCrow != 0) {
	    	EntityRegistry.registerGlobalEntityID(EntityCrow.class, "crow", entityIdCrow, 0x000000, 0xB000CC);
	    }
	    else {
	    	EntityRegistry.registerGlobalEntityID(EntityCrow.class, "crow", EntityRegistry.findGlobalUniqueEntityId(), 0x000000, 0xB000CC);
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
