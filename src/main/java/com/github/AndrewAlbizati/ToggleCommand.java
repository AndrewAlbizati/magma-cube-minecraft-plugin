package com.github.AndrewAlbizati;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!label.equalsIgnoreCase("magma")) {
            return true;
        }

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!p.isOp()) {
                p.sendMessage(ChatColor.RED + "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is a mistake.");
                return true;
            }
        }

        if (MagmaCube.on) {
            MagmaCube.on = false;
            sender.sendMessage(ChatColor.GREEN + "Magma Cube taming turned off");
        } else {
            MagmaCube.on = true;
            sender.sendMessage(ChatColor.GREEN + "Magma Cube taming turned on");
        }

        return true;
    }
}