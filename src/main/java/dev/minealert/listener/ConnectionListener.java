package dev.minealert.listener;

import dev.minealert.MineAlert;
import dev.minealert.database.DatabaseUUIDTool;
import dev.minealert.database.SQLUtils;
import dev.minealert.database.cache.CacheSystem;
import dev.minealert.files.DatabaseFile;
import dev.minealert.inventories.SettingsMenu;
import dev.minealert.modules.AbstractModule;
import dev.minealert.modules.alert.AbstractAlertModule;
import dev.minealert.utils.MineDataUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.concurrent.CompletableFuture;


public class ConnectionListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        final Player player = e.getPlayer();
        CompletableFuture.runAsync(() -> {
            for (Class<? extends AbstractAlertModule> alertList : MineDataUtils.getInstance().getModuleAlertList()) {
                AbstractModule.getModule(alertList).ifPresent(alert -> alert.callInit(player));
            }
            if(!DatabaseFile.getInstance().getFileConfiguration().getBoolean("enable")){
                return;
            }
            SQLUtils.getInstance().executeQuery("SELECT * FROM MINEDATA WHERE UUID =?", ps -> ps.setBinaryStream(1, DatabaseUUIDTool.convertUniqueId(player.getUniqueId())), rs -> {
                if (rs.next()) {
                    return rs;
                } else {
                    final String insertQuery = "INSERT INTO MINEDATA (UUID,NAME,ANCIENTDEBRIS,COAL,COPPER,DEEPCOAL,DEEPCOPPER,DEEPDIAMOND,DEEPEMERALD,"
                            + "DEEPGOLD,DEEPIRON,DEEPLAPIS,DEEPREDSTONE,DIAMOND,EMERALD,GOLD,IRON,LAPIS,NETHERGOLD,REDSTONE,SPAWNER) VALUES" +
                            " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    SQLUtils.getInstance().executeUpdate(insertQuery, ps -> {
                        ps.setBinaryStream(1, DatabaseUUIDTool.convertUniqueId(player.getUniqueId()));
                        ps.setString(2, player.getName());
                        for (int i = 3; i < 22; i++)
                            ps.setInt(i, 0);
                    });
                }
                return rs.next();
            });
        });
    }


    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        Bukkit.getScheduler().runTaskAsynchronously(MineAlert.getInstance(), () -> {
            CacheSystem.getInstance().loopCacheSystem(player);
            MineDataUtils.getInstance().removeMiner(player);
        });
        if (!SettingsMenu.itemPickup.contains(player.getName())) return;
        SettingsMenu.itemPickup.remove(player.getName());
    }
}

