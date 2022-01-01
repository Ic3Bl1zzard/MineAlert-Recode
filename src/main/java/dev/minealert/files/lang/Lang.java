package dev.minealert.files.lang;

public enum Lang {

    PREFIX("prefix", "&7[&a&lMA&7] "),
    NO_PERMISSION("no-permission", "&cYou don't have enough permission to do that!"),
    NO_PLAYER_EXIST("no-player-exist", "&cThat player does not exist!"),
    NOTIFY_DESC("notify-desc", "&7Allows you turn on and off alerts"),
    NOTIFY_SYN("notify-syn", "&7/minealert notify"),
    NOTIFY_ENABLED("notify-enabled", "&7You have successfully enabled alert notifications!"),
    NOTIFY_DISABLED("notify-disabled", "&7You have successfully disabled alert notifications!"),
    INSPECT_DESC("inspect-desc", "&7Allows you to view a specific player!"),
    INSPECT_SYN("inspect-syn", "&7/minealert inspect <player>"),
    SETTINGS_DESC("settings-desc", "&7Allows you to view setting options"),
    SETTINGS_SYN("settings-syn", "&7/minealert settings"),
    INTERVAL_DESC("interval-desc", "&7Gives you the interval of when the cache system will loop!"),
    INTERVAL_SYN("interval-syn", "&7/minealert interval"),
    INTERVAL_MESSAGE("interval-message", "&7Interval: &a%amount%"),
    RESET_DATA_SUCCESSFUL("reset-data-successful", "&7You have successfully resetted everyones mine data!"),
    DATA_RESET_MESSAGE("data-reset-message", "&7All data has been resetted! Cached data has been stored!"),
    ALERTDEBRIS_ALERT_MESSAGE("alert-debris-message", "&e%time% &6%player% &7has mined &fAncient Debris &7[&e%amount%x&7]"),
    COAL_ALERT_MESSAGE("coal-alert-message", "&e%time% &6%player% &7has mined &8Coal &7[&e%amount%x&7]"),
    COPPER_ALERT_MESSAGE("copper-alert-message", "&e%time% &6%player% &7has mined &6Copper &7[&e%amount%x&7]"),
    DEEPCOAL_ALERT_MESSAGE("deepcoal-alert-message", "&e%time% &6%player% &7has mined &8Deep Coal &7[&e%amount%x&7]"),
    DEEPCOPPER_ALERT_MESSAGE("deepcopper-alert-message", "&e%time% &6%player% &7has mined &6Deep Copper &7[&e%amount%x&7]"),
    DEEPDIAMOND_ALERT_MESSAGE("deepdiamond-alert-message", "&e%time% &6%player% &7has mined &bDeep Diamond &7[&e%amount%x&7]"),
    DEEPEMERALD_ALERT_MESSAGE("deepemerald-alert-message", "&e%time% &6%player% &7has mined &aDeep Emerald &7[&e%amount%x&7]"),
    DEEPGOLD_ALERT_MESSAGE("deepgold-alert-message", "&e%time% &6%player% &7has mined &6Deep Gold &7[&e%amount%x&7]"),
    DEEPIRON_ALERT_MESSAGE("deepiron-alert-message", "&e%time% &6%player% &7has mined &fDeep Iron &7[&e%amount%x&7]"),
    DEEPLAPIS_ALERT_MESSAGE("deeplapis-alert-message", "&e%time% &6%player% &7has mined &3Deep Lapis &7[&e%amount%x&7]"),
    DEEPREDSTONE_ALERT_MESSAGE("deepredstone-alert-message", "&e%time% &6%player% &7has mined &cDeep Redstone &7[&e%amount%x&7]"),
    //
    DIAMOND_ALERT_MESSAGE("diamond-alert-message", "&e%time% &6%player% &7has mined &bDiamond &7[&e%amount%x&7]"),
    EMERALD_ALERT_MESSAGE("emerald-alert-message", "&e%time% &6%player% &7has mined &aEmerald &7[&e%amount%x&7]"),
    GOLD_ALERT_MESSAGE("gold-alert-message", "&e%time% &6%player% &7has mined &6Gold &7[&e%amount%x&7]"),
    IRON_ALERT_MESSAGE("iron-alert-message", "&e%time% &6%player% &7has mined &fIron &7[&e%amount%x&7]"),
    LAPIS_ALERT_MESSAGE("lapis-alert-message", "&e%time% &6%player% &7has mined &3Lapis &7[&e%amount%x&7]"),
    NETHERGOLD_ALERT_MESSAGE("nethergold-alert-message", "&e%time% &6%player% &7has mined &6Nether Gold &7[&e%amount%x&7]"),
    REDSTONE_ALERT_MESSAGE("deepredstone-alert-message", "&e%time% &6%player% &7has mined &cRedstone &7[&e%amount%x&7]"),
    SPAWNER_ALERT_MESSAGE("spawner-alert-message", "&e%time% &6%player% &7has mined a &e%mobtype% &7spawner &7[&e%amount%x&7]"),

