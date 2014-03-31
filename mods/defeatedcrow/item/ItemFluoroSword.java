package mods.defeatedcrow.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSword;

public class ItemFluoroSword extends ItemSword{
	
	private int damage;
	
	public ItemFluoroSword (int par1, EnumToolMaterial par2){
		super (par1, par2);

		this.setMaxStackSize(1);
		this.setMaxDamage(192);
		this.damage = 18;
	}
	
	public int getDamageVsEntity(Entity par1Entity)
    {
        return this.damage;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon("crowsdefeat:crowswakizasi");
	}

}
