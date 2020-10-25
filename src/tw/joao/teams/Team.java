package tw.joao.teams;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.material.Bed;
import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static tw.joao.messages.ChatMessages.*;

public class Team {
    private static List<Team> teams = new ArrayList<>();
    private static List<Player> players = new ArrayList<>();
    public static List<Player> deadPlayers = new ArrayList<>();
    public static List<Player> respawningPlayers = new ArrayList<>();
    private List<Player> teamPlayers = new ArrayList<>();
    private final Location location;
    private final Location bedLocation;
    private final String name;
    private final String colorCode;
    private final int colorR;
    private final int colorG;
    private final int colorB;
    private final int woolData;
    private boolean bed;
    private boolean alive;

    public Team(Teams team) {
        this.location = team.getLocation();
        this.name = team.getName();
        this.colorCode = team.getColorCode();
        this.colorR = team.getColor('r');
        this.colorG = team.getColor('g');
        this.colorB = team.getColor('b');
        this.woolData = team.getData();
        this.bedLocation = team.getBedLocation();
        this.bed = true;
        this.alive = true;

        teams.add(this);
    }

    public void addPlayer(Player player) {
        this.teamPlayers.add(player);
        players.add(player);
        sendTeamAssignedMessage(player);
    }

    public void breakBed(Player player, Block block) {
        sendBedBreakMessage(player, block);
        this.bed = false;
    }

    public void eliminate() {
        this.alive = false;
        sendTeamEliminatedMessage(this);
    }

    public boolean hasPlayer(Player player) {
        return teamPlayers.contains(player);
    }

    public boolean isAlive() {
        return alive;
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
                .anyMatch(team -> team.hasBed() && (team.getBedLocation().equals(location) || team.getBedLocation().equals(upperBedLocation)));
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

    public boolean hasBed() { return bed; }

    public String getName() {
        return name;
    }

    public String getColorCode() {
        return colorCode;
    }

    public int getColor(char id) {
        switch(id) {
            case 'r':
                return this.colorR;
            case 'g':
                return this.colorG;
            case 'b':
                return this.colorB;
            default:
                return 0;
        }
    }

    @Nonnull
    public Location getLocation() { return location; }

    public Location getBedLocation() {
        return bedLocation;
    }

    public int getWoolData() { return woolData; }

    public List<Player> getTeamPlayers() { return teamPlayers; }

    public static List<Team> getTeams() { return teams; }

    public static List<Player> getPlayers() { return players; }

}
