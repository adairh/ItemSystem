package net.wdlvn.IS.Listener;

import net.wdlvn.IS.Attr;
import net.wdlvn.IS.Attributes;
import net.wdlvn.IS.GuiMenu.ModifyGui;
import net.wdlvn.IS.Item;
import net.wdlvn.IS.ItemSystem;
import net.wdlvn.IS.Util.API;
import net.wdlvn.IS.Util.ReflectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ChatListener implements Listener {

    public static HashMap<Player,Attributes> editItem = new HashMap<Player,Attributes>();

    public static HashMap<Player,Attr> save = new HashMap<Player,Attr>();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        String mess = e.getMessage();
        Player p = e.getPlayer();
        if (editItem.containsKey(p)){
            e.setCancelled(true);
            if (API.isDouble(mess)){
                double value = Double.parseDouble(mess);
                ReflectionUtils.setField(editItem.get(p).toString(),Attr.class,ItemSystem.editItem.get(p),value);
                if (API.isArmor(ItemSystem.editItem.get(p).itemStack.getType())){
                    Inventory inv = ModifyGui.getInventory(ModifyGui.ModifyType.ARMOR);
                    for (int i = 0; i < inv.getSize(); i++){
                        if (inv.getItem(i).hasItemMeta()) {
                            if (ReflectionUtils.isContainField(ChatColor.stripColor(inv.getItem(i).getItemMeta().getDisplayName()),Attr.class)) {
                                String va = String.valueOf(ReflectionUtils.getField(
                                        ChatColor.stripColor(inv.getItem(i).getItemMeta().getDisplayName()),
                                        Attr.class,
                                        ItemSystem.editItem.get(p)));
                                ItemMeta im = inv.getItem(i).getItemMeta();
                                im.setLore(Arrays.asList(API.Color("&f"+va)));
                                inv.getItem(i).setItemMeta(im);
                            }
                        }
                    }
                    p.openInventory(inv);
                    editItem.remove(p);
                } else if (API.isWeapon(ItemSystem.editItem.get(p).itemStack.getType())) {
                    Inventory inv = ModifyGui.getInventory(ModifyGui.ModifyType.WEAPON);
                    for (int i = 0; i < inv.getSize(); i++) {
                         if (inv.getItem(i).hasItemMeta()) {
                             if (ReflectionUtils.isContainField(ChatColor.stripColor(inv.getItem(i).getItemMeta().getDisplayName()), Attr.class)) {
                                String va = String.valueOf(ReflectionUtils.getField(
                                        ChatColor.stripColor(inv.getItem(i).getItemMeta().getDisplayName()),
                                        Attr.class,
                                        ItemSystem.editItem.get(p)));
                                ItemMeta im = inv.getItem(i).getItemMeta();
                                im.setLore(Arrays.asList(API.Color("&f"+va)));
                                inv.getItem(i).setItemMeta(im);
                            }
                        }
                    }
                    p.openInventory(inv);
                    editItem.remove(p);
                }
            }
        }
        else if (save.containsKey(p)){
            e.setCancelled(true);
            for (Item i:Item.getAllItem()) {
                if (mess.equals(i.getPath())) {
                    p.sendMessage(API.Color("&6"+mess+" &chave been already created before, try using a new name"));
                    return;
                }
            }
            Attr at = save.get(p);
            HashMap<Attributes,Double> attr = new HashMap<Attributes,Double>();
            attr.put(Attributes.DAMAGE,at.DAMAGE);
            attr.put(Attributes.MAGICAL_DAMAGE,at.MAGICAL_DAMAGE);
            attr.put(Attributes.CRITICAL,at.CRITICAL);
            attr.put(Attributes.CRITICAL_CHANCE,at.CRITICAL_CHANCE);
            attr.put(Attributes.PIERCE,at.PIERCE);
            attr.put(Attributes.MAGIC_PIERCE,at.MAGIC_PIERCE);
            attr.put(Attributes.ARMOR,at.ARMOR);
            attr.put(Attributes.MAGICAL_ARMOR,at.MAGICAL_ARMOR);
            attr.put(Attributes.VAMPIRE,at.VAMPIRE);
            attr.put(Attributes.VAMPIRE_CHANCE,at.VAMPIRE_CHANCE);
            Item item = new Item(
                at.itemStack,
                attr,
                mess
            );
            save.remove(p);

            ItemSystem.editItem.remove(p);
            p.sendMessage(API.Color("&aSave your file with name &e"+mess));
        }
    }
}
