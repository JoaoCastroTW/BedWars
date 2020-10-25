package tw.joao;

import lombok.Getter;

public enum GameStatus {
    AWAITING_PLAYERS ("&eAguardando jogadores"),
    STARTING ("&eIniciando partida"),
    IN_PROGRESS ("&aPartida em andamento");

    @Getter private final String text;
    @Getter private final GameStatus status = this;

    GameStatus(String text) {
        this.text = text;
    }
}
