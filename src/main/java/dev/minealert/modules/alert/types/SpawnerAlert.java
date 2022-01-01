package dev.minealert.modules.alert.types;

import com.google.common.collect.Maps;
import dev.minealert.files.OreSettingsFile;
import dev.minealert.files.lang.Lang;
import dev.minealert.modules.ModuleInfo;
import dev.minealert.modules.alert.AbstractAlertModule;
import org.bukkit.entity.Player;

import java.util.Map;

@ModuleInfo(moduleName = "Spawner Alert",
        moduleDesc = "Spawner Alert for Alert Components",
        abstractFile = OreSettingsFile.class)
public class SpawnerAlert extends AbstractAlertModule {

    private final Map<String, Integer> mineMap = Maps.newConcurrentMap();

    public SpawnerAlert() {

    }

    @Override
    public void callInit(Player player) {
        init(mineMap, player, "alert.spawner-enable", "alert.spawner-amount", Lang.SPAWNER_ALERT_MESSAGE, true);
    }

    public Map<String, Integer> getMineMap() {
        return mineMap;
    }

    @Override
    public String update() {
        return "UPDATE MINEDATA SET SPAWNER=? WHERE UUID=?";
    }

    @Override
    public String resultID() {
        return "SPAWNER";
    }
}


