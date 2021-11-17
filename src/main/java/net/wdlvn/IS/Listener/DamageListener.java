package net.wdlvn.IS.Listener;

import net.wdlvn.IS.Attributes;
import net.wdlvn.IS.Item;
import net.wdlvn.IS.Util.API;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof LivingEntity) {
            if (e.getDamager() instanceof Player) {
                Player damager = (Player) e.getDamager();
                double dmg = e.getDamage();
                ItemStack damagerMainHand = damager.getInventory().getItemInMainHand();
                double extraDamage = dmg;
                double vampireValue = 0.0;
                if (e.getEntity() instanceof LivingEntity){
                    Item item = Item.getItem(damagerMainHand);
                    if (e.getEntity() instanceof Player){
                        Player entity = (Player) e.getEntity();

                        double helmetMagicDef = (entity.getInventory().getHelmet()==null)?0.0:(!Item.isControled(entity.getInventory().getHelmet())?0.0:Item.getItem(entity.getInventory().getHelmet()).getAttrValue(Attributes.MAGICAL_ARMOR));
                        double chestMagicDef = (entity.getInventory().getChestplate()==null)?0.0:(!Item.isControled(entity.getInventory().getChestplate())?0.0:Item.getItem(entity.getInventory().getChestplate()).getAttrValue(Attributes.MAGICAL_ARMOR));
                        double legMagicDef = (entity.getInventory().getLeggings()==null)?0.0:(!Item.isControled(entity.getInventory().getLeggings())?0.0:Item.getItem(entity.getInventory().getLeggings()).getAttrValue(Attributes.MAGICAL_ARMOR));
                        double bootMagicDef = (entity.getInventory().getBoots()==null)?0.0:(!Item.isControled(entity.getInventory().getBoots())?0.0:Item.getItem(entity.getInventory().getBoots()).getAttrValue(Attributes.MAGICAL_ARMOR));
                        double allMagicDef = helmetMagicDef+chestMagicDef+legMagicDef+bootMagicDef;

                        double helmetDef = (entity.getInventory().getHelmet()==null)?0.0:(!Item.isControled(entity.getInventory().getHelmet())?0.0:Item.getItem(entity.getInventory().getHelmet()).getAttrValue(Attributes.ARMOR));
                        double chestDef = (entity.getInventory().getChestplate()==null)?0.0:(!Item.isControled(entity.getInventory().getChestplate())?0.0:Item.getItem(entity.getInventory().getChestplate()).getAttrValue(Attributes.ARMOR));
                        double legDef = (entity.getInventory().getLeggings()==null)?0.0:(!Item.isControled(entity.getInventory().getLeggings())?0.0:Item.getItem(entity.getInventory().getLeggings()).getAttrValue(Attributes.ARMOR));
                        double bootDef = (entity.getInventory().getBoots()==null)?0.0:(!Item.isControled(entity.getInventory().getBoots())?0.0:Item.getItem(entity.getInventory().getBoots()).getAttrValue(Attributes.ARMOR));
                        double allDef = helmetDef+chestDef+legDef+bootDef;

                        if (Item.isControled(damagerMainHand)) {
                            double pierce = (damagerMainHand==null)?0.0:item.getAttrValue(Attributes.PIERCE);
                            double magicPierce = (damagerMainHand==null)?0.0:item.getAttrValue(Attributes.MAGIC_PIERCE);

                            double damage = (damagerMainHand==null)?0.0:item.getAttrValue(Attributes.DAMAGE);
                            double magicDamage = (damagerMainHand==null)?0.0:item.getAttrValue(Attributes.MAGICAL_DAMAGE);

                            double critical = (damagerMainHand==null)?0.0:item.getAttrValue(Attributes.CRITICAL);
                            double criticalChance = (damagerMainHand==null)?0.0:item.getAttrValue(Attributes.CRITICAL_CHANCE);

                            double vampire = (damagerMainHand==null)?0.0:item.getAttrValue(Attributes.VAMPIRE);
                            double vampireChance = (damagerMainHand==null)?0.0:item.getAttrValue(Attributes.VAMPIRE_CHANCE);

                            extraDamage = API.toPositiveNumber((damage-API.toPositiveNumber((allDef-magicPierce))))>0?extraDamage+API.toPositiveNumber(damage-(allDef<pierce?0.0:API.toPositiveNumber(allDef-pierce))):extraDamage+0.0;
                            extraDamage = API.toPositiveNumber(magicDamage-API.toPositiveNumber(allMagicDef-magicPierce))>0?extraDamage+API.toPositiveNumber(magicDamage-(allMagicDef<magicPierce?0.0:API.toPositiveNumber(allMagicDef-magicPierce))):extraDamage+0.0;

                            if (API.random(criticalChance)){
                                extraDamage = extraDamage+extraDamage*critical;
                            }
                            if (API.random(vampireChance)){
                                vampireValue = extraDamage*vampire;
                            }
                        }

                    } else {
                        if (Item.isControled(damagerMainHand)) {
                            double damage = (damagerMainHand == null) ? 0.0 : item.getAttrValue(Attributes.DAMAGE);
                            double magicDamage = (damagerMainHand == null) ? 0.0 : item.getAttrValue(Attributes.MAGICAL_DAMAGE);

                            double critical = (damagerMainHand == null) ? 0.0 : item.getAttrValue(Attributes.CRITICAL);
                            double criticalChance = (damagerMainHand == null) ? 0.0 : item.getAttrValue(Attributes.CRITICAL_CHANCE);

                            double vampire = (damagerMainHand == null) ? 0.0 : item.getAttrValue(Attributes.VAMPIRE);
                            double vampireChance = (damagerMainHand == null) ? 0.0 : item.getAttrValue(Attributes.VAMPIRE_CHANCE);

                            extraDamage = (damage) > 0 ? extraDamage + damage : extraDamage + 0.0;
                            extraDamage = (magicDamage) > 0 ? extraDamage + magicDamage : extraDamage + 0.0;

                            if (API.random(criticalChance)) {
                                extraDamage = extraDamage + extraDamage * critical;
                            }
                            if (API.random(vampireChance)) {
                                vampireValue = extraDamage * vampire;
                            }
                        }
                    }
                }
                e.setDamage(extraDamage);
                if (damager.getHealth()+vampireValue>=damager.getMaxHealth()){
                    damager.setHealth(damager.getMaxHealth());
                } else {
                    damager.setHealth(damager.getHealth()+vampireValue);
                }
            }
            else {
                if (e.getEntity() instanceof LivingEntity) {
                    if (e.getEntity() instanceof Player) {
                        Player entity = (Player) e.getEntity();

                            double helmetMagicDef = (entity.getInventory().getHelmet() == null) ? 0.0 : Item.getItem(entity.getInventory().getHelmet()).getAttrValue(Attributes.MAGICAL_ARMOR);
                            double chestMagicDef = (entity.getInventory().getChestplate() == null) ? 0.0 : Item.getItem(entity.getInventory().getChestplate()).getAttrValue(Attributes.MAGICAL_ARMOR);
                            double legMagicDef = (entity.getInventory().getLeggings() == null) ? 0.0 : Item.getItem(entity.getInventory().getLeggings()).getAttrValue(Attributes.MAGICAL_ARMOR);
                            double bootMagicDef = (entity.getInventory().getBoots() == null) ? 0.0 : Item.getItem(entity.getInventory().getBoots()).getAttrValue(Attributes.MAGICAL_ARMOR);
                            double allMagicDef = helmetMagicDef + chestMagicDef + legMagicDef + bootMagicDef;

                            double helmetDef = (entity.getInventory().getHelmet() == null) ? 0.0 : Item.getItem(entity.getInventory().getHelmet()).getAttrValue(Attributes.ARMOR);
                            double chestDef = (entity.getInventory().getChestplate() == null) ? 0.0 : Item.getItem(entity.getInventory().getChestplate()).getAttrValue(Attributes.ARMOR);
                            double legDef = (entity.getInventory().getLeggings() == null) ? 0.0 : Item.getItem(entity.getInventory().getLeggings()).getAttrValue(Attributes.ARMOR);
                            double bootDef = (entity.getInventory().getBoots() == null) ? 0.0 : Item.getItem(entity.getInventory().getBoots()).getAttrValue(Attributes.ARMOR);
                            double allDef = helmetDef + chestDef + legDef + bootDef;

                           e.setDamage(API.toPositiveNumber(e.getDamage()-allDef/2-allMagicDef/2));


                    }
                }
            }
        }
    }
}
