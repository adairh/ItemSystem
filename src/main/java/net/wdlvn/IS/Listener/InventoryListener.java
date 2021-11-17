package net.wdlvn.IS.Listener;

import net.wdlvn.IS.Attr;
import net.wdlvn.IS.Attributes;
import net.wdlvn.IS.GuiMenu.ItemCreatorGui;
import net.wdlvn.IS.GuiMenu.ListGui;
import net.wdlvn.IS.GuiMenu.MainGui;
import net.wdlvn.IS.GuiMenu.ModifyGui;
import net.wdlvn.IS.Item;
import net.wdlvn.IS.ItemSystem;
import net.wdlvn.IS.Util.API;
import net.wdlvn.IS.Util.AttrsItem;
import net.wdlvn.IS.Util.InvAPI;
import net.wdlvn.IS.Util.NBT.NBTItem;
import net.wdlvn.IS.Util.ReflectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class InventoryListener implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent e){
        Inventory inv = e.getClickedInventory();
        int s = e.getSlot();
        Player p = (Player) e.getWhoClicked();
        Inventory top = p.getOpenInventory().getTopInventory();
        Inventory bot = p.getOpenInventory().getBottomInventory();
        if (inv.equals(top)) {
            if (inv.getTitle().equals(MainGui.TITLE)) {
                e.setCancelled(true);
                if (s == 20) {
                    p.closeInventory();
                    ListGui.setUp(p);
                    ListGui.getGui(p, 0);
                } else if (s == 24) {
                    p.closeInventory();
                    ItemCreatorGui.openInvetory(p);
                } else if (s == 22) {
                    p.closeInventory();
                    p.sendMessage(API.Color("&aReloaded"));
                    for (Player pp:Bukkit.getOnlinePlayers()){
                        for (int j = 0; j < pp.getInventory().getContents().length; j++){
                            ItemStack is = pp.getInventory().getContents()[j];
                            if (is != null){
                                NBTItem nbti = new NBTItem(is);
                                if (nbti.hasNBTData()){
                                    if (nbti.hasKey("ItemSystem")){
                                        String path = nbti.getString("ItemSystem");
                                        Item i = new Item(path);
                                        if (!API.compare(i.getItemStack(),is)){
                                            pp.getInventory().setItem(j,i.getItemStack());
                                        }
                                    }
                                }
                            }
                        }
                    }
                    ItemSystem.editItem.remove(p);
                    for (Item i:Item.getAllItem()){
                        String path = i.getPath();
                        File f = new File(ItemSystem.getIns().getDataFolder()+"/Items",path+".yml");
                        if (f.exists()){
                            try {
                                YamlConfiguration file = YamlConfiguration.loadConfiguration(f);
                                file.options().copyDefaults(true);
                                file.save(f);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            } else if (inv.getTitle().equals(ItemCreatorGui.TITLE)) {
                e.setCancelled(true);
                 if (s==13){
                     if (inv.getItem(s)!= null || !InvAPI.isEmpty(inv,s)){
                         top.setItem(s,ItemCreatorGui.getInventory().getItem(13));
                     }
                 }
                 else if (s==31){
                     if (inv.getItem(13)!= null || !InvAPI.isEmpty(inv,13)){
                        Attr at = new Attr();
                        at.itemStack = inv.getItem(13);
                        ItemSystem.editItem.put(p,at);
                        if (API.isArmor(inv.getItem(13).getType())){
                            ModifyGui.openInvetory(p,ModifyGui.ModifyType.ARMOR);
                        } else if (API.isWeapon(inv.getItem(13).getType())) {
                            ModifyGui.openInvetory(p,ModifyGui.ModifyType.WEAPON);
                        }
                     }
                 }
            } else  if (inv.getTitle().equals(ModifyGui.TITLE)){
                e.setCancelled(true);
                if (s!=22) {
                    if (inv.getItem(s) != null || !InvAPI.isEmpty(inv, s)) {
                        for (Attributes a : Attributes.values()) {
                            if (a.toString().equalsIgnoreCase(ChatColor.stripColor(inv.getItem(s).getItemMeta().getDisplayName()))) {
                                ChatListener.editItem.put(p, a);
                                p.closeInventory();
                                p.sendMessage(API.Color("&aType value and press enter"));
                            }
                        }
                    }
                }
                else if (s == 22){
                    ChatListener.save.put(p,ItemSystem.editItem.get(p));
                    p.closeInventory();
                    p.sendMessage(API.Color("&aType the name and press enter"));
                }
            }
            else if (inv.getTitle().equals(ListGui.TITLE)) {
                e.setCancelled(true);
                if (inv.getItem(s) != null || !InvAPI.isEmpty(inv, s)) {
                    int stage = ListGui.pi.get(p);
                    if (e.getSlot() == 53) {
                        if (stage >= 0 && stage < (ListGui.pis.get(p).size() - 1)) {
                            ListGui.getGui(p, 1);
                        }
                    } else if (e.getSlot() == 45) {
                        if (stage > 0 && stage < ListGui.pis.get(p).size()) {
                            ListGui.getGui(p, -1);
                        }
                    } else {
                        if (inv.getItem(s) != null || !InvAPI.isEmpty(inv, s)) {
                            if (e.getClick() != ClickType.SHIFT_RIGHT) {
                                NBTItem nbti = new NBTItem(inv.getItem(s));
                                if (nbti.hasNBTData()) {
                                    if (nbti.hasKey("ItemSystem")) {
                                        String path = nbti.getString("ItemSystem");
                                        Item i = new Item(path);
                                        bot.addItem(i.getItemStack());
                                    }
                                }
                            } else {
                                NBTItem nbti = new NBTItem(inv.getItem(s));
                                if (nbti.hasNBTData()) {
                                    if (nbti.hasKey("ItemSystem")) {
                                        String path = nbti.getString("ItemSystem");
                                        File f = new File(ItemSystem.getIns().getDataFolder() + "/Items", path + ".yml");
                                        if (f.exists()) {
                                            f.delete();
                                            p.closeInventory();
                                            ListGui.setUp(p);
                                            ListGui.getGui(p, 0);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (inv.equals(bot)){
            if (top.getTitle().equals(ItemCreatorGui.TITLE)) {
                e.setCancelled(true);
                if (top.getItem(13) == null || InvAPI.isEmpty(top,3)){
                    if ((API.isArmor(inv.getItem(s).getType()) || API.isWeapon(inv.getItem(s).getType()))
                            && (!Item.isControled(inv.getItem(s)))) {
                        top.setItem(13, inv.getItem(s));
                    }
                }
            }
        }
    }
}
