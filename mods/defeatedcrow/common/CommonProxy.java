package mods.defeatedcrow.common;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;


public class CommonProxy implements IGuiHandler{
	
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
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		return null;
	}
 
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		return null;
	}

}
