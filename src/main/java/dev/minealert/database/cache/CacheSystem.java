package dev.minealert.database.cache;

import dev.minealert.database.DatabaseUUIDTool;
import dev.minealert.files.DatabaseFile;
import dev.minealert.modules.AbstractModule;
import dev.minealert.modules.alert.AbstractAlertModule;
import dev.minealert.database.SQLUtils;
import dev.minealert.utils.MineDataUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Map;

public class CacheSystem {

    private static CacheSystem instance = null;

    public static CacheSystem getInstance() {
        if (instance == null) {
            instance = new CacheSystem();
        }

        return instance;
    }

    public void loopCacheSystem(Player player) {
        if (!DatabaseFile.getInstance().getFileConfiguration().getBoolean("enable")) {
            return;
        }

        StringBuilder statement = new StringBuilder();

        statement.append("UPDATE MINEDATA SET ");
        int maxAlerts = MineDataUtils.getInstance().getModuleAlertList().size();

        for (int i = 0; i < MineDataUtils.getInstance().getModuleAlertList().size(); i++) {
            Class<? extends AbstractAlertModule> alertClasses = MineDataUtils.getInstance().getModuleAlertList().get(i);
            if (AbstractModule.getModule(alertClasses).isPresent()) {
                Map<String, Integer> mineMap = AbstractModule.getModule(alertClasses).get().getMineMap();
                if (mineMap.isEmpty()) return;
                if (!mineMap.containsKey(player.getName()) || mineMap.get(player.getName()) < 1) return;
                int finalI = i;
                AbstractModule.getModule(alertClasses).ifPresent(alert -> statement.append(String.format("%s=%s+%s%s",
                        alert.resultID(), alert.resultID(), mineMap.get(player.getName()) - 1, finalI < maxAlerts - 1 ? ", " : " ")));
            }
        }

        statement.append(" WHERE UUID=?");
        SQLUtils.getInstance().executeUpdate(statement.toString(), ps -> ps.setBinaryStream(1, DatabaseUUIDTool.convertUniqueId(player.getUniqueId())));
    }
}