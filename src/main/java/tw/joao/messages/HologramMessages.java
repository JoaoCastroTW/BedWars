package tw.joao.messages;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import tw.joao.BedWars;
import tw.joao.generator.island.IslandGenerator;
import tw.joao.generator.natural.NaturalGenerator;
import tw.joao.holograms.Hologram;

import static tw.joao.utils.MessageUtils.*;

public class HologramMessages {
    public static void spawnNaturalGeneratorHologram(NaturalGenerator generator) {
        String[] textLines = {
            translateColorCodes(String.format("&eNível %s", generator.getLevel())),
            translateColorCodes(String.format("&eGerador de %s%s", generator.getColor(), generator.getName())),
            translateColorCodes(String.format("&eNovos minerios em &a%s &esegundos", generator.getCurrentCooldown() / 20))
        };

        Hologram hologram = new Hologram(generator.getLocation().clone().add(0.5, 2.5, 0.5), textLines);
        new BukkitRunnable(){
            @Override
            public void run() {
                hologram.modifyText(0, translateColorCodes(String.format("&eNível &a%s", generator.getLevel())));

                if (generator.isActive()) {
                    hologram.modifyText(2, translateColorCodes(String.format("&eNovos minerios em &a%s &esegundos", generator.getCurrentCooldown() / 20)));
                } else {
                    hologram.modifyText(2, translateColorCodes("&cExistem muitos minérios por perto"));
                }
            }
        }.runTaskTimer(JavaPlugin.getPlugin(BedWars.class), 0, 20);
    }

    public static void spawnIslandGeneratorHologram(IslandGenerator generator) {
        String[] textLines = {
                translateColorCodes(String.format("&eNível &a%s", generator.getLevel())),
                translateColorCodes(String.format("&eGerador do time %s%s", generator.getTeam().getColorCode(), generator.getTeam().getName()))
        };

        Hologram hologram = new Hologram(generator.getLocation().clone().add(0.5, 2.5, 0.5), textLines);

        new BukkitRunnable(){
            @Override
            public void run() {
                hologram.modifyText(0, translateColorCodes(String.format("&eNível &a%s", generator.getLevel())));
            }
        }.runTaskTimer(JavaPlugin.getPlugin(BedWars.class), 0, 20);
    }
}
