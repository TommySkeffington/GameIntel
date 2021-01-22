package me.tskeff.gameintel;

public enum Player {

    COMPUTER,
    OPPONENT;

    public static Player getOpposite(Player player) {
        if (player.equals(COMPUTER)) return OPPONENT;
        return COMPUTER;
    }

}
