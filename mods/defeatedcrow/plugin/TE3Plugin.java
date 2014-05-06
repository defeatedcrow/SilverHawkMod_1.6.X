package mods.defeatedcrow.plugin;

import cpw.mods.fml.common.event.FMLInterModComms;
import mods.defeatedcrow.common.SilverHawkCore;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TE3Plugin {
	
	public void load()
	{
		NBTTagCompound toSend = new NBTTagCompound();
		toSend.setInteger("energy", 3200);
		toSend.setCompoundTag("input", new NBTTagCompound());
		toSend.setCompoundTag("primaryOutput", new NBTTagCompound());
		toSend.setCompoundTag("secondaryOutput", new NBTTagCompound());

		new ItemStack(SilverHawkCore.Ores, 1, 0).writeToNBT(toSend.getCompoundTag("input"));
		new ItemStack(SilverHawkCore.Fluorite, 2, 0).writeToNBT(toSend.getCompoundTag("primaryOutput"));
		new ItemStack(SilverHawkCore.Fluorite, 1, 0).writeToNBT(toSend.getCompoundTag("secondaryOutput"));
		toSend.setInteger("secondaryChance", 50);
		FMLInterModComms.sendMessage("ThermalExpansion", "PulverizerRecipe", toSend);
		
		NBTTagCompound toSend2 = new NBTTagCompound();
		toSend2.setInteger("energy", 3200);
		toSend2.setCompoundTag("input", new NBTTagCompound());
		toSend2.setCompoundTag("primaryOutput", new NBTTagCompound());
		toSend2.setCompoundTag("secondaryOutput", new NBTTagCompound());

		new ItemStack(SilverHawkCore.Ores, 1, 2).writeToNBT(toSend2.getCompoundTag("input"));
		new ItemStack(SilverHawkCore.Fluorite, 2, 1).writeToNBT(toSend2.getCompoundTag("primaryOutput"));
		new ItemStack(SilverHawkCore.Fluorite, 1, 1).writeToNBT(toSend2.getCompoundTag("secondaryOutput"));
		toSend2.setInteger("secondaryChance", 50);
		FMLInterModComms.sendMessage("ThermalExpansion", "PulverizerRecipe", toSend2);
		
		NBTTagCompound toSend3 = new NBTTagCompound();
		toSend3.setInteger("energy", 3200);
		toSend3.setCompoundTag("input", new NBTTagCompound());
		toSend3.setCompoundTag("primaryOutput", new NBTTagCompound());
		toSend3.setCompoundTag("secondaryOutput", new NBTTagCompound());

		new ItemStack(SilverHawkCore.Fluorite, 1, 1).writeToNBT(toSend3.getCompoundTag("input"));
		new ItemStack(SilverHawkCore.dustSilver, 2, 1).writeToNBT(toSend3.getCompoundTag("primaryOutput"));
		new ItemStack(SilverHawkCore.dustSilver, 1, 2).writeToNBT(toSend3.getCompoundTag("secondaryOutput"));
		toSend3.setInteger("secondaryChance", 25);
		FMLInterModComms.sendMessage("ThermalExpansion", "PulverizerRecipe", toSend3);
	}

}
