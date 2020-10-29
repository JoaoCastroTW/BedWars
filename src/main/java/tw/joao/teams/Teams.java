package tw.joao.teams;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public enum Teams {
    RED("Vermelho", "&c", 14, 206, 39, 28, new Location(Bukkit.getWorld("world"), -33.5, 66, 0.5), new Location(Bukkit.getWorld("world"), -30, 66, 0), new Location(Bukkit.getWorld("world"), -36, 66, 0)),
    GREEN("Verde", "&a", 5, 4, 206, 28, new Location(Bukkit.getWorld("world"), 0.5, 66, -33.5), new Location(Bukkit.getWorld("world"), 0, 66, -30), new Location(Bukkit.getWorld("world"), 0, 66, -36)),
    BLUE("Azul", "&9", 3, 25, 12, 199, new Location(Bukkit.getWorld("world"), 34.5, 66, 0.5), new Location(Bukkit.getWorld("world"), 31, 66, 0), new Location(Bukkit.getWorld("world"), 37, 66, 0)),
    ORANGE("Laranja", "&6", 1, 255, 131, 28, new Location(Bukkit.getWorld("world"), 0.5, 66, 34.5), new Location(Bukkit.getWorld("world"), 0, 66, 31), new Location(Bukkit.getWorld("world"), 0, 66, 37));

    @Getter private final String name;
    @Getter private final int data;
    @Getter private final String colorCode;
    private final int colorR;
    private final int colorG;
    private final int colorB;
    @Getter private final Location location;
    @Getter private final Location bedLocation;
    @Getter private final Location generatorLocation;

    Teams(String name, String colorCode, int woolData, int colorR, int colorG, int colorB, Location location, Location bedLocation, Location generatorLocation) {
        this.name = name;
        this.data = woolData;
        this.colorCode = colorCode;
        this.colorR = colorR;
        this.colorG = colorG;
        this.colorB = colorB;
        this.location = location;
        this.bedLocation = bedLocation;
        this.generatorLocation = generatorLocation;
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
}
