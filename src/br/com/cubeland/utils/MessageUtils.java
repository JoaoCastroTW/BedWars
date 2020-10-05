package br.com.cubeland.utils;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class MessageUtils {

    public static String translateColorCodes(String rawMessage) {
        String message = ChatColor.translateAlternateColorCodes('&', rawMessage);

        return message;
    }

    public static void sendActionBar(Player player, String text) {
        PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a("{\"text\":\"" + text + "\"}"), (byte) 2);

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    public static void broadcastActionBar(String text) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendActionBar(player, text);
        }
    }

    public static void sendTitle(Player player, String text, PacketPlayOutTitle.EnumTitleAction type, int fadeInLength, int displayLength, int fadeOutLength) {
        PacketPlayOutTitle title = new PacketPlayOutTitle(type, ChatSerializer.a("{\"text\": \"" + text + "\"}"));
        PacketPlayOutTitle length = new PacketPlayOutTitle(fadeInLength, displayLength, fadeOutLength);

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(length);
    }

    public static void broadcastTitle(String text, PacketPlayOutTitle.EnumTitleAction type, int fadeInLength, int displayLength, int fadeOutLength) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendTitle(player, text, type, fadeInLength, displayLength, fadeOutLength);
        }
    }

}
