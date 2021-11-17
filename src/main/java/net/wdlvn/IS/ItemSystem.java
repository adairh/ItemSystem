package net.wdlvn.IS;

import net.wdlvn.IS.Listener.ChatListener;
import net.wdlvn.IS.Listener.DamageListener;
import net.wdlvn.IS.Listener.InventoryListener;
import net.wdlvn.IS.Listener.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;

public class ItemSystem extends JavaPlugin{
    private static ItemSystem plugin;
    public static HashMap<Player,Attr> editItem = new HashMap<Player,Attr>();
    @Override
    public void onEnable() {
        plugin = this;

        File f = new File(ItemSystem.getIns().getDataFolder()+"/Items");
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            f.mkdir();
        }
        PluginManager pm = Bukkit.getPluginManager();
        getCommand("itemsystem").setExecutor(new Command());
        pm.registerEvents(new DamageListener(), this);
        pm.registerEvents(new PlayerListener(), this);
        pm.registerEvents(new InventoryListener(), this);
        pm.registerEvents(new ChatListener(), this);
    }

    public static ItemSystem getIns(){
        return plugin;
    }
}
