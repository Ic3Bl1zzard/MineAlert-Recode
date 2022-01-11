package dev.minealert.listener;

import dev.minealert.modules.AbstractModule;
import dev.minealert.modules.alert.AbstractAlertModule;
import dev.minealert.modules.alert.types.*;
import dev.minealert.utils.BlockPlacePatchUtil;
import dev.minealert.utils.MineDataUtils;
import dev.minealert.utils.Version;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Optional;

public class BlockListener implements Listener {


    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player miner = e.getPlayer();
        Block block = e.getBlock();
        Material blockType = block.getType();
        if (BlockPlacePatchUtil.getInstance().containsLocation(block.getLocation())) return;
        switch (blockType) {
            case COAL_ORE -> AbstractModule.getModule(CoalAlert.class).ifPresent(coalAlert -> coalAlert.callInit(miner));
            case DIAMOND_ORE -> AbstractModule.getModule(DiamondAlert.class).ifPresent(diamondalert -> diamondalert.callInit(miner));
            case EMERALD_ORE -> AbstractModule.getModule(EmeraldAlert.class).ifPresent(emeraldAlert -> emeraldAlert.callInit(miner));
            case GOLD_ORE -> AbstractModule.getModule(GoldAlert.class).ifPresent(goldAlert -> goldAlert.callInit(miner));
            case IRON_ORE -> AbstractModule.getModule(IronAlert.class).ifPresent(ironAlert -> ironAlert.callInit(miner));
            case LAPIS_ORE -> AbstractModule.getModule(LapisAlert.class).ifPresent(lapisAlert -> lapisAlert.callInit(miner));
            case REDSTONE_ORE -> AbstractModule.getModule(RedstoneAlert.class).ifPresent(redstoneAlert -> redstoneAlert.callInit(miner));
            case SPAWNER -> {
                EntityType entityType = ((CreatureSpawner) block.getState()).getSpawnedType();
                switch (entityType) {
                    case SKELETON, ZOMBIE, BLAZE, CAVE_SPIDER, SPIDER, MAGMA_CUBE, SILVERFISH -> {
                        Optional<SpawnerAlert> spawnerAlert = AbstractModule.getModule(SpawnerAlert.class);
                        spawnerAlert.ifPresent(spawner -> {
                            spawner.setType(entityType.getName());
                            spawner.callInit(miner);
                        });
                    }
                }
            }
        }

        //Checking for newer versions


