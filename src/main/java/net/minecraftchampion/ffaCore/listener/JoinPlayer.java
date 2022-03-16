package net.minecraftchampion.ffaCore.listener;

import net.minecraftchampion.ffaCore.manager.ConfigManager;
import net.minecraftchampion.ffaCore.manager.KitManager;
import net.minecraftchampion.ffaCore.manager.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;

import java.util.Collection;

public class JoinPlayer implements Listener {

    private final YamlConfiguration config;
    private final LocationManager locationManager;

    public JoinPlayer(YamlConfiguration kit, YamlConfiguration config) {
        this.config = kit;
        this.locationManager = new LocationManager(config);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent e) {
        final Player player = e.getPlayer();
        resetPlayer(player);

        final Location coords = this.locationManager.getLocation(ConfigManager.SPAWN, player.getWorld());

        if (coords.getBlockY() == 0) {
            player.teleport(new Location(Bukkit.getWorld("world"), 4, 4, 0));
        } else {
            player.teleport(coords);
        }

        final PlayerInventory pInventory = player.getInventory();

        final KitManager kitManager = new KitManager(this.config, pInventory);
        kitManager.giveKit();
    }

    private void resetPlayer(Player player) {
        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();
        player.setHealth(20);

        final Collection<PotionEffect> effects = player.getActivePotionEffects();

        if (effects == null) return;

        effects.forEach(effect -> player.removePotionEffect(effect.getType()));
    }
}
