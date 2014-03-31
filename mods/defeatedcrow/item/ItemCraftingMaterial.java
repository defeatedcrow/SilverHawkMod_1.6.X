package mods.defeatedcrow.item;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import org.bouncycastle.util.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;

public class ItemCraftingMaterial extends Item{
	
	@SideOnly(Side.CLIENT)
    private Icon iconType[];
	
	private static final String[] iconName = new String[] {"blackegg", "hawkeye", "freezingeye", "ingotsilver"};
	
	public ItemCraftingMaterial (int itemId)
	{
		super(itemId);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		
	}
	
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1)
    {
        int j = MathHelper.clamp_int(par1, 0, 4);
        return this.iconType[j];
    }

	@Override
	public int getMetadata(int par1) {
		return par1;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return super.getUnlocalizedName() + "_" + this.iconName[par1ItemStack.getItemDamage()];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(this, 1, 0));
		par3List.add(new ItemStack(this, 1, 1));
		par3List.add(new ItemStack(this, 1, 2));
		par3List.add(new ItemStack(this, 1, 3));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
    {
        this.iconType = new Icon[4];

        for (int i = 0; i < 4; ++i)
        {
            this.iconType[i] = par1IconRegister.registerIcon("crowsdefeat:" + iconName[i]);
        }
    }

}
