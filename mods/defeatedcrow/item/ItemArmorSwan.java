package mods.defeatedcrow.item;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.common.SilverHawkCore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class ItemArmorSwan extends ItemArmor{
	
	public static ArmorProperties swanArmorProp;
	private static final String[] armorPartSW = new String[] {"helmet","suits","leggins","boots"};
					
	public ItemArmorSwan(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
		
	}
		
	@Override
	public void onCreated(ItemStack par1Itemstack, World par2World, EntityPlayer par3EntityPlayer)
	{
		super.onCreated(par1Itemstack, par2World, par3EntityPlayer);
		if(par1Itemstack.itemID == SilverHawkCore.armSWMet.itemID)
		{
			par1Itemstack.addEnchantment(Enchantment.aquaAffinity, 1);
		}
	}
		
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon("crowsdefeat:swan" + armorPartSW[this.armorType]);
	}
	
	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, int layer) {
		
		int armorId = itemstack.itemID;
		
		if(armorId == SilverHawkCore.armSWLegs.itemID)
			return "crowsdefeat:textures/armor/swanarmor2.png";
		
		return "crowsdefeat:textures/armor/swanarmor1.png";
	}

}
