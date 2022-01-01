package dev.minealert.utils.inventory;


import com.google.common.collect.Maps;
import dev.minealert.utils.FormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractMenu {

    private static final Map<UUID, AbstractMenu> inventoriesByUUID = Maps.newConcurrentMap();
    private static final Map<UUID, UUID> openedInventories = Maps.newConcurrentMap();
    private final UUID uuid;
    private final Inventory abstractMenu;
    private final Map<Integer, IMenuClick> clickMap;

    /**
     * Column IDs:
     * inventories-by-UUID
     * opened-inventories
     */

    public AbstractMenu(int slots, String name) {
        uuid = UUID.randomUUID();
        this.abstractMenu = Bukkit.createInventory(null, slots, FormatUtils.color(name));
        clickMap = new ConcurrentHashMap<>();
        inventoriesByUUID.put(getUUID(), this);
    }


    public void setItem(int slot, ItemStack item, IMenuClick menuAction) {
        abstractMenu.setItem(slot, item);
        if (menuAction != null) {
            clickMap.put(slot, menuAction);
        }
    }

    public void setItem(int slot, ItemStack item) {
        setItem(slot, item, null);
    }

    public void open(Player player) {
        player.openInventory(abstractMenu);
        openedInventories.put(player.getUniqueId(), getUUID());
    }


    public Inventory getAbstractMenu() {
        return abstractMenu;
    }

    public UUID getUUID() {
        return uuid;
    }

    public static Map<UUID, AbstractMenu> getInventoriesByUUID() {
        return inventoriesByUUID;
    }

    public static Map<UUID, UUID> getInventoriesOpened() {
        return openedInventories;
    }

    public void delete() {
        for (Player all : Bukkit.getOnlinePlayers()) {
            UUID playerUUID = getInventoriesOpened().get(all.getUniqueId());
            if (playerUUID.equals(getUUID())) {
                all.closeInventory();
            }
        }
        getInventoriesByUUID().remove(getUUID());
    }

    public Map<Integer, IMenuClick> getActions() {
        return clickMap;
    }
}
