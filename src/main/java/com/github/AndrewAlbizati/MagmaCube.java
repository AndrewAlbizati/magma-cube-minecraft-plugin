package com.github.AndrewAlbizati;

import org.bukkit.plugin.java.JavaPlugin;

public class MagmaCube extends JavaPlugin {
    public static boolean on = false;

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new PlayerDamageListener(), this);
        this.getCommand("magma").setExecutor(new ToggleCommand());
    }
}