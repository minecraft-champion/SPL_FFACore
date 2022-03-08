package net.minecraftchampion.ffaCore.manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class LocationManager {

    private final YamlConfiguration config;

    public LocationManager (YamlConfiguration config) {
        this.config = config;
    }

    /**
     * Get the location in the config file
     *
     * @param parentPath Path of the parent (e.g. spawn.)
     * @param world World of the location
     * @return Location
     */
    public Location getLocation(String parentPath, World world) {
        final double x = this.config.getDouble(parentPath + "x");
        final double y = this.config.getDouble(parentPath + "y");
        final double z = this.config.getDouble(parentPath + "z");
        final float yaw = (float) this.config.getDouble(parentPath + "yaw");
        final float pitch = (float) this.config.getDouble(parentPath + "pitch");
        return new Location(world, x, y, z, yaw, pitch);
    }

    /**
     * Get the location in the config file
     *
     * @param parentPath Path of the parent (e.g. spawn.)
     * @param world World of the location
     * @return Location
     */
    public Location getLocation(String parentPath, String world) {
        return getLocation(parentPath, Bukkit.getWorld(world));
    }

    /**
     * Get the location in the config file
     *
     * @param parentPath Path of the parent (e.g. spawn.)
     * @return Location
     */
    public Location getLocation(String parentPath) {
        return getLocation(parentPath, Bukkit.getWorld("world"));
    }

    /**
     * Set a location in the config file
     *
     * @param parentPath Path of the parent (e.g. spawn.)
     * @param x Coords X
     * @param y Coords Y
     * @param z Coords Z
     * @param yaw Yaw
     * @param pitch Pitch
     * @param file File where the config file come from
     */
    public void setLocation(String parentPath, double x, double y, double z, double yaw, double pitch, File file) {
        final DataManager dataManager = new DataManager();
        this.config.set(parentPath + "x", x);
        this.config.set(parentPath + "y", y);
        this.config.set(parentPath + "z", z);
        this.config.set(parentPath + "yaw", yaw);
        this.config.set(parentPath + "pitch", pitch);
        dataManager.save(this.config, file);
    }

}
