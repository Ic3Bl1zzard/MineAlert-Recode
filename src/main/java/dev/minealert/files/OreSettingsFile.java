package dev.minealert.files;

import dev.minealert.MineAlert;
import org.bukkit.configuration.file.FileConfiguration;

public class OreSettingsFile extends AbstractFile {

    private static OreSettingsFile instance = null;

    public static OreSettingsFile getInstance() {
        if (instance == null) instance = new OreSettingsFile();
        return instance;
    }

    @Override
    public void registerFile() {
        createFile(MineAlert.getInstance(), "oresettings.yml");
        setData();
        saveFile();
    }

    @Override
    public void setData() {
        if (isFileNotEmpty()) return;
        final FileConfiguration config = getFileConfiguration();
        config.set("interval", 300);
        config.set("alert.ancientdebris-enable", true);
        config.set("alert.ancientdebris-amount", 5);
        config.set("alert.coal-enable", true);
        config.set("alert.coal-amount", 5);
        config.set("alert.copper-enable", true);
        config.set("alert.copper-amount", 5);
        config.set("alert.deepcoal-enable", true);
        config.set("alert.deepcoal-amount", 5);
        config.set("alert.deepcopper-enable", true);
        config.set("alert.deepcopper-amount", 5);
        config.set("alert.deepdiamond-enable", true);
        config.set("alert.deepdiamond-amount", 5);
        config.set("alert.deepemerald-enable", true);
        config.set("alert.deepemerald-amount", 5);
        config.set("alert.deepgold-enable", true);
        config.set("alert.deepgold-amount", 5);
        config.set("alert.deepiron-enable", true);
        config.set("alert.deepiron-amount", 5);
        config.set("alert.deeplapis-enable", true);
        config.set("alert.deeplapis-amount", 5);
        config.set("alert.deepredstone-enable", true);
        config.set("alert.deepredstone-amount", 5);
        config.set("alert.diamond-enable", true);
        config.set("alert.diamond-amount", 5);
        config.set("alert.emerald-enable", true);
        config.set("alert.emerald-amount", 5);
        config.set("alert.gold-enable", true);
        config.set("alert.gold-amount", 5);
        config.set("alert.iron-enable", true);
        config.set("alert.iron-amount", 5);
        config.set("alert.lapis-enable", true);
        config.set("alert.lapis-amount", 5);
        config.set("alert.nethergold-enable", true);
        config.set("alert.nethergold-amount", 5);
        config.set("alert.redstone-enable", true);
        config.set("alert.redstone-amount", 5);
        config.set("alert.spawner-enable", true);
        config.set("alert.spawner-amount", 5);
    }
}
