package mods.defeatedcrow.event;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.common.SilverHawkCore;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class PlateSWEvent implements IForgeEvent {
	
	@ForgeSubscribe
	  public void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
	  {
		Entity entity = event.entity;
		
		if (entity != null && (entity instanceof EntityPlayer))
		{
			EntityPlayer player = (EntityPlayer)event.entity;
			
			if(player != null)
			{
				InventoryPlayer inventory = player.inventory;
				
				if ((player.isInWater()) && (!player.capabilities.isCreativeMode))
				{
					if ((inventory.armorInventory[3] != null) && (inventory.armorInventory[3].itemID == SilverHawkCore.armSWMet.itemID))
					{
						if(!player.isPotionActive(Potion.waterBreathing))
						{
							player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 200, 0));
						}
					}
				}
			}
		}
		
		if (entity != null && (entity instanceof EntityPlayer) && entity.worldObj.isRemote)
		{
			EntityPlayerSP playerSP = (EntityPlayerSP)event.entity;
			InventoryPlayer inventorySP = playerSP.inventory;
			
			if((playerSP.isInWater()) && (inventorySP.armorInventory[2] != null) && (inventorySP.armorInventory[2].itemID == SilverHawkCore.armSWPlate.itemID))
			{
				this.swim(playerSP);
			}
			else if((inventorySP.armorInventory[2] != null) && (inventorySP.armorInventory[2].itemID == SilverHawkCore.armHKPlate.itemID))
			{
				int x = (int) playerSP.posX;
				int y = (int) playerSP.posY + 1;
				int z = (int) playerSP.posZ;
				int lightValue = entity.worldObj.getBlockLightValue(x, y, z);
				
				if (lightValue >= 5) {
					Flight(playerSP);
				}
			}
			
		}
	  }
	
	@SideOnly(Side.CLIENT)
	private void swim(EntityPlayerSP player)
	{
		boolean allowUseSW = true;
		if (player.capabilities.isCreativeMode)
		{
			allowUseSW = false;
		}
		
		if(allowUseSW)
		{
			player.fallDistance = 0.0F;
			player.motionY += 0.035F;
			float swimSpeed = 0.075F;
			boolean swim = SilverHawkCore.proxy.getSneakKeyDown();
			
			if ((swim) && !(player.onGround))
			{
				player.motionX += (double)(-MathHelper.sin((player.rotationYaw) * (float)Math.PI / 180.0F) * swimSpeed);
	            player.motionZ += (double)(MathHelper.cos((player.rotationYaw) * (float)Math.PI / 180.0F) * swimSpeed);
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void Flight(EntityPlayerSP player)
	{
		boolean useHKPlate = true;
		boolean allowedFly = false;
		
		if(player.capabilities.isCreativeMode)
        {
			allowedFly = false;
			return;
        }

		player.fallDistance = 0.0f;
		boolean flyKey = SilverHawkCore.proxy.getFlyKeyDown();

		if (allowedFly && (flyKey || !player.onGround))
        {
			useHKPlate = true;
        }

		if (player.onGround && useHKPlate)
        {
           useHKPlate = false;
        }
		
		if (useHKPlate)
        {
           player.jumpMovementFactor = 0.06f;
           if (flyKey)
           {
              player.motionY += 0.15f;
           }

        }
        else
           player.jumpMovementFactor = 0.02f;
	}
	
}
