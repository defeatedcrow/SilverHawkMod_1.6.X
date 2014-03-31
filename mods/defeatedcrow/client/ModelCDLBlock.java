package mods.defeatedcrow.client;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelCDLBlock extends ModelBase
{
  //fields
    ModelRenderer CDLLeg;
    ModelRenderer CDLLeg2;
    ModelRenderer CDLLeg3;
    ModelRenderer CDL;
    ModelRenderer CDLHead1;
    ModelRenderer CDLHead2;
    ModelRenderer CDLHead3;
  
  public ModelCDLBlock()
  {
    textureWidth = 32;
    textureHeight = 32;
    
    CDLLeg = new ModelRenderer(this, 0, 0);
    CDLLeg.addBox(0F, 0F, 0F, 6, 1, 6);
    CDLLeg.setRotationPoint(-3F, 23F, -3F);
    CDLLeg.setTextureSize(64, 32);
    CDLLeg.mirror = true;
    setRotation(CDLLeg, 0F, 0F, 0F);
    CDLLeg2 = new ModelRenderer(this, 0, 0);
    CDLLeg2.addBox(0F, 0F, 0F, 2, 2, 2);
    CDLLeg2.setRotationPoint(-1F, 21F, -1F);
    CDLLeg2.setTextureSize(64, 32);
    CDLLeg2.mirror = true;
    setRotation(CDLLeg2, 0F, 0F, 0F);
    CDLLeg3 = new ModelRenderer(this, 0, 0);
    CDLLeg3.addBox(0F, 0F, 0F, 8, 1, 8);
    CDLLeg3.setRotationPoint(-4F, 20F, -4F);
    CDLLeg3.setTextureSize(64, 32);
    CDLLeg3.mirror = true;
    setRotation(CDLLeg3, 0F, 0F, 0F);
    CDL = new ModelRenderer(this, 0, 10);
    CDL.addBox(0F, 0F, 0F, 6, 9, 6);
    CDL.setRotationPoint(-3F, 11F, -3F);
    CDL.setTextureSize(64, 32);
    CDL.mirror = true;
    setRotation(CDL, 0F, 0F, 0F);
    CDLHead1 = new ModelRenderer(this, 0, 0);
    CDLHead1.addBox(0F, 0F, 0F, 8, 1, 8);
    CDLHead1.setRotationPoint(-4F, 10F, -4F);
    CDLHead1.setTextureSize(64, 32);
    CDLHead1.mirror = true;
    setRotation(CDLHead1, 0F, 0F, 0F);
    CDLHead2 = new ModelRenderer(this, 0, 0);
    CDLHead2.addBox(0F, 0F, 0F, 1, 2, 1);
    CDLHead2.setRotationPoint(-0.5F, 8F, -0.5F);
    CDLHead2.setTextureSize(64, 32);
    CDLHead2.mirror = true;
    setRotation(CDLHead2, 0F, 0F, 0F);
    CDLHead3 = new ModelRenderer(this, 0, 0);
    CDLHead3.addBox(0F, 0F, 0F, 2, 1, 2);
    CDLHead3.setRotationPoint(-1F, 8F, -1F);
    CDLHead3.setTextureSize(64, 32);
    CDLHead3.mirror = true;
    setRotation(CDLHead3, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    CDLLeg.render(f5);
    CDLLeg2.render(f5);
    CDLLeg3.render(f5);
    CDL.render(f5);
    CDLHead1.render(f5);
    CDLHead2.render(f5);
    CDLHead3.render(f5);
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
  
  public void renderCDL()
  {
    CDLLeg.render(0.0625F);
    CDLLeg2.render(0.0625F);
    CDLLeg3.render(0.0625F);
    CDL.render(0.0625F);
    CDLHead1.render(0.0625F);
    CDLHead2.render(0.0625F);
    CDLHead3.render(0.0625F);
  }

}
