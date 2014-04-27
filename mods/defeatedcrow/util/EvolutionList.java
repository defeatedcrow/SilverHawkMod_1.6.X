package mods.defeatedcrow.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import mods.defeatedcrow.common.SilverHawkCore;
import mods.defeatedcrow.entity.EntityCrow;
import mods.defeatedcrow.entity.EntitySilverHawk;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * AppleMilkTeaのTeaMakerAPIと酷似している。
 * 要するに変化アイテムと変化モブをMapで紐付けているだけ。
 */
public class EvolutionList {
	
		//内部用。登録できるレシピの上限を設定している。
		//ティーメーカーと同じ。絶対にこれは超えないと思う。
		private static int maxID = 0;
		private static final int limitID = 127;
		
		//ダメージ値の上限判定用。不要な気はする。
		private static final int WILDCARD_VALUE = Short.MAX_VALUE;
		
		/**
	     * ティーメーカーのパクリなのでレシピみたいに扱われている。手抜き。
	     */
		protected static HashMap<ItemStack, Integer> recipeID = new HashMap<ItemStack, Integer>();
		protected static HashMap<Integer, Class<? extends EntityLivingBase>> before = new HashMap<Integer, Class<? extends EntityLivingBase>>();
		private static HashMap<Integer, Class<? extends EntityLivingBase>> after = new HashMap<Integer, Class<? extends EntityLivingBase>>();
		
		/**
	     * 内部でのみ使用。すでにID登録したインプットの二重登録を禁止するためのもの。
	     */
		private static boolean dupe(ItemStack input)
		{
			Integer val = recipeID.get(input);
			if (val != null) return true;
			else return false;
		}
		
		/**
	     * インプットを引数にして、そのインプットのレシピIDを返す。未登録時には -1 を返す。
	     */
		private static int getRecipeID(ItemStack input)
		{
			if (input == null)
			{
				return -1;
			}
			else
			{
				for(Entry<ItemStack, Integer> check : recipeID.entrySet())
		        {
					ItemStack target = check.getKey();
					if(input.itemID == target.itemID && (target.getItemDamage() == WILDCARD_VALUE || input.getItemDamage() == target.getItemDamage()))
	                {
	                    return check.getValue();
	                }
		        }
				return -1;
			}
		}
		
		/**
	     * インプットを引数にして、そのインプットのレシピIDを返す。未登録時には -1 を返す。
	     * getRecipeIDと異なり、inputの個数を無視したチェックを行う。
	     * 外部からの参照時にメインで使うメソッド。
	     */
		public static int getID(ItemStack input)
		{
			Integer val = -1;
			if (input == null)
			{
				val = -1;
			}
			else
			{
				ItemStack inputs = new ItemStack(input.itemID, 1, input.getItemDamage());
				val = getRecipeID(inputs);
				SHLogger.debugInfo("ID: " + val);
			}
			
			return val;
		}
		
		/**
	     * IDからインプットを返す。
	     * 失敗判定時など、入れたものを取り戻したい時に使う。
	     */
		public static ItemStack getInput(int id)
		{
			for (Map.Entry<ItemStack, Integer> entry : recipeID.entrySet())
	        {
	            if (id == entry.getValue())
	            {
	                return entry.getKey();
	            }
	        }
	        return (ItemStack)null;
		}
		
		/**
	     * 変化前MOBの登録IDを返す。
	     */
		public static int getBeforeID(EntityLivingBase entity)
		{
			for (Map.Entry<Integer, Class<? extends EntityLivingBase>> entry : before.entrySet())
	        {
	            if (entity.getClass().equals(entry))
	            {
	                return entry.getKey();
	            }
	        }
	        return -1;
		}
		
		/**
	     * IDに対応する変化前MOBの名前を返す。（IDでは比較できない）
	     * 未登録の場合に"None"を返す。
	     */
		public static String getBefore(int id, World world)
		{
			EntityLivingBase entity = null;
			String name = "None";
			
			try
	        {
	            Class oclass = before.get(id);

	            if (oclass != null)
	            {
	                entity = (EntityLivingBase)oclass.getConstructor(new Class[] {World.class}).newInstance(new Object[] {world});
	            }
	            
	            name = entity.getEntityName();
	        }
	        catch (Exception exception)
	        {
	            exception.printStackTrace();
	        }

	        if (entity == null)
	        {
	            SHLogger.debugInfo("Skipping Entity with id " + id);
	        }
	        
	        return name;
		}
		
		/**
	     * IDに対応する変化後MOBを返す。
	     * 未登録の場合にnullを返す。
	     */
		public static EntityLivingBase getAfter(int id, World world)
		{
			EntityLivingBase entity = null;
			
			try
	        {
	            Class oclass = after.get(id);

	            if (oclass != null)
	            {
	                entity = (EntityLivingBase)oclass.getConstructor(new Class[] {World.class}).newInstance(new Object[] {world});
	            }
	        }
	        catch (Exception exception)
	        {
	            exception.printStackTrace();
	        }

	        if (entity == null)
	        {
	            SHLogger.debugInfo("Skipping Entity with id " + id);
	        }
	        
	        return entity;
		}
		
		/**
	     * 新規にレシピIDを登録するメソッド。
	     * 上限に達している時は-1を返す。
	     */
		private static int setMaxID()
		{
			if (maxID < limitID){
				maxID++;
				return maxID;
			}
			else
			{
				return -1;
			}
		}
		
		/**
	     * 新レシピの登録メソッド。成功するとtrueを返す。
	     */
		private static boolean newRecipeID(ItemStack use, Class<? extends EntityLivingBase> beforeEntity, Class<? extends EntityLivingBase> afterEntity)
		{
			Integer val = recipeID.get(use);
			if (use != null && beforeEntity != null && afterEntity != null)
			{
				if (val == null)
				{
					int newID = setMaxID();
					
					if (newID != -1)
					{
						recipeID.put(use, newID);
						before.put(newID, beforeEntity);
						after.put(newID, afterEntity);
						SHLogger.debugInfo("newID: " + newID);
						SHLogger.debugInfo("input: " + beforeEntity);
						SHLogger.debugInfo("output: " + afterEntity);
						
						
						return true;
					}
				}
			}
			return false;
		}
		
		/**
	     * 進化アイテム、進化前、進化後を登録する。
	     * @param input (ItemStack) 与えるアイテム
	     * @param output (Class<? extends EntityLivingBase>) 進化させるエンティティ
	     * @Param after (Class<? extends EntityLivingBase>) 進化後のエンティティ
	     */
		public static void register(ItemStack input, Class<? extends EntityLivingBase> before, Class<? extends EntityLivingBase> after)
		{
			ItemStack inputs = new ItemStack(input.itemID, 1, input.getItemDamage());
			if (!dupe(inputs) && newRecipeID(inputs,before,after))
			{
				
			}
		}
		
		public void registerEvol()
		{
			this.register(new ItemStack(SilverHawkCore.flighterCore, 1, 0), EntityCrow.class, EntitySilverHawk.class);
		}
}
