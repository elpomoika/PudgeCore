package me.elpomoika.pudgecore.utils;

import me.elpomoika.pudgecore.listeners.RightClickListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

import static me.elpomoika.pudgecore.listeners.RightClickListener.taskID;

public class PlayerUtils {

    public void setEffectToNearbyPlayer(Player player, double x, double y, double z) {
        ArrayList<Player> nearbyPlayers = new ArrayList<>();
        for (Entity entity : player.getNearbyEntities(x, y, z)) {
            if (entity instanceof Player) {
                nearbyPlayers.add((Player) entity);
            }
        }

        if (Bukkit.getScheduler().isCurrentlyRunning(taskID)) {
            for (Player p : nearbyPlayers) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 1000, 1));
            }
        } else {
            for (Player p : nearbyPlayers) {
                p.removePotionEffect(PotionEffectType.WITHER);
            }
        }
    }
}
