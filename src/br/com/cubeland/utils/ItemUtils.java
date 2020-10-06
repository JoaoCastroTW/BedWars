package br.com.cubeland.utils;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class ItemUtils {

    public static ItemStack getColoredArmor(ItemStack item, int r, int g, int b) {
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.fromRGB(r, g, b));
        item.setItemMeta(meta);

        return item;
    }
}
