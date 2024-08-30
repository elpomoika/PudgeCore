package me.elpomoika.pudgecore.commands;

import me.elpomoika.pudgecore.PudgeCore;
import me.elpomoika.pudgecore.utils.RodItem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ActivatePudgeMod implements CommandExecutor {

    RodItem rodItem = new RodItem();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (!(sender instanceof Player)) {
            PudgeCore.getInstance().getLogger().info("Only players can do this");
            return false;
        }

        Player player = (Player) sender;
        Inventory playerInventory = player.getInventory();

        // if player inventory non-empty clear this
        if (!playerInventory.isEmpty()) {
            player.sendMessage("Your inventory has been cleaned");
            playerInventory.clear();
        }

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', PudgeCore.getInstance().getConfig().getString("pudge-activate-message")));

        // Шишкиииии (Give rod item)

        rodItem.giveRod(playerInventory);



        return true;
    }
}