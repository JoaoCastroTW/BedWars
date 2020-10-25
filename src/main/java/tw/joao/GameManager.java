package tw.joao;

import lombok.Getter;
import org.bukkit.*;
import tw.joao.generators.Generator;
import tw.joao.generators.NaturalGenerator;
import tw.joao.generators.NaturalGeneratorType;
import tw.joao.teams.Team;
import tw.joao.teams.TeamManager;
import tw.joao.utils.MessageUtils;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import static tw.joao.messages.ChatMessages.*;
import static tw.joao.messages.TitleMessages.*;
import static tw.joao.teams.Team.*;
import static tw.joao.utils.ItemUtils.getColoredArmor;
import static tw.joao.utils.MessageUtils.broadcastActionBar;
import static tw.joao.utils.MessageUtils.clearTitle;
import static tw.joao.utils.SoundUtils.*;

public class GameManager {

    @Getter private static GameStatus gameStatus = GameStatus.AWAITING_PLAYERS;
    private static int countdownTimer = BedWarsConstants.MATCH_START_COUNTDOWN;
    private static final NaturalGenerator[] generators = {
            new NaturalGenerator(NaturalGeneratorType.DIAMOND, 12, 65, 0),
            new NaturalGenerator(NaturalGeneratorType.EMERALD, 0, 65, -12),
            new NaturalGenerator(NaturalGeneratorType.DIAMOND, -12, 65, 0),
            new NaturalGenerator(NaturalGeneratorType.EMERALD, 0, 65, 12),
    };

    public static void countdown() {
        setGameStatus(GameStatus.STARTING);

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
        }.runTaskTimer(JavaPlugin.getPlugin(BedWars.class), 0, 20);
    }

    private static void startMatch() {
        TeamManager.assignTeams();
        setGameStatus(GameStatus.IN_PROGRESS);
        Generator.startAllGenerators();

        for (Player player : Team.getPlayers()) {
            Team team = Team.getTeam(player);

            player.teleport(team.getLocation());
            giveInitialItems(player, team);
        }

        broadcastSoundEffect(Sound.FIREWORK_BLAST2, 20, 2);
    }

    private static void giveInitialItems(Player player, Team team) {
        int r = team.getColor('r');
        int g = team.getColor('g');
        int b = team.getColor('b');

        player.getInventory().setHelmet(getColoredArmor(new ItemStack(Material.LEATHER_HELMET), r, g, b));
        player.getInventory().setChestplate(getColoredArmor(new ItemStack(Material.LEATHER_CHESTPLATE), r, g, b));
        player.getInventory().setLeggings(getColoredArmor(new ItemStack(Material.LEATHER_LEGGINGS), r, g, b));
        player.getInventory().setBoots(getColoredArmor(new ItemStack(Material.LEATHER_BOOTS), r, g, b));
        player.getInventory().addItem(new ItemStack(Material.WOOD_SWORD));
    }

    public static void handlePlayerDeath(Player player, Player killer) {
        Team playerTeam = Team.getTeam(player);
        player.setHealth(player.getMaxHealth());

        if (playerTeam.isWithBed()) {
            spectator(player);
            respawningPlayers.add(player);

            if (killer == null) {
                sendPlayerKillMessage(player);
            } else {

                sendPlayerKillMessage(player, killer);
            }

            handlePlayerRespawn(player, playerTeam);
        } else {
            handleFinalKill(player, killer);
        }
    }

    private static void handleFinalKill(Player player, Player killer) {
        Team playerTeam = getTeam(player);
        Team.deadPlayers.add(player);
        spectator(player);

        if (killer == null) {
            sendFinalKillMessage(player);
        } else {
            sendFinalKillMessage(player, killer);
        }

        if (playerTeam.isAlive() && deadPlayers.containsAll(playerTeam.getTeamPlayers())) {
            playerTeam.eliminate();
        }

        if (getAliveTeams() == 1) {
            finishMatch(killer);
        }
    }

    private static void handlePlayerRespawn(Player player, Team team) {
        new BukkitRunnable(){
            int time = 5;
            @Override
            public void run() {
                if (time <= 0) {
                    this.cancel();
                    clearTitle(player);
                    player.setGameMode(GameMode.SURVIVAL);
                    player.setFlying(false);
                    player.setAllowFlight(false);
                    respawningPlayers.remove(player);
                    player.teleport(team.getLocation());

                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.showPlayer(player);
                    }

                } else {
                    sendRespawnTimerTitle(player, time);
                    time--;
                }
            }
        }.runTaskTimer(JavaPlugin.getPlugin(BedWars.class), 0, 20);
    }

    private static void spectator(Player player) {
        player.setGameMode(GameMode.ADVENTURE);
        player.setAllowFlight(true);
        player.setFlying(true);
        player.setVelocity(new Vector(0, 0.25, 0));

        for (Player players : Bukkit.getOnlinePlayers()) {
            players.hidePlayer(player);
        }

    }

    public static void handleVoidDamage(EntityDamageEvent event, Player player) {
        event.setCancelled(true);
        player.setFallDistance(0);

        if (gameStatus.equals(GameStatus.AWAITING_PLAYERS) || gameStatus.equals(GameStatus.STARTING) || respawningPlayers.contains(player) || deadPlayers.contains(player)) {
            player.teleport(BedWarsConstants.LOBBY_LOCATION);
        } else {
            handlePlayerDeath(player, null);
        }
    }

    private static void finishMatch(Player player) {
        Bukkit.getServer().getScheduler().runTaskLater(JavaPlugin.getPlugin(BedWars.class), Bukkit::shutdown, 160);

        new BukkitRunnable(){
            @Override
            public void run() {
                Bukkit.getServer().getWorld("world").spawnEntity(player.getLocation(), EntityType.FIREWORK);
            }
        }.runTaskTimer(JavaPlugin.getPlugin(BedWars.class), 0, 10);

        sendMatchEndingTitle(getTeam(player));
    }

    public static void setGameStatus(GameStatus status) {
        broadcastActionBar(MessageUtils.translateColorCodes(status.getText()));
        gameStatus = status;
    }

}
