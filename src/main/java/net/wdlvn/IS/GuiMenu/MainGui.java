package net.wdlvn.IS.GuiMenu;

import net.wdlvn.IS.Item;
import net.wdlvn.IS.Util.API;
import net.wdlvn.IS.Util.InvAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class MainGui {
    public static String TITLE = API.Color("&6&lItem System");

    public static int rows = 5;

    public static int size = rows*9;


    private static ItemStack listOfItem(){
        ItemStack listOfItem = API.IS(Material.PAPER,
                1,
                Arrays.asList(API.Color("&aShow your list of item"),
                        API.Color("&aYou have &e" + Item.getAllItem().size() + " &aitem")),
                API.Color("&a&lList Of Item").toUpperCase(),
                Enchantment.ARROW_DAMAGE, 1,
                ItemFlag.HIDE_ENCHANTS);
        return listOfItem;
    }

    private static ItemStack itemCreator(){
        ItemStack listOfItem = API.IS(Material.ANVIL,
                1,
                Arrays.asList(API.Color("&aClick here to create new item")),
                API.Color("&a&lItem Creator").toUpperCase(),
                Enchantment.ARROW_DAMAGE, 1,
                ItemFlag.HIDE_ENCHANTS);
        return listOfItem;
    }

    private static ItemStack reloadSystem(){
        ItemStack listOfItem = API.IS(Material.EYE_OF_ENDER,
                1,
                Arrays.asList(API.Color("&aClick here to reload ItemSystem")),
                API.Color("&a&lReload").toUpperCase(),
                Enchantment.ARROW_DAMAGE, 1,
                ItemFlag.HIDE_ENCHANTS);
        return listOfItem;
    }

    private static ItemStack border(){
        ItemStack listOfItem = API.IS(Material.STAINED_GLASS_PANE,
                (byte)11,
                1,
                Arrays.asList(),
                " ");
        return listOfItem;
    }

    private static ItemStack fill(){
        ItemStack listOfItem = API.IS(Material.STAINED_GLASS_PANE,
                (byte)7,
                1,
                Arrays.asList(),
                " ");
        return listOfItem;
    }
    public static Inventory getInventory(){
        Inventory inv = Bukkit.createInventory(null, size, TITLE);

        InvAPI.fillAll(fill(),inv);
        InvAPI.fillBorders(0, 0, rows - 1, 9 - 1,border(), inv);

        inv.setItem(20, listOfItem());
        inv.setItem(24, itemCreator());
        inv.setItem(22, reloadSystem());

        return inv;
    }
    public static void openInvetory(Player p){
        p.openInventory(getInventory());
    }

}
