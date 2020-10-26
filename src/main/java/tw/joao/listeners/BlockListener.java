package tw.joao.listeners;

import tw.joao.GameManager;
import tw.joao.GameStatus;
import tw.joao.teams.Team;
import tw.joao.teams.TeamManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        if (!GameManager.getGameStatus().equals(GameStatus.IN_PROGRESS)) {
            event.setCancelled(true);
            return;
        }

        if (block.getType().equals(Material.BED_BLOCK) && Team.isAlive(player)) {
            TeamManager.handleBedBreak(player, block);
        }
    }

}
