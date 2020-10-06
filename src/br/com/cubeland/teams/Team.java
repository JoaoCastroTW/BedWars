package br.com.cubeland.teams;

import static br.com.cubeland.messages.ChatMessages.*;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.material.Bed;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class Team {
    private static List<Team> teams = new ArrayList<>();
    private static List<Player> players = new ArrayList<>();
    public static List<Player> deadPlayers = new ArrayList<>();
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

    public static Team getTeam(Player player) {
        for (Team team : teams) {
            if (getTeam(team, player) != null) {
                return team;
            }
        }

        return null;
    }

    private static Team getTeam(Team team, Player player) {
        if (team.teamPlayers.contains(player)) {
            return team;
        }

        return null;
    }

    public static Team getTeam(Block block) {
        for (Team team : teams) {
            if (getTeam(team, block) != null) {
                return team;
            }
        }

        return null;
    }

    private static Team getTeam(Team team, Block block) {
        if (isBedLocation(team, block)) {
            return team;
        }

        return null;
    }

    public static boolean isBedLocation(Block block) {
        for (Team team : teams) {
            if (isBedLocation(team, block)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isBedLocation(Team team, Block block) {
        Location location = block.getLocation();
        Location upperBedLocation = getUpperBedLocation(block);

        if (team.hasBed() && (team.bedLocation.equals(location) || team.bedLocation.equals(upperBedLocation))) {
            return true;
        }

        return false;
    }

    public static boolean isAlive(Player player) {
        if (deadPlayers.contains(player)) {
            return false;
        }

        return true;
    }

    public static boolean isAlive(Team team) {
        for (Player player : team.getTeamPlayers()) {
            if (isAlive(player)) {
                return true;
            }
        }

        return false;
    }

    public static int getAliveTeams() {
        int aliveTeams = 0;

        for (Team team : teams) {
            for (Player player : team.getTeamPlayers()) {
                if (isAlive(player)) {
                    aliveTeams++;
                    break;
                }
            }
        }

        return aliveTeams;
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

    public Location getBedLocation() { return bedLocation; }

    public int getWoolData() { return woolData; }

    public List<Player> getTeamPlayers() { return teamPlayers; }

    public static List<Team> getTeams() { return teams; }

    public static List<Player> getPlayers() { return players; }

}
