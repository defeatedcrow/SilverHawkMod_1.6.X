package mods.defeatedcrow.common;

import net.minecraft.creativetab.CreativeTabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.src.*;
import net.minecraft.creativetab.CreativeTabs;

public class CreativeTabCrow extends CreativeTabs {
	
	public CreativeTabCrow(String type)
	{
		super(type);
	}
 
	@Override
	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex()
	{
		return SilverHawkCore.BlackFeather.itemID;
	}
 
	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel()
	{
		return "SilverHawkMod";
	}
	

}
