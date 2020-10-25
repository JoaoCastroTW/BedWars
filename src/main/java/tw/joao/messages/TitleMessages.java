package tw.joao.messages;

import tw.joao.teams.Team;
import org.bukkit.entity.Player;

import static tw.joao.utils.MessageUtils.*;
import static net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction.*;


public class TitleMessages {

    public static void sendMatchEndingTitle(Team team) {
        String title = translateColorCodes(String.format("&a&LPARTIDA FINALIZADA!"));
        String subTitle = translateColorCodes(String.format("&eO time %s%s &evenceu a partida!", team.getColorCode(), team.getName()));

        broadcastTitle(title, TITLE, 10, 120, 10);
        broadcastTitle(subTitle, SUBTITLE, 10, 120, 10);
    }

    public static void sendRespawnTimerTitle(Player player, int time) {
        String title = translateColorCodes(String.format("&cVOCE MORREU!"));
        String subTitle = translateColorCodes(String.format("&eRenascendo em &a%s &esegundos.", time));

        sendTitle(player, title, TITLE, 0, 120, 0);
        sendTitle(player, subTitle, SUBTITLE, 0, 120, 0);
    }
}
