package tw.joao.teams;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.material.Bed;
import tw.joao.generator.island.IslandGenerator;

import java.util.ArrayList;
import java.util.List;

import static tw.joao.messages.ChatMessages.*;

public class Team {
    private static List<Team> teams = new ArrayList<>();
    private static List<Player> players = new ArrayList<>();
    public static List<Player> deadPlayers = new ArrayList<>();
    public static List<Player> respawningPlayers = new ArrayList<>();

    @Getter private List<Player> teamPlayers = new ArrayList<>();
    @Getter private final Location location;
    @Getter private final Location bedLocation;
    @Getter private final Location generatorLocation;
    @Getter private final IslandGenerator generator;
    @Getter private final String name;
    @Getter private final String colorCode;
    @Getter private final int colorR;
    @Getter private final int colorG;
    @Getter private final int colorB;
    @Getter private final int woolData;
    @Getter private boolean withBed;
    @Getter private boolean alive;

    public Team(Teams team) {
        this.location = team.getLocation();
        this.bedLocation = team.getBedLocation();
        this.generatorLocation = team.getGeneratorLocation();
        this.name = team.getName();
        this.colorCode = team.getColorCode();
        this.colorR = team.getColor('r');
        this.colorG = team.getColor('g');
        this.colorB = team.getColor('b');
        this.woolData = team.getData();
        this.withBed = true;
        this.alive = true;

        this.generator = new IslandGenerator(this);
        teams.add(this);
    }

    public void addPlayer(Player player) {
        teamPlayers.add(player);
        players.add(player);
        sendTeamAssignedMessage(player);
    }

    public void breakBed(Player player, Block block) {
        sendBedBreakMessage(player, block);
        this.withBed = false;
    }

    public void eliminate() {
        this.alive = false;
        sendTeamEliminatedMessage(this);
    }

    public boolean hasPlayer(Player player) {
        return teamPlayers.contains(player);
    }

    public static Team getTeam(Player player) {
        return teams.stream()
                .filter(team -> team.hasPlayer(player))
                .findAny()
                .orElse(null);
    }

    public static Team getBedTeam(Block bed) {
        Location location = bed.getLocation();
        Location upperBedLocation = getUpperBedLocation(bed);

        return teams.stream()
                .filter(team -> team.bedLocation.equals(location) || team.bedLocation.equals(upperBedLocation))
                .findAny()
                .orElse(null);
    }

    public static boolean isBedLocation(Block block) {
        Location location = block.getLocation();
        Location upperBedLocation = getUpperBedLocation(block);

        return teams.stream()
                .anyMatch(team -> team.isWithBed() && (team.getBedLocation().equals(location) || team.getBedLocation().equals(upperBedLocation)));
    }

    public static boolean isAlive(Player player) {
        return !deadPlayers.contains(player);
    }

    public static int getAliveTeams() {
        return (int) teams.stream()
                .filter(Team::isAlive)
                .count();
    }

    public static Location getUpperBedLocation(Block block) {
        Bed bedBlock = (Bed) block.getState().getData();
        BlockFace facing = bedBlock.getFacing();
        Location bedUpperLocation = block.getLocation();

        switch (facing) {
            case NORTH:
                bedUpperLocation.add(0, 0, 1);
                break;
            case EAST:
                bedUpperLocation.subtract(1, 0, 0);
                break;
            case SOUTH:
                bedUpperLocation.subtract(0, 0, 1);
                break;
            case WEST:
                bedUpperLocation.add(1, 0, 0);
                break;
        }

        return bedUpperLocation;
    }

    public static List<Team> getTeams() { return teams; }

    public static List<Player> getPlayers() { return players; }

}
