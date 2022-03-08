package net.minecraftchampion.ffaCore.command.set;

import net.minecraftchampion.ffaCore.FFACore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Objects;

public class SetCommand implements CommandExecutor {

    private final YamlConfiguration config;
    private final File file;

    public final static String PERMISSION = FFACore.PERMISSION + "set";

    public SetCommand(YamlConfiguration config, File file) {
        this.config = config;
        this.file = file;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && Objects.equals(label, "set") && args.length != 0) {
            if (!sender.hasPermission(PERMISSION)) {
                sender.sendMessage(ChatColor.RED + "You don't have the permission to use this command!\nPermission required: " + PERMISSION);
                return true;
            }
            if (Objects.equals(args[0], "spawn")) {
                new SetSpawnCommand(config, this.file).setSpawn(sender, args);
            }
            return true;
        }
        return false;
    }
}
