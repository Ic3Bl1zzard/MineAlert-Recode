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
import dev.minealert.tasks.MineDataTask;
import dev.minealert.utils.MessageUtils;
import dev.minealert.utils.MineDataUtils;
import dev.minealert.modules.AbstractModule;
import dev.minealert.modules.alert.AbstractAlertModule;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;

public class MineAlert extends JavaPlugin {

    public void onEnable() {
        registerFiles();
        registerDatabase();
        registerModules();
        registerCommands();
        registerListeners();
        registerTasks();
        MessageUtils.sendConsoleMessage(Lang.PREFIX.toConfigString() + "If any changes were made its best to restart the server!");
    }

    public void onDisable() {
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
        addListener(new ConnectionListener(),
                new BlockListener(), new AbstractMenuListener(), new ItemListeners());
    }

    private void addListener(Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, this);
        }
    }

    private void registerTasks() {
        MineDataTask.initRunnable();
    }

    public static MineAlert getInstance() {
        return MineAlert.getPlugin(MineAlert.class);
    }

    public void doAsync(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(this, runnable);
    }

    public void doSync(Runnable runnable) {
        Bukkit.getScheduler().runTask(this, runnable);
    }
}
