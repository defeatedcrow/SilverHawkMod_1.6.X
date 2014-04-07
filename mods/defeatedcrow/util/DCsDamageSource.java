package mods.defeatedcrow.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.StatCollector;

public class DCsDamageSource extends EntityDamageSource{
	
	public Entity damageSourceEntity;

	protected DCsDamageSource(String par1Str, Entity par2Entity) {
		super(par1Str, par2Entity);
		this.damageSourceEntity = par2Entity;
	}
	
	public Entity getEntity() {
		return this.damageSourceEntity;
	}
	
	public ChatMessageComponent getDeathMessage(EntityLivingBase par1EntityLivingBase)
    {
        EntityLivingBase entity = this.damageSourceEntity instanceof EntityLivingBase ? ((EntityLivingBase)this.damageSourceEntity) : null;
        String s = "death.attack." + this.damageType;
        String s1 = s + ".name";
        return entity != null && StatCollector.func_94522_b(s1) ? ChatMessageComponent.createFromTranslationWithSubstitutions(s1, new Object[] {par1EntityLivingBase.getTranslatedEntityName(), this.damageSourceEntity.getTranslatedEntityName(), entity.getEntityName()}): ChatMessageComponent.createFromTranslationWithSubstitutions(s, new Object[] {par1EntityLivingBase.getTranslatedEntityName(), this.damageSourceEntity.getTranslatedEntityName()});
    }
	
	public static DamageSource NormalGutling(Entity entity) {
		return (new DCsDamageSource("DCsGatling", entity)).setProjectile();
	}
	
	public static DamageSource Laser(Entity entity) {
		return (new DCsDamageSource("DCsLaser", entity)).setFireDamage();
	}
	
	public static DamageSource Energy(Entity entity) {
		return (new DCsDamageSource("DCsWave", entity)).setDamageBypassesArmor().setMagicDamage();
	}

}
