package me.elpomoika.pudgecore;

import me.elpomoika.pudgecore.commands.ActivatePudgeMod;
import me.elpomoika.pudgecore.listeners.RightClickListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class PudgeCore extends JavaPlugin {

    private static PudgeCore instance;

    @Override
    public void onEnable() {
        instance = this;
        getCommand("pudge").setExecutor(new ActivatePudgeMod());
        getServer().getPluginManager().registerEvents(new RightClickListener(), this);

        getConfig().options().copyDefaults(true);
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Pudge is crying");
    }

    public static PudgeCore getInstance() {
        return instance;
    }
}
