package mods.defeatedcrow.event;

import mods.defeatedcrow.common.SilverHawkCore;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

public class AttackWithSwordEvent implements IForgeEvent{
	
	@ForgeSubscribe
	public void AttackEntityEvent(AttackEntityEvent event)
	{
		EntityPlayer player = event.entityPlayer;
		ItemStack current  = player.inventory.getCurrentItem();
		
		if (event.target instanceof EntityLivingBase)
		{
			EntityLivingBase target = (EntityLivingBase) event.target;
			
			if (!player.worldObj.isRemote)
			{
				if(target.isImmuneToFire() && current != null && current.itemID == SilverHawkCore.blueSword.itemID)
				{
					target.addPotionEffect(new PotionEffect(Potion.harm.id, 1, 1));
				}
			}
		}
	}

}
