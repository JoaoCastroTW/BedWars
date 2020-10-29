package tw.joao.generator.island;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum IslandGeneratorItem {
    EMERALD(new ItemStack(Material.EMERALD), 3, 4, 100, 80),
    DIAMOND(new ItemStack(Material.DIAMOND), 3, 4, 80, 60),
    GOLD(new ItemStack(Material.GOLD_INGOT), 1, 8,  40, 20, 10),
    IRON(new ItemStack(Material.IRON_INGOT), 1, 12, 20, 10, 5);

    @Getter private final ItemStack item;
    @Getter private final int minLevel;
    @Getter private final int maxIdleItems;
    @Getter private final int[] delay;

    IslandGeneratorItem(ItemStack item, int minLevel, int maxIdleItems, int... delay) {
        this.item = item;
        this.minLevel = minLevel;
        this.maxIdleItems = maxIdleItems;
        this.delay = delay;
    }

    public int getDelay(int level) {
        if (level >= minLevel) {
            return (level - minLevel > delay.length) ?
                    delay[delay.length - 1] :
                    delay[level - minLevel];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
