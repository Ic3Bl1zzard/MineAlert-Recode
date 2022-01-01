package dev.minealert.command;

import dev.minealert.command.sub.*;

public enum SubCommandTypes {

    NOTIFY("notify", new Notify()),
    INSPECT("inspect", new Inspect()),
    INTERVAL("interval", new Interval()),
    SETTINGS("settings", new Settings()),
    RELOAD("reload", new ReloadConfig());

    private final String name;
    private final SubCommand subCommand;
    public static final SubCommandTypes[] CACHE = values();

    SubCommandTypes(String name, SubCommand subCommand) {
        this.name = name;
        this.subCommand = subCommand;
    }

    public static SubCommandTypes fromName(String name) {
        for (SubCommandTypes types : CACHE) {
            if (types.getName().equalsIgnoreCase(name)) {
                return types;
            }
        }
        return null;
    }

    public <T extends SubCommand> T getSubCommand(Class<T> clazz) {
        if (!clazz.isInstance(this.subCommand)) {
            try {
                throw new Exception(name() + " is not instance of " + clazz.getSimpleName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return clazz.cast(this.subCommand);
    }

    public String getName() {
        return name;
    }

    public SubCommand getSubCommand() {
        return subCommand;
    }
}
