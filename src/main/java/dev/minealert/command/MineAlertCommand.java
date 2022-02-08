package dev.minealert.command;

import dev.minealert.files.lang.Lang;
import dev.minealert.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MineAlertCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command command, String label, String[] args) {
        if (!s.hasPermission("minealert.help")) {
            MessageUtils.sendFormattedMessage(Lang.PREFIX.toConfigString() + Lang.NO_PERMISSION.toConfigString(), s);
            return true;
        }

        if (args.length > 0) {
            for (SubCommand subCommand : SubCommandRegistry.getInstance().getSubCommandList()) {
                if (args[0].equalsIgnoreCase(subCommand.getName())) {
                    subCommand.perform(s, args);
                }
            }
        } else {
            for (SubCommand subCommand : SubCommandRegistry.getInstance().getSubCommandList()) {
                MessageUtils.sendFormattedMessage(Lang.PREFIX.toConfigString() + subCommand.getSyntax() + " - " + subCommand.getDescription(), s);
            }
        }
        return true;
    }
}
