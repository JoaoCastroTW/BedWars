package br.com.cubeland.teams;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public enum Teams {
    RED("Vermelho", "&c", 14, new Location(Bukkit.getWorld("world"), -33.5, 66, 0.5), new Location(Bukkit.getWorld("world"), -30, 66, 0)),
    GREEN("Verde", "&a", 5, new Location(Bukkit.getWorld("world"), 0.5, 66, -33.5), new Location(Bukkit.getWorld("world"), 0, 66, -30)),
    BLUE("Azul", "&9", 3, new Location(Bukkit.getWorld("world"), 34.5, 66, 0.5), new Location(Bukkit.getWorld("world"), 31, 66, 0)),
    ORANGE("Laranja", "&6", 1, new Location(Bukkit.getWorld("world"), 0.5, 66, 34.5), new Location(Bukkit.getWorld("world"), 0, 66, 31));

    private final String name;
    private final int data;
    private final String color;
    private final Location location;
    private final Location bedLocation;

    Teams(String name, String color, int woolData, Location location, Location bedLocation) {
        this.name = name;
        this.data = woolData;
        this.color = color;
        this.location = location;
        this.bedLocation = bedLocation;
    }

    public String getName() {
        return name;
    }

    public int getData() {
        return data;
    }

    public String getColor() {
        return color;
    }

    public Location getLocation() { return location; }

    public Location getBedLocation() {
        return bedLocation;
    }
}
