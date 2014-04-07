package mods.defeatedcrow.client.projectile;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelShortLaser extends ModelBase{
	
	ModelRenderer shortLaser;
	  
	  public ModelShortLaser()
	  {
	      shortLaser = new ModelRenderer(this, 0, 0);
	      shortLaser.addBox(-8F, 0F, -4.5F, 14, 1, 1);
	      shortLaser.setRotationPoint(0F, 16F, 0F);
	      shortLaser.setTextureSize(32, 32);
	      shortLaser.mirror = true;
	      setRotation(shortLaser, 0F, 0F, 0F);
	  }
	  
	  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    shortLaser.render(f5);
	  }
	  
	  private void setRotation(ModelRenderer model, float x, float y, float z)
	  {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	  }
	  
	  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	  {
	    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	  }

}
