package me.elpomoika.pudgecore.utils;

import me.elpomoika.pudgecore.PudgeCore;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class RodItem {

    private PudgeCore plugin = PudgeCore.getInstance();

    public void giveRod(Inventory inventory) {

        ItemStack rodItem = new ItemStack(Material.SLIME_BALL);

        // Rod Meta
        ItemMeta rodMeta = rodItem.getItemMeta();
        rodMeta.setDisplayName("Rod");

        // Persistent Data Container
        PersistentDataContainer data = rodMeta.getPersistentDataContainer();
        data.set(new NamespacedKey(PudgeCore.getInstance(), "rod"), PersistentDataType.STRING, "rod");

        rodItem.setItemMeta(rodMeta);
        inventory.setItem(0, rodItem);
    }
}
