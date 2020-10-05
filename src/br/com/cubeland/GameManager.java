package br.com.cubeland;

import br.com.cubeland.teams.Team;
import br.com.cubeland.teams.TeamManager;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;

import static br.com.cubeland.GameStatus.*;
import static br.com.cubeland.messages.ChatMessages.*;
import static br.com.cubeland.messages.ChatMessages.sendFinalKillMessage;
import static br.com.cubeland.utils.SoundUtils.broadcastSoundEffect;

public class GameManager {

    private static GameStatus gameStatus = AWAITING_PLAYERS;
    private static int onlinePlayers;
    private static int countdownTimer = 10;
    private static final int minPlayers = 2;
    private static final int maxPlayers = 4;

    public static void countdown() {
        setGameStatus(STARTING);

        new BukkitRunnable(){
            @Override
            public void run() {
                switch (gameStatus) {
                    case STARTING:
                        if (countdownTimer <= 0) {
                            this.cancel();
                            startMatch();
                        } else {
                            sendCountdownMessage(countdownTimer--);
                        }
                        break;
                    case AWAITING_PLAYERS:
                        this.cancel();
                        sendCancelCountdownMessage();
                        countdownTimer = 10;
                        break;
                }
            }
        }.runTaskTimer(new BedWars(), 0, 20);
    }

    public static void startMatch() {
        TeamManager.assignTeams();
        setGameStatus(IN_PROGRESS);

        for (Player player : Team.getPlayers()) {
            Team team = Team.getTeam(player);
            player.teleport(team.getLocation());
        }

        broadcastSoundEffect(Sound.FIREWORK_BLAST2, 20, 2);
    }

    public static void handlePlayerDeath(Player player, Player killer) {
        Team playerTeam = Team.getTeam(player);

        if (playerTeam.hasBed()) {
            player.teleport(playerTeam.getLocation());
            player.setHealth(player.getMaxHealth());
            sendPlayerKillMessage(player, killer);
        } else {
            spectator(player);
            sendFinalKillMessage(player, killer);
        }
    }

    public static void handlePlayerDeath(Player player) {
        Team playerTeam = Team.getTeam(player);

        if (playerTeam.hasBed()) {
            player.teleport(playerTeam.getLocation());
            player.setHealth(player.getMaxHealth());
            sendPlayerKillMessage(player);
        } else {
            spectator(player);
            sendFinalKillMessage(player);
        }
    }

    public static void handleBedBreak(Player playerBreaker, Block block) {
        if (Team.isBedLocation(block)) {
            Team bedTeam = Team.getTeam(block);
            bedTeam.breakBed(playerBreaker, block);
        }
    }

    public static void handleVoidDamage(EntityDamageEvent event, Player player) {
        event.setCancelled(true);
        player.setFallDistance(0);

        if (gameStatus.equals(AWAITING_PLAYERS) || gameStatus.equals(STARTING)) {
            player.teleport(BedWarsConstants.LOBBY_LOCATION);
        } else {
            handlePlayerDeath(player);
        }
    }


    private static void spectator(Player player) {
        player.setGameMode(GameMode.SPECTATOR);
    }

    public static GameStatus getGameStatus() {
        return gameStatus;
    }

    public static void setGameStatus(GameStatus status) {
        gameStatus = status;
    }

    public static int getMaxPlayers() {
        return maxPlayers;
    }

    public static int getMinPlayers() {
        return minPlayers;
    }

}
