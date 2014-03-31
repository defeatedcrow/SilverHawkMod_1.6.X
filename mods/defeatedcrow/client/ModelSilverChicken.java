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

public class ModelSilverChicken extends ModelBase {
	
	ModelRenderer head;
    ModelRenderer head2;
    ModelRenderer beak;
    ModelRenderer body;
    ModelRenderer hand11;
    ModelRenderer hand12;
    ModelRenderer hand13;
    ModelRenderer hand21;
    ModelRenderer hand22;
    ModelRenderer hand23;
    ModelRenderer wingtop1;
    ModelRenderer wingtop2;
    ModelRenderer wingmiddle1;
    ModelRenderer wingmiddle2;
    ModelRenderer tail;
    ModelRenderer leg1;
    ModelRenderer leg2;
    
    public ModelSilverChicken()
    {
    	textureWidth = 128;
        textureHeight = 64;
        
          head = new ModelRenderer(this, 0, 0);
          head.addBox(-4F, -4F, -7F, 8, 10, 7);
          head.setRotationPoint(0F, 9F, -4F);
          head.setTextureSize(128, 64);
          head.mirror = true;
          setRotation(head, 0F, 0F, 0F);
          head2 = new ModelRenderer(this, 48, 0);
          head2.addBox(-2F, -1F, 0F, 4, 1, 6);
          head2.setRotationPoint(0F, 6F, -5F);
          head2.setTextureSize(128, 64);
          head2.mirror = true;
          setRotation(head2, 0.4363323F, 0F, 0F);
          beak = new ModelRenderer(this, 9, 46);
          beak.addBox(-3F, -1F, -10F, 6, 3, 3);
          beak.setRotationPoint(0F, 10F, -4F);
          beak.setTextureSize(128, 64);
          beak.mirror = true;
          setRotation(beak, 0F, 0F, 0F);
          body = new ModelRenderer(this, 0, 19);
          body.addBox(-6F, -10F, -8F, 12, 16, 9);
          body.setRotationPoint(0F, 12F, 3F);
          body.setTextureSize(128, 64);
          body.mirror = true;
          setRotation(body, 1.570796F, 0F, 0F);
          hand11 = new ModelRenderer(this, 48, 20);
          hand11.addBox(0F, -1F, -1F, 12, 1, 2);
          hand11.setRotationPoint(6F, 13F, -2F);
          hand11.setTextureSize(128, 64);
          hand11.mirror = true;
          setRotation(hand11, 0.3141593F, 0.4886922F, 0.5235988F);
          hand12 = new ModelRenderer(this, 48, 8);
          hand12.addBox(3F, -2F, -3F, 6, 2, 6);
          hand12.setRotationPoint(6F, 13F, -2F);
          hand12.setTextureSize(128, 64);
          hand12.mirror = true;
          setRotation(hand12, 0.3141593F, 0.4886922F, 0.5235988F);
          hand13 = new ModelRenderer(this, 22, 27);
          hand13.addBox(10F, -2F, 0F, 3, 2, 28);
          hand13.setRotationPoint(6F, 13F, -2F);
          hand13.setTextureSize(128, 64);
          hand13.mirror = true;
          setRotation(hand13, 0.3141593F, 0.6981317F, 0.6108652F);
          hand21 = new ModelRenderer(this, 48, 20);
          hand21.addBox(-12F, -1F, -1F, 12, 1, 2);
          hand21.setRotationPoint(-5F, 13F, -2F);
          hand21.setTextureSize(128, 64);
          hand21.mirror = true;
          setRotation(hand21, 0.3141593F, -0.4886922F, -0.5235988F);
          hand22 = new ModelRenderer(this, 48, 8);
          hand22.addBox(-9F, -2F, -3F, 6, 2, 6);
          hand22.setRotationPoint(-5F, 13F, -2F);
          hand22.setTextureSize(128, 64);
          hand22.mirror = true;
          setRotation(hand22, 0.3141593F, -0.4886922F, -0.5235988F);
          hand23 = new ModelRenderer(this, 22, 27);
          hand23.addBox(-13F, -2F, -1F, 3, 2, 28);
          hand23.setRotationPoint(-5F, 13F, -2F);
          hand23.setTextureSize(128, 64);
          hand23.mirror = true;
          setRotation(hand23, 0.3141593F, -0.6981317F, -0.6108652F);
          wingtop1 = new ModelRenderer(this, 65, 0);
          wingtop1.addBox(-5F, 0F, 16F, 16, 0, 28);
          wingtop1.setRotationPoint(0F, 16F, -16F);
          wingtop1.setTextureSize(128, 64);
          wingtop1.mirror = true;
          setRotation(wingtop1, 0.418879F, -0.314593F, -0.418879F);
          wingtop2 = new ModelRenderer(this, 65, 0);
          wingtop2.addBox(-10F, 0F, 16F, 16, 0, 28);
          wingtop2.setRotationPoint(0F, 16F, -16F);
          wingtop2.setTextureSize(128, 64);
          wingtop2.mirror = true;
          setRotation(wingtop2, 0.418879F, 0.314593F, 0.418879F);
          wingmiddle1 = new ModelRenderer(this, 58, 0);
          wingmiddle1.addBox(1F, -1F, 3F, 10, 0, 15);
          wingmiddle1.setRotationPoint(6F, 13F, -2F);
          wingmiddle1.setTextureSize(128, 64);
          wingmiddle1.mirror = true;
          setRotation(wingmiddle1, 0.3490659F, 0.5235988F, 0.5235988F);
          wingmiddle2 = new ModelRenderer(this, 58, 0);
          wingmiddle2.addBox(-11F, -1F, 2F, 10, 0, 15);
          wingmiddle2.setRotationPoint(-5F, 13F, -2F);
          wingmiddle2.setTextureSize(128, 64);
          wingmiddle2.mirror = true;
          setRotation(wingmiddle2, 0.3490659F, -0.5235988F, -0.5235988F);
          tail = new ModelRenderer(this, 50, 26);
          tail.addBox(-2F, -1F, 0F, 4, 2, 35);
          tail.setRotationPoint(0F, 12F, 7F);
          tail.setTextureSize(128, 64);
          tail.mirror = true;
          setRotation(tail, 0.2443461F, 0F, 0F);
          leg1 = new ModelRenderer(this, 0, 45);
          leg1.addBox(-1F, 0F, -1F, 2, 6, 2);
          leg1.setRotationPoint(-2F, 18F, 1F);
          leg1.setTextureSize(128, 64);
          leg1.mirror = true;
          setRotation(leg1, 0F, 0F, 0F);
          leg2 = new ModelRenderer(this, 0, 45);
          leg2.addBox(-1F, 0F, -1F, 2, 6, 2);
          leg2.setRotationPoint(2F, 18F, 1F);
          leg2.setTextureSize(128, 64);
          leg2.mirror = true;
          setRotation(leg2, 0F, 0F, 0F);
    }
    
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      head.render(f5);
      body.render(f5);
      head2.render(f5);
      beak.render(f5);
      leg1.render(f5);
      leg2.render(f5);
      hand11.render(f5);
      hand12.render(f5);
      hand13.render(f5);
      hand21.render(f5);
      hand22.render(f5);
      hand23.render(f5);
      tail.render(f5);
      wingtop1.render(f5);
      wingtop2.render(f5);
      wingmiddle1.render(f5);
      wingmiddle2.render(f5);
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
            head.setRotationPoint(0F, 11F, -4F);
            head2.setRotationPoint(0F, 8F, -5F);
            beak.setRotationPoint(0F, 12F, -11F);
            body.setRotationPoint(0F, 14F, 3F);
            hand11.setRotationPoint(6F, 15F, -2F);
            hand12.setRotationPoint(6F, 15F, -2F);
            hand13.setRotationPoint(6F, 15F, -2F);
            hand21.setRotationPoint(-5F, 15F, -2F);
            hand22.setRotationPoint(-5F, 15F, -2F);
            hand23.setRotationPoint(-5F, 15F, -2F);
            wingtop1.setRotationPoint(0F, 18F, -16F);
            wingtop2.setRotationPoint(0F, 18F, -16F);
            wingmiddle1.setRotationPoint(6F, 15F, -2F);
            wingmiddle1.setRotationPoint(-5F, 15F, -2F);
            tail.setRotationPoint(0F, 14F, 7F);
            leg1.setRotationPoint(-2F, 18F, 1F);
            leg2.setRotationPoint(2F, 18F, 1F);
    	}
    	else
    	{
    		head.setRotationPoint(0F, 9F, -4F);
            head2.setRotationPoint(0F, 6F, -5F);
            beak.setRotationPoint(0F, 10F, -11F);
            body.setRotationPoint(0F, 12F, 3F);
            hand11.setRotationPoint(6F, 13F, -2F);
            hand12.setRotationPoint(6F, 13F, -2F);
            hand13.setRotationPoint(6F, 13F, -2F);
            hand21.setRotationPoint(-5F, 13F, -2F);
            hand22.setRotationPoint(-5F, 13F, -2F);
            hand23.setRotationPoint(-5F, 13F, -2F);
            wingtop1.setRotationPoint(0F, 16F, -16F);
            wingtop2.setRotationPoint(0F, 16F, -16F);
            wingmiddle1.setRotationPoint(6F, 13F, -2F);
            wingmiddle1.setRotationPoint(-5F, 13F, -2F);
            tail.setRotationPoint(0F, 12F, 7F);
            leg1.setRotationPoint(-2F, 18F, 1F);
            leg2.setRotationPoint(2F, 18F, 1F);
    	}
//    	this.wing1.rotateAngleX = entitycrow.getWingAngle(f4);
//    	this.wing2.rotateAngleX = entitycrow.getWingAngle(-f4);
    }
    
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
    	this.head.rotateAngleX = f5 / (180F / (float)Math.PI);
        this.head.rotateAngleY = f4 / (180F / (float)Math.PI);
        this.head2.rotateAngleX = 0.4363323F + this.head.rotateAngleX;
        this.head2.rotateAngleY = this.head.rotateAngleY;
        this.beak.rotateAngleX = this.head.rotateAngleX;
        this.beak.rotateAngleY = this.head.rotateAngleY;
        this.tail.rotateAngleY = 0.0F;
        this.body.rotateAngleX = ((float)Math.PI / 2F);
        this.leg1.rotateAngleX = MathHelper.cos(f1 * 0.6662F) * 1.4F * f1;
        this.leg2.rotateAngleX = MathHelper.cos(f1 * 0.6662F + (float)Math.PI) * 1.4F * f1;
    }

}
