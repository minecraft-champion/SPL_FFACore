package net.minecraftchampion.ffaCore.listener;

import net.minecraftchampion.ffaCore.manager.ConfigManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamagePlayer implements Listener {

    private final YamlConfiguration config;

    public DamagePlayer(YamlConfiguration config) {
        this.config = config;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void EntityDamageEvent(EntityDamageEvent event){
        final Entity e = event.getEntity();
        final double y = config.getDouble(ConfigManager.SPAWN + "y");

        if (!(e instanceof Player) || y == 0) {
            return;
        }

        final Player player = ((Player) e).getPlayer();

        if (event.getCause() == EntityDamageEvent.DamageCause.FALL){
            event.setCancelled(true);
        }

        if (e.getLocation().getBlockY() >= (y - 1)) {
            event.setCancelled(true);
        }
    }
}
