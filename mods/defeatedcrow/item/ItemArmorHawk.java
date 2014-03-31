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

public class ItemArmorHawk extends ItemArmor{
	
	public static ArmorProperties hawkArmorProp;
	private int playerX;
	private static final String[] armorPartHK = new String[] {"helmet","suits","leggins"};
	
	//for fling
	private boolean usedHKPlate = false;
	private boolean allowUseHKPlate = false;
	private int HKflyToggleTimer = 0;
	private int sprintToggleTimer = 0;
	boolean flyKey = false;
	private boolean lightCheck = false;
	
				
	public ItemArmorHawk(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
	}
		
	@Override
	public void onCreated(ItemStack par1Itemstack, World par2World, EntityPlayer par3EntityPlayer)
	{
		super.onCreated(par1Itemstack, par2World, par3EntityPlayer);
		if(par1Itemstack.itemID == SilverHawkCore.armHKMet.itemID)
		{
			par1Itemstack.addEnchantment(Enchantment.projectileProtection, 1);
		}
	}
	
	public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack)
    {
		int x = (int) player.posX;
		int y = (int) player.posY + 1;
		int z = (int) player.posZ;
		int lightValue = world.getBlockLightValue(x, y, z);
		
		if (player != null)
		{
			if ((itemStack.itemID == SilverHawkCore.armHKPlate.itemID)) {
				player.fallDistance = 0.0f;
			}
		}
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon("crowsdefeat:hawk" + armorPartHK[this.armorType]);
	}
	
	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, int layer) {
		
		int armorId = itemstack.itemID;
		
		if(armorId == SilverHawkCore.armHKLegs.itemID)
			return "crowsdefeat:textures/armor/hawkarmor2.png";
		
		return "crowsdefeat:textures/armor/hawkarmor1.png";
	}

}
