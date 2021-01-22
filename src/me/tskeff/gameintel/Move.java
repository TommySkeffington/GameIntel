package me.tskeff.gameintel;

public interface Move {

    Position getNewPosition(Position oldPosition);

    Player getType();

}
