package net.minecraftchampion.ffaCore.manager;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;

public class DataManager {

    public DataManager() {}

    /**
     * Save YML File
     *
     * @param config Config to save (YamlConfiguration)
     * @param basesFile File to save (File)
     */
    public void save(YamlConfiguration config, File basesFile) {
        try {
            config.save(basesFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}