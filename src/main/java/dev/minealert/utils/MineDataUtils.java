package dev.minealert.utils;

import dev.minealert.modules.AbstractModule;
import dev.minealert.modules.alert.AbstractAlertModule;
import dev.minealert.modules.alert.types.*;
import org.bukkit.entity.Player;

import java.util.*;

public class MineDataUtils {

    private static MineDataUtils instance = null;

    public static MineDataUtils getInstance() {
        if (instance == null) {
            instance = new MineDataUtils();
        }

        return instance;
    }

    private final List<Class<? extends AbstractAlertModule>> moduleAlertList = Collections.synchronizedList(Arrays.asList(
            AncientDebrisAlert.class, CoalAlert.class, CopperAlert.class, DeepCoalAlert.class, DeepCopperAlert.class, DeepDiamondAlert.class,
            DeepEmeraldAlert.class, DeepGoldAlert.class, DeepIronAlert.class, DeepLapisAlert.class, DeepRedstoneAlert.class,
            DiamondAlert.class, EmeraldAlert.class, GoldAlert.class, IronAlert.class, LapisAlert.class, NetherGoldAlert.class, RedstoneAlert.class,
            SpawnerAlert.class));

    public void clearMineData() {
        for (Class<? extends AbstractAlertModule> alertClasses : moduleAlertList) {
            AbstractModule.getModule(alertClasses).ifPresent(alerts -> alerts.clearData(alerts.getMineMap()));
        }
    }

    public void removeMiner(Player miner) {
        for (Class<? extends AbstractAlertModule> alertClasses : moduleAlertList) {
            if (AbstractModule.getModule(alertClasses).isPresent()) {
                Map<String, Integer> mineMap = AbstractModule.getModule(alertClasses).get().getMineMap();
                AbstractModule.getModule(alertClasses).ifPresent(alert -> mineMap.remove(miner.getName()));
            }
        }
    }

    public void addAll(Player allInstance) {
        for (Class<? extends AbstractAlertModule> alertClasses : moduleAlertList) {
            AbstractModule.getModule(alertClasses).ifPresent(alert -> alert.callInit(allInstance));
        }
    }


    public List<Class<? extends AbstractAlertModule>> getModuleAlertList() {
        return moduleAlertList;
    }
}
