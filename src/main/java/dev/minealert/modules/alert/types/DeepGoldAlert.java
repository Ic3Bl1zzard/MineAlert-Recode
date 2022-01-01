package dev.minealert.modules.alert.types;

import com.google.common.collect.Maps;
import dev.minealert.files.OreSettingsFile;
import dev.minealert.files.lang.Lang;
import dev.minealert.modules.ModuleInfo;
import dev.minealert.modules.alert.AbstractAlertModule;
import org.bukkit.entity.Player;

import java.util.Map;

@ModuleInfo(moduleName = "Deep Gold Alert",
        moduleDesc = "Deep Gold Alert for Alert Components",
        abstractFile = OreSettingsFile.class)
public class DeepGoldAlert extends AbstractAlertModule {

    private final Map<String, Integer> mineMap = Maps.newConcurrentMap();

    public DeepGoldAlert() {

    }

    @Override
    public void callInit(Player player) {
        init(mineMap, player, "alert.deepgold-enable", "alert.deepgold-amount", Lang.DEEPGOLD_ALERT_MESSAGE, false);
    }

    public Map<String, Integer> getMineMap() {
        return mineMap;
    }

    @Override
    public String update() {
        return "UPDATE MINEDATA SET DEEPGOLD=? WHERE UUID=?";
    }

    @Override
    public String resultID() {
        return "DEEPGOLD";
    }
}

