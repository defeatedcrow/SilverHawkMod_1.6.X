package mods.defeatedcrow.plugin;

import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.Recipes;
import mods.defeatedcrow.common.SilverHawkCore;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class IC2Plugin {
	
	public void load()
	{
		RecipeInputItemStack input = new RecipeInputItemStack(new ItemStack(SilverHawkCore.Ores, 1, 0), 1);
		NBTTagCompound metadata = new NBTTagCompound();
        metadata.setInteger("macerater", 2000);
        ItemStack outputs = new ItemStack(SilverHawkCore.Fluorite, 2, 0);
        
        RecipeInputItemStack input2 = new RecipeInputItemStack(new ItemStack(SilverHawkCore.Ores, 1, 1), 1);
		NBTTagCompound metadata2 = new NBTTagCompound();
        metadata2.setInteger("macerater", 2000);
        ItemStack outputs2 = new ItemStack(SilverHawkCore.dustSilver, 2, 0);
        
        RecipeInputItemStack input3 = new RecipeInputItemStack(new ItemStack(SilverHawkCore.Ores, 1, 2), 1);
		NBTTagCompound metadata3 = new NBTTagCompound();
        metadata3.setInteger("macerater", 2000);
        ItemStack outputs3 = new ItemStack(SilverHawkCore.Fluorite, 2, 1);
        
        RecipeInputItemStack input4 = new RecipeInputItemStack(new ItemStack(SilverHawkCore.Fluorite, 1, 1), 1);
		NBTTagCompound metadata4 = new NBTTagCompound();
        metadata4.setInteger("macerater", 2000);
        ItemStack outputs4 = new ItemStack(SilverHawkCore.dustSilver, 2, 1);
        
        Recipes.macerator.addRecipe(input, metadata, outputs);
        //Recipes.macerator.addRecipe(input2, metadata2, outputs2);
        Recipes.macerator.addRecipe(input3, metadata2, outputs3);
        Recipes.macerator.addRecipe(input4, metadata4, outputs4);
	}

}
