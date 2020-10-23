package tw.joao.listeners;

import tw.joao.BedWarsConstants;
import tw.joao.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import tw.joao.GameStatus;

import static tw.joao.messages.ChatMessages.sendPlayerJoinMessage;
import static tw.joao.messages.ChatMessages.sendPlayerLeaveMessage;

public class PlayerConnectionListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        int onlinePlayers = Bukkit.getOnlinePlayers().size();
        Player player = event.getPlayer();

        event.setJoinMessage(null);
        sendPlayerJoinMessage(player, onlinePlayers, BedWarsConstants.MAX_PLAYERS);

        if (GameManager.getGameStatus().equals(GameStatus.AWAITING_PLAYERS) && onlinePlayers == BedWarsConstants.MIN_PLAYERS) {
            GameManager.countdown();
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        int onlinePlayers = Bukkit.getOnlinePlayers().size() - 1; //Correção temporária (caso haja uma forma menos gambiarrenta de resolver).
        Player player = event.getPlayer();

        event.setQuitMessage(null);
        sendPlayerLeaveMessage(player, onlinePlayers, BedWarsConstants.MAX_PLAYERS);

        if (GameManager.getGameStatus().equals(GameStatus.STARTING) && onlinePlayers < BedWarsConstants.MAX_PLAYERS) {
            GameManager.setGameStatus(GameStatus.AWAITING_PLAYERS);
        }
    }

}
