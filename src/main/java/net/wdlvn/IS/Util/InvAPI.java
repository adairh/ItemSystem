package net.wdlvn.IS.Util;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class InvAPI {
    public static void fillBorders(int fromRow, int fromColumn, int toRow, int toColumn, ItemStack item, Inventory inv) {
        for (int row = fromRow; row <= toRow; row++) {
            for (int column = fromColumn; column <= toColumn; column++) {
                if (row != fromRow && row != toRow && column != fromColumn && column != toColumn)
                    continue;
                inv.setItem(9 * row + column, item);
            }
        }
    }

    public static void fillAll(ItemStack is, Inventory inv){
        for (int i = 0; i < inv.getSize(); i++){
            inv.setItem(i, is);
        }
    }

    public static void fillExcept(ItemStack is, Inventory inv, List<Integer> slots){
        for (int i = 0; i < inv.getSize(); i++){
            if (!slots.contains(i)) {
                inv.setItem(i, is);
            }
        }
    }

    public static void fillOnly(ItemStack is, Inventory inv, List<Integer> slots){
        for (int i = 0; i < inv.getSize(); i++){
            if (slots.contains(i)) {
                inv.setItem(i, is);
            }
        }
    }

    public static boolean isEmpty(Inventory inv, int s){
        return inv.getItem(s).getType()==Material.STAINED_GLASS_PANE;
    }

}
