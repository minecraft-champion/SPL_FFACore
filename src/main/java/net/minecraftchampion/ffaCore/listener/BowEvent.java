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
        // check if the player is not dead
        final LivingEntity player = e.getEntity();
        if (player == null) return;

        // delete arrow if the player is in the spawn
        final double y = this.config.getDouble(ConfigManager.SPAWN + "y");

        if (player.getLocation().getBlockY() >= y - 1) {
            e.getProjectile().remove();
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onProjectileHit(ProjectileHitEvent e) {
        // delete if it's an arrow
        final Projectile arrow = e.getEntity();
        if (!(arrow instanceof Arrow)) return;
        arrow.remove();
    }

    /**
     * Get coords with a vector
     *
     * @param loc base location
     * @param vec vector
     * @return Location
     */
    private Location getNewCoords(Location loc, Vector vec) {
        return new Location(loc.getWorld(), loc.getX()+vec.getX(), loc.getY()+vec.getY(), loc.getZ()+vec.getZ());
    }
}
