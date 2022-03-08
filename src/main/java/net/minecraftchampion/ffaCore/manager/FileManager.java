package net.minecraftchampion.ffaCore.manager;

import net.minecraftchampion.ffaCore.FFACore;

import java.io.File;

public class FileManager {

    /**
     * Get the config (config.yml)
     *
     * @return Config file
     */
    public static File getConfig(FFACore main) {
        return new File(main.getDataFolder(), "config.yml");
    }

    public static File getKitConfig(FFACore main) {
        return new File(main.getDataFolder(), "kit.yml");
    }
}