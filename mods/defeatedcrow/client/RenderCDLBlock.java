package mods.defeatedcrow.client;

import mods.defeatedcrow.common.*;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCDLBlock implements ISimpleBlockRenderingHandler{
	
	private Icon frameIcon;
	private Icon glowIcon;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		
		this.frameIcon = SilverHawkCore.CDLantern.getBlockTextureFromSide(0);
		this.glowIcon = SilverHawkCore.CDLantern.getBlockTextureFromSide(2);
		
		if (modelId == this.getRenderId())
		{
			//glow
			renderer.setOverrideBlockTexture(this.glowIcon);
			block.setBlockBounds(5.0F/16.0F, 3.0F/16.0F, 5.0F/16.0F, 11.0F/16.0F, 12.0F/16.0F, 11.0F/16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
			//bottom
			renderer.setOverrideBlockTexture(this.frameIcon);
			block.setBlockBounds(4.0F/16.0F, 2.0F/16.0F, 4.0F/16.0F, 12.0F/16.0F, 3.0F/16.0F, 12.0F/16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
			
			block.setBlockBounds(7.0F/16.0F, 1.0F/16.0F, 7.0F/16.0F, 9.0F/16.0F, 2.0F/16.0F, 9.0F/16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
			
			block.setBlockBounds(6.0F/16.0F, 0.0F, 6.0F/16.0F, 10.0F/16.0F, 1.0F/16.0F, 10.0F/16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
			//top
			block.setBlockBounds(4.0F/16.0F, 12.0F/16.0F, 4.0F/16.0F, 12.0F/16.0F, 13.0F/16.0F, 12.0F/16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
			
			block.setBlockBounds(5.0F/16.0F, 13.0F/16.0F, 5.0F/16.0F, 11.0F/16.0F, 14.0F/16.0F, 11.0F/16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
			
			block.setBlockBounds(7.0F/16.0F, 14.0F/16.0F, 7.0F/16.0F, 9.0F/16.0F, 1.0F, 9.0F/16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
			
			renderer.clearOverrideBlockTexture();
			block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			renderer.setRenderBoundsFromBlock(block);
			return true;
		}
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		
		return false;
	}

	@Override
	public int getRenderId() {
		
		return SilverHawkCore.modelLantern;
	}
	

}
