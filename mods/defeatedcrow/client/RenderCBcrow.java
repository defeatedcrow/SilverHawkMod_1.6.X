package mods.defeatedcrow.client;

import java.io.File;

import mods.defeatedcrow.entity.EntityCrow;
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

public class RenderCBcrow extends RenderLiving {
	
	private static final String dir1 = Minecraft.getMinecraft().toString();
	
	private static final ResourceLocation crowTextures = new ResourceLocation("crowsdefeat:textures/entity/CBcrow.png");
	private static final ResourceLocation tamedCrowTextures = new ResourceLocation("crowsdefeat:textures/entity/CBcrow_tamed.png");

	public RenderCBcrow(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		
	}
	
	public void renderCBCrow(EntityCrow entity, double d, double d1, double d2, float f, float f1) {
        if(((EntityCrow)entity).isSitting())
        {
        	d1 -= 0.15D;
        }
        super.doRenderLiving(entity, d, d1, d2, f, f1);
    }
	
	protected ResourceLocation getCrowTextures(EntityCrow par1Entitycrow)
    {
        return par1Entitycrow.isTamed() ? tamedCrowTextures : crowTextures;
    }
	
	protected float getWingRotation(EntityCrow par1EntityCrow, float par2)
    {
        float f1 = par1EntityCrow.wingB + (par1EntityCrow.wingA - par1EntityCrow.wingB) * par2;
        float f2 = par1EntityCrow.wingC + (par1EntityCrow.dPos - par1EntityCrow.wingC) * par2;
        return (MathHelper.sin(f1) + 1.0F) * f2;
    }
	
	protected float handleRotationFloat(EntityLiving par1EntityLiving, float par2)
    {
        return this.getWingRotation((EntityCrow)par1EntityLiving, par2);
    }
	
	public void doRenderLiving(EntityLiving entityliving, double d, double d1, double d2, float f, float f1) {
        renderCBCrow((EntityCrow)entityliving, d, d1, d2, f, f1);
    }
	
	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
    {
        renderCBCrow((EntityCrow)entity, d, d1, d2, f, f1);
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		
		return this.getCrowTextures((EntityCrow)entity);
	}

}
