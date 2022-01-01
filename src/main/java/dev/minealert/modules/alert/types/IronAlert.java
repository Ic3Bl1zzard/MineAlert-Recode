package dev.minealert.modules.alert.types;

import com.google.common.collect.Maps;
import dev.minealert.files.OreSettingsFile;
import dev.minealert.files.lang.Lang;
import dev.minealert.modules.ModuleInfo;
import dev.minealert.modules.alert.AbstractAlertModule;
import org.bukkit.entity.Player;

import java.util.Map;

@ModuleInfo(moduleName = "Iron Alert",
        moduleDesc = "Iron Alert for Alert Components",
        abstractFile = OreSettingsFile.class)
public class IronAlert extends AbstractAlertModule {

    private final Map<String, Integer> mineMap = Maps.newConcurrentMap();

    public IronAlert() {

    }

    @Override
    public void callInit(Player player) {
        init(mineMap, player, "alert.iron-enable","alert.iron-amount", Lang.IRON_ALERT_MESSAGE, false);
    }

    public Map<String, Integer> getMineMap() {
        return mineMap;
    }

    @Override
    public String update() {
        return "UPDATE MINEDATA SET IRON=? WHERE UUID=?";
    }

    @Override
    public String resultID() {
        return "IRON";
    }
}


