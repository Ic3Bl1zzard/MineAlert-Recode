package dev.minealert.command.sub;

import dev.minealert.command.SubCommand;
import dev.minealert.files.lang.Lang;
import dev.minealert.inventories.InspectionMenu;
import dev.minealert.inventories.SettingsMenu;
import dev.minealert.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Settings extends SubCommand {

    @Override
    public String getName() {
        return "settings";
    }

    @Override
    public String getDescription() {
        return Lang.SETTINGS_DESC.toConfigString();
    }

    @Override
    public String getSyntax() {
        return Lang.SETTINGS_SYN.toConfigString();
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        final Player player = (Player) sender;
        if (!player.hasPermission("minealert.settings")) {
            MessageUtils.sendFormattedMessage(Lang.PREFIX.toConfigString() + Lang.NO_PERMISSION.toConfigString(), player);
            return;
        }
        new SettingsMenu().open(player);
    }
}
