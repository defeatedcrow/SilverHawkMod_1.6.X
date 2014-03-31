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
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.EnumSkyBlock;
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

public class ItemArmorCrow extends ItemArmor implements ISpecialArmor{
	
	public static ArmorProperties crowArmorProp;
	private int playerX;
	private static final String[] armorPartBF = new String[] {"helmet","suits","leggins","boots"};
	
	//for fling
	private boolean usedBFPlate = false;
	private boolean allowUseBFPlate = false;
	boolean flyKey = false;
	
	public static boolean fliyng = false;
	
				
	public ItemArmorCrow(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
		
	}
	
	@Override
	public ArmorProperties getProperties(EntityLivingBase player,
			ItemStack armor, DamageSource source, double damage, int slot) {
		
		if ((source == DamageSource.fall) && (this.armorType == 3))
		{
			return new ISpecialArmor.ArmorProperties(10, 1.0D, 2147483647);
		}
		else
		{
			return new ISpecialArmor.ArmorProperties(0, 1.0D, (armor.getItemDamage()/4) + 1);
		}
	}
	
	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {

		return 0;
	}
	
	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack,
			DamageSource source, int damage, int slot) {
		
		int armDamage = damage;
		stack.damageItem(armDamage, entity);
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon("crowsdefeat:crow" + armorPartBF[this.armorType]);
	}
	
	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, int layer) {
		
		int armorId = itemstack.itemID;
		
		if(armorId == SilverHawkCore.armBFLegs.itemID)
			return "crowsdefeat:textures/armor/crowarmor2.png";
		
		return "crowsdefeat:textures/armor/crowarmor1.png";
	}

}
