package dev.minealert.utils;

import com.google.common.collect.Lists;
import org.bukkit.Location;

import java.util.Collections;
import java.util.List;

public class BlockPlacePatchUtil {

    private static BlockPlacePatchUtil instance;
    private final List<Location> blockLocations = Collections.synchronizedList(Lists.newArrayList());

    public static BlockPlacePatchUtil getInstance() {
        if (instance == null) {
            instance = new BlockPlacePatchUtil();
        }

        return instance;
    }

    public void addBlockLocation(Location location) {
        if (blockLocations.contains(location)) return;
        blockLocations.add(location);
    }

    public boolean containsLocation(Location location) {
        return blockLocations.contains(location);
    }

    public List<Location> getBlockLocations() {
        return blockLocations;
    }
}
