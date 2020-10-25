package tw.joao.listeners;

import tw.joao.GameManager;
import tw.joao.teams.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import tw.joao.GameStatus;

public class PlayerDamageListener implements Listener {

    @EventHandler
    public void onPlayerDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            Player player = (Player) event.getEntity();
            Player attacker = (Player) event.getDamager();

            if (Team.respawningPlayers.contains(attacker) || Team.deadPlayers.contains(attacker)) {
                event.setCancelled(true);
            }

            if (player.getHealth() - event.getDamage() < 1) {
                event.setCancelled(true);
                GameManager.handlePlayerDeath(player, attacker);
            }
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (event.getCause().equals(EntityDamageEvent.DamageCause.VOID)) {
                GameManager.handleVoidDamage(event, player);
            }

            if (GameManager.getGameStatus().equals(GameStatus.AWAITING_PLAYERS) || GameManager.getGameStatus().equals(GameStatus.STARTING)) {
                event.setCancelled(true);
            }
        }
    }

}
