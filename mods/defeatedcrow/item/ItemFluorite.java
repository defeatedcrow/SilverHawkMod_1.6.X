package mods.defeatedcrow.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.entity.projectile.*;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ItemFluorite extends Item{
	
	public ItemFluorite(int itemId)
	{
		super(itemId);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon("crowsdefeat:fluorite");
	}
	

    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	return par1ItemStack;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 72000;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.none;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {

    	par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        this.shootingBullet(par1ItemStack, par2World, par3EntityPlayer);

        return par1ItemStack;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)

    /**
     * used to cycle through icons based on their used duration, i.e. for the bow
     */
    public Icon getItemIconForUseDuration(int par1)
    {
        return this.itemIcon;
    }
    
    private void shootingBullet (ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	float f = 1.0F;

        //プレイヤーが撃つときのEntityArrowを生成。
    	if (par3EntityPlayer.isSneaking())
    	{
    		//EntitySmallWave wave = new EntitySmallWave(par2World, par3EntityPlayer, (EntityLivingBase)null, f * 1.5F, 1.0F, 0.0F, 0.0F);
    		EntityLargeWave wave = new EntityLargeWave(par2World, par3EntityPlayer, (EntityLivingBase)null, f * 1.5F, 1.0F, 0.0F, 0.0F);
    		EntityNormalGatling bullet1 = new EntityNormalGatling(par2World, par3EntityPlayer, (EntityLivingBase)null, f * 1.5F, 1.0F, 0.0F, 1.0F);
    		EntityNormalGatling bullet2 = new EntityNormalGatling(par2World, par3EntityPlayer, (EntityLivingBase)null, f * 1.5F, 1.0F, 0.0F, -1.0F);
    		
    		if (!par2World.isRemote)
            {
                par2World.spawnEntityInWorld(wave);
                par2World.spawnEntityInWorld(bullet1);
                par2World.spawnEntityInWorld(bullet2);
            }
    	}
    	else
    	{
    		EntityShortLaser laser = new EntityShortLaser(par2World, par3EntityPlayer, (EntityLivingBase)null, f * 2.5F, 1.0F, 0.0F, 0.0F);
    		
    		if (!par2World.isRemote)
            {
                par2World.spawnEntityInWorld(laser);
            }
    	}
    	
        par2World.playSoundAtEntity(par3EntityPlayer, "random.pop", 0.4F, 1.8F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

    }

}
