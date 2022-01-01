package dev.minealert.inventories;

import dev.minealert.MineAlert;
import dev.minealert.database.DatabaseUUIDTool;
import dev.minealert.database.SQLUtils;
import dev.minealert.files.lang.Lang;
import dev.minealert.modules.AbstractModule;
import dev.minealert.modules.alert.AbstractAlertModule;
import dev.minealert.tasks.MineDataTask;
import dev.minealert.utils.*;
import dev.minealert.utils.inventory.AbstractMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class InspectionMenu extends AbstractMenu {

    private final Player inspected;
    private final String prefix = Lang.PREFIX.toConfigString();

    public InspectionMenu(Player inspected) {
        super(54, FormatUtils.color("&7Inspection Menu &8(&f" + inspected + "&8)"));

        final List<String> singleLore = List.of("&7Click to check the user's data!");
        this.inspected = inspected;
        final ItemBuilder.Builder builder = ItemBuilder.Builder.getInstance();
        setItem(10, builder.itemType(Material.COAL_ORE).itemAmount(1).itemName("&7Coal").itemLore(singleLore).build(),
                player -> onClick(player, Lang.COAL_MAPPED_DATA, Lang.COAL_OVERALL_DATA, 1, "COAL"));

        setItem(11, builder.itemType(Material.IRON_ORE).itemAmount(1).itemName("&7Iron").itemLore(singleLore).build(),
                player -> onClick(player, Lang.IRON_MAPPED_DATA, Lang.IRON_OVERALL_DATA, 14, "IRON"));

        setItem(12, builder.itemType(Material.GOLD_ORE).itemAmount(1).itemName("&6Gold").itemLore(singleLore).build(),
                player -> onClick(player, Lang.GOLD_MAPPED_DATA, Lang.GOLD_OVERALL_DATA, 13, "GOLD"));

        setItem(13, builder.itemType(Material.LAPIS_ORE).itemAmount(1).itemName("&3Lapis").itemLore(singleLore).build(),
                player -> onClick(player, Lang.LAPIS_MAPPED_DATA, Lang.LAPIS_OVERALL_DATA, 15, "LAPIS"));

        setItem(14, builder.itemType(Material.REDSTONE_ORE).itemAmount(1).itemName("&cRedstone").itemLore(singleLore).build(),
                player -> onClick(player, Lang.REDSTONE_MAPPED_DATA, Lang.REDSTONE_OVERALL_DATA, 17, "REDSTONE"));

        setItem(15, builder.itemType(Material.DIAMOND_ORE).itemAmount(1).itemName("&bDiamond").itemLore(singleLore).build(),
                player -> onClick(player, Lang.DIAMOND_MAPPED_DATA, Lang.DIAMOND_OVERALL_DATA, 11, "DIAMOND"));

        setItem(16, builder.itemType(Material.EMERALD_ORE).itemAmount(1).itemName("&aEmerald").itemLore(singleLore).build(),
                player -> onClick(player, Lang.EMERALD_MAPPED_DATA, Lang.EMERALD_OVERALL_DATA, 12, "EMERALD"));

        setItem(20, builder.itemType(Material.SPAWNER).itemAmount(1).itemName("&7Spawners").itemLore(singleLore).build(),
                player -> onClick(player, Lang.SPAWNER_MAPPED_DATA, Lang.SPAWNER_OVERALL_DATA, 18, "SPAWNER"));

        //Checking for versions older than 1.16 to fill up slots in the middle
        if (Version.getServerVersion().isOlderThan(Version.v1_16_R1)) {
            for (int i = 21; i <= 24; i++) {
                setItem(i, builder.itemType(Material.DEAD_BUSH).itemName("&7???").itemLore(List.of("???")).build(),
                        player -> player.getOpenInventory().close());
            }
        }

        //Checking for versions newer than or equal to 1.16 to fill up slots in the middle in support of Ancient Debris, and Nether Gold. Rest are ???
        if (Version.getServerVersion().isNewerOrSameThan(Version.v1_16_R1)) {
            setItem(21, builder.itemType(Material.ANCIENT_DEBRIS).itemAmount(1).itemName("&fAncient Debris").itemLore(singleLore).build(),
                    player -> onClick(player, Lang.ANCIENTDEBRIS_MAPPED_DATA, Lang.ANCIENTDEBRIS_OVERALL_DATA, 0, "ANCIENTDEBRIS"));

            setItem(22, builder.itemType(Material.NETHER_GOLD_ORE).itemAmount(1).itemName("&6Nether Gold").itemLore(singleLore).build(),
                    player -> onClick(player, Lang.NETHERGOLD_MAPPED_DATA, Lang.NETHERGOLD_OVERALL_DATA, 16, "NETHERGOLD"));
            for (int i = 23; i <= 24; i++) {
                setItem(i, builder.itemType(Material.DEAD_BUSH).itemName("&7???").itemLore(List.of("&7???")).build(),
                        player -> player.getOpenInventory().close());
            }
        }

        //Checking for versions older than 1.17 to fill up bottom slots due to copper, and deep slate ores not existing!
        if (Version.getServerVersion().isOlderThan(Version.v1_17_R1)) {
            for (int i = 28; i <= 34; i++) {
                setItem(i, builder.itemType(Material.DEAD_BUSH).itemName("&7???").itemLore(List.of("&7???")).build(),
                        player -> player.getOpenInventory().close());
            }
        }

        //Checking for versions newer than 1.17 to fill up the slots  with copper and deep slate ores.
        if (Version.getServerVersion().isNewerOrSameThan(Version.v1_17_R1)) {
            setItem(23, builder.itemType(Material.COPPER_ORE).itemAmount(1).itemName("&6Copper").itemLore(singleLore).build(),
                    player -> onClick(player, Lang.COPPER_MAPPED_DATA, Lang.COPPER_OVERALL_DATA, 2, "COPPER"));

            setItem(24, builder.itemType(Material.DEEPSLATE_COPPER_ORE).itemAmount(1).itemName("&6Deep Copper").itemLore(singleLore).build(),
                    player -> onClick(player, Lang.DEEPCOPPER_MAPPED_DATA, Lang.DEEPCOPPER_OVERALL_DATA, 4, "DEEPCOPPER"));

            setItem(28, builder.itemType(Material.DEEPSLATE_COAL_ORE).itemAmount(1).itemName("&7Deep Coal").itemLore(singleLore).build(),
                    player -> onClick(player, Lang.DEEPCOAL_MAPPED_DATA, Lang.DEEPCOAL_OVERALL_DATA, 3, "DEEPCOAL"));

            setItem(29, builder.itemType(Material.DEEPSLATE_IRON_ORE).itemAmount(1).itemName("&7Deep Iron").itemLore(singleLore).build(),
                    player -> onClick(player, Lang.DEEPIRON_MAPPED_DATA, Lang.DEEPIRON_OVERALL_DATA, 8, "DEEPIRON"));

            setItem(30, builder.itemType(Material.DEEPSLATE_GOLD_ORE).itemAmount(1).itemName("&6Deep Gold").itemLore(singleLore).build(),
                    player -> onClick(player, Lang.DEEPGOLD_MAPPED_DATA, Lang.DEEPGOLD_OVERALL_DATA, 7, "DEEPGOLD"));

            setItem(31, builder.itemType(Material.DEEPSLATE_LAPIS_ORE).itemAmount(1).itemName("&3Deep Lapis").itemLore(singleLore).build(),
                    player -> onClick(player, Lang.DEEPLAPIS_MAPPED_DATA, Lang.DEEPLAPIS_OVERALL_DATA, 9, "DEEPLAPIS"));

            setItem(32, builder.itemType(Material.DEEPSLATE_REDSTONE_ORE).itemAmount(1).itemName("&cDeep Redstone").itemLore(singleLore).build(),
                    player -> onClick(player, Lang.DEEPREDSTONE_MAPPED_DATA, Lang.DEEPREDSTONE_OVERALL_DATA, 10, "DEEPREDSTONE"));

            setItem(33, builder.itemType(Material.DEEPSLATE_DIAMOND_ORE).itemAmount(1).itemName("&bDeep Diamond").itemLore(singleLore).build(),
                    player -> onClick(player, Lang.DEEPDIAMOND_MAPPED_DATA, Lang.DEEPDIAMOND_OVERALL_DATA, 5, "DEEPDIAMOND"));

            setItem(34, builder.itemType(Material.DEEPSLATE_EMERALD_ORE).itemAmount(1).itemName("&aDeep Emerald").itemLore(singleLore).build(),
                    player -> onClick(player, Lang.DEEPEMERALD_MAPPED_DATA, Lang.DEEPEMERALD_OVERALL_DATA, 6, "DEEPEMERALD"));
        }

        setItem(48, builder.itemType(Material.CLOCK).itemAmount(1).itemName("&7Interval").itemLore(List.of("&7Interval: &a" + MineDataTask.getInterval())).build(),
                player -> player.getOpenInventory().close());

        setItem(49, builder.itemType(Material.BARRIER).itemAmount(1).itemName("&cClose").itemLore(List.of("&7Click to close the menu!")).build(),
                player -> player.getOpenInventory().close());

        setItem(50, builder.itemType(Material.LEVER).itemAmount(1).itemName("&7Settings Menu").itemLore(List.of("&7Click to go into settings menu!")).build(),
                player -> {
                    player.getOpenInventory().close();
                    if (!player.hasPermission("minealert.settings")) {
                        MessageUtils.sendFormattedMessage(Lang.PREFIX.toConfigString() + Lang.NO_PERMISSION.toConfigString());
                        return;
                    }

                    new SettingsMenu().open(player);
                });
    }

    private String getMapData(int element) {
        AtomicInteger mappedAmount = new AtomicInteger();
        final List<Class<? extends AbstractAlertModule>> tempList = MineDataUtils.getInstance().getModuleAlertList();
        AbstractModule.getModule(tempList.get(element)).ifPresent(fetcher -> mappedAmount.set(fetcher.getMineMap().get(inspected.getName())));
        return String.valueOf(mappedAmount.get());
    }

    private String getDatabaseData(String resultID) {
        AtomicInteger overAllAmount = new AtomicInteger();
        SQLUtils.getInstance().executeQuery("SELECT * FROM MINEDATA WHERE UUID =?", ps -> ps.setBinaryStream(1,
                DatabaseUUIDTool.convertUniqueId(inspected.getUniqueId())), rs -> {
            if (rs.next()) {
                overAllAmount.set(rs.getInt(resultID));
            }
            return rs;
        });
        return String.valueOf(overAllAmount.get());
    }

    private synchronized void onClick(Player player, Lang mappedValue, Lang overallValue, int listElement, String resultID) {
        CompletableFuture.runAsync(() -> {
            MessageUtils.sendFormattedMessage(prefix + mappedValue.toConfigString().replace("%amount%", getMapData(listElement)), player);
            MessageUtils.sendFormattedMessage(prefix + overallValue.toConfigString().replace("%amount%", getDatabaseData(resultID)));
            MineAlert.getInstance().doSync(() -> player.getOpenInventory().close());
        });
    }
}
