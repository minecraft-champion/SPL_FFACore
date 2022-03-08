package net.minecraftchampion.ffaCore.listener;

import net.minecraftchampion.ffaCore.manager.ConfigManager;
import net.minecraftchampion.ffaCore.manager.KitManager;
import net.minecraftchampion.ffaCore.manager.LocationManager;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class DeathPlayer implements Listener {

    private final YamlConfiguration config;
    private final LocationManager locationManager;

    public DeathPlayer (YamlConfiguration kit, YamlConfiguration config) {
        this.config = kit;
        this.locationManager = new LocationManager(config);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerDeath(PlayerDeathEvent e) {
        // remove drops
        e.getDrops().removeAll(e.getDrops());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        final Player player = e.getPlayer();

        final Location coords = this.locationManager.getLocation(ConfigManager.SPAWN, player.getWorld());
        e.setRespawnLocation(coords);

        final KitManager kitManager = new KitManager(this.config, player.getInventory());
        kitManager.giveKit();
    }
}