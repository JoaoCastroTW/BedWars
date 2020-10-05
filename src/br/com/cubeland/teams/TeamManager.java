package br.com.cubeland.teams;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class TeamManager {

    static final Team[] teams = new Team[4];

    public static void registerTeams() {
        teams[0] = new Team(Teams.RED);
        teams[1] = new Team(Teams.BLUE);
        teams[2] = new Team(Teams.GREEN);
        teams[3] = new Team(Teams.ORANGE);
    }

    public static void handleBedBreak(Player playerBreaker, Block block) {
        if (Team.isBedLocation(block)) {
            Team bedTeam = Team.getTeam(block);
            bedTeam.breakBed(playerBreaker, block);
        }
    }

    public static void assignTeams() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (Team team : teams) {
                if (team.getTeamPlayers().size() < 1) {
                    team.addPlayer(player);
                    break;
                }
            }
        }
    }



}
