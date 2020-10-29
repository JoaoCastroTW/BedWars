package tw.joao.generator.natural;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import tw.joao.BedWars;
import tw.joao.generator.Generator;

import static tw.joao.messages.HologramMessages.*;

public abstract class NaturalGenerator extends Generator {

    @Getter @Setter private String name;
    @Getter @Setter private String color;
    @Getter @Setter private Material icon;
    @Getter @Setter private ItemStack item;
    @Getter @Setter private int maxIdleItems;
    @Getter private boolean active = true;
    @Setter private int[] upgradeDelay;
    @Setter private int[] generationDelay;
    @Getter private int currentCooldown;

    @Override
    public void spawnGenerator() {
        getLocation().clone().subtract(0, 1, 0).getBlock().setType(icon);
        spawnNaturalGeneratorHologram(this);
    }

    @Override
    public void startGenerator() {
        currentCooldown = getGenerationDelay(getLevel());

        new BukkitRunnable() {
            @Override
            public void run() {
                if (getIdleItems(item) <= maxIdleItems) {
                    active = true;

                    if (getGenerationDelay(getLevel()) - currentCooldown >= getGenerationDelay(getLevel())) {
                        generate(item);
                        currentCooldown = getGenerationDelay(getLevel());
                    } else {
                        currentCooldown -= 2;
                    }
                } else {
                    active = false;
                }
            }
        }.runTaskTimer(JavaPlugin.getPlugin(BedWars.class), 0, 2);

        new BukkitRunnable(){
            int currentTicks;

            @Override
            public void run() {
                if (getLevel() < getMaxLevel()) {
                    if (currentTicks >= getUpgradeDelay(getLevel())) {
                        upgrade();
                        if (currentCooldown > getGenerationDelay(getLevel())) {
                            currentCooldown = getGenerationDelay(getLevel());
                        }
                        currentTicks = 0;
                    } else {
                        currentTicks += 20;
                    }
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(JavaPlugin.getPlugin(BedWars.class), 20, 20);
    }

    public int getUpgradeDelay(int level) {
        if (!(level > upgradeDelay.length)) {
            return upgradeDelay[level-1];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public int getGenerationDelay(int level) {
        if (!(level > generationDelay.length)) {
            return generationDelay[level-1];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

}
