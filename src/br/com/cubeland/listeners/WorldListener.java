package br.com.cubeland.listeners;

import br.com.cubeland.teams.TeamManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;

public class WorldListener implements Listener {

    @EventHandler
    public void onWordLoad(WorldLoadEvent event) {
        if (event.getWorld().getName().equalsIgnoreCase("world")) {
            TeamManager.registerTeams();
        }
    }

}
