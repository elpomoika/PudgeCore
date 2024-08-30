package me.elpomoika.pudgecore.listeners;

import me.elpomoika.pudgecore.PudgeCore;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class RightClickListener implements Listener {
    private final ArrayList<Player> listOfPlayers = new ArrayList<>();
    private int taskID = 0;

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        // TODO: когда игрок нажимает на пкм, то проверять если у него в руках предмет с pdc rod, то созадавать абилку
        Player player = event.getPlayer();
        ItemStack heldItem = player.getInventory().getItemInMainHand();

        if (event.getAction() == Action.RIGHT_CLICK_AIR && heldItem.getType() == Material.SLIME_BALL) {
            PersistentDataContainer data = heldItem.getItemMeta().getPersistentDataContainer();

            if (data.has(new NamespacedKey(PudgeCore.getInstance(), "rod"), PersistentDataType.STRING)) {
                // Logic rod item
                if (isPlayerClickOnce(player)) {

                    taskID = new BukkitRunnable() {
                        @Override
                        public void run() {
                            double radius = 2;
                            Location playerLocation = player.getLocation();
                            for (double t = 0; t <= 2*Math.PI*radius; t += 0.05) {
                                double x = (radius * Math.cos(t)) + playerLocation.getX();
                                double z = (playerLocation.getZ() + radius * Math.sin(t));
                                Location particle = new Location(player.getWorld(), x, playerLocation.getY() + 0.76, z);
                                player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, particle, 10, 0, 0, 0, 0.0001);
                                player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 10000, 1));
                                setEffectToNearbyPlayer(player, x, playerLocation.getY(), z);
                            }
                        }
                    }.runTaskTimer(PudgeCore.getInstance(), 0L, 5).getTaskId();

                } else {
                    // disable particles
                    Bukkit.getScheduler().cancelTask(taskID);

                    player.removePotionEffect(PotionEffectType.WITHER);
                }
            }
        }
    }

    public boolean isPlayerClickOnce(Player player) {
        if (!listOfPlayers.contains(player)) {
            listOfPlayers.add(player);
            PudgeCore.getInstance().getLogger().info("player " + player.getDisplayName() + "successful add to array lsit");
            return true;
        }
        listOfPlayers.remove(player);
        PudgeCore.getInstance().getLogger().info("player" + player.getDisplayName() + "has removing from array list");
        return false;
    }

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
