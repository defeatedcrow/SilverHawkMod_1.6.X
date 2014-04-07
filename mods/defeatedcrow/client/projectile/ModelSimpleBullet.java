package mods.defeatedcrow.client.projectile;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSimpleBullet extends ModelBase{
	
	ModelRenderer nomalBullet;
	  
	  public ModelSimpleBullet()
	  {
	    textureWidth = 32;
	    textureHeight = 32;
	    
	      nomalBullet = new ModelRenderer(this, 0, 0);
	      nomalBullet.addBox(8F, -2F, -2F, 4, 4, 4);
	      nomalBullet.setRotationPoint(0F, 18F, 0F);
	      nomalBullet.setTextureSize(32, 32);
	      nomalBullet.mirror = true;
	      setRotation(nomalBullet, 0F, 0F, 0F);
	  }
	  
	  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    nomalBullet.render(f5);
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