    //Inventory Click Data Overview
    COAL_MAPPED_DATA("coal-mapped-data", "&7Coal Mined: &a%amount%"),
    COAL_OVERALL_DATA("coal-overall-data", "&7Coal Overall Mined: &a%amount%"),
    IRON_MAPPED_DATA("iron-mapped-data", "&7Iron Mined: &a%amount%"),
    IRON_OVERALL_DATA("iron-overall-data", "&7Iron Overall Mined: &a%amount%"),
    GOLD_MAPPED_DATA("gold-mapped-data", "&6Gold &7Mined: &a%amount%"),
    GOLD_OVERALL_DATA("gold-overall-data", "&6Gold &7Overall Mined: &a%amount%"),
    LAPIS_MAPPED_DATA("lapis-mapped-data", "&3Lapis &7Mined: &a%amount%"),
    LAPIS_OVERALL_DATA("lapis-overall-data", "&3Lapis &7Overall Mined: &a%amount%"),
    REDSTONE_MAPPED_DATA("redstone-mapped-data", "&cRedstone &7Mined: &a%amount%"),
    REDSTONE_OVERALL_DATA("redstone-overall-data", "&cRedstone &7Overall Mined: &a%amount%"),
    DIAMOND_MAPPED_DATA("diamond-mapped-data", "&bDiamond &7Mined: &a%amount%"),
    DIAMOND_OVERALL_DATA("diamond-overall-data", "&bDiamond &7Overall Mined: &a%amount%"),
    EMERALD_MAPPED_DATA("emerald-mapped-data", "&aEmerald &7Mined: &a%amount%"),
    EMERALD_OVERALL_DATA("emerald-overall-data", "&aEmerald &7Overall Mined: &a%amount%"),
    SPAWNER_MAPPED_DATA("spawner-mapped-data", "&7Spawner &7Mined: &a%amount%"),
    SPAWNER_OVERALL_DATA("spawner-overall-data", "&7Spawner &7Overall Mined: &a%amount%"),
    ANCIENTDEBRIS_MAPPED_DATA("ancientdebris-mapped-data", "&fAncient Debris &7Mined: &a%amount%"),
    ANCIENTDEBRIS_OVERALL_DATA("ancientdebris-overall-data", "&fAncient Debris &7Overall Mined: &a%amount%"),
    NETHERGOLD_MAPPED_DATA("nethergold-mapped-data", "&6Nether Gold &7Mined: &a%amount%"),
    NETHERGOLD_OVERALL_DATA("nethergold-overall-data", "&6Nether Gold &7Overall Mined: &a%amount%"),
    COPPER_MAPPED_DATA("copper-mapped-data", "&6Copper &7Mined: &a%amount%"),
    COPPER_OVERALL_DATA("copper-overall-data", "&6Copper &7Overall Mined: &a%amount%"),

    //DEEP SLATES NEXT:
    DEEPCOPPER_MAPPED_DATA("deepcopper-mapped-data", "&6Deep Copper &7Mined: &a%amount%"),
    DEEPCOPPER_OVERALL_DATA("deepcopper-overall-data", "&6Deep Copper &7Overall Mined: &a%amount%"),
    DEEPCOAL_MAPPED_DATA("deepcoal-mapped-data", "&7Deep Coal Mined: &a%amount%"),
    DEEPCOAL_OVERALL_DATA("deepcoal-overall-data", "&7Deep Coal Overall Mined: &a%amount%"),
    DEEPIRON_MAPPED_DATA("deepiron-mapped-data", "&7Deep Iron Mined: &a%amount%"),
    DEEPIRON_OVERALL_DATA("deepiron-overall-data", "&7Deep Iron Overall Mined: &a%amount%"),
    DEEPGOLD_MAPPED_DATA("deepgold-mapped-data", "&6Deep Gold &7Mined: &a%amount%"),
    DEEPGOLD_OVERALL_DATA("deepgold-overall-data", "&6Deep Gold &7Overall Mined: &a%amount%"),
    DEEPLAPIS_MAPPED_DATA("deeplapis-mapped-data", "&3Deep Lapis &7Mined: &a%amount%"),
    DEEPLAPIS_OVERALL_DATA("deeplapis-overall-data", "&3Deep Lapis &7Overall Mined: &a%amount%"),
    DEEPREDSTONE_MAPPED_DATA("deepredstone-mapped-data", "&cDeep Redstone &7Mined: &a%amount%"),
    DEEPREDSTONE_OVERALL_DATA("deepredstone-overall-data", "&cDeep Redstone &7Overall Mined: &a%amount%"),
    DEEPDIAMOND_MAPPED_DATA("deepdiamond-mapped-data", "&bDeep Diamond &7Mined: &a%amount%"),
    DEEPDIAMOND_OVERALL_DATA("deepdiamond-overall-data", "&bDeep Diamond &7Overall Mined: &a%amount%"),
    DEEPEMERALD_MAPPED_DATA("deepemerald-mapped-data", "&aDeep Emerald &7Mined: &a%amount%"),
    DEEPEMERALD_OVERALL_DATA("deepemerald-overall-data", "&aDeep Emerald &7Overall Mined: &a%amount%"),

    ;

    public static final Lang[] CACHE = values();
    private final String path;
    private final String value;

    Lang(String path, String value) {
        this.path = path;
        this.value = value;
    }

    public String toConfigString() {
        return LangFile.getInstance().getFileConfiguration().getString(this.path, this.value);
    }

    public String getPath() {
        return path;
    }

    public String getValue() {
        return value;
    }

}
