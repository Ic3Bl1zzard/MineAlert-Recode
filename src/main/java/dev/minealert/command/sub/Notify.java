package dev.minealert.command.sub;

import dev.minealert.command.SubCommand;
import dev.minealert.files.lang.Lang;
import dev.minealert.modules.alert.StaffAlert;
import dev.minealert.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Notify extends SubCommand {

    @Override
    public String getName() {
        return "notify";
    }


    @Override
    public String getDescription() {
        return Lang.NOTIFY_DESC.toConfigString();
    }

    @Override
    public String getSyntax() {
        return Lang.NOTIFY_SYN.toConfigString();
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (!(sender instanceof final Player player)) {
            System.out.println("Only players can use this command!");
            return;
        }
        if (!player.hasPermission("minealert.notify")) {
            MessageUtils.sendFormattedMessage(Lang.PREFIX.toConfigString() + Lang.NO_PERMISSION.toConfigString(), player);
            return;
        }
        StaffAlert.getInstance().toggleAlert(player);
    }
}
