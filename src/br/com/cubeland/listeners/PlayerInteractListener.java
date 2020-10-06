package br.com.cubeland.listeners;

import br.com.cubeland.utils.BlockedDrops;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onArmorSlotClick(InventoryClickEvent event) {
        if (event.getSlotType().equals(InventoryType.SlotType.ARMOR)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (BlockedDrops.contains(event.getItemDrop().getItemStack().getType().toString())) {
            event.setCancelled(true);
        }
    }
}
