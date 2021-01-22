package me.tskeff.gameintel.tictactoe;

import me.tskeff.gameintel.Player;
import me.tskeff.gameintel.Move;
import me.tskeff.gameintel.Position;

import java.util.HashMap;
import java.util.Map;

public class GameMove implements Move {

    private final int square;
    private final Player type;

    public GameMove(int square, Player type) {
        this.square = square;
        this.type = type;
    }

    public int getSquare() {
        return square;
    }

    @Override
    public Position getNewPosition(Position oldPosition) {
        Map<Integer, Player> squares = new HashMap<>(((GamePosition) oldPosition).getSquares());
        squares.put(square, type);

        return new GamePosition(squares);
    }

    @Override
    public Player getType() {
        return type;
    }

}
