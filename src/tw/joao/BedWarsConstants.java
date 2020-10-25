package tw.joao;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class BedWarsConstants {
    public static final Location LOBBY_LOCATION = new Location(Bukkit.getWorld("world"), 0.5, 65, 0.5);
    public static final int TEAM_MAX_PLAYERS = 1;
    public static final int MIN_PLAYERS = 2;
    public static final int MAX_PLAYERS = 4;
    public static final int MATCH_START_COUNTDOWN = 10;
}
