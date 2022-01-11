package dev.minealert.database;

import com.zaxxer.hikari.HikariDataSource;
import dev.minealert.files.DatabaseFile;
import org.bukkit.configuration.Configuration;

public class DatabaseInit extends HikariSetup {

    private static DatabaseInit instance = null;

    public static DatabaseInit getInstance() {
        if (instance == null) instance = new DatabaseInit();
        return instance;
    }

    public DatabaseInit initDatabase() {
        final Configuration configuration = DatabaseFile.getInstance().getFileConfiguration();
        final String host = configuration.getString("host");
        final int port = configuration.getInt("port");
        final String database = configuration.getString("database");
        final String user = configuration.getString("user");
        final String password = configuration.getString("password");
        DatabaseInfoRecord infoRecord = new DatabaseInfoRecord(host, port, database, user, password);
        init(SQLTypes.fromName(configuration.getString("driver"), infoRecord),
                infoRecord, configuration.getInt("timeout"), configuration.getInt("maxpool"));
        initTables();
        return this;
    }

    private void initTables() {
         String playerDataTable = "CREATE TABLE IF NOT EXISTS MINEDATA(UUID BINARY(16) NOT NULL, " +
                "NAME VARCHAR(16) NOT NULL, ANCIENTDEBRIS INT(8) NOT NULL, COAL INT(8) NOT NULL, COPPER INT(8) NOT NULL, DEEPCOAL INT(8) NOT NULL"
                + ", DEEPCOPPER INT(8) NOT NULL, DEEPDIAMOND INT(8) NOT NULL, DEEPEMERALD INT(8) NOT NULL, DEEPGOLD INT(8) NOT NULL, DEEPIRON INT(8) NOT NULL, "
                + "DEEPLAPIS INT(8) NOT NULL, DEEPREDSTONE INT(8) NOT NULL, DIAMOND INT(8) NOT NULL, EMERALD INT(8) NOT NULL, GOLD INT(8) NOT NULL, " +
                "IRON INT(8) NOT NULL, LAPIS INT(8) NOT NULL, NETHERGOLD INT(8) NOT NULL, REDSTONE INT(8) NOT NULL, SPAWNER INT(8) NOT NULL, PRIMARY KEY (UUID))";
        createTable(playerDataTable);
    }

    @Override
    public HikariDataSource getDataSource() {
        return super.getDataSource();
    }

    @Override
    public void disconnect() {
        super.disconnect();
    }
}

