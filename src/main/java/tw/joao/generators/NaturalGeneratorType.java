package tw.joao.generators;

import lombok.Getter;
import org.bukkit.Material;

public enum NaturalGeneratorType {
    EMERALD("Esmeralda", "&a", Material.EMERALD, Material.EMERALD_BLOCK, 4),
    DIAMOND("Diamante", "&b", Material.DIAMOND, Material.DIAMOND_BLOCK, 4),
    GOLD("Ouro", "&6", Material.GOLD_INGOT, Material.GOLD_BLOCK, 12),
    IRON("Ferro", "&f", Material.IRON_INGOT, Material.IRON_BLOCK, 32);

    @Getter private final String name;
    @Getter private final String color;
    @Getter private final Material material;
    @Getter private final Material icon;
    @Getter private final int maxAmount;

    NaturalGeneratorType(String name, String color, Material material, Material icon, int maxAmount) {
        this.name = name;
        this.color = color;
        this.material = material;
        this.icon = icon;
        this.maxAmount = maxAmount;
    }

    //Valor em ticks do delay entre cada ciclo de geracao.
    public long getDelay(int level) {
        long[] emeraldGenerator = {200, 100, 60};
        long[] diamondGenerator = {200, 100, 60};
        long[] goldGenerator = {200, 100, 60};
        long[] ironGenerator = {200, 100, 60};

        switch (this) {
            case EMERALD:
                return emeraldGenerator[level-1];
            case DIAMOND:
                return diamondGenerator[level-1];
            case GOLD:
                return goldGenerator[level-1];
            case IRON:
                return ironGenerator[level-1];
            default:
                return 100;
        }
    }
}
