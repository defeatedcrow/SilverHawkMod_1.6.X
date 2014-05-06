package mods.defeatedcrow.client;

import org.lwjgl.input.Keyboard;

import net.minecraft.world.World;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.client.particle.SHParticleTex;
import mods.defeatedcrow.client.projectile.*;
import mods.defeatedcrow.common.CommonProxy;
import mods.defeatedcrow.common.SHPacketHandler;
import mods.defeatedcrow.common.SilverHawkCore;
import mods.defeatedcrow.entity.*;
import mods.defeatedcrow.entity.projectile.*;
import mods.defeatedcrow.item.*;


@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	
	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}
	
	@Override
	public int getRenderID()
	{
		return RenderingRegistry.getNextAvailableRenderId();
	}
	
	@Override
	public int addArmor(String armor)
	{
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}

	@Override
	public void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityCrow.class, new RenderCBcrow(new ModelCBcrow(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntitySilverHawk.class, new RenderHawk(new ModelSilverHawk(), 0.8F));
		RenderingRegistry.registerEntityRenderingHandler(EntitySilverChicken.class, new RenderSilverChicken(new ModelSilverChicken(), 0.8F));
		
		RenderingRegistry.registerBlockHandler(new RenderCDLBlock());
		RenderingRegistry.registerBlockHandler(new RenderInvisible());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityNormalGatling.class, new RenderSimpleBullet(new ModelSimpleBullet()));
		RenderingRegistry.registerEntityRenderingHandler(EntityShortLaser.class, new RenderShortLaser(new ModelShortLaser()));
		RenderingRegistry.registerEntityRenderingHandler(EntitySmallWave.class, new RenderSmallWave(new ModelSmallWave()));
		RenderingRegistry.registerEntityRenderingHandler(EntityLargeWave.class, new RenderLargeWave(new ModelLargeWave()));
		
		MinecraftForge.EVENT_BUS.register(new SHParticleTex());
	}
	
	@Override
	public boolean getFlyKeyDown() {
		return Keyboard.isKeyDown(SilverHawkCore.cfgFlyKeySet);
		
	}

	@Override
	public boolean getSneakKeyDown() {
		return Keyboard.isKeyDown(SilverHawkCore.cfgSneakKeySet);
		
	}
}
