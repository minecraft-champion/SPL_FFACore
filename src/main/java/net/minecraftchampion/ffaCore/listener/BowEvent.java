package net.minecraftchampion.ffaCore.listener;

import net.minecraftchampion.ffaCore.FFACore;
import net.minecraftchampion.ffaCore.manager.ConfigManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;

public class BowEvent implements Listener {

    private final YamlConfiguration config;

    public BowEvent (YamlConfiguration config) {
        this.config = config;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onShootBow(EntityShootBowEvent e) {
        final LivingEntity player = e.getEntity();
        if (player == null) return;

        final double y = this.config.getDouble(ConfigManager.SPAWN + "y");


        if (player.getLocation().getBlockY() >= y - 1) {
            e.getProjectile().remove();
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onProjectileHit(ProjectileHitEvent e) {
        final Projectile arrow = e.getEntity();
        if (!(arrow instanceof Arrow)) return;
        FFACore.LOGGER.info("delete");
        arrow.remove();
    }

    private Location getNewCoords(Location loc, Vector vec) {
        return new Location(loc.getWorld(), loc.getX()+vec.getX(), loc.getY()+vec.getY(), loc.getZ()+vec.getZ());
    }
}
