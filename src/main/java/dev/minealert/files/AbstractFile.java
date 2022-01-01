package dev.minealert.files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class AbstractFile {


    private File file;
    private FileConfiguration fileConfiguration;


    public void createFile(JavaPlugin plugin, String fileName) {
        file = new File(plugin.getDataFolder(), fileName);
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public void createFile(JavaPlugin plugin, String folderName, String fileName) {
        file = new File(plugin.getDataFolder() + File.separator + folderName, fileName);
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }


    public void saveFile() {
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }

    public void reloadFile(JavaPlugin plugin, String fileName) {
        if (file == null) {
            file = new File(plugin.getDataFolder(), fileName);
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
        InputStream configStream = plugin.getResource(fileName);
        if (configStream != null) {
            YamlConfiguration newFile = YamlConfiguration.loadConfiguration(new InputStreamReader(configStream));
            getFileConfiguration().setDefaults(newFile);
        }
    }

    public void reloadFile(JavaPlugin plugin, String folderName, String fileName) {
        if (file == null) {
            file = new File(plugin.getDataFolder() + File.separator + folderName, fileName);
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
        InputStream configStream = plugin.getResource(fileName);
        if (configStream != null) {
            YamlConfiguration newFile = YamlConfiguration.loadConfiguration(new InputStreamReader(configStream));
            getFileConfiguration().setDefaults(newFile);
        }
    }


    public boolean isFileNotEmpty() {
        return getFileConfiguration().getKeys(false).size() != 0;
    }

    public boolean containsPath(String path) {
        return getFileConfiguration().contains(path);
    }

    public abstract void registerFile();

    public abstract void setData();

}