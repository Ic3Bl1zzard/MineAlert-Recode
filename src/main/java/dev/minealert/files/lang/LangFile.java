package dev.minealert.files.lang;

import dev.minealert.MineAlert;
import dev.minealert.files.AbstractFile;
import org.bukkit.configuration.Configuration;

public class LangFile extends AbstractFile {

    private static LangFile instance = null;


    public static LangFile getInstance() {
        if (instance == null) {
            instance = new LangFile();
        }
        return instance;
    }

    @Override
    public void registerFile() {
        createFile(MineAlert.getInstance(), "message.yml");
        setData();
        saveFile();
    }

    @Override
    public void setData() {
        if (isFileNotEmpty()) return;
        final Configuration configuration = getFileConfiguration();
        for (Lang item : Lang.CACHE) {
            configuration.set(item.getPath(), item.getValue());
        }
    }
}
