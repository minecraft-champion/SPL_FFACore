package net.minecraftchampion.ffaCore.manager;

import net.minecraftchampion.ffaCore.FFACore;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Objects;

public class ConfigManager {

    public static final String VERSION = "0.0 #DO NOT CHANGE THIS";
    public static final String VERSION_PATH = "version";
    public static final String OUTDATED = "outdated";
    public static final String SPAWN = "spawn.";

    private final File config;
    private final YamlConfiguration yml;
    private final File kitFile;
    private final YamlConfiguration kit;
    private final DataManager data;
    private final FFACore main;

    public ConfigManager(FFACore main) {
        this.config = FileManager.getConfig(main);
        this.yml = YamlConfiguration.loadConfiguration(this.config);
        this.kitFile = FileManager.getKitConfig(main);
        this.kit = YamlConfiguration.loadConfiguration(this.kitFile);
        this.data = new DataManager();
        this.main = main;
    }

    /**
     * Load the main configuration
     *
     * @return Main configuration
     */
    public YamlConfiguration load() {
        final String version = this.yml.getString(VERSION_PATH);

        if (!Objects.equals(version, VERSION)) {
            final boolean outdated = this.yml.getBoolean(OUTDATED);
            if (version == null || outdated) {
                generateNewConfig();
                this.data.save(this.yml, this.config);

                ChatManager.sendConsoleMessage("The configuration has been created");
            } else {
                this.yml.set(OUTDATED, true);
                this.data.save(this.yml, this.config);
                ChatManager.sendWarnMessage("The configuration file is outdated! At the next loading, this file will be deleted!");
            }
        } else {
            ChatManager.sendConsoleMessage("The configuration has been loaded");
        }
        return this.yml;
    }

    public YamlConfiguration loadKit() {
        final boolean outdated = this.kit.getBoolean(OUTDATED);
        if (!outdated) {
            generateNewKit();
            this.data.save(this.kit, this.kitFile);
            ChatManager.sendConsoleMessage("The kit configuration has been created");
        } else {
            ChatManager.sendConsoleMessage("The kit configuration has been loaded");
        }
        return this.kit;
    }

    /**
     * Generate the new config
     */
    private void generateNewConfig() {
        this.yml.set(VERSION_PATH, VERSION);
        this.yml.set(OUTDATED, false);

        this.yml.set(SPAWN + "x", 0);
        this.yml.set(SPAWN + "y", 0);
        this.yml.set(SPAWN + "z", 0);
        this.yml.set(SPAWN + "yaw", 0);
        this.yml.set(SPAWN + "pitch", 0);
    }

    private void generateNewKit() {
        this.kit.set(OUTDATED, false);

        String key = KitManager.DEFAULT_KIT_PATH + KitManager.HELMET_PATH;
        this.kit.set(key + KitManager.ITEM_PATH, "IRON_HELMET");
        this.kit.set(key + KitManager.PROTECTION_LEVEL_PATH, 1);

        key = KitManager.DEFAULT_KIT_PATH + KitManager.CHESTPLATE_PATH;
        this.kit.set(key + KitManager.ITEM_PATH, "IRON_CHESTPLATE");
        this.kit.set(key + KitManager.PROTECTION_LEVEL_PATH, 1);

        key = KitManager.DEFAULT_KIT_PATH + KitManager.LEGGINGS_PATH;
        this.kit.set(key + KitManager.ITEM_PATH, "IRON_LEGGINGS");
        this.kit.set(key + KitManager.PROTECTION_LEVEL_PATH, 1);

        key = KitManager.DEFAULT_KIT_PATH + KitManager.BOOTS_PATH;
        this.kit.set(key + KitManager.ITEM_PATH, "IRON_BOOTS");
        this.kit.set(key + KitManager.PROTECTION_LEVEL_PATH, 1);

        key = KitManager.DEFAULT_KIT_PATH + KitManager.SWORD_PATH;
        this.kit.set(key + KitManager.ITEM_PATH, "STONE_SWORD");
        this.kit.set(key + KitManager.SHARPNESS_LEVEL_PATH, 1);
    }

}
