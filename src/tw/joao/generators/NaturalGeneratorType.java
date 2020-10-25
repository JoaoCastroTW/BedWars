package tw.joao.generators;

import org.bukkit.Material;

public enum NaturalGeneratorType {
    EMERALD("Esmeralda", "&a", Material.EMERALD, Material.EMERALD_BLOCK, 4),
    DIAMOND("Diamante", "&b", Material.DIAMOND, Material.DIAMOND_BLOCK, 4),
    GOLD("Ouro", "&6", Material.GOLD_INGOT, Material.GOLD_BLOCK, 12),
    IRON("Ferro", "&f", Material.IRON_INGOT, Material.IRON_BLOCK, 32);

    private final String name;
    private final String color;
    private final Material material;
    private final Material icon;
    private final int maxAmount;

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

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public Material getMaterial() {
        return material;
    }

    public Material getIcon() {
        return icon;
    }

    public int getMaxAmount() {
        return maxAmount;
    }
}
