package me.tskeff.gameintel;

import java.util.List;

public interface Position {

    List<Move> getMoves(Player type);

    int getValue();

}
