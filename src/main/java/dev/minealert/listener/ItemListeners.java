package dev.minealert.listener;

import dev.minealert.inventories.SettingsMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class ItemListeners implements Listener {

    @EventHandler
    public void onItemPickUp(EntityPickupItemEvent e) {
        if (!(e.getEntity() instanceof Player player)) {
            return;
        }
        if (SettingsMenu.itemPickup.contains(player.getName())) {
            e.setCancelled(true);
        }
    }
}

