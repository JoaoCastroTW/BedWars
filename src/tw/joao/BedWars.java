package tw.joao;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import tw.joao.utils.AdminCommands;
import org.bukkit.plugin.java.JavaPlugin;
import tw.joao.listeners.*;

public class BedWars extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BlockListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerConnectionListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDamageListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInventoryListener(), this);
        getServer().getPluginManager().registerEvents(new WorldListener(), this);
        this.getCommand("admin").setExecutor(new AdminCommands());
    }

    @Override
    public void onDisable() {
        Bukkit.getWorld("world").getEntities().stream()
                .filter(entity -> !(entity instanceof Player))
                .forEach(Entity::remove);
    }
}
