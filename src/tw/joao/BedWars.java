package tw.joao;

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

}
