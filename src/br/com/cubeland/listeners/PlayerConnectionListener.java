package br.com.cubeland.listeners;

import br.com.cubeland.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static br.com.cubeland.GameStatus.*;
import static br.com.cubeland.messages.ChatMessages.sendPlayerJoinMessage;
import static br.com.cubeland.messages.ChatMessages.sendPlayerLeaveMessage;

public class PlayerConnectionListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        int onlinePlayers = Bukkit.getOnlinePlayers().size();
        Player player = event.getPlayer();

        event.setJoinMessage(null);
        sendPlayerJoinMessage(player, onlinePlayers, GameManager.getMaxPlayers());

        if (GameManager.getGameStatus().equals(AWAITING_PLAYERS) && onlinePlayers == GameManager.getMinPlayers()) {
            GameManager.countdown();
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        int onlinePlayers = Bukkit.getOnlinePlayers().size() - 1; //Correção temporária (caso haja uma forma menos gambiarrenta de resolver).
        Player player = event.getPlayer();

        event.setQuitMessage(null);
        sendPlayerLeaveMessage(player, onlinePlayers, GameManager.getMaxPlayers());

        if (GameManager.getGameStatus().equals(STARTING) && onlinePlayers < GameManager.getMaxPlayers()) {
            GameManager.setGameStatus(AWAITING_PLAYERS);
        }
    }

}
