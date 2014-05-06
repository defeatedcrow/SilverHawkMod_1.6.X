package mods.defeatedcrow.event;

import mods.defeatedcrow.common.SilverHawkCore;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class FallOnBedrockEvent implements IForgeEvent{
	
	/**追加したネオ岩盤に着地した際に落下ダメージを無効化するイベント。*/
	@ForgeSubscribe
	public void onFallEvent(LivingFallEvent event)
	{
		EntityLivingBase living = event.entityLiving;
		World world = living.worldObj;
		int x = (int) living.posX;
		int y = (int) living.posY - 1;
		int z = (int) living.posZ;
		
		if (living.onGround && world.getBlockId(x, y, z) == SilverHawkCore.NeoBedrock.blockID)
		{
			event.distance = 0.0F;
			event.setCanceled(true);
		}
		
	}

}
