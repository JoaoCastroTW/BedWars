package tw.joao.generators;

import lombok.Getter;
import org.bukkit.Location;
import java.util.ArrayList;

public abstract class Generator{

    private static ArrayList<Generator> generators = new ArrayList<>();
    @Getter private int level;
    @Getter private Location location;
    @Getter private NaturalGeneratorType type;

    public Generator() {
        generators.add(this);
    }

    protected abstract void spawnGenerator();
    protected abstract void startGenerator();
    public abstract void generate();
    public abstract void upgrade();

    public static void startAllGenerators() {
        for (Generator generator : generators) {
            generator.startGenerator();
        }
    }

}
