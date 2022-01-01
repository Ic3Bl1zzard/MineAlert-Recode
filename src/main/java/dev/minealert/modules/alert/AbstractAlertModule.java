package dev.minealert.modules.alert;

import dev.minealert.files.OreSettingsFile;
import dev.minealert.files.lang.Lang;
import dev.minealert.modules.AbstractModule;
import dev.minealert.database.cache.CacheRequest;
import dev.minealert.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public abstract class AbstractAlertModule extends AbstractModule implements CacheRequest<String, String> {

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    private String type;

    public AbstractAlertModule() {
        MessageUtils.sendConsoleMessage(Lang.PREFIX.toConfigString() + getName() + "(" + getDescription() + ") has loaded correctly!");
    }

    public void setType(String type) {
        this.type = type;
    }

    protected void addToMap(Map<String, Integer> minedMap, Player miner, String enablePath) {
        if (!isEnabled(enablePath)) return;
        final String name = miner.getName();
        if (!minedMap.containsKey(name)) {
            minedMap.put(name, 1 );
        } else {
            minedMap.put(name, minedMap.get(name) + 1);
        }
    }

    protected void notifyStaff(Map<String, Integer> minedMap, Player miner, String pathAmount, Lang value, boolean isSpawner) {
        final String name = miner.getName();
        if (minedMap.get(name) >= OreSettingsFile.getInstance().getFileConfiguration().getInt(pathAmount)) {
            Bukkit.getOnlinePlayers().forEach(staff -> {
                if (!staff.hasPermission("minealert.notify")) {
                    return;
                }

                final StaffAlert instance = StaffAlert.getInstance();
                if (!instance.containsStaffMember(staff.getName())) {
                    return;
                }
                String prefix = Lang.PREFIX.toConfigString();
                String message = value.toConfigString();

                message = message.replace("%time%", dtf.format(LocalDateTime.now()));
                message = message.replace("%player%", name);
                message = message.replace("%amount%", minedMap.get(name).toString());
                if (isSpawner) {
                    message = message.replace("%mobtype%", type);
                }
                MessageUtils.sendFormattedMessage(prefix + message, staff);
            });
        }
    }

    protected void init(Map<String, Integer> minedMap, Player miner, String enablePath, String pathAmount, Lang value, boolean isSpawner) {
        addToMap(minedMap, miner, enablePath);
        notifyStaff(minedMap, miner, pathAmount, value, isSpawner);
    }

    public boolean isEnabled(String path) {
        return getAbstractConfig(OreSettingsFile.class).getFileConfiguration().getBoolean(path);
    }

    public void clearData(Map<String, Integer> map) {
        if (map.isEmpty()) return;
        map.clear();
    }


    public abstract Map<String, Integer> getMineMap();

    public abstract void callInit(Player player);

}
