package net.wdlvn.IS.Listener;

import net.wdlvn.IS.Item;
import net.wdlvn.IS.Util.API;
import net.wdlvn.IS.Util.NBT.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {
    @EventHandler
    public void onMove(PlayerJoinEvent e){
        Player p = e.getPlayer();
        for (int j = 0; j < p.getInventory().getContents().length; j++){
            ItemStack is = p.getInventory().getContents()[j];
            if (is != null){
                NBTItem nbti = new NBTItem(is);
                if (nbti.hasNBTData()){
                    if (nbti.hasKey("ItemSystem")){
                        String path = nbti.getString("ItemSystem");
                        Item i = new Item(path);
                        if (!API.compare(i.getItemStack(),is)){
                            p.getInventory().setItem(j,i.getItemStack());
                        }
                    }
                }
            }
        }
    }
}
