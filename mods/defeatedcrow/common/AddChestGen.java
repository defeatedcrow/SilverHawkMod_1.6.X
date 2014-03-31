package mods.defeatedcrow.common;

import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class AddChestGen {
	
	public void addChestItems() {
		
		String[] categoryA = new String[]{"mineshaftCorridor", "pyramidDesertyChest", "pyramidJungleChest" };
		
		for (String category : categoryA)
		{
			ChestGenHooks dungeon = ChestGenHooks.getInfo(category);
			
			dungeon.addItem(new WeightedRandomChestContent(new ItemStack(SilverHawkCore.BlackFeather),1,8,10));
			dungeon.addItem(new WeightedRandomChestContent(new ItemStack(SilverHawkCore.Fluorite),1,8,20));
			dungeon.addItem(new WeightedRandomChestContent(new ItemStack(SilverHawkCore.ingotSilver),1,4,10));
		}
		
		ChestGenHooks dungeon = ChestGenHooks.getInfo("dungeonChest");
		
		dungeon.addItem(new WeightedRandomChestContent(new ItemStack(SilverHawkCore.BlackFeather),1,8,20));
	}
	
}
