package dev.minealert.utils;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageUtils {

    public static void sendFormattedMessage(String text, Player player) {
        if (player != null) {
            player.sendMessage(FormatUtils.color(text));
        } else {
            Bukkit.broadcastMessage(FormatUtils.color(text));
        }
    }

    public static void sendActionBar(Player player, String text){
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(FormatUtils.color(text)));
    }

    public static void sendFormattedMessage(String text) {
        sendFormattedMessage(text, null);
    }

    public static void sendConsoleMessage(String text) {
        Bukkit.getConsoleSender().sendMessage(FormatUtils.color(text));
    }

    public static void sendFormattedMessage(String text, CommandSender sender){
        sender.sendMessage(FormatUtils.color(text));
    }
}
