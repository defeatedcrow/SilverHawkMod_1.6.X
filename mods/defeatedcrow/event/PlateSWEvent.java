package mods.defeatedcrow.event;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.common.SHPacketHandler;
import mods.defeatedcrow.common.SilverHawkCore;
import mods.defeatedcrow.entity.EnumFlighterType;
import mods.defeatedcrow.entity.IEntityFlighter;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

/**「白鳥装備のイベント」というクラス名なのに、その実プレイヤー監視系イベントを全て詰め込んである*/
public class PlateSWEvent implements IForgeEvent {
	
	private static boolean flyKey;
	private static boolean sneakKey;
	private int flyKeyTime = 0;
	
	@ForgeSubscribe
	  public void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
	  {
		Entity entity = event.entity;
		
		/**プレイヤーのサイドを問わない処理
		 * その1：装備品によりプレイヤーにポーション効果を与えるもの
		 * その2：騎乗中のモブにプレイヤーのポーション効果を同期させる処理
		 * （これにより、自分だけ死んだりなどはしにくくなるはず。）
		 * ただし、負の効果も同期されるのでウィザーなどは注意が必要。*/
		if (entity != null && (entity instanceof EntityPlayer))
		{
			EntityPlayer player = (EntityPlayer)event.entity;
			
			if(player != null)
			{
				//1：装備品のチェック
				InventoryPlayer inventory = player.inventory;
				//白鳥装備
				if ((inventory.armorInventory[3] != null) && (inventory.armorInventory[3].itemID == SilverHawkCore.armSWMet.itemID))
				{
					if ((player.isInWater()) && (!player.capabilities.isCreativeMode))
					{
						if(!player.isPotionActive(Potion.waterBreathing))
						{
							player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 200, 0));
						}
					}
				}
				
				//2：騎乗モブのチェック
				if (player.ridingEntity != null && player.ridingEntity instanceof EntityLivingBase)
				{
					EntityLivingBase living = (EntityLivingBase) player.ridingEntity;
					
					//騎乗モブにプレイヤーのポーション効果を与える
					Collection potionList = player.getActivePotionEffects();
					if (!potionList.isEmpty())
					{
						Iterator iterator = potionList.iterator();

			            while (iterator.hasNext())
			            {
			                PotionEffect potioneffect = (PotionEffect)iterator.next();
			                if (!living.isPotionActive(potioneffect.getPotionID()))
			                {
			                	living.addPotionEffect(potioneffect);
			                }
			            }
					}
				}
				
			}
		}
		
		/**クライアント限定処理。主にキー入力に対応するもの。
		 * その1：特定の装備品を着ている時にプレイヤー自身の動作を操作。多分クライアント側だけ動かせば動くっぽい
		 * その2：エンティティ（弾）を発生させる。同期が必要なのでパケットを送る*/
		if (entity != null && (entity instanceof EntityPlayer) && entity.worldObj.isRemote)
		{
			EntityPlayerSP playerSP = (EntityPlayerSP)event.entity;
			InventoryPlayer inventorySP = playerSP.inventory;
			boolean criative = playerSP.capabilities.isCreativeMode;
			
			if (playerSP.ridingEntity != null && playerSP.ridingEntity instanceof IEntityFlighter)
			{
				IEntityFlighter flighter = (IEntityFlighter) playerSP.ridingEntity;
				EnumFlighterType type = flighter.thisType();
				
				if (SilverHawkCore.proxy.getFlyKeyDown() && !flyKey)
			    {
			        this.flyKeyTime = 1;
			    }
				
			    if (this.flyKeyTime > 0)
			    {
			        this.flyKeyTime += 1;
			        
			        if (this.flyKeyTime > 100)
			    	{
			          this.flyKeyTime = 100;
			        }
			    }
			    
			    if (!SilverHawkCore.proxy.getFlyKeyDown() && flyKey)
			    {
			    	if (type != null && flyKey)
			    	{
							int x = (int) playerSP.posX;
							int y = (int) playerSP.posY;
							int z = (int) playerSP.posZ;
							byte projType = 0;
							
							playerSP.capabilities.allowFlying = false;
							
							switch (type)
							{
							case EARL_GRAY:
								projType = 0;
								break;
							case FIRE_LEO:
								projType = 0;
								break;
							case KERBEROS:
								projType = 0;
								break;
							case SHILVER_CHICKEN:
								projType = 0;
								break;
							case SILVER_HAWK:
								projType = (byte) ((playerSP.experienceLevel > 39 || criative) ? 3 : (playerSP.experienceLevel / 10));
								break;
							case UNKNOUN:
								projType = 0;
								break;
							case VIPER:
								projType = 0;
								break;
							default:
								break;
							}
							
							this.sendProj(x, y, z, projType, this.flyKeyTime);
							this.flyKeyTime = 0;
					}
			    }
			}
			else //対象のモブに乗っていない
			{
				
				if((playerSP.isInWater()) && (inventorySP.armorInventory[2] != null) && (inventorySP.armorInventory[2].itemID == SilverHawkCore.armSWPlate.itemID))
				{
					this.swim(playerSP);
				}
				else if((inventorySP.armorInventory[2] != null) && (inventorySP.armorInventory[2].itemID == SilverHawkCore.armHKPlate.itemID))
				{
					int x = (int) playerSP.posX;
					int y = (int) (playerSP.posY + 1);
					int z = (int) playerSP.posZ;
					int lightValue = entity.worldObj.getBlockLightValue(x, y, z);
					
					if (lightValue >= 5) {
						Flight(playerSP);
					}
				}
			}
			
			this.flyKey = SilverHawkCore.proxy.getFlyKeyDown();
			this.sneakKey = SilverHawkCore.proxy.getSneakKeyDown();
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

		if (allowedFly && (this.flyKey || !player.onGround))
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
           if (this.flyKey)
           {
              player.motionY += 0.15f;
           }
           
           if (this.sneakKey)
           {
        	   player.motionY = 0.0f;
           }

        }
        else
           player.jumpMovementFactor = 0.02f;
	}
	
	@SideOnly(Side.CLIENT)
	private void sendProj(int x, int y, int z, byte projType, int time)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(bos);
		
		int xCood = x;
		int yCood = y;
		int zCood = z;
		byte type = projType;
		
		try
		{
			data.writeInt(xCood);
			data.writeInt(yCood);
			data.writeInt(zCood);
			data.writeByte(type);
		}
		catch (IOException e){}
		
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "FlyKey";
        packet.data = bos.toByteArray();
        packet.length = bos.size();
        packet.isChunkDataPacket = true;
		PacketDispatcher.sendPacketToServer(packet);
	}
}
