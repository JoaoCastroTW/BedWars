package br.com.cubeland.listeners;

import br.com.cubeland.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import static br.com.cubeland.GameStatus.AWAITING_PLAYERS;
import static br.com.cubeland.GameStatus.STARTING;

public class PlayerDamageListener implements Listener {

    @EventHandler
    public void onPlayerDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            Player player = (Player) event.getEntity();
            Player attacker = (Player) event.getDamager();

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

            if (GameManager.getGameStatus().equals(AWAITING_PLAYERS) || GameManager.getGameStatus().equals(STARTING)) {
                event.setCancelled(true);
            }
        }
    }

}
