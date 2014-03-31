package mods.defeatedcrow.common;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class LangRegister {
	
	public void registerLang()
	{
		LanguageRegistry.addName(new ItemStack(SilverHawkCore.Ores, 1, 0), "Fluorite Ore");
		LanguageRegistry.instance().addNameForObject(new ItemStack(SilverHawkCore.Ores, 1, 0), "ja_JP", "フローライト鉱石");
		
		LanguageRegistry.addName(new ItemStack(SilverHawkCore.Ores, 1, 1), "Silver Ore");
		LanguageRegistry.instance().addNameForObject(new ItemStack(SilverHawkCore.Ores, 1, 1), "ja_JP", "銀鉱石");
 
		LanguageRegistry.addName(SilverHawkCore.FluoriteLight, "Fluorite Light");
		LanguageRegistry.instance().addNameForObject(SilverHawkCore.FluoriteLight, "ja_JP", "蛍石ランプ");
		
		LanguageRegistry.addName(SilverHawkCore.FluoroGlass, "Luminecence Glass");
		LanguageRegistry.instance().addNameForObject(SilverHawkCore.FluoroGlass, "ja_JP", "発光ガラス");
		
		LanguageRegistry.addName(SilverHawkCore.CDLantern, "Fluorite Lantern");
		LanguageRegistry.instance().addNameForObject(SilverHawkCore.CDLantern, "ja_JP", "蛍石カンデラ");
		
		LanguageRegistry.addName(SilverHawkCore.GlowButton, "Glowing Button");
		LanguageRegistry.instance().addNameForObject(SilverHawkCore.GlowButton, "ja_JP", "光るボタン");
		
		LanguageRegistry.addName(SilverHawkCore.GlowMoss, "Glowing Moss");
		LanguageRegistry.instance().addNameForObject(SilverHawkCore.GlowMoss, "ja_JP", "ヒカリゴケ");
		
		LanguageRegistry.addName(SilverHawkCore.Fluorite, "Fluorite");
		LanguageRegistry.instance().addNameForObject(SilverHawkCore.Fluorite, "ja_JP", "フローライト");
 
		LanguageRegistry.addName(SilverHawkCore.dustSilver, "Silver Dust");
		LanguageRegistry.instance().addNameForObject(SilverHawkCore.dustSilver, "ja_JP", "銀の粉末");
		
		LanguageRegistry.addName(SilverHawkCore.ingotSilver, "Silver Ingot");
		LanguageRegistry.instance().addNameForObject(SilverHawkCore.ingotSilver, "ja_JP", "銀インゴット");
		
		LanguageRegistry.addName(new ItemStack(SilverHawkCore.BlackEgg, 1, 0), "Black Egg");
		LanguageRegistry.instance().addNameForObject(new ItemStack(SilverHawkCore.BlackEgg, 1, 0), "ja_JP", "黒色たまご");
		LanguageRegistry.addName(new ItemStack(SilverHawkCore.BlackEgg, 1, 1), "Hawkeye");
		LanguageRegistry.instance().addNameForObject(new ItemStack(SilverHawkCore.BlackEgg, 1, 1), "ja_JP", "鷹の目");
		LanguageRegistry.addName(new ItemStack(SilverHawkCore.BlackEgg, 1, 2), "Freezingeye");
		LanguageRegistry.instance().addNameForObject(new ItemStack(SilverHawkCore.BlackEgg, 1, 2), "ja_JP", "氷の目");
 
		LanguageRegistry.addName(SilverHawkCore.BlackFeather, "Black Feather");
		LanguageRegistry.instance().addNameForObject(SilverHawkCore.BlackFeather, "ja_JP", "カラスの羽根");
		
		LanguageRegistry.addName(SilverHawkCore.armBFMet, "Crow Helmet");
		LanguageRegistry.instance().addNameForObject(SilverHawkCore.armBFMet, "ja_JP", "カラスのメット");
		
		LanguageRegistry.addName(SilverHawkCore.armBFPlate, "Crow Suits");
		LanguageRegistry.instance().addNameForObject(SilverHawkCore.armBFPlate, "ja_JP", "カラスのスーツ");
		
		LanguageRegistry.addName(SilverHawkCore.armBFLegs, "Crow Leggins");
		LanguageRegistry.instance().addNameForObject(SilverHawkCore.armBFLegs, "ja_JP", "カラスのレギンス");
		
		LanguageRegistry.addName(SilverHawkCore.armBFBoots, "Crow Boots");
		LanguageRegistry.instance().addNameForObject(SilverHawkCore.armBFBoots, "ja_JP", "カラスのブーツ");
		
		LanguageRegistry.addName(SilverHawkCore.armHKMet, "Hawk Helmet");
		LanguageRegistry.instance().addNameForObject(SilverHawkCore.armHKMet, "ja_JP", "鷹のメット");
		
		LanguageRegistry.addName(SilverHawkCore.armHKPlate, "Hawk Suits");
		LanguageRegistry.instance().addNameForObject(SilverHawkCore.armHKPlate, "ja_JP", "鷹のスーツ");
		
		LanguageRegistry.addName(SilverHawkCore.armHKLegs, "Hawk Leggins");
		LanguageRegistry.instance().addNameForObject(SilverHawkCore.armHKLegs, "ja_JP", "鷹のレギンス");
		
		LanguageRegistry.addName(SilverHawkCore.armHKBoots, "Hawk Boots");
		LanguageRegistry.instance().addNameForObject(SilverHawkCore.armHKBoots, "ja_JP", "鷹のブーツ");
		
		LanguageRegistry.addName(SilverHawkCore.armSWMet, "Swan Helmet");
		LanguageRegistry.instance().addNameForObject(SilverHawkCore.armSWMet, "ja_JP", "白鳥のメット");
		
		LanguageRegistry.addName(SilverHawkCore.armSWPlate, "Swan Suits");
		LanguageRegistry.instance().addNameForObject(SilverHawkCore.armSWPlate, "ja_JP", "白鳥のスーツ");
		
		LanguageRegistry.addName(SilverHawkCore.armSWLegs, "Swan Leggins");
		LanguageRegistry.instance().addNameForObject(SilverHawkCore.armSWLegs, "ja_JP", "白鳥のレギンス");
		
		LanguageRegistry.addName(SilverHawkCore.armSWBoots, "Swan Boots");
		LanguageRegistry.instance().addNameForObject(SilverHawkCore.armSWBoots, "ja_JP", "白鳥のブーツ");
		
		LanguageRegistry.addName(SilverHawkCore.fluoroSword, "Crow Katana");
		LanguageRegistry.instance().addNameForObject(SilverHawkCore.fluoroSword, "ja_JP", "カラスの脇差");
	}

}
