package tw.joao.utils;

import static tw.joao.utils.SoundUtils.*;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Este comando não pode ser enviado pelo console.");
            return true;
        }

        Player player = (Player) sender;

        if (args[0].equalsIgnoreCase("sounds")) {
            allSoundEffectsMessage(player);
            return true;
        }

        return false;
    }
}
