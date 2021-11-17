package net.wdlvn.IS.GuiMenu;

import net.wdlvn.IS.Util.API;
import net.wdlvn.IS.Util.AttrsItem;
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

public class ModifyGui {
    public static String TITLE = API.Color("&6&lModify Creator");

    public static int rows = 3;

    public static int size = rows*9;

    private static List<Integer> emptySlot = Arrays.asList(13);

    private static ItemStack finish(){
        ItemStack listOfItem = API.IS(Material.ANVIL,
                1,
                Arrays.asList(API.Color("&aClick here to export item")),
                API.Color("&a&lExport!").toUpperCase(),
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
                "");
        return listOfItem;
    }





    public static Inventory getInventory(ModifyType mt){
        Inventory inv = Bukkit.createInventory(null, size, TITLE);

        InvAPI.fillAll(fill(),inv);
        InvAPI.fillBorders(0, 0, rows - 1, 9 - 1,border(), inv);
        InvAPI.fillOnly(border(),inv,Arrays.asList(13));
        inv.setItem(22, finish());

        if (mt == ModifyType.ARMOR){
            inv.setItem(12,AttrsItem.armor());
            inv.setItem(14,AttrsItem.magicArmor());
        }

        if (mt == ModifyType.WEAPON){
            inv.setItem(9,AttrsItem.damage());
            inv.setItem(10,AttrsItem.magicdamage());
            inv.setItem(11,AttrsItem.pierce());
            inv.setItem(12,AttrsItem.magicPierce());

            inv.setItem(14,AttrsItem.crit());
            inv.setItem(15,AttrsItem.critChance());
            inv.setItem(16,AttrsItem.vampire());
            inv.setItem(17,AttrsItem.vampireChance());
        }



        return inv;
    }
    public static void openInvetory(Player p,ModifyType mt){
        p.openInventory(getInventory(mt));
    }


    public static enum ModifyType{
        ARMOR,
        WEAPON;
    }
}
