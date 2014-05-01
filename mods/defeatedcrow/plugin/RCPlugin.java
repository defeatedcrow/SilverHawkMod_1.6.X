package mods.defeatedcrow.plugin;

import net.minecraft.item.ItemStack;
import mods.defeatedcrow.common.SilverHawkCore;
import mods.railcraft.api.crafting.IRockCrusherRecipe;
import mods.railcraft.api.crafting.RailcraftCraftingManager;

public class RCPlugin {
	
	public void load()
	{
		IRockCrusherRecipe SHRecipe = RailcraftCraftingManager.rockCrusher.createNewRecipe(new ItemStack(SilverHawkCore.Ores, 1, 0),  true,  false);
		SHRecipe.addOutput(new ItemStack(SilverHawkCore.Fluorite, 2, 0), 1.0F);
		SHRecipe.addOutput(new ItemStack(SilverHawkCore.Fluorite, 1, 0), 0.5F);
		
		SHRecipe = RailcraftCraftingManager.rockCrusher.createNewRecipe(new ItemStack(SilverHawkCore.Ores, 1, 2),  true,  false);
		SHRecipe.addOutput(new ItemStack(SilverHawkCore.Fluorite, 2, 1), 1.0F);
		SHRecipe.addOutput(new ItemStack(SilverHawkCore.Fluorite, 1, 1), 0.5F);
		
		SHRecipe = RailcraftCraftingManager.rockCrusher.createNewRecipe(new ItemStack(SilverHawkCore.Fluorite, 1, 1),  true,  false);
		SHRecipe.addOutput(new ItemStack(SilverHawkCore.dustSilver, 2, 1), 1.0F);
		SHRecipe.addOutput(new ItemStack(SilverHawkCore.dustSilver, 1, 2), 0.25F);
	}

}
