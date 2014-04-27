package mods.defeatedcrow.client;

import java.util.EnumSet;

import codechicken.lib.math.MathHelper;
import mods.defeatedcrow.common.SilverHawkCore;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class SHClientTickHandler implements ITickHandler{
	
	private final EnumSet<TickType> tickSet;
	
	private static boolean flyKeyDown = false;
	private static boolean sneakKeyDown = false;
	private int flyKeyTime = 0;
	
	public SHClientTickHandler(EnumSet<TickType> tickType)
	{
		this.tickSet = tickType;
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		
		if (player == null)
		{
			return;
		}
		
		int x = MathHelper.floor_double(player.posX);
		int y = MathHelper.floor_double(player.posY);
		int z = MathHelper.floor_double(player.posZ);
		float yaw = player.cameraYaw;
		float pitch = player.cameraPitch;
		
		if (SilverHawkCore.proxy.getFlyKeyDown() && !this.flyKeyDown)
		{
			this.flyKeyTime = 1;
			this.flyKeyDown = true;
		}
		
		if (SilverHawkCore.proxy.getFlyKeyDown() && this.flyKeyDown)
		{
			this.flyKeyTime++;
			if (this.flyKeyTime > 100)
			{
				this.flyKeyTime = 100;
			}
		}
		
		if (!SilverHawkCore.proxy.getFlyKeyDown() && this.flyKeyDown)
		{
			this.flyKeyTime = 0;
			this.flyKeyDown = false;
		}
		
		flyKeyDown = SilverHawkCore.proxy.getFlyKeyDown();
		sneakKeyDown = SilverHawkCore.proxy.getSneakKeyDown();
		
		
	}
	
	private void sendFlyKeyTime()
	{
		
	}
	
	private void sendFlyKey()
	{
		
	}
	
	private void sendSneakKey()
	{
		
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {}

	@Override
	public EnumSet<TickType> ticks()
	{
		return this.tickSet;
	}

	@Override
	public String getLabel()
	{
		return "SilverHawkClient";
	}

}
