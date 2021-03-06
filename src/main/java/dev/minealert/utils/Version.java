package dev.minealert.utils;

import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;
import org.bukkit.Server;

public enum Version implements Comparable<Version> {

    v1_18_R1(16),
    v1_17_R1(15),
    v1_16_R3(14),
    v1_16_R2(13),
    v1_16_R1(12),
    v1_15_R1(11),
    v1_14_R1(10),
    v1_13_R2(9),
    v1_13_R1(8),
    UNKNOWN(-1);

    private final int value;
    public static final String NMS_VERSION;

    static {
        NMS_VERSION = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];;
    }

    Version(int value) {
        this.value = value;
    }


    public static Version getServerVersion() {
        return getServerVersion(Bukkit.getServer());
    }

    /**
     * @param server to get the version from
     * @return the version of the server
     * @throws IllegalArgumentException if server is null
     */
    public static Version getServerVersion( Server server) {
        Preconditions.checkNotNull(server, "Server cannot be null");

        String packageName = server.getClass().getPackage().getName();
        String version = packageName.substring(packageName.lastIndexOf('.') + 1);

        try {
            return valueOf(version.trim());
        } catch (final IllegalArgumentException e) {
            return Version.UNKNOWN;
        }
    }

    /**
     * @param server to check
     * @return true if the server is Paper or false of not
     * @throws IllegalArgumentException if server is null
     */
    public static boolean isPaper(Server server) {
        Preconditions.checkNotNull(server, "Server cannot be null");

        return server.getName().equalsIgnoreCase("Paper");
    }

    /**
     * Checks if the version is newer than the given version
     * <p>
     * If both versions are the same, the method will return false
     *
     * @param version to check against
     * @return true if the version is newer than the given one, otherwise false
     * @throws IllegalArgumentException if version is null
     * @throws IllegalArgumentException if this version or the given version, is the version UNKNOWN
     */
    public boolean isNewerThan(Version version) {
        Preconditions.checkNotNull(version, "Version cannot be null");
        Preconditions.checkState(this != UNKNOWN, "Cannot check, if version UNKNOWN is newer");
        Preconditions.checkState(version != UNKNOWN, "Cannot check, if version UNKNOWN is newer");

        return value > version.value;
    }

    /**
     * Checks if the version is newer or the same than the given version
     *
     * @param version to check against
     * @return true if the version is newer or the same than the given one, otherwise false
     * @throws IllegalArgumentException if version is null
     * @throws IllegalArgumentException if this version or the given version, is the version UNKNOWN
     */
    public boolean isNewerOrSameThan(Version version) {
        Preconditions.checkNotNull(version, "Version cannot be null");
        Preconditions.checkState(this != UNKNOWN, "Cannot check, if version UNKNOWN is newer or same");
        Preconditions.checkState(version != UNKNOWN, "Cannot check, if version UNKNOWN is newer or same");

        return value >= version.value;
    }

    /**
     * Checks if the version is older than the given version
     *
     * @param version to check against
     * @return true if the version is older than the given one, otherwise false
     * @throws IllegalArgumentException if version is null
     * @throws IllegalArgumentException if this version or the given version, is the version UNKNOWN
     */
    public boolean isOlderThan(Version version) {
        Preconditions.checkNotNull(version, "Version cannot be null");
        Preconditions.checkState(this != UNKNOWN, "Cannot check, if version UNKNOWN is older");
        Preconditions.checkState(version != UNKNOWN, "Cannot check, if version UNKNOWN is older");

        return value < version.value;
    }

    /**
     * Checks if the version is older or the same than the given version
     *
     * @param version to check against
     * @return true if the version is older or the same than the given one, otherwise false
     * @throws IllegalArgumentException if version is null
     * @throws IllegalArgumentException if this version or the given version, is the version UNKNOWN
     */
    public boolean isOlderOrSameThan(Version version) {
        Preconditions.checkNotNull(version, "Version cannot be null");
        Preconditions.checkState(this != UNKNOWN, "Cannot check, if version UNKNOWN is older or same");
        Preconditions.checkState(version != UNKNOWN, "Cannot check, if version UNKNOWN is older or same");

        return value <= version.value;
    }

}

