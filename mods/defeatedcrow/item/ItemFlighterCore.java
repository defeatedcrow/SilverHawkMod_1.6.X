package mods.defeatedcrow.item;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import org.bouncycastle.util.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.entity.EntityCrow;
import mods.defeatedcrow.entity.EnumFlighterType;
import mods.defeatedcrow.entity.IEntityFlighter;
import mods.defeatedcrow.util.EvolutionList;
import mods.defeatedcrow.util.SHLogger;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.Facing;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemFlighterCore extends Item{
	
	@SideOnly(Side.CLIENT)
    private Icon iconType[];
	
	private static final String[] iconName = new String[] {"blackholecore"};
	
	public ItemFlighterCore (int itemId)
	{
		super(itemId);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		
	}
	
	@Override
    public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity)
    {
        if (entity.worldObj.isRemote)
        {
            return true;
        }
        else
        {
        	if(evolutionEntity(player, entity, entity.worldObj, (int)entity.posX, (int)entity.posY, (int)entity.posZ, itemstack))
        	{
        		if (!player.capabilities.isCreativeMode)
                {
                    --itemstack.stackSize;
                }
        	}
        	return true;
        }

    }
	
	public static boolean evolutionEntity(EntityPlayer player, EntityLivingBase entity, World world, int par2, int par3, int par4, ItemStack use)
    {
		boolean flag = false;
		EntityLiving target = null;
		
		if (entity != null && use != null)
		{
			int evolID = EvolutionList.getID(new ItemStack(use.itemID, 1, use.getItemDamage()));
            String before = EvolutionList.getBefore(evolID, world);
            target = (EntityLiving) EvolutionList.getAfter(evolID, world);
            
            SHLogger.debugInfo("target : " + target.entityId);
        	SHLogger.debugInfo("target name : " + target.getEntityName());
        	SHLogger.debugInfo("before : " + before);
            
            if (entity.getEntityName().equalsIgnoreCase(before) && target != null)
            {
            	target.setLocationAndAngles(par2, par3, par4, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
                target.rotationYawHead = entity.rotationYaw;
                target.renderYawOffset = entity.rotationYaw;
                world.spawnEntityInWorld(target);
                
                if (target instanceof EntityTameable)
                {
                	((EntityTameable) target).setTamed(true);
                	((EntityTameable) target).setOwner(player.getDisplayName());
                }
                
                target.playLivingSound();
                
                entity.setDead();
                
                flag = true;
            }
		}

        return flag;
    }
	
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1)
    {
        int j = MathHelper.clamp_int(par1, 0, 1);
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
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
    {
        this.iconType = new Icon[1];

        for (int i = 0; i < 1; ++i)
        {
            this.iconType[i] = par1IconRegister.registerIcon("crowsdefeat:" + iconName[i]);
        }
    }

	public static EnumFlighterType type(int meta) {
		return EnumFlighterType.SILVER_HAWK;
	}

}
