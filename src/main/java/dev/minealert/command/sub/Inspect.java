package dev.minealert.command.sub;

import dev.minealert.command.SubCommand;
import dev.minealert.files.lang.Lang;
import dev.minealert.inventories.InspectionMenu;
import dev.minealert.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Inspect extends SubCommand {

    @Override
    public String getName() {
        return "inspect";
    }

    @Override
    public String getDescription() {
        return Lang.INSPECT_DESC.toConfigString();
    }

    @Override
    public String getSyntax() {
        return Lang.INSPECT_SYN.toConfigString();
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (args.length <= 1) return;
        final Player player = (Player) sender;
        if (!player.hasPermission("minealert.inspect")) {
            MessageUtils.sendFormattedMessage(Lang.PREFIX.toConfigString() + Lang.NO_PERMISSION.toConfigString(), player);
            return;
        }
        Player target = Bukkit.getPlayer(args[1]);
        if (target == null) {
            MessageUtils.sendFormattedMessage(Lang.PREFIX.toConfigString() + Lang.NO_PLAYER_EXIST.toConfigString(), player);
            return;
        }

        new InspectionMenu(target).open(player);
    }
}
