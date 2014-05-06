package mods.defeatedcrow.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import mods.defeatedcrow.common.SilverHawkCore;
import mods.defeatedcrow.entity.EntitySilverHawk;
import mods.defeatedcrow.entity.projectile.*;
import mods.defeatedcrow.item.ItemArmorCrow;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class SHPacketHandler implements IPacketHandler
{
	private Random rand;
	
        @Override
        public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
        {
            if (packet.channel.equals("FlyKey")) {
    			ByteArrayDataInput bis = ByteStreams.newDataInput(packet.data);
    			
    			int xCood = bis.readInt();
    			int yCood = bis.readInt();
    			int zCood = bis.readInt();
    			byte type = bis.readByte();
                
                boolean isServer = false;
                EntityPlayerMP thisPlayer = (EntityPlayerMP) player;
                World world = thisPlayer.worldObj;
				
				if (world != null)
				{
					switch (type)
					{
					case 0://白玉
						EntityNormalGatling ball = new EntityNormalGatling(world, thisPlayer, (EntityLivingBase)null, 2.5F, 1.0F, 0.0F, 0.0F, -1.0F);
						world.spawnEntityInWorld(ball);
						world.playSoundEffect(thisPlayer.posX + 0.5D, thisPlayer.posY - 0.5D, thisPlayer.posZ + 0.5D, "random.pop", 1.0F, 1.8F);
						break;
					case 1://レーザー
						EntityShortLaser laser = new EntityShortLaser(world, thisPlayer, (EntityLivingBase)null, 2.5F, 1.0F, 0.0F, 0.0F, -1.0F);
						world.spawnEntityInWorld(laser);
						world.playSoundEffect(thisPlayer.posX + 0.5D, thisPlayer.posY - 0.5D, thisPlayer.posZ + 0.5D, "random.pop", 1.0F, 1.8F);
						break;
					case 2://緑ウェーブ
						EntitySmallWave wave = new EntitySmallWave(world, thisPlayer, (EntityLivingBase)null, 2.5F, 1.0F, 0.0F, 0.0F, -1.0F);
						world.spawnEntityInWorld(wave);
						world.playSoundEffect(thisPlayer.posX + 0.5D, thisPlayer.posY - 0.5D, thisPlayer.posZ + 0.5D, "random.pop", 1.0F, 1.8F);
						break;
					case 3://赤ウェーブ＋白玉
						EntityLargeWave largeWave = new EntityLargeWave(world, thisPlayer, (EntityLivingBase)null, 2.5F, 1.0F, 0.0F, 0.0F, -1.0F);
						EntityNormalGatling ball2 = new EntityNormalGatling(world, thisPlayer, (EntityLivingBase)null, 2.5F, 1.0F, 1.0F, 0.0F, -1.0F);
						EntityNormalGatling ball3 = new EntityNormalGatling(world, thisPlayer, (EntityLivingBase)null, 2.5F, 1.0F, -1.0F, 0.0F, -1.0F);
						world.spawnEntityInWorld(largeWave);
						world.spawnEntityInWorld(ball2);
						world.spawnEntityInWorld(ball3);
						world.playSoundEffect(thisPlayer.posX + 0.5D, thisPlayer.posY - 0.5D, thisPlayer.posZ + 0.5D, "random.pop", 1.0F, 1.8F);
						break;
					case 4:
						break;
					case 5:
						break;
					case 6:
						break;
					case 7:
						break;
					default:
						break;
					}
				}

            }
        }

}
