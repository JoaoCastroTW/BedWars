package tw.joao.generators;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import tw.joao.BedWars;

public class NaturalGenerator extends Generator {
    private final NaturalGeneratorType type;
    private final Location location;
    private final String name;
    private final Material material;
    private final Material icon;
    private final int maxAmount;
    private int level;
    private long delay;

    public NaturalGenerator(NaturalGeneratorType type, int x, int y, int z) {
        this.type = type;
        this.location = new Location(Bukkit.getWorld("world"), x, y, z);
        this.name = type.getName();
        this.material = type.getMaterial();
        this.icon = type.getIcon();
        this.maxAmount = type.getMaxAmount();
        this.level = 1;
        this.delay = type.getDelay(level);

        spawnGenerator();
    }

    public void startGenerator() {
        new BukkitRunnable(){
            @Override
            public void run() {
                if (getIdleItems() < maxAmount) {
                    generate();
                }
            }
        }.runTaskTimer(JavaPlugin.getPlugin(BedWars.class), 0, delay);
    }

    public int getIdleItems() {
        int idleItems = 0;

        for (Entity entity : Bukkit.getServer().getWorld("world").getNearbyEntities(location, 4, 4, 4)) {
            if (entity instanceof Item) {
                Item item = (Item) entity;

                if (item.getItemStack().getType().equals(material)) {
                    idleItems += item.getItemStack().getAmount();
                }
            }
        }

        return idleItems;
    }

    @Override
    protected void spawnGenerator() {
        location.subtract(0, 1, 0).getBlock().setType(icon);
    }

    @Override
    public void generate() {
        Bukkit.getServer().getWorld("world")
                .dropItemNaturally(location.add(0.5, 0.5, 0.5), new ItemStack(material, 1))
                .setVelocity(new Vector(0, 0, 0));
    }

    @Override
    public void upgrade() {
        this.level++;
        this.delay = type.getDelay(level);
    }
}
