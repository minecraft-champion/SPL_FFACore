package net.minecraftchampion.ffaCore.listener;

import net.minecraftchampion.ffaCore.manager.ConfigManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

public class BowEvent implements Listener {

    private final YamlConfiguration config;

    public BowEvent (YamlConfiguration config) {
        this.config = config;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onShootBow(EntityShootBowEvent e) {
        final LivingEntity player = e.getEntity();
        if (player == null) return;

        final double y = this.config.getDouble(ConfigManager.SPAWN + "y");


        if (player.getLocation().getBlockY() >= y - 1) {
            e.getProjectile().remove();
        }
    }
}
