package dev.minealert.command.sub;

import dev.minealert.MineAlert;
import dev.minealert.command.SubCommand;
import dev.minealert.files.DatabaseFile;
import dev.minealert.files.OreSettingsFile;
import dev.minealert.files.lang.Lang;
import dev.minealert.files.lang.LangFile;
import dev.minealert.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadConfig extends SubCommand {

        @Override
        public String getName() {
            return "reload";
        }


        @Override
        public String getDescription() {
            return Lang.RELOAD_DESC.toConfigString();
        }

        @Override
        public String getSyntax() {
            return Lang.RELOAD_SYN.toConfigString();
        }

        @Override
        public void perform(CommandSender sender, String[] args) {
            final Player player = (Player) sender;
            if (!player.hasPermission("minealert.admin")) {
                MessageUtils.sendFormattedMessage(Lang.PREFIX.toConfigString() + Lang.NO_PERMISSION.toConfigString(), player);
                return;
            }
            LangFile.getInstance().reloadFile(MineAlert.getInstance(), "messages.yml");
            DatabaseFile.getInstance().reloadFile(MineAlert.getInstance(), "database.yml");
            OreSettingsFile.getInstance().reloadFile(MineAlert.getInstance(), "oresettings.yml");
            MessageUtils.sendFormattedMessage(Lang.PREFIX.toConfigString() + Lang.RELOAD_MESSAGE.toConfigString(), player);
        }
    }
