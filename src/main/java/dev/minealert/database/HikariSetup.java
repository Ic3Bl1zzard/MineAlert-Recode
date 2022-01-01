package dev.minealert.database;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class HikariSetup {

    private DatabaseInfoRecord databaseInfoRecord;
    private HikariDataSource dataSource;

    protected HikariSetup init(DatabaseInfoRecord databaseInfoRecord) {
        this.databaseInfoRecord = databaseInfoRecord;
        dataSource = new HikariDataSource();
        setDataSourceSettings();
        return this;
    }

    private void setDataSourceSettings() {
        dataSource.setMaximumPoolSize(10);
        dataSource.setJdbcUrl("jdbc:mysql://" + databaseInfoRecord.host() + ":" + databaseInfoRecord.port() + "/" + databaseInfoRecord.database());
        dataSource.addDataSourceProperty("serverName", databaseInfoRecord.host());
        dataSource.addDataSourceProperty("port", databaseInfoRecord.port());
        dataSource.addDataSourceProperty("databaseName", databaseInfoRecord.database());
        dataSource.addDataSourceProperty("user", databaseInfoRecord.user());
        dataSource.addDataSourceProperty("password", databaseInfoRecord.password());
    }

    protected void disconnect() {
        if (dataSource == null) return;
        if (!dataSource.isClosed()) {
            dataSource.close();
        }
    }

    protected HikariSetup createTable(String tableOutput) {
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(tableOutput)) {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return this;
    }

    public HikariDataSource getDataSource() {
        return dataSource;
    }
}
