package tw.joao.listeners;

import tw.joao.utils.BlockedDrops;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerInventoryListener implements Listener {

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

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        if (event.getItem().getItemStack().getType().equals(Material.BED)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerItemDamage(PlayerItemDamageEvent event) {
        event.setCancelled(true);
    }
}
