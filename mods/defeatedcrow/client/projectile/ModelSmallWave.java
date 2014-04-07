package mods.defeatedcrow.client.projectile;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSmallWave extends ModelBase{
	
	ModelRenderer wave1;
	  
	  public ModelSmallWave()
	  {
	      wave1 = new ModelRenderer(this, 0, 0);
	      wave1.addBox(-0F, 0F, -8F, 6, 1, 16);
	      wave1.setRotationPoint(0F, 16F, 0F);
	      wave1.setTextureSize(64, 32);
	      wave1.mirror = true;
	      setRotation(wave1, 0F, 0F, 0F);
	  }
	  
	  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    wave1.render(f5);
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
