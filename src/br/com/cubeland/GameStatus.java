package br.com.cubeland;

public enum GameStatus {
    AWAITING_PLAYERS ("&eAguardando jogadores"),
    STARTING ("&eIniciando partida"),
    IN_PROGRESS ("&aPartida em andamento");

    String text;

    GameStatus(String text) {
        this.text = text;
    }

    public GameStatus getStatus() {
        return this;
    }

}
