package net.wdlvn.IS.GuiMenu;

import net.wdlvn.IS.Item;
import net.wdlvn.IS.Util.API;
import net.wdlvn.IS.Util.InvAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ItemCreatorGui {
    public static String TITLE = API.Color("&6&lItem Creator");

    public static int rows = 5;

    public static int size = rows*9;

    private static List<Integer> emptySlot = Arrays.asList(13);

    private static ItemStack itemCreator(){
        ItemStack listOfItem = API.IS(Material.ANVIL,
                1,
                Arrays.asList(API.Color("&aClick here to modify the attributes")),
                API.Color("&a&lModify").toUpperCase(),
                Enchantment.ARROW_DAMAGE, 1,
                ItemFlag.HIDE_ENCHANTS);
        return listOfItem;
    }

    private static ItemStack border(){
        ItemStack listOfItem = API.IS(Material.STAINED_GLASS_PANE,
                (byte)4,
                1,
                Arrays.asList(),
                " ");
        return listOfItem;
    }

    private static ItemStack fill(){
        ItemStack listOfItem = API.IS(Material.STAINED_GLASS_PANE,
                (byte)13,
                1,
                Arrays.asList(),
                " ");
        return listOfItem;
    }

    private static ItemStack putSlot(){
        ItemStack listOfItem = API.IS(Material.STAINED_GLASS_PANE,
                1,
                (byte)5,
                Arrays.asList(),
                " ",
                Enchantment.ARROW_DAMAGE, 1,
                ItemFlag.HIDE_ENCHANTS);
        return listOfItem;
    }




    public static Inventory getInventory(){
        Inventory inv = Bukkit.createInventory(null, size, TITLE);

        InvAPI.fillAll(fill(),inv);
        InvAPI.fillBorders(0, 0, rows - 1, 9 - 1,border(), inv);
        InvAPI.fillOnly(putSlot(),inv,Arrays.asList(13));
        inv.setItem(31, itemCreator());



        return inv;
    }
    public static void openInvetory(Player p){
        p.openInventory(getInventory());
    }

}
