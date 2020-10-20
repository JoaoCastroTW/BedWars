package tw.joao.generators;

import org.bukkit.Material;

public enum NaturalGeneratorType {
    EMERALD("Esmeralda", Material.EMERALD, Material.EMERALD_BLOCK, 4),
    DIAMOND("Diamante", Material.DIAMOND, Material.DIAMOND_BLOCK, 4),
    GOLD("Ouro", Material.GOLD_INGOT, Material.GOLD_BLOCK, 12),
    IRON("Ferro", Material.IRON_INGOT, Material.IRON_BLOCK, 32);

    private final String name;
    private final Material material;
    private final Material icon;
    private final int maxAmount;

    NaturalGeneratorType(String name, Material material, Material icon, int maxAmount) {
        this.name = name;
        this.material = material;
        this.icon = icon;
        this.maxAmount = maxAmount;
    }

    //Valor em ticks do delay entre cada ciclo de geracao.
    public long getDelay(int level) {
        long[] emeraldGenerator = {200, 100, 50};
        long[] diamondGenerator = {200, 100, 50};
        long[] goldGenerator = {200, 100, 50};
        long[] ironGenerator = {200, 100, 50};

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
