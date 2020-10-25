package tw.joao.utils;

public enum BlockedDrops {
    DIAMOND_SWORD,
    GOLD_SWORD,
    IRON_SWORD,
    STONE_SWORD,
    WOOD_SWORD,
    DIAMOND_PICKAXE,
    GOLD_PICKAXE,
    IRON_PICKAXE,
    STONE_PICKAXE,
    WOOD_PICKAXE,
    DIAMOND_AXE,
    GOLD_AXE,
    IRON_AXE,
    STONE_AXE,
    WOOD_AXE,
    FEATHER,
    SHEARS;

    public static boolean contains(String value) {
        for (BlockedDrops drops : BlockedDrops.values()) {
            if (drops.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
