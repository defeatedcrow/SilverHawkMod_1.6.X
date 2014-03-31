package mods.defeatedcrow.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;


public class CommonProxy {
	
	public int addArmor(String armor)
	{
		return 0;
	}
	
	public int getRenderID()
	{
		return -1;
	}
	
	public void registerRenderers()
	{
		
	}

	public World getClientWorld() {
		
		return null;
	}
	
	public boolean getFlyKeyDown() {
		return false;
	}
	
	public boolean getSneakKeyDown() {
		return false;
	}

}
