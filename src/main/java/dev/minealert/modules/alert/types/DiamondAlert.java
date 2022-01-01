package dev.minealert.modules.alert.types;

import com.google.common.collect.Maps;
import dev.minealert.files.OreSettingsFile;
import dev.minealert.files.lang.Lang;
import dev.minealert.modules.ModuleInfo;
import dev.minealert.modules.alert.AbstractAlertModule;
import org.bukkit.entity.Player;

import java.util.Map;

@ModuleInfo(moduleName = "Diamond Alert",
        moduleDesc = "Diamond Alert for Alert Components",
        abstractFile = OreSettingsFile.class)
public class DiamondAlert extends AbstractAlertModule {

    private final Map<String, Integer> mineMap = Maps.newConcurrentMap();

    public DiamondAlert() {

    }

    @Override
    public void callInit(Player player) {
        init(mineMap, player, "alert.diamond-enable","alert.diamond-amount", Lang.DIAMOND_ALERT_MESSAGE, false);
    }

    public Map<String, Integer> getMineMap() {
        return mineMap;
    }

    @Override
    public String update() {
        return "UPDATE MINEDATA SET DIAMOND=? WHERE UUID=?";
    }

    @Override
    public String resultID() {
        return "DIAMOND";
    }
}

