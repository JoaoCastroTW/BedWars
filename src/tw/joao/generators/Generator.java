package tw.joao.generators;

import org.bukkit.Location;
import java.util.ArrayList;

public abstract class Generator{

    private static ArrayList<Generator> generators = new ArrayList<>();
    private int generatorLevel;
    private Location location;
    private NaturalGeneratorType type;

    public Generator() {
        generators.add(this);
    }

    protected abstract void spawnGenerator();
    protected abstract void startGenerator();
    public abstract void generate();
    public abstract void upgrade();

    public int getLevel() {
        return this.generatorLevel;
    }

    public static void startAllGenerators() {
        for (Generator generator : generators) {
            generator.startGenerator();
        }
    }

}
