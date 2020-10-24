package tw.joao.teams;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import tw.joao.BedWarsConstants;

import java.util.HashSet;

public class TeamManager {

    static final HashSet<Team> teams = new HashSet<>();

    public static void registerTeams() {
        teams.add(new Team(Teams.RED));
        teams.add(new Team(Teams.BLUE));
        teams.add(new Team(Teams.GREEN));
        teams.add(new Team(Teams.ORANGE));
    }

    public static void handleBedBreak(Player playerBreaker, Block block) {
        if (Team.isBedLocation(block)) {
            Team bedTeam = Team.getBedTeam(block);
            bedTeam.breakBed(playerBreaker, block);
        }
    }

    public static void assignTeams() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            teams.stream()
                    .filter(team -> team.getTeamPlayers().size() < BedWarsConstants.TEAM_MAX_PLAYERS)
                    .findAny()
                    .ifPresent(team -> team.addPlayer(player));
        }
    }
}
