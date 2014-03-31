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

public class ModelSilverHawk extends ModelBase {
	
	ModelRenderer head;
    ModelRenderer neck;
    ModelRenderer bottom;
    ModelRenderer body;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer tail;
    ModelRenderer Nose;
    ModelRenderer wing1;
    ModelRenderer wing2;
    ModelRenderer wing3;
    ModelRenderer wing4;
    ModelRenderer wing5;
    ModelRenderer wing6;
    
    public ModelSilverHawk()
    {
    	textureWidth = 128;
        textureHeight = 64;
        
          head = new ModelRenderer(this, 0, 16);
          head.addBox(-3F, -4F, -6F, 6, 5, 6);
          head.setRotationPoint(-1F, 11.5F, -6F);
          head.setTextureSize(128, 64);
          head.mirror = true;
          setRotation(head, 0F, 0F, 0F);
          Nose = new ModelRenderer(this, 100, 0);
          Nose.addBox(-1.5F, -3F, -8F, 3, 2, 5);
          Nose.setRotationPoint(-1F, 11.5F, -7F);
          Nose.setTextureSize(128, 64);
          Nose.mirror = true;
          setRotation(Nose, 0F, 0F, 0F);
          neck = new ModelRenderer(this, 0, 0);
          neck.addBox(-4.5F, -4F, -3F, 9, 8, 6);
          neck.setRotationPoint(-1F, 13F, -7F);
          neck.setTextureSize(128, 64);
          neck.mirror = true;
          setRotation(neck, 0F, 0F, 0F);
          bottom = new ModelRenderer(this, 64, 0);
          bottom.addBox(-4F, -2F, -3F, 6, 9, 6);
          bottom.setRotationPoint(0F, 16F, 2F);
          bottom.setTextureSize(128, 64);
          bottom.mirror = true;
          setRotation(bottom, 1.570796F, 0F, 0F);
          body = new ModelRenderer(this, 32, 0);
          body.addBox(-4F, -3F, -3F, 8, 11, 7);
          body.setRotationPoint(-1F, 16F, -3F);
          body.setTextureSize(128, 64);
          body.mirror = true;
          setRotation(body, 1.570796F, 0F, 0F);
          leg1 = new ModelRenderer(this, 90, 0);
          leg1.addBox(-1F, 0F, -1F, 2, 8, 2);
          leg1.setRotationPoint(-2.5F, 16F, -1F);
          leg1.setTextureSize(128, 64);
          leg1.mirror = true;
          setRotation(leg1, 0F, 0F, 0F);
          leg2 = new ModelRenderer(this, 90, 0);
          leg2.addBox(-1F, 0F, -1F, 2, 8, 2);
          leg2.setRotationPoint(0.5F, 16F, -1F);
          leg2.setTextureSize(128, 64);
          leg2.mirror = true;
          setRotation(leg2, 0F, 0F, 0F);
          tail = new ModelRenderer(this, 100, 9);
          tail.addBox(-3F, 0F, -1F, 6, 8, 2);
          tail.setRotationPoint(-1F, 14F, 8F);
          tail.setTextureSize(128, 64);
          tail.mirror = true;
          setRotation(tail, 1.832596F, 0F, 0F);
          wing1 = new ModelRenderer(this, 57, 28);
          wing1.addBox(-8F, -1F, -4F, 8, 1, 8);
          wing1.setRotationPoint(-5F, 12F, -2F);
          wing1.setTextureSize(128, 64);
          wing1.mirror = true;
          setRotation(wing1, 0F, 0F, 0F);
          wing2 = new ModelRenderer(this, 57, 39);
          wing2.addBox(0F, -1F, -4F, 8, 1, 8);
          wing2.setRotationPoint(3F, 12F, -2F);
          wing2.setTextureSize(128, 64);
          wing2.mirror = true;
          setRotation(wing2, 0F, 0F, 0F);
          wing3 = new ModelRenderer(this, 0, 28);
          wing3.addBox(-22F, -1F, -4F, 14, 1, 14);
          wing3.setRotationPoint(-5F, 12F, -2F);
          wing3.setTextureSize(128, 64);
          wing3.mirror = true;
          setRotation(wing3, 0F, 0F, 0F);
          wing4 = new ModelRenderer(this, 0, 45);
          wing4.addBox(8F, -1F, -4F, 14, 1, 14);
          wing4.setRotationPoint(3F, 12F, -2F);
          wing4.setTextureSize(128, 64);
          wing4.mirror = true;
          setRotation(wing4, 0F, 0F, 0F);
          wing5 = new ModelRenderer(this, 68, 29);
          wing5.addBox(22F, -1F, -6F, 2, 2, 24);
          wing5.setRotationPoint(3F, 12F, -2F);
          wing5.setTextureSize(128, 64);
          wing5.mirror = true;
          setRotation(wing5, 0F, 0F, 0F);
          wing6 = new ModelRenderer(this, 68, 29);
          wing6.addBox(-24F, -1F, -6F, 2, 2, 24);
          wing6.setRotationPoint(-5F, 12F, -2F);
          wing6.setTextureSize(128, 64);
          wing6.mirror = true;
          setRotation(wing6, 0F, 0F, 0F);
    }
    
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      head.render(f5);
      body.render(f5);
      bottom.render(f5);
      neck.render(f5);
      leg1.render(f5);
      leg2.render(f5);
      wing1.render(f5);
      wing2.render(f5);
      wing3.render(f5);
      wing4.render(f5);
      wing5.render(f5);
      wing6.render(f5);
      tail.render(f5);
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
    		this.head.setRotationPoint(-1F, 13.5F, -6F);
    		this.body.setRotationPoint(-2.5F, 18F, -1F);
    		this.neck.setRotationPoint(-1F, 15F, -7F);
    		this.leg1.setRotationPoint(-2.5F, 16F, -1F);
    		this.leg2.setRotationPoint(-0.5F, 16F, -1F);
    		this.wing1.setRotationPoint(-5F, 13F, -2F);
    		this.wing2.setRotationPoint(3F, 14F, -2F);
    		this.wing3.setRotationPoint(-5F, 14F, -2F);
    		this.wing4.setRotationPoint(3F, 14F, -2F);
    		this.wing5.setRotationPoint(3F, 14F, -2F);
    		this.wing6.setRotationPoint(-5F, 14F, -2F);
    		this.bottom.setRotationPoint(0F, 18F, 2F);
    		this.tail.setRotationPoint(-1F, 16F, 8F);
    		this.Nose.setRotationPoint(-1F, 10.5F, -7F);
    	}
    	else
    	{
    		this.head.setRotationPoint(-1F, 11.5F, -6F);
    		this.body.setRotationPoint(-2.5F, 16F, -1F);
    		this.neck.setRotationPoint(-1F, 13F, -7F);
    		this.leg1.setRotationPoint(-2.5F, 16F, -1F);
    		this.leg2.setRotationPoint(-0.5F, 16F, -1F);
    		this.wing1.setRotationPoint(-5F, 11F, -2F);
    		this.wing2.setRotationPoint(3F, 12F, -2F);
    		this.wing3.setRotationPoint(-5F, 12F, -2F);
    		this.wing4.setRotationPoint(3F, 12F, -2F);
    		this.wing5.setRotationPoint(3F, 12F, -2F);
    		this.wing6.setRotationPoint(-5F, 12F, -2F);
    		this.bottom.setRotationPoint(0F, 16F, 2F);
    		this.tail.setRotationPoint(-1F, 14F, 8F);
    		this.Nose.setRotationPoint(-1F, 8.5F, -7F);
    	}
//    	this.wing1.rotateAngleX = entitycrow.getWingAngle(f4);
//    	this.wing2.rotateAngleX = entitycrow.getWingAngle(-f4);
    }
    
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
    	this.head.rotateAngleX = f5 / (180F / (float)Math.PI);
        this.head.rotateAngleY = f4 / (180F / (float)Math.PI);
        this.Nose.rotateAngleX = this.head.rotateAngleX;
        this.Nose.rotateAngleY = this.head.rotateAngleY;
        this.tail.rotateAngleY = 0.0F;
        this.body.rotateAngleX = ((float)Math.PI / 2F);
        this.leg1.rotateAngleX = MathHelper.cos(f1 * 0.6662F) * 1.4F * f1;
        this.leg2.rotateAngleX = MathHelper.cos(f1 * 0.6662F + (float)Math.PI) * 1.4F * f1;
    }

}
