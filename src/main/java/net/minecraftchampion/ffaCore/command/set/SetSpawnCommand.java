package net.minecraftchampion.ffaCore.command.set;

import net.minecraftchampion.ffaCore.manager.ConfigManager;
import net.minecraftchampion.ffaCore.manager.LocationManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Objects;

public class SetSpawnCommand {

    private final YamlConfiguration config;
    private final LocationManager locationManager;
    private final File file;

    public SetSpawnCommand(YamlConfiguration config, File file) {
        this.config = config;
        this.locationManager = new LocationManager(config);
        this.file = file;
    }

    /**
     * Set the spawn of the FFA
     *
     * @param sender CommandSender
     * @param args Args of the command
     */
    public void setSpawn(CommandSender sender, String[] args) {
        if (args.length == 1) {
            if (Objects.equals(args[0], "spawn")) {
                // set the location of the spawn
                final Location coords = ((Player) sender).getLocation();
                this.locationManager.setLocation(ConfigManager.SPAWN, coords.getX(), coords.getY(), coords.getZ(), coords.getYaw(), coords.getPitch(), file);
                sender.sendMessage(ChatColor.GREEN + "You have perfectly set the spawn");
            }
        }
    }
}
