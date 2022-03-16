package net.minecraftchampion.ffaCore;

import net.minecraftchampion.ffaCore.command.set.SetCommand;
import net.minecraftchampion.ffaCore.listener.BowEvent;
import net.minecraftchampion.ffaCore.listener.DamagePlayer;
import net.minecraftchampion.ffaCore.listener.DeathPlayer;
import net.minecraftchampion.ffaCore.listener.JoinPlayer;
import net.minecraftchampion.ffaCore.manager.ConfigManager;
import net.minecraftchampion.ffaCore.manager.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

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

        final World world = Bukkit.getWorld("world");

        world.setGameRuleValue("naturalRegeneration", "false");
        world.setGameRuleValue("doMobSpawning", "false");
        world.setGameRuleValue("doMobLoot", "false");
        world.setGameRuleValue("doDaylightCycle", "false");
        world.setTime(6000);
        world.setPVP(true);

        pluginManager.registerEvents(new JoinPlayer(kit, config), this);
        pluginManager.registerEvents(new DeathPlayer(kit, config), this);
        pluginManager.registerEvents(new DamagePlayer(config), this);
        pluginManager.registerEvents(new BowEvent(config), this);

        getCommand("set").setExecutor(new SetCommand(config, FileManager.getConfig(this)));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        LOGGER.info("[" + PLUGIN_NAME + "] " + PLUGIN_NAME + " has been disabled");
    }
}