        if (Version.getServerVersion().isNewerOrSameThan(Version.v1_17_R1)) {
            switch (blockType) {
                case COPPER_ORE -> AbstractModule.getModule(CopperAlert.class).ifPresent(copperAlert -> copperAlert.callInit(miner));
                case DEEPSLATE_COAL_ORE -> AbstractModule.getModule(DeepCoalAlert.class).ifPresent(deepCoalAlert -> deepCoalAlert.callInit(miner));
                case DEEPSLATE_COPPER_ORE -> AbstractModule.getModule(DeepCopperAlert.class).ifPresent(deepCopperAlert -> deepCopperAlert.callInit(miner));
                case DEEPSLATE_DIAMOND_ORE -> AbstractModule.getModule(DeepDiamondAlert.class).ifPresent(deepDiamondAlert -> deepDiamondAlert.callInit(miner));
                case DEEPSLATE_EMERALD_ORE -> AbstractModule.getModule(DeepEmeraldAlert.class).ifPresent(deepEmeraldAlert -> deepEmeraldAlert.callInit(miner));
                case DEEPSLATE_GOLD_ORE -> AbstractModule.getModule(DeepGoldAlert.class).ifPresent(deepgoldAlert -> deepgoldAlert.callInit(miner));
                case DEEPSLATE_IRON_ORE -> AbstractModule.getModule(DeepIronAlert.class).ifPresent(deepIronAlert -> deepIronAlert.callInit(miner));
                case DEEPSLATE_LAPIS_ORE -> AbstractModule.getModule(DeepLapisAlert.class).ifPresent(deepLapisAlert -> deepLapisAlert.callInit(miner));
                case DEEPSLATE_REDSTONE_ORE -> AbstractModule.getModule(DeepRedstoneAlert.class).ifPresent(deepredstone -> deepredstone.callInit(miner));
            }
        }
        if (Version.getServerVersion().isNewerOrSameThan(Version.v1_16_R1)) {
            switch (blockType) {
                case ANCIENT_DEBRIS -> AbstractModule.getModule(AncientDebrisAlert.class).ifPresent(ancientDebrisAlert -> ancientDebrisAlert.callInit(miner));

                case NETHER_GOLD_ORE -> AbstractModule.getModule(NetherGoldAlert.class).ifPresent(netherGoldAlert -> netherGoldAlert.callInit(miner));
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Block block = e.getBlock();
        Material blockType = block.getType();
        switch (blockType) {
            case COAL_ORE -> checkIfEnabledForPlacing("alert.coal-enable", block.getLocation());
            case DIAMOND_ORE -> checkIfEnabledForPlacing("alert.diamond-enable", block.getLocation());
            case EMERALD_ORE -> checkIfEnabledForPlacing("alert.emerald-enable", block.getLocation());
            case GOLD_ORE -> checkIfEnabledForPlacing("alert.gold-enable", block.getLocation());
            case IRON_ORE -> checkIfEnabledForPlacing("alert.iron-enable", block.getLocation());
            case LAPIS_ORE -> checkIfEnabledForPlacing("alert.lapis-enable", block.getLocation());
            case REDSTONE_ORE -> checkIfEnabledForPlacing("alert.redstone-enable", block.getLocation());
            case SPAWNER -> {
                EntityType entityType = ((CreatureSpawner) block.getState()).getSpawnedType();
                switch (entityType) {
                    case SKELETON, ZOMBIE, BLAZE, CAVE_SPIDER, SPIDER, MAGMA_CUBE, SILVERFISH -> checkIfEnabledForPlacing("alert.spawner-enable", block.getLocation());
                }
            }
        }

        //Checking for newer versions:

        if (Version.getServerVersion().isNewerOrSameThan(Version.v1_17_R1)) {
            switch (blockType) {
                case COPPER_ORE -> checkIfEnabledForPlacing("alert.copper-enable", block.getLocation());
                case DEEPSLATE_COAL_ORE -> checkIfEnabledForPlacing("alert.deepcoal-enable", block.getLocation());
                case DEEPSLATE_COPPER_ORE -> checkIfEnabledForPlacing("alert.deepcopper-enable", block.getLocation());
                case DEEPSLATE_DIAMOND_ORE -> checkIfEnabledForPlacing("alert.deepdiamond-enable", block.getLocation());
                case DEEPSLATE_EMERALD_ORE -> checkIfEnabledForPlacing("alert.deepemerald-enable", block.getLocation());
                case DEEPSLATE_GOLD_ORE -> checkIfEnabledForPlacing("alert.deepgold-enable", block.getLocation());
                case DEEPSLATE_IRON_ORE -> checkIfEnabledForPlacing("alert.deepiron-enable", block.getLocation());
                case DEEPSLATE_LAPIS_ORE -> checkIfEnabledForPlacing("alert.deeplapis-enable", block.getLocation());
                case DEEPSLATE_REDSTONE_ORE -> checkIfEnabledForPlacing("alert.deepredstoner-enable", block.getLocation());
            }
        }

        if (Version.getServerVersion().isNewerOrSameThan(Version.v1_16_R1)) {
            switch (blockType) {
                case ANCIENT_DEBRIS -> checkIfEnabledForPlacing("alert.ancientdebris-enable", block.getLocation());
                case NETHER_GOLD_ORE -> checkIfEnabledForPlacing("alert.nethergold-enable", block.getLocation());
            }
        }
    }

    private void checkIfEnabledForPlacing(String path, Location location) {
        for (Class<? extends AbstractAlertModule> alertList : MineDataUtils.getInstance().getModuleAlertList()) {
            AbstractModule.getModule(alertList).ifPresent(alert -> {
                if (alert.isEnabled(path)) {
                    BlockPlacePatchUtil.getInstance().addBlockLocation(location);
                }
            });
        }
    }
}
