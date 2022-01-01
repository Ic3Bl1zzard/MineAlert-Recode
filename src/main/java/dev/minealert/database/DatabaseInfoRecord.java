package dev.minealert.database;

public record DatabaseInfoRecord(String host, int port, String database, String user, String password) {

    public String host() {
        return host;
    }

    public int port() {
        return port;
    }

    public String database() {
        return database;
    }

    public String user() {
        return user;
    }

    public String password() {
        return password;
    }
}
