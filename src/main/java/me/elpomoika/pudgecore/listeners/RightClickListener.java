package me.elpomoika.pudgecore.listeners;

import me.elpomoika.pudgecore.PudgeCore;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
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

import java.util.ArrayList;

public class RightClickListener implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        // TODO: когда игрок нажимает на пкм, то проверять если у него в руках предмет с pdc rod, то созадавать абилку
        Player player = event.getPlayer();
        ItemStack heldItem = player.getInventory().getItemInMainHand();

        if (event.getAction() == Action.RIGHT_CLICK_AIR && heldItem.getType() == Material.SLIME_BALL) {
            PersistentDataContainer data = heldItem.getItemMeta().getPersistentDataContainer();

            if (data.has(new NamespacedKey(PudgeCore.getInstance(), "rod"), PersistentDataType.STRING)) {
                // Logic rod item
                checkIfPlayerHasClicked(player);
                
            }
        }
    }

    public void checkIfPlayerHasClicked(Player player) {
        ArrayList<Player> listOfPlayers = new ArrayList<>();
        if (!listOfPlayers.contains(player)) {
            listOfPlayers.add(player);
        }
        listOfPlayers.remove(player);
    }

}
