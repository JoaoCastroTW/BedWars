package br.com.cubeland.messages;

import static br.com.cubeland.utils.MessagesUtil.*;
import static br.com.cubeland.utils.SoundUtils.*;
import br.com.cubeland.teams.Team;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class ChatMessages {
    public static void sendPlayerJoinMessage(Player player, int onlinePlayers, int maxPlayers) {
        String message = translateColorCodes(String.format("&7%s &eentrou na partida! &a(%s/%s)", player.getName(), onlinePlayers, maxPlayers));

        Bukkit.broadcastMessage(message);
        broadcastSoundEffect(Sound.CHICKEN_EGG_POP, 20, 1.3f);
    }

    public static void sendPlayerLeaveMessage(Player player, int onlinePlayers, int maxPlayers) {
        String message = translateColorCodes(String.format("&7%s &esaiu da partida! &a(%s/%s)", player.getName(), onlinePlayers, maxPlayers));

        Bukkit.broadcastMessage(message);
        broadcastSoundEffect(Sound.CHICKEN_EGG_POP, 20, 0.8f);
    }

    public static void sendCountdownMessage(int second) {
        String message = translateColorCodes(String.format("&eA partida será iniciada em &a%s &esegundos.", second));

        Bukkit.broadcastMessage(message);
        broadcastSoundEffect(Sound.NOTE_STICKS, 20, 1);
    }

    public static void sendCancelCountdownMessage() {
        String message = translateColorCodes("&cO início da partida foi cancelado.");

        Bukkit.broadcastMessage(message);
        broadcastSoundEffect(Sound.VILLAGER_NO, 20, 1f);
    }

    public static void sendTeamAssignedMessage(Player player) {
        Team team = Team.getTeam(player);
        String message = translateColorCodes(String.format("&eVocê está no time %s%s&e!", team.getColor(), team.getName()));

        player.sendMessage(message);
    }

    public static void sendPlayerKillMessage(Player player, Player killer) {
        Team playerTeam = Team.getTeam(player);
        Team killerTeam = Team.getTeam(killer);
        String message = translateColorCodes(String.format("%s%s &efoi abatido por %s%s&e.", playerTeam.getColor(), player.getName(), killerTeam.getColor(), killer.getName()));

        Bukkit.broadcastMessage(message);
        playSoundEffect(player, Sound.HURT_FLESH, 20, 1);
        playSoundEffect(killer, Sound.HURT_FLESH, 20, 1);
        playSoundEffect(killer, Sound.SUCCESSFUL_HIT, 20, 1);
    }

    public static void sendPlayerKillMessage(Player player) {
        Team playerTeam = Team.getTeam(player);
        String message = translateColorCodes(String.format("%s%s &efoi abatido.", playerTeam.getColor(), player.getName()));

        Bukkit.broadcastMessage(message);
        playSoundEffect(player, Sound.HURT_FLESH, 20, 1);
    }

    public static void sendFinalKillMessage(Player player, Player killer) {
        Team playerTeam = Team.getTeam(player);
        Team killerTeam = Team.getTeam(killer);
        String message = translateColorCodes(String.format("\n&c&lABATE FINAL!\n%s%s &efoi eliminado da partida por %s%s&e.\n ", playerTeam.getColor(), player.getName(), killerTeam.getColor(), killer.getName()));

        Bukkit.broadcastMessage(message);
        broadcastSoundEffect(Sound.ANVIL_LAND, 20, 1);
    }

    public static void sendFinalKillMessage(Player player) {
        Team playerTeam = Team.getTeam(player);
        String message = translateColorCodes(String.format("\n&c&lABATE FINAL!\n%s%s &efoi eliminado da partida.\n ", playerTeam.getColor(), player.getName()));

        Bukkit.broadcastMessage(message);
        broadcastSoundEffect(Sound.ANVIL_LAND, 20, 1);
    }

    public static void sendBedBreakMessage(Player breaker, Block block) {
        Team breakerTeam = Team.getTeam(breaker);
        Team bedTeam = Team.getTeam(block);
        String message = translateColorCodes(String.format("\n%1$s&lCAMA DESTRUÍDA!\n&eA cama do time %s%s &efoi destruída pelo time %s%s&e!\n ", bedTeam.getColor(), bedTeam.getName(), breakerTeam.getColor(), breakerTeam.getName()));

        Bukkit.broadcastMessage(message);
        broadcastSoundEffect(Sound.ZOMBIE_REMEDY, 20, 1.3f);
    }
}
