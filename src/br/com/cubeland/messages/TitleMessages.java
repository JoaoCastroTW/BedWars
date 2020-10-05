package br.com.cubeland.messages;

import br.com.cubeland.teams.Team;
import static net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction.*;

import static br.com.cubeland.utils.MessageUtils.broadcastTitle;
import static br.com.cubeland.utils.MessageUtils.translateColorCodes;


public class TitleMessages {

    public static void sendMatchEndingTitle(Team team) {
        String title = translateColorCodes(String.format("&a&LPARTIDA FINALIZADA!"));
        String subTitle = translateColorCodes(String.format("&eO time %s%s &evenceu a partida!", team.getColor(), team.getName()));

        broadcastTitle(title, TITLE, 10, 120, 10);
        broadcastTitle(subTitle, SUBTITLE, 10, 120, 10);
    }
}
