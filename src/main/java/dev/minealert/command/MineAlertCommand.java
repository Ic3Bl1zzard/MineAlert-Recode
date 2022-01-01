package dev.minealert.command;

import dev.minealert.files.lang.Lang;
import dev.minealert.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MineAlertCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command command, String label, String[] args) {
            if (!(s instanceof final Player player)) {
                System.out.println("Only players can use this command!");
                return true;
            }

            if (!player.hasPermission("minealert.help")) {
                MessageUtils.sendFormattedMessage(Lang.PREFIX.toConfigString() + Lang.NO_PERMISSION.toConfigString(), player);
                return true;
            }

            if (args.length > 0) {
                for (SubCommand subCommand : SubCommandRegistry.getInstance().getSubCommandList()) {
                    if (args[0].equalsIgnoreCase(subCommand.getName())) {
                        subCommand.perform(player, args);
                    }
                }
            } else {
                for (SubCommand subCommand : SubCommandRegistry.getInstance().getSubCommandList()) {
                    MessageUtils.sendFormattedMessage(Lang.PREFIX.toConfigString() + subCommand.getSyntax() + " - " + subCommand.getDescription(), player);
                }
            }
        return true;
    }
}
