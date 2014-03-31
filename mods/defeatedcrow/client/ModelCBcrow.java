package mods.defeatedcrow.client;

import net.minecraft.src.*;
import net.minecraft.util.MathHelper;
import mods.defeatedcrow.entity.EntityCrow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityWolf;

public class ModelCBcrow extends ModelBase {
	
	ModelRenderer Head;
    ModelRenderer Body;
    ModelRenderer Neck;
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Wing1;
    ModelRenderer Wing2;
    ModelRenderer Tail;
    ModelRenderer Nose;
    
    public ModelCBcrow()
    {
      textureWidth = 64;
      textureHeight = 32;
      
        Head = new ModelRenderer(this, 0, 0);
        Head.addBox(-3F, -3F, -2F, 4, 3, 3);
        Head.setRotationPoint(1F, 18F, -3F);
        Head.setTextureSize(64, 32);
        Head.mirror = true;
        setRotation(Head, 0F, 0F, 0F);
        Body = new ModelRenderer(this, 0, 9);
        Body.addBox(-4F, -2F, -3F, 4, 7, 5);
        Body.setRotationPoint(2F, 19F, 0F);
        Body.setTextureSize(64, 32);
        Body.mirror = true;
        setRotation(Body, 1.570796F, 0F, 0F);
        Neck = new ModelRenderer(this, 0, 21);
        Neck.addBox(-4F, -3F, -3F, 4, 3, 5);
        Neck.setRotationPoint(2F, 18F, -1F);
        Neck.setTextureSize(64, 32);
        Neck.mirror = true;
        setRotation(Neck, 1.570796F, 0F, 0F);
        Leg1 = new ModelRenderer(this, 16, 0);
        Leg1.addBox(-1F, 0F, -1F, 1, 3, 1);
        Leg1.setRotationPoint(-0.5F, 21F, 1F);
        Leg1.setTextureSize(64, 32);
        Leg1.mirror = true;
        setRotation(Leg1, 0F, 0F, 0F);
        Leg2 = new ModelRenderer(this, 16, 4);
        Leg2.addBox(-1F, 0F, -1F, 1, 3, 1);
        Leg2.setRotationPoint(1.5F, 21F, 1F);
        Leg2.setTextureSize(64, 32);
        Leg2.mirror = true;
        setRotation(Leg2, 0F, 0F, 0F);
        Wing1 = new ModelRenderer(this, 20, 0);
        Wing1.addBox(-1F, 0F, -1F, 1, 4, 5);
        Wing1.setRotationPoint(-2F, 17F, 0F);
        Wing1.setTextureSize(64, 32);
        Wing1.mirror = true;
        setRotation(Wing1, 0F, 0F, 0F);
        Wing2 = new ModelRenderer(this, 20, 9);
        Wing2.addBox(-1F, 0F, -1F, 1, 4, 5);
        Wing2.setRotationPoint(3F, 17F, 0F);
        Wing2.setTextureSize(64, 32);
        Wing2.mirror = true;
        setRotation(Wing2, 0F, 0F, 0F);
        Tail = new ModelRenderer(this, 20, 18);
        Tail.addBox(-1F, 0F, -1F, 4, 4, 1);
        Tail.setRotationPoint(-1F, 18F, 4F);
        Tail.setTextureSize(64, 32);
        Tail.mirror = true;
        setRotation(Tail, 2.022356F, 0F, 0F);
        Nose = new ModelRenderer(this, 20, 23);
        Nose.addBox(-2F, 0F, -5F, 2, 1, 2);
        Nose.setRotationPoint(1F, 15.5F, -2F);
        Nose.setTextureSize(64, 32);
        Nose.mirror = true;
        setRotation(Nose, 0F, 0F, 0F);
    }
    
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      Head.render(f5);
      Body.render(f5);
      Neck.render(f5);
      Leg1.render(f5);
      Leg2.render(f5);
      Wing1.render(f5);
      Wing2.render(f5);
      Tail.render(f5);
      Nose.render(f5);
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
    }
    
    public void setLivingAnimations(EntityLiving par1EntityLiving, float f2, float f3, float f4, float f5)
    {
    	EntityCrow entitycrow = (EntityCrow)par1EntityLiving;
    	
    	if (entitycrow.isSitting())
    	{
    		this.Head.setRotationPoint(1F, 19F, -3F);
    		this.Body.setRotationPoint(2F, 20F, 0F);
    		this.Neck.setRotationPoint(2F, 19F, -1F);
    		this.Wing1.setRotationPoint(-2F, 18F, 0F);
    		this.Wing2.setRotationPoint(3F, 18F, 0F);
    		this.Tail.setRotationPoint(-1F, 19F, 4F);
    		this.Nose.setRotationPoint(1F, 16.5F, -2F);
    	}
    	else
    	{
    		this.Head.setRotationPoint(1F, 18F, -3F);
    		this.Body.setRotationPoint(2F, 19F, 0F);
    		this.Neck.setRotationPoint(2F, 18F, -1F);
    		this.Wing1.setRotationPoint(-2F, 17F, 0F);
    		this.Wing2.setRotationPoint(3F, 17F, 0F);
    		this.Tail.setRotationPoint(-1F, 18F, 4F);
    		this.Nose.setRotationPoint(1F, 15.5F, -2F);
    	}
    	this.Wing1.rotateAngleX = entitycrow.getWingAngle(f4);
    	this.Wing2.rotateAngleX = entitycrow.getWingAngle(-f4);
    }
    
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
    	this.Head.rotateAngleX = f5 / (180F / (float)Math.PI);
        this.Head.rotateAngleY = f4 / (180F / (float)Math.PI);
        this.Nose.rotateAngleX = this.Head.rotateAngleX;
        this.Nose.rotateAngleY = this.Head.rotateAngleY;
        this.Tail.rotateAngleY = 0.0F;
        this.Body.rotateAngleX = ((float)Math.PI / 2F);
        this.Leg1.rotateAngleX = MathHelper.cos(f1 * 0.6662F) * 1.4F * f1;
        this.Leg2.rotateAngleX = MathHelper.cos(f1 * 0.6662F + (float)Math.PI) * 1.4F * f1;
        this.Wing2.rotateAngleZ = f3;
        this.Wing1.rotateAngleZ = -f3;
    }

}
