package mods.defeatedcrow.client.projectile;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLargeWave extends ModelBase{
	
	ModelRenderer bigwave1;
	  
	  public ModelLargeWave()
	  {
	      bigwave1 = new ModelRenderer(this, 0, 0);
	      bigwave1.addBox(-0F, 0F, -12F, 8, 1, 24);
	      bigwave1.setRotationPoint(0F, 16F, 0F);
	      bigwave1.setTextureSize(64, 32);
	      bigwave1.mirror = true;
	      setRotation(bigwave1, 0F, 0F, 0F);
	  }
	  
	  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    bigwave1.render(f5);
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
