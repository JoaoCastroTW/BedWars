package tw.joao.generator.island;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import tw.joao.BedWars;
import tw.joao.generator.Generator;
import tw.joao.teams.Team;

import static tw.joao.messages.HologramMessages.*;

public class IslandGenerator extends Generator {

    @Getter private final Team team;
    @Getter private final int maxLevel = 4;
    private final IslandGeneratorItem[] items = {
            IslandGeneratorItem.EMERALD,
            IslandGeneratorItem.DIAMOND,
            IslandGeneratorItem.GOLD,
            IslandGeneratorItem.IRON
    };

    public IslandGenerator(Team team) {
        this.team = team;
        setLocation(team.getGeneratorLocation());
        setMaxLevel(maxLevel);
        spawnGenerator();
    }

    @Override
    public void spawnGenerator() {
        spawnIslandGeneratorHologram(this);
    }

    @Override
    public void startGenerator() {
        for (IslandGeneratorItem item : items) {
            new BukkitRunnable() {
                int currentTicks;

                @Override
                public void run() {
                    if (getLevel() >= item.getMinLevel() && getIdleItems(item.getItem()) <= item.getMaxIdleItems()) {
                        if (currentTicks >= item.getDelay(getLevel())) {
                            generate(item.getItem());
                            currentTicks = 0;
                        } else {
                            currentTicks++;
                        }
                    }
                }
            }.runTaskTimer(JavaPlugin.getPlugin(BedWars.class), 1, 1);
        }
    }
}
