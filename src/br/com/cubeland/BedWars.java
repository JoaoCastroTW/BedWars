package br.com.cubeland;

import br.com.cubeland.listeners.*;
import br.com.cubeland.utils.AdminCommands;
import org.bukkit.plugin.java.JavaPlugin;

public class BedWars extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BlockListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerConnectionListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDamageListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new WorldListener(), this);
        this.getCommand("admin").setExecutor(new AdminCommands());
    }

}
