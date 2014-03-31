package mods.defeatedcrow.common;

//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//
//import com.google.common.io.ByteArrayDataInput;
//import com.google.common.io.ByteStreams;
//
//import mods.defeatedcrow.common.SilverHawkCore;
//import mods.defeatedcrow.item.ItemArmorCrow;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.ItemStack;
//import net.minecraft.network.INetworkManager;
//import net.minecraft.network.packet.Packet;
//import net.minecraft.network.packet.Packet250CustomPayload;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.world.World;
//import cpw.mods.fml.client.FMLClientHandler;
//import cpw.mods.fml.common.network.IPacketHandler;
//import cpw.mods.fml.common.network.Player;
//
//public class SHPacketHandler implements IPacketHandler
//{
//        @Override
//        public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
//        {
//            if (packet.channel.equals("FlyKey")) {
//    			ByteArrayDataInput bis = ByteStreams.newDataInput(packet.data);
//    			
//                boolean keydown = bis.readBoolean();
//                
//                World world = SilverHawkCore.proxy.getClientWorld();
//				if (world == null) {
//					world = ((EntityPlayer) player).worldObj;
//				}
//				
//				if (player != null && (player instanceof EntityPlayer) 
//						&& ((EntityPlayer)player).inventory.armorItemInSlot(1) != null) {
//					if ((((EntityPlayer)player).inventory.armorItemInSlot(1).getItem() instanceof ItemArmorCrow)) {
//						ItemArmorCrow.setFlyKey(keydown);
//					}
//				}
//
//                
//            }
//            else if (packet.channel.equals("SneakKey")) {
//    			ByteArrayDataInput bis = ByteStreams.newDataInput(packet.data);
//    			
//    			boolean keydown = bis.readBoolean();
//                
//                World world = SilverHawkCore.proxy.getClientWorld();
//				if (world == null) {
//					world = ((EntityPlayer) player).worldObj;
//				}
//				
//				if (player != null && (player instanceof EntityPlayer) 
//						&& ((EntityPlayer)player).inventory.armorItemInSlot(1) != null) {
//					if ((((EntityPlayer)player).inventory.armorItemInSlot(1).getItem() instanceof ItemArmorCrow)) {
//						ItemArmorCrow.setFlyKey(keydown);
//					}
//				}
//
//                
//            } 
//        }
//
//        public static Packet getFlyKeyPacket(EntityPlayer player, boolean key)
//        {
//                ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                DataOutputStream dos = new DataOutputStream(bos);
//
//                boolean keydown = false;
//
//                try
//                {
//                        dos.writeBoolean(key);
//                }
//                catch(Exception e)
//                {
//                        e.printStackTrace();
//                }
//
//                Packet250CustomPayload packet = new Packet250CustomPayload();
//                packet.channel = "FlyKey";
//                packet.data = bos.toByteArray();
//                packet.length = bos.size();
//                packet.isChunkDataPacket = true;
//
//                return packet;
//        }
//        
//        public static Packet getSneakKeyPacket(EntityPlayer player, boolean key)
//        {
//        	ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            DataOutputStream dos = new DataOutputStream(bos);
//
//            boolean keydown = false;
//
//            try
//            {
//                    dos.writeBoolean(key);
//            }
//            catch(Exception e)
//            {
//                    e.printStackTrace();
//            }
//
//            Packet250CustomPayload packet = new Packet250CustomPayload();
//            packet.channel = "SneakKey";
//            packet.data = bos.toByteArray();
//            packet.length = bos.size();
//            packet.isChunkDataPacket = true;
//
//            return packet;
//        }
//
//}
