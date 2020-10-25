package tw.joao.generators;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import tw.joao.BedWars;

import static tw.joao.messages.HologramMessages.*;

public class NaturalGenerator extends Generator {
    @Getter private final NaturalGeneratorType type;
    @Getter private final Location location;
    @Getter private final String name;
    @Getter private final String color;
    @Getter private final Material material;
    @Getter private final Material icon;
    @Getter private final int maxAmount;
    @Getter private int level;
    @Getter private long delay;
    @Getter private long currentCooldown;

    public NaturalGenerator(NaturalGeneratorType type, int x, int y, int z) {
        this.type = type;
        this.location = new Location(Bukkit.getWorld("world"), x, y, z);
        this.name = type.getName();
        this.color = type.getColor();
        this.material = type.getMaterial();
        this.icon = type.getIcon();
        this.maxAmount = type.getMaxAmount();
        this.level = 1;
        this.delay = type.getDelay(level);
        this.currentCooldown = delay / 20;

        spawnGenerator();
    }

    //Gambiarra temporaria, favor ignorar.
    public void startGenerator() {
        new BukkitRunnable(){
            @Override
            public void run() {
                currentCooldown--;
                if (getIdleItems() < maxAmount && currentCooldown <= 0) {
                    generate();
                    currentCooldown = getDelay() / 20;
                }
            }
        }.runTaskTimer(JavaPlugin.getPlugin(BedWars.class), 20, 20);

        new BukkitRunnable(){
            @Override
            public void run() {
                if (level < 3) {
                    upgrade();
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(JavaPlugin.getPlugin(BedWars.class), 200, 200);
    }

    public int getIdleItems() {
        return Bukkit.getServer().getWorld("world").getNearbyEntities(getLocation(), 4, 4, 4).stream()
                .filter(entity -> entity instanceof Item && ((Item) entity).getItemStack().getType().equals(material))
                .mapToInt(entity -> ((Item) entity).getItemStack().getAmount())
                .sum();
    }

    @Override
    protected void spawnGenerator() {
        getLocation().clone().subtract(0, 1, 0).getBlock().setType(icon);
        spawnNaturalGeneratorHologram(this);
    }

    @Override
    public void generate() {
        Bukkit.getServer().getWorld("world")
                .dropItemNaturally(getLocation().clone().add(0.5, 1.5, 0.5), new ItemStack(material, 1))
                .setVelocity(new Vector(0, 0, 0));
    }

    @Override
    public void upgrade() {
        this.level++;
        this.delay = type.getDelay(level);
    }
}
