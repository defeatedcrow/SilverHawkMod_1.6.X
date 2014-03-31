package mods.defeatedcrow.client;

import java.io.File;

import mods.defeatedcrow.entity.EntitySilverChicken;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderSilverChicken extends RenderLiving {
	
	private static final String dir1 = Minecraft.getMinecraft().toString();
	
	private static final ResourceLocation textures = new ResourceLocation("crowsdefeat:textures/entity/silverchicken.png");
	private static final ResourceLocation tamedTextures = new ResourceLocation("crowsdefeat:textures/entity/silverchicken.png");

	public RenderSilverChicken(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		
	}
	
	public void renderSilverChicken(EntitySilverChicken entity, double d, double d1, double d2, float f, float f1) {
        if(((EntitySilverChicken)entity).isSitting())
        {
        	d1 -= 0.15D;
        }
        super.doRenderLiving(entity, d, d1, d2, f, f1);
    }
	
	protected ResourceLocation getCrowTextures(EntitySilverChicken par1Entitycrow)
    {
        return par1Entitycrow.isTamed() ? tamedTextures : textures;
    }
	
	protected float getWingRotation(EntitySilverChicken par1Entity, float par2)
    {
        float f1 = par1Entity.wingB + (par1Entity.wingA - par1Entity.wingB) * par2;
        float f2 = par1Entity.wingC + (par1Entity.dPos - par1Entity.wingC) * par2;
        return (MathHelper.sin(f1) + 1.0F) * f2;
    }
	
	protected float handleRotationFloat(EntityLiving par1EntityLiving, float par2)
    {
        return this.getWingRotation((EntitySilverChicken)par1EntityLiving, par2);
    }
	
	public void doRenderLiving(EntityLiving entityliving, double d, double d1, double d2, float f, float f1) {
        renderSilverChicken((EntitySilverChicken)entityliving, d, d1, d2, f, f1);
    }
	
	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
    {
        renderSilverChicken((EntitySilverChicken)entity, d, d1, d2, f, f1);
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		
		return this.getCrowTextures((EntitySilverChicken)entity);
	}

}
