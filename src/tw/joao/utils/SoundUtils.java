package tw.joao.utils;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundUtils {

    public static void broadcastSoundEffect(Sound sound, float volume, float pitch) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player.getLocation(), sound, volume, pitch);
        }
    }

    public static void playSoundEffect(Player[] players, Sound sound, float volume, float pitch) {
        for (Player player : players) {
            playSoundEffect(player, sound, volume, pitch);
        }
    }

    public static void playSoundEffect(Player player, Sound sound, float volume, float pitch) {
        player.playSound(player.getLocation(), sound, volume, pitch);
    }

    public static void allSoundEffectsMessage(Player player) {
        TextComponent message = new TextComponent();

        for (Sound sound : Sound.values()) {
            TextComponent soundName = new TextComponent(sound.name() + " ");
            soundName.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/playsound " + sound.name()));

            message.addExtra(soundName);
        }

        player.spigot().sendMessage(message);
    }

}
