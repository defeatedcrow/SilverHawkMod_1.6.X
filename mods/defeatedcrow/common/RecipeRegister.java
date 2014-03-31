package mods.defeatedcrow.common;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeRegister {
	
	public void addRecipe()
	{
		GameRegistry.addRecipe(
	    		  new ItemStack(SilverHawkCore.FluoriteLight, 1),
	    		  new Object[]{"XX","XX",
	    			  Character.valueOf('X'), SilverHawkCore.Fluorite});
		 
		 GameRegistry.addRecipe(
	    		  new ItemStack(SilverHawkCore.FluoroGlass, 8),
	    		  new Object[]{"XXX","XYX","XXX",
	    			  Character.valueOf('X'), Block.glass,
	    			  Character.valueOf('Y'), SilverHawkCore.Fluorite});
		 
		 GameRegistry.addRecipe(
	    		  new ItemStack(SilverHawkCore.CDLantern, 1),
	    		  new Object[]{"　Y　","XZX"," Y ",
	    			  Character.valueOf('X'), Block.thinGlass,
	    			  Character.valueOf('Y'), Block.fenceIron,
	    			  Character.valueOf('Z'), SilverHawkCore.Fluorite
	    			  });
		 
		 GameRegistry.addRecipe(
	    		  new ItemStack(SilverHawkCore.GlowButton, 1),
	    		  new Object[]{"　X　"," Z "," Y ",
	    			  Character.valueOf('X'), Item.redstone,
	    			  Character.valueOf('Y'), Block.cobblestone,
	    			  Character.valueOf('Z'), SilverHawkCore.Fluorite
	    			  });
		 
		 GameRegistry.addRecipe(
	    		  new ItemStack(SilverHawkCore.BlackEgg, 1, 1),
	    		  new Object[]{" Z ","XYX"," Z ",
	    			  Character.valueOf('Z'), SilverHawkCore.Fluorite,
	    			  Character.valueOf('Y'), Item.enderPearl,
	    			  Character.valueOf('X'), Item.ingotGold
	    			  });
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
						 new ItemStack(SilverHawkCore.BlackEgg, 1, 2),
			    		  new Object[]{" Z ","XYX"," Z ",
			    			  Character.valueOf('Z'), Block.ice,
			    			  Character.valueOf('Y'), Item.enderPearl,
			    			  Character.valueOf('X'), "ingotSilver"
			    			  }));
		 
		 //arm recipe
		 GameRegistry.addRecipe(
	    		  new ItemStack(SilverHawkCore.armBFPlate, 1),
	    		  new Object[]{"X X","ZYZ","XXX",
	    			  Character.valueOf('X'), new ItemStack(Block.cloth, 1, 15),
	    			  Character.valueOf('Y'), SilverHawkCore.Fluorite,
	    			  Character.valueOf('Z'), SilverHawkCore.BlackFeather});
		 
		 GameRegistry.addRecipe(
	    		  new ItemStack(SilverHawkCore.armBFLegs, 1),
	    		  new Object[]{"ZYZ","X X","X X",
	    			  Character.valueOf('X'), new ItemStack(Block.cloth, 1, 15),
	    			  Character.valueOf('Y'), SilverHawkCore.Fluorite,
	    			  Character.valueOf('Z'), SilverHawkCore.BlackFeather});
		 
		 GameRegistry.addRecipe(
	    		  new ItemStack(SilverHawkCore.armBFMet, 1),
	    		  new Object[]{"XXX","Z Z",
	    			  Character.valueOf('X'), new ItemStack(Block.cloth, 1, 15),
	    			  Character.valueOf('Z'), Item.leather});
		 
		 GameRegistry.addRecipe(
	    		  new ItemStack(SilverHawkCore.armBFBoots, 1),
	    		  new Object[]{"Z Z","XYX",
	    			  Character.valueOf('X'), new ItemStack(Block.cloth, 1, 15),
	    			  Character.valueOf('Y'), SilverHawkCore.Fluorite,
	    			  Character.valueOf('Z'), SilverHawkCore.BlackFeather});
		 
		 
		 GameRegistry.addRecipe(
	    		  new ItemStack(SilverHawkCore.armHKMet, 1),
	    		  new Object[]{" Z ","XYX",
	    			  Character.valueOf('Y'), SilverHawkCore.armBFMet,
	    			  Character.valueOf('X'), Item.ingotGold,
	    			  Character.valueOf('Z'), new ItemStack(SilverHawkCore.BlackEgg, 1, 1)});
		 
		 GameRegistry.addRecipe(
	    		  new ItemStack(SilverHawkCore.armHKLegs, 1),
	    		  new Object[]{" Z ","XYX",
	    			  Character.valueOf('Y'), SilverHawkCore.armBFLegs,
	    			  Character.valueOf('X'), Item.ingotGold,
	    			  Character.valueOf('Z'), new ItemStack(SilverHawkCore.BlackEgg, 1, 1)});
		 
		 GameRegistry.addRecipe(
	    		  new ItemStack(SilverHawkCore.armHKPlate, 1),
	    		  new Object[]{" Z ","XYX",
	    			  Character.valueOf('Y'), SilverHawkCore.armBFPlate,
	    			  Character.valueOf('X'), Item.ingotGold,
	    			  Character.valueOf('Z'), new ItemStack(SilverHawkCore.BlackEgg, 1, 1)});
		 
		 GameRegistry.addRecipe(
	    		  new ItemStack(SilverHawkCore.armHKBoots, 1),
	    		  new Object[]{" Z ","XYX",
	    			  Character.valueOf('Y'), SilverHawkCore.armBFBoots,
	    			  Character.valueOf('X'), Item.ingotGold,
	    			  Character.valueOf('Z'), new ItemStack(SilverHawkCore.BlackEgg, 1, 1)});
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(SilverHawkCore.armSWMet, 1),
	    		  new Object[]{" Z ","XYX",
	    			  Character.valueOf('Y'), SilverHawkCore.armBFMet,
	    			  Character.valueOf('X'), "ingotSilver",
	    			  Character.valueOf('Z'), new ItemStack(SilverHawkCore.BlackEgg, 1, 2)}));
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(SilverHawkCore.armSWPlate, 1),
	    		  new Object[]{" Z ","XYX",
	    			  Character.valueOf('Y'), SilverHawkCore.armBFPlate,
	    			  Character.valueOf('X'), "ingotSilver",
	    			  Character.valueOf('Z'), new ItemStack(SilverHawkCore.BlackEgg, 1, 2)}));
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(SilverHawkCore.armSWLegs, 1),
	    		  new Object[]{" Z ","XYX",
	    			  Character.valueOf('Y'), SilverHawkCore.armBFLegs,
	    			  Character.valueOf('X'), "ingotSilver",
	    			  Character.valueOf('Z'), new ItemStack(SilverHawkCore.BlackEgg, 1, 2)}));
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(SilverHawkCore.armSWBoots, 1),
	    		  new Object[]{" Z ","XYX",
	    			  Character.valueOf('Y'), SilverHawkCore.armBFBoots,
	    			  Character.valueOf('X'), "ingotSilver",
	    			  Character.valueOf('Z'), new ItemStack(SilverHawkCore.BlackEgg, 1, 2)}));
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(SilverHawkCore.fluoroSword, 1),
	    		  new Object[]{" X "," X ","YZY",
	    			  Character.valueOf('X'), "ingotSilver",
	    			  Character.valueOf('Y'), SilverHawkCore.Fluorite,
	    			  Character.valueOf('Z'), "stickWood"}));
		 
		 
		 //another
		 GameRegistry.addRecipe(
	    		  new ItemStack(Item.clay, 4),
	    		  new Object[]{"X",
	    			  'X', Block.blockClay});
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(Item.arrow, 4),
	    		  new Object[]{"X","Z","Y",
	    			  Character.valueOf('X'), Item.flint,
	    			  Character.valueOf('Y'), SilverHawkCore.BlackFeather,
	    			  Character.valueOf('Z'), "stickWood"}));
		 
		 GameRegistry.addShapelessRecipe(
	    		  new ItemStack(SilverHawkCore.Fluorite, 4, 0),
	    			  new ItemStack(SilverHawkCore.FluoriteLight, 1, 0));
		 
		 
		 //furnace
		 FurnaceRecipes.smelting().addSmelting(
				 SilverHawkCore.Ores.blockID, 0,
				 new ItemStack(SilverHawkCore.Fluorite, 2, 0), 0.3F);
	      
		 FurnaceRecipes.smelting().addSmelting(
				 SilverHawkCore.Ores.blockID, 1,
				 new ItemStack(SilverHawkCore.ingotSilver, 1, 0), 0.5F);
	      
	      GameRegistry.addSmelting(
	    		  SilverHawkCore.dustSilver.itemID,
	    		  new ItemStack(SilverHawkCore.ingotSilver,1),
	    		  0.3F);
	      
	}

	public void addEXRecipe() {
		
		//add Extra recipe
	      
	      GameRegistry.addRecipe(
					 new ShapedOreRecipe(
		    		  new ItemStack(SilverHawkCore.BlackFeather, 1),
		    		  new Object[]{" X ","XYX", " X ",
		    			  Character.valueOf('X'), "dyeBlack",
		    			  Character.valueOf('Y'), Item.feather}));
	      
	      GameRegistry.addRecipe(
	    		  new ItemStack(SilverHawkCore.GlowMoss, 1),
	    		  new Object[]{" Z ","ZXZ"," Z ",
	    			  Character.valueOf('Z'), Item.seeds,
	    			  Character.valueOf('X'), SilverHawkCore.Fluorite
	    			  });
	      
	      GameRegistry.addRecipe(
	    		  new ItemStack(SilverHawkCore.Fluorite, 1),
	    		  new Object[]{"XXX","XXX","XXX",
	    			  Character.valueOf('X'), SilverHawkCore.GlowMoss
	    			  });
	      
	}

}
