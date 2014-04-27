package mods.defeatedcrow.plugin;

import ic2.api.recipe.RecipeInputItemStack;
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
        
        RecipeInputItemStack input3 = new RecipeInputItemStack(new ItemStack(SilverHawkCore.Ores, 1, 3), 1);
		NBTTagCompound metadata3 = new NBTTagCompound();
        metadata3.setInteger("macerater", 2000);
        ItemStack outputs3 = new ItemStack(SilverHawkCore.Fluorite, 2, 1);
        
        RecipeInputItemStack input4 = new RecipeInputItemStack(new ItemStack(SilverHawkCore.Fluorite, 1, 1), 1);
		NBTTagCompound metadata4 = new NBTTagCompound();
        metadata4.setInteger("macerater", 2000);
        ItemStack outputs4 = new ItemStack(SilverHawkCore.dustSilver, 2, 1);
	}

}
