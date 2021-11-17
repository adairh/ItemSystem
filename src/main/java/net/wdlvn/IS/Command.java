package net.wdlvn.IS;

import net.wdlvn.IS.GuiMenu.MainGui;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("ItemSystem")){
            if (commandSender instanceof Player){
                Player p = (Player) commandSender;
                if (p.hasPermission("itemsystem.admin")) {
                    MainGui.openInvetory(p);
                }
            }
        }
        return false;
    }
}
