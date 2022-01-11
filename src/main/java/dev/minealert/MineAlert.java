package dev.minealert;

import dev.minealert.command.MineAlertCommand;
import dev.minealert.command.SubCommandRegistry;
import dev.minealert.database.cache.CacheSystem;
import dev.minealert.files.DatabaseFile;
import dev.minealert.files.OreSettingsFile;
import dev.minealert.files.lang.Lang;
import dev.minealert.files.lang.LangFile;
import dev.minealert.database.DatabaseInit;
import dev.minealert.listener.AbstractMenuListener;
import dev.minealert.listener.BlockListener;
import dev.minealert.listener.ConnectionListener;
import dev.minealert.listener.ItemListeners;
import dev.minealert.modules.alert.StaffAlert;
import dev.minealert.utils.BlockPlacePatchUtil;
import dev.minealert.utils.MessageUtils;
import dev.minealert.utils.MineDataUtils;
import dev.minealert.modules.AbstractModule;
import dev.minealert.modules.alert.AbstractAlertModule;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class MineAlert extends JavaPlugin {
    private static int interval;

    public void onEnable() {
        registerFiles();
        registerDatabase();
        registerModules();
        registerCommands();
        registerListeners();
        MessageUtils.sendConsoleMessage(Lang.PREFIX.toConfigString() + "If any changes were made its best to restart the server!");
        interval = OreSettingsFile.getInstance().getFileConfiguration().getInt("interval");
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            interval--;
            if (interval <= 0) {
                final MineDataUtils instance = MineDataUtils.getInstance();
                BlockPlacePatchUtil.getInstance().getBlockLocations().clear();
                Collection<? extends Player> playerList = Bukkit.getServer().getOnlinePlayers();
                if (playerList.size() != 0) {
                    CompletableFuture.runAsync(() -> {
                        for (Player all : playerList) {
                            CacheSystem.getInstance().loopCacheSystem(all);
                            instance.clearMineData();
                            instance.addAll(all);
                            if (StaffAlert.getInstance().containsStaffMember(all.getName()))
                                MessageUtils.sendFormattedMessage(Lang.PREFIX.toConfigString() + Lang.DATA_RESET_MESSAGE.toConfigString(), all);
                        }
                    }).whenComplete((unused, throwable) -> interval = OreSettingsFile.getInstance().getFileConfiguration().getInt("interval"));
                }
            }
        }, 20, 20);
    }


    public void onDisable() {
        if (!DatabaseFile.getInstance().getFileConfiguration().getBoolean("enable")) return;
        final MineDataUtils instance = MineDataUtils.getInstance();
        Collection<? extends Player> playerList = Bukkit.getServer().getOnlinePlayers();
        if (playerList.size() == 0) return;
        for (Player all : playerList) {
            CacheSystem.getInstance().loopCacheSystem(all);
            instance.clearMineData();
        }
    }

    private void registerFiles() {
        LangFile.getInstance().registerFile();
        OreSettingsFile.getInstance().registerFile();
        DatabaseFile.getInstance().registerFile();
    }

    private void registerDatabase() {
        if (!DatabaseFile.getInstance().getFileConfiguration().getBoolean("enable")) return;
        DatabaseInit.getInstance().initDatabase();
    }

    private void registerModules() {
        for (Class<? extends AbstractAlertModule> alertModules : MineDataUtils.getInstance().getModuleAlertList()) {
            AbstractModule.loadModule(alertModules);
        }
    }

    private void registerCommands() {
        getCommand("minealert").setExecutor(new MineAlertCommand());
        SubCommandRegistry.getInstance().registerCommands();
    }

    private void registerListeners() {
        addListener(new ConnectionListener(), new BlockListener(), new AbstractMenuListener(), new ItemListeners());
    }

    private void addListener(Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, this);
        }
    }

    public static MineAlert getInstance() {
        return MineAlert.getPlugin(MineAlert.class);
    }

    public static int getInterval() {
        return interval;
    }

    public void doAsync(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(this, runnable);
    }

    public void doSync(Runnable runnable) {
        Bukkit.getScheduler().runTask(this, runnable);
    }
}
