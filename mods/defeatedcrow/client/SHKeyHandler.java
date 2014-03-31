package mods.defeatedcrow.client;
//
//import java.util.EnumSet;
//
//import mods.defeatedcrow.common.SHPacketHandler;
//import mods.defeatedcrow.common.SilverHawkCore;
//import mods.defeatedcrow.item.ItemArmorCrow;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.settings.KeyBinding;
//import net.minecraft.entity.player.EntityPlayer;
//import cpw.mods.fml.client.FMLClientHandler;
//import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
//import cpw.mods.fml.common.TickType;
//
//
//public class SHKeyHandler extends KeyHandler{
//	
//	public static boolean nowKeyDownA;
//	public static boolean nowKeyDownB;
//	
//	public SHKeyHandler(KeyBinding[] keyBindings, boolean[] repeatings) {
//		super(keyBindings, repeatings);
//	}
//
//	@Override
//	public String getLabel() {
//		return "SHKeyHandler";
//	}
//
//	@Override
//	public void keyDown(EnumSet<TickType> types, KeyBinding kb,
//			boolean tickEnd, boolean isRepeat) {
//		if (kb == SilverHawkCore.FlyKey) {
//			if ((FMLClientHandler.instance().getClient().running) && (isRepeat))
//		    {
//				if (!nowKeyDownA)
//				{
//					Minecraft mc = Minecraft.getMinecraft();
//					EntityPlayer player = mc.thePlayer;
//					if ((player != null) && (player.inventory.armorItemInSlot(1) != null))
//					{
//						if (player.inventory.armorItemInSlot(1).getItem() instanceof ItemArmorCrow) {
//							SHPacketHandler.getFlyKeyPacket(player, true);
//						}
//					}
//					nowKeyDownA = true;
//				}
//		    }
//		}
//		else if (kb == SilverHawkCore.SneakKey) {
//				if ((FMLClientHandler.instance().getClient().running) && (isRepeat))
//				{
//					if (!nowKeyDownB)
//					{
//						Minecraft mc = Minecraft.getMinecraft();
//						EntityPlayer player = mc.thePlayer;
//						if ((player != null) && (player.inventory.armorItemInSlot(1) != null))
//						{
//							if (player.inventory.armorItemInSlot(1).getItem() instanceof ItemArmorCrow) {
//								SHPacketHandler.getFlyKeyPacket(player, true);
//							}
//						}
//						nowKeyDownB = true;
//					}
//				}
//		}
//	}
//
//	@Override
//	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
//		if (kb == SilverHawkCore.FlyKey) {
//			if ((FMLClientHandler.instance().getClient().inGameHasFocus))
//		    {
//				Minecraft mc = Minecraft.getMinecraft();
//				EntityPlayer player = mc.thePlayer;
//				if ((player != null) && (player.inventory.armorItemInSlot(1) != null))
//				{
//					if (player.inventory.armorItemInSlot(1).getItem() instanceof ItemArmorCrow) {
//						SHPacketHandler.getFlyKeyPacket(player, false);
//					}
//				}
//				nowKeyDownA = false;
//		    }
//		}
//		else if (kb == SilverHawkCore.SneakKey) {
//				if ((FMLClientHandler.instance().getClient().inGameHasFocus))
//				{
//					Minecraft mc = Minecraft.getMinecraft();
//					EntityPlayer player = mc.thePlayer;
//					if ((player != null) && (player.inventory.armorItemInSlot(1) != null))
//					{
//						if (player.inventory.armorItemInSlot(1).getItem() instanceof ItemArmorCrow) {
//							SHPacketHandler.getFlyKeyPacket(player, false);
//						}
//					}
//					nowKeyDownB = false;
//				}
//		}
//	}
//
//	@Override
//	public EnumSet<TickType> ticks() {
//		return EnumSet.of(TickType.CLIENT);
//	}
//
//}
