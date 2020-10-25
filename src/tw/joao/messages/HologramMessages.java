package tw.joao.messages;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import tw.joao.BedWars;
import tw.joao.generators.NaturalGenerator;
import tw.joao.holograms.Hologram;

import static tw.joao.utils.MessageUtils.*;

public class HologramMessages {
    public static void spawnNaturalGeneratorHologram(NaturalGenerator generator) {
        String[] textLines = {
            translateColorCodes(String.format("&eNível %s", generator.getLevel())),
            translateColorCodes(String.format("&eGerador de %s%s", generator.getColor(), generator.getName())),
            translateColorCodes(String.format("&eNovos minerios em &a%s &esegundos", generator.getDelay() / 20))
        };

        Hologram hologram = new Hologram(generator.getLocation().clone().add(0.5, 2.5, 0.5), textLines);
        new BukkitRunnable(){
            @Override
            public void run() {
                hologram.modifyText(0, translateColorCodes(String.format("&eNível &a%s", generator.getLevel())));

                if (generator.getCurrentCooldown() < 0) {
                    hologram.modifyText(2, translateColorCodes(String.format("&cExistem muitos minerios por perto")));
                } else {
                    hologram.modifyText(2, translateColorCodes(String.format("&eNovos minerios em &a%s &esegundos", generator.getCurrentCooldown())));
                }
            }
        }.runTaskTimer(JavaPlugin.getPlugin(BedWars.class), 0, 20);
    }
}
