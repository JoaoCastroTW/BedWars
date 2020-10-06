package br.com.cubeland.teams;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public enum Teams {
    RED("Vermelho", "&c", 14, 206, 39, 28, new Location(Bukkit.getWorld("world"), -33.5, 66, 0.5), new Location(Bukkit.getWorld("world"), -30, 66, 0)),
    GREEN("Verde", "&a", 5, 4, 206, 28, new Location(Bukkit.getWorld("world"), 0.5, 66, -33.5), new Location(Bukkit.getWorld("world"), 0, 66, -30)),
    BLUE("Azul", "&9", 3, 25, 12, 199, new Location(Bukkit.getWorld("world"), 34.5, 66, 0.5), new Location(Bukkit.getWorld("world"), 31, 66, 0)),
    ORANGE("Laranja", "&6", 1, 255, 131, 28, new Location(Bukkit.getWorld("world"), 0.5, 66, 34.5), new Location(Bukkit.getWorld("world"), 0, 66, 31));

    private final String name;
    private final int data;
    private final String colorCode;
    private final int colorR;
    private final int colorG;
    private final int colorB;
    private final Location location;
    private final Location bedLocation;

    Teams(String name, String colorCode, int woolData, int colorR, int colorG, int colorB, Location location, Location bedLocation) {
        this.name = name;
        this.data = woolData;
        this.colorCode = colorCode;
        this.colorR = colorR;
        this.colorG = colorG;
        this.colorB = colorB;
        this.location = location;
        this.bedLocation = bedLocation;
    }

    public String getName() {
        return name;
    }

    public int getData() {
        return data;
    }

    public String getColorCode() {
        return colorCode;
    }

    public int getColor(char id) {
        switch(id) {
            case 'r':
                return this.colorR;
            case 'g':
                return this.colorG;
            case 'b':
                return this.colorB;
            default:
                return 0;
        }
    }

    public Location getLocation() { return location; }

    public Location getBedLocation() {
        return bedLocation;
    }
}
