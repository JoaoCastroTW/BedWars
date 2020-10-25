package tw.joao.holograms;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.Arrays;

public class Hologram {
    private Location location;
    private ArrayList<String> textLines = new ArrayList<>();
    private ArrayList<ArmorStand> hologramLines = new ArrayList<>();

    public Hologram(Location location, String... args) {
        this.location = location;
        this.textLines.addAll(Arrays.asList(args));
        spawnHologram();
    }

    public void spawnHologram() {
        if (!location.getChunk().isLoaded()) {
            location.getChunk().load();
        }

        for (int i = 0; i < textLines.size(); i++) {
            addLine(textLines.get(i));
        }
    }

    public void modifyText(int line, String text) {
        hologramLines.get(line).setCustomName(text);
    }

    public void addLine(String text) {
        Location lineLocation = hologramLines.size() > 0 ?
                hologramLines.get(hologramLines.size() - 1).getLocation().clone().subtract(0, 0.25, 0) :
                this.location;
        ArmorStand line = (ArmorStand) lineLocation.getWorld().spawnEntity(lineLocation, EntityType.ARMOR_STAND);

        line.setSmall(true);
        line.setGravity(false);
        line.setBasePlate(false);
        line.setVisible(false);
        line.setCustomNameVisible(true);
        line.setCustomName(text);
        hologramLines.add(line);
    }

    public void removeLine(int line) {
        hologramLines.get(line).remove();
        hologramLines.remove(line);
    }

}
