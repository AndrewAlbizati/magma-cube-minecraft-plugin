package com.github.AndrewAlbizati;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PlayerDamageListener implements Listener {
    private static final ArrayList<String> names = new ArrayList<>();

    public PlayerDamageListener() {
        // Generate names
        try {
            JSONParser parser = new JSONParser();
            InputStream namesStream = PlayerDamageListener.class.getResourceAsStream("/names.json");
            JSONArray namesJsonArray = (JSONArray)parser.parse(new InputStreamReader(namesStream, "UTF-8"));

            for (Object o : namesJsonArray) {
                names.add(o.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if (!MagmaCube.on) {
            return;
        }

        Entity entityDamaged = event.getEntity();
        Entity entityDamager = event.getDamager();

        // If damaged isn't a player
        if (!(entityDamaged instanceof Player)) {
            return;
        }

        Player damaged = (Player) event.getEntity();

        // If the damager is a magma cube
        if (!(entityDamager instanceof org.bukkit.entity.MagmaCube)) {
            return;
        }

        // If the Magma Cube already has name
        // Aka already has been tamed
        if (entityDamager.getCustomName() != null) {
            event.setCancelled(true);
            return;
        }


        org.bukkit.entity.MagmaCube damager = (org.bukkit.entity.MagmaCube) event.getDamager();

        // Play sniffing sound
        damaged.playSound(damaged.getLocation(), Sound.ENTITY_FOX_SNIFF, 1000, 1);


        // 1/3 chance of taming Magma Cube
        int i = (int) (Math.random() * 3);
        if (i != 0) {
            return;
        }

        String name = getRandomName();
        damager.setCustomName(name);
        damager.setCustomNameVisible(true);
        damager.setPersistent(true);

        Bukkit.broadcastMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + name + ChatColor.RESET + " has been tamed by " + damaged.getDisplayName());


        Location loc = damager.getLocation();

        // Set the range of heart particles
        // Range is higher for bigger Magma Cubes
        double rangeMin = 0.0;
        double rangeMax = 0.0;
        if (damager.getSize() == 1) {
            rangeMin = -0.5;
            rangeMax = 0.5;
        } else if (damager.getSize() == 2) {
            rangeMin = -1;
            rangeMax = 1;
        } else if (damager.getSize() == 4) {
            rangeMin = -2;
            rangeMax = 2;
        }

        // Make 10 heart particles in random places around the Magma Cube
        for (double j = 0; j < 10; j++) {
            double randomX = rangeMin + (rangeMax - rangeMin) * Math.random();
            double randomY = rangeMin + (rangeMax - rangeMin) * Math.random();
            double randomZ = rangeMin + (rangeMax - rangeMin) * Math.random();

            damaged.getWorld().spawnParticle(Particle.HEART, loc.clone().add(randomX, randomY, randomZ), 0);
        }
    }

    private static String getRandomName() {
        return names.get((int) (Math.random() * names.size()));
    }
}