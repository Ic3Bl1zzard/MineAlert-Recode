package dev.minealert.files;

import dev.minealert.MineAlert;
import org.bukkit.configuration.file.FileConfiguration;

public class DatabaseFile extends AbstractFile {

    private static DatabaseFile instance = null;

    public static DatabaseFile getInstance() {
        if (instance == null) instance = new DatabaseFile();
        return instance;
    }

    @Override
    public void registerFile() {
        createFile(MineAlert.getInstance(), "database.yml");
        setData();
        saveFile();
    }

    @Override
    public void setData() {
        if (isFileNotEmpty()) return;
        final FileConfiguration config = getFileConfiguration();
        config.set("host", "localhost");
        config.set("port", 3306);
        config.set("database", "minealertdb");
        config.set("user", "username");
        config.set("password", "admin");
    }
}
