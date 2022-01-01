package dev.minealert.modules.alert.types;

import com.google.common.collect.Maps;
import dev.minealert.files.OreSettingsFile;
import dev.minealert.files.lang.Lang;
import dev.minealert.modules.ModuleInfo;
import dev.minealert.modules.alert.AbstractAlertModule;
import org.bukkit.entity.Player;

import java.util.Map;

@ModuleInfo(moduleName = "Deep Diamond Alert",
        moduleDesc = "Deep Diamond Alert for Alert Components",
        abstractFile = OreSettingsFile.class)
public class DeepDiamondAlert extends AbstractAlertModule {

    private final Map<String, Integer> mineMap = Maps.newConcurrentMap();

    public DeepDiamondAlert() {

    }

    @Override
    public void callInit(Player player) {
        init(mineMap, player, "alert.deepdiamond-enable", "alert.deepdiamond-amount", Lang.DEEPDIAMOND_ALERT_MESSAGE, false);
    }

    public Map<String, Integer> getMineMap() {
        return mineMap;
    }

    @Override
    public String update() {
        return "UPDATE MINEDATA SET DEEPDIAMOND=? WHERE UUID=?";
    }

    @Override
    public String resultID() {
        return "DEEPDIAMOND";
    }
}
