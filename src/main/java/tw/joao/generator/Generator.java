package tw.joao.generator;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashSet;

public abstract class Generator {

    private static final HashSet<Generator> generators = new HashSet<>();
    @Getter @Setter private int level = 1;
    @Getter @Setter private Location location;
    @Getter @Setter private int maxLevel;
    @Getter private int[] delay;

    public Generator() {
        generators.add(this);
    }

    public abstract void spawnGenerator();
    public abstract void startGenerator();

    public void upgrade() {
        if (level < maxLevel) {
            level++;
        }
    }

    public void generate(ItemStack item) {
        Bukkit.getServer().getWorld("world")
                .dropItemNaturally(getLocation().clone().add(0.5, 1.5, 0.5), item)
                .setVelocity(new Vector(0, 0, 0));
    }

    public int getIdleItems(ItemStack item) {
        return Bukkit.getServer().getWorld("world").getNearbyEntities(getLocation(), 4, 4, 4).stream()
                .filter(entity -> entity instanceof Item && ((Item) entity).getItemStack().getType().equals(item.getType()))
                .mapToInt(entity -> ((Item) entity).getItemStack().getAmount())
                .sum();
    }

    public static void startAllGenerators() {
        for (Generator generator : generators) {
            generator.startGenerator();
        }
    }
}
