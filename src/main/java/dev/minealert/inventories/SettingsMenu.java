package dev.minealert.inventories;

import com.google.common.collect.Lists;
import dev.minealert.utils.FormatUtils;
import dev.minealert.utils.ItemBuilder;
import dev.minealert.utils.MessageUtils;
import dev.minealert.utils.inventory.AbstractMenu;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class SettingsMenu extends AbstractMenu {

    public static List<String> itemPickup = Lists.newArrayList();

    public SettingsMenu() {
        super(9, "Settings Menu");
        final ItemBuilder.Builder builder = ItemBuilder.Builder.getInstance();
        setItem(1, builder.itemType(Material.EMERALD).itemAmount(1).itemName("&aEnable Item Pickup").itemLore(
                List.of("&7Click this to enable Item Pick Up")).build(), player -> {
            if (!itemPickup.contains(player.getName())) {
                MessageUtils.sendActionBar(player, "&7You already have &aItem Pickup &7enabled! &7Just enable it!");
                player.getOpenInventory().close();
            } else {
                itemPickup.remove(player.getName());
                MessageUtils.sendActionBar(player, "&7You have enabled &aItem Pickup&7!");
                player.getOpenInventory().close();
            }
        });

        setItem(2, builder.itemType(Material.ENDER_EYE).itemAmount(1).itemName("&aEnable Night Vision").itemLore(
                List.of("&7Click this to enable Night Vision")).build(), player -> {
            if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                MessageUtils.sendActionBar(player, "&7You already have &aNight Vision! &7Just disable it!");
                player.getOpenInventory().close();
            } else {
                player.addPotionEffect(PotionEffectType.NIGHT_VISION.createEffect(Integer.MAX_VALUE, 3));
                MessageUtils.sendActionBar(player, "&7You have enabled &aNight Vision&7!");
                player.getOpenInventory().close();
            }
        });

        setItem(3, ItemBuilder.Builder.getInstance().itemType(Material.GHAST_TEAR).itemAmount(1).itemName("&aEnable Spectator Mode").itemLore(
                List.of("&7Click this to enable Spectator Mode")).build(), player -> {
            if (player.getGameMode() == GameMode.SPECTATOR) {
                MessageUtils.sendActionBar(player, "&7You're already in &aSpectator &7mode! Just disable it!");
                player.getOpenInventory().close();
            } else {
                player.setGameMode(GameMode.SPECTATOR);
                MessageUtils.sendActionBar(player, "&7You have enabled &aSpectator &7mode!");
                player.getOpenInventory().close();
            }
        });

        setItem(5, ItemBuilder.Builder.getInstance().itemType(Material.BARRIER).itemAmount(1).itemName("&cDisable Item Pickup").itemLore(
                List.of("&7Click this to disable Item Pick Up")).build(), player -> {
            if (itemPickup.contains(player.getName())) {
                MessageUtils.sendActionBar(player, "&7You already have &aItem Pickup &7disabled! &7Just disable it!");
                player.getOpenInventory().close();
            } else {
                itemPickup.add(player.getName());
                MessageUtils.sendActionBar(player, "&7You have disabled &aItem Pickup&7!");
                player.getOpenInventory().close();
            }
        });


        setItem(6, ItemBuilder.Builder.getInstance().itemType(Material.MILK_BUCKET).itemAmount(1).itemName("&cClear Night Vision").itemLore(
                List.of("&7Click this to clear your Night Vision")).build(), player -> {
            if (!player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                MessageUtils.sendActionBar(player, "&7You don't have &aNight Vision! &7Just enable it!");
                player.getOpenInventory().close();
            } else {
                player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                MessageUtils.sendActionBar(player, "&7You have disable &aNight Vision&7!");
                player.getOpenInventory().close();
            }
        });

        setItem(7, ItemBuilder.Builder.getInstance().itemType(Material.APPLE).itemAmount(1).itemName("&cDisable Spectator Mode").itemLore(
                List.of("&7Click this to go into Survival Mode")).build(), player -> {

            if (player.getGameMode() != GameMode.SPECTATOR) {
                MessageUtils.sendActionBar(player, "&7You're not in &aSpectator &7mode! Just enable it!");
                player.getOpenInventory().close();
            } else {
                player.setGameMode(GameMode.SURVIVAL);
                MessageUtils.sendActionBar(player, "&7You have disable &aSpectator &7mode!");
                player.getOpenInventory().close();
            }
        });
    }
}
