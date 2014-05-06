package mods.defeatedcrow.block;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemClearOres extends ItemBlock{
	
	private static final String[] boxItemType = new String[] {"_coal", "_iron", "_gold", "_diamond", "_red", "_emerald", "_fluorite", "_silver", "_crocoite"};
	
	public ItemClearOres(int itemId)
	{
		super(itemId);
		setMaxDamage(0);
		setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		int m = (par1ItemStack.getItemDamage());
		if (m < 9) return super.getUnlocalizedName() + boxItemType[m];
		else return super.getUnlocalizedName() + m;
	}
	
	@Override
	public int getMetadata(int par1)
	{
		return par1;
	}

}
