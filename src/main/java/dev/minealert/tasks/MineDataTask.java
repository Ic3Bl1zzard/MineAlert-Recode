package dev.minealert.tasks;

import dev.minealert.MineAlert;
import dev.minealert.database.cache.CacheSystem;
import dev.minealert.files.OreSettingsFile;
import dev.minealert.files.lang.Lang;
import dev.minealert.modules.alert.StaffAlert;
import dev.minealert.utils.BlockPlacePatchUtil;
import dev.minealert.utils.MessageUtils;
import dev.minealert.utils.MineDataUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;

public class MineDataTask {

    private static int interval;

    public static void initRunnable() {
        interval = OreSettingsFile.getInstance().getFileConfiguration().getInt("interval");
        Bukkit.getScheduler().runTaskTimerAsynchronously(MineAlert.getInstance(), () -> {
            interval--;
            if (interval <= 0) {
                final MineDataUtils instance = MineDataUtils.getInstance();
                BlockPlacePatchUtil.getInstance().getBlockLocations().clear();
                Collection<? extends Player> playerList = Bukkit.getServer().getOnlinePlayers();
                if (playerList.size() != 0) {
                    for (Player all : playerList) {
                        CacheSystem.getInstance().loopCacheSystem(all);
                        if (!all.hasPermission("minealert.notify")) return;
                        if (!StaffAlert.getInstance().containsStaffMember(all.getName())) return;
                        MessageUtils.sendFormattedMessage(Lang.PREFIX.toConfigString() + Lang.DATA_RESET_MESSAGE.toConfigString(), all);
                        instance.clearMineData();
                        instance.addAll(all);
                    }
                }
                interval = OreSettingsFile.getInstance().getFileConfiguration().getInt("interval");
            }
        }, 20, 20);
    }

    public static int getInterval() {
        return interval;
    }
}