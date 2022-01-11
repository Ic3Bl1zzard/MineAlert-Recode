package dev.minealert.command.sub;

import dev.minealert.MineAlert;
import dev.minealert.command.SubCommand;
import dev.minealert.files.lang.Lang;
import dev.minealert.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Interval extends SubCommand {

    @Override
    public String getName() {
        return "interval";
    }

    @Override
    public String getDescription() {
        return Lang.INTERVAL_DESC.toConfigString();
    }

    @Override
    public String getSyntax() {
        return Lang.INTERVAL_SYN.toConfigString();
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        final Player player = (Player) sender;
        if (!player.hasPermission("minealert.notify")) {
            MessageUtils.sendFormattedMessage(Lang.PREFIX.toConfigString() + Lang.NO_PERMISSION.toConfigString(), player);
            return;
        }
        String message = Lang.INTERVAL_MESSAGE.toConfigString();
        message = message.replace("%amount%", String.valueOf(MineAlert.getInterval()));
        MessageUtils.sendFormattedMessage(Lang.PREFIX.toConfigString() + message, player);
    }
}
