package net.minecraftchampion.ffaCore;

import net.minecraftchampion.ffaCore.command.set.SetCommand;
import net.minecraftchampion.ffaCore.listener.DeathPlayer;
import net.minecraftchampion.ffaCore.listener.JoinPlayer;
import net.minecraftchampion.ffaCore.manager.ConfigManager;
import net.minecraftchampion.ffaCore.manager.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public final class FFACore extends JavaPlugin {

    public static String PLUGIN_NAME = "FFACore";

    public static final Logger LOGGER = Logger.getLogger("Minecraft");
    public static final String PERMISSION = "ffa.core.";

    @Override
    public void onEnable() {
        // Plugin startup logic
        LOGGER.info("[" + PLUGIN_NAME + "] " + PLUGIN_NAME + " has been loaded");

        final ConfigManager configManager = new ConfigManager(this);

        final YamlConfiguration config = configManager.load();
        final YamlConfiguration kit = configManager.loadKit();
        final PluginManager pluginManager = Bukkit.getPluginManager();

        Bukkit.getWorld("world").setGameRuleValue("naturalRegeneration", "false");
        Bukkit.getWorld("world").setGameRuleValue("doMobSpawning", "false");
        Bukkit.getWorld("world").setGameRuleValue("doMobLoot", "false");
        Bukkit.getWorld("world").setGameRuleValue("doDaylightCycle", "false");
        Bukkit.getWorld("world").setTime(6000);

        pluginManager.registerEvents(new JoinPlayer(kit, config), this);
        pluginManager.registerEvents(new DeathPlayer(kit, config), this);

        getCommand("set").setExecutor(new SetCommand(config, FileManager.getConfig(this)));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        LOGGER.info("[" + PLUGIN_NAME + "] " + PLUGIN_NAME + " has been disabled");
    }
}
