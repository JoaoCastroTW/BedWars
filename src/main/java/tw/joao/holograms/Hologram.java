package tw.joao.holograms;

import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftArmorStand;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
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

        for (String textLine : textLines) {
            addLine(textLine);
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

        EntityArmorStand nmsArmorStand = ((CraftArmorStand) line).getHandle();
        NBTTagCompound compound = new NBTTagCompound();

        nmsArmorStand.c(compound);
        compound.setBoolean("Marker", true);
        nmsArmorStand.f(compound);

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
