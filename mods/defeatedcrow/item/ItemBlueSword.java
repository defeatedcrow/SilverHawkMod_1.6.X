package mods.defeatedcrow.item;

import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.entity.projectile.EntityLargeWave;
import mods.defeatedcrow.entity.projectile.EntityNormalGatling;
import mods.defeatedcrow.entity.projectile.EntityShortLaser;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemBlueSword extends ItemSword{
	
	private float damage;
	
	public ItemBlueSword (int par1, EnumToolMaterial par2){
		super (par1, par2);

		this.setMaxStackSize(1);
		this.setMaxDamage(192);
		this.damage = 7.0F;
	}
	
	public float getDamageVsEntity(Entity par1Entity, ItemStack par2ItemStack)
    {
        int dam = (int) this.damage;
        
        if (par1Entity != null && par1Entity instanceof EntityLivingBase)
        {
        	EntityLivingBase target = (EntityLivingBase) par1Entity;
        	if (target.isImmuneToFire())
        	{
        		dam *= 2;
        	}
        }
		
		return dam;
    }
	
	@Override
	public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double)this.damage, 0));
        return multimap;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon("crowsdefeat:deepbluesword");
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
        return EnumAction.block;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {

    	par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        boolean flag = par3EntityPlayer.capabilities.isCreativeMode;
    	if (flag)
    	{
    		this.rain(par1ItemStack, par2World, par3EntityPlayer);
    	}
    	else
    	{
    		//経験値の減少
    		int exp = par3EntityPlayer.experienceTotal;
    		int level = par3EntityPlayer.experienceLevel;
        	if (exp > 2 || level > 0)
        	{
        		this.rain(par1ItemStack, par2World, par3EntityPlayer);
        		this.reduceEXP(par3EntityPlayer);
        	}
    	}
        
        return par1ItemStack;
    }
    
    private void reduceEXP(EntityPlayer player)
    {
    	int exp = player.experienceTotal;
    	int expBar = player.experienceLevel;
    	
    	player.experience -= 2.0F / (float)player.xpBarCap();
    	
    	if (player.experience < 0.0F)
    	{
    		player.experience = 1.0F;
    		player.addExperienceLevel(-1);
    	}
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
    
    private void rain(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {

        //天候をトグルで変える。
    	if (par3EntityPlayer.isSneaking())
    	{
    		if (!par2World.isRemote && !par2World.provider.isHellWorld)
            {
                if (par2World.rainingStrength > 0)
                {
                	par2World.provider.resetRainAndThunder();
                }
                else
                {
                	par2World.provider.toggleRain();
                }
    			
            }
    	}

    }

}
