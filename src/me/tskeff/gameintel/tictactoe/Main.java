package me.tskeff.gameintel.tictactoe;

import me.tskeff.gameintel.Player;
import me.tskeff.gameintel.Tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Player> squares = new HashMap<>();

        squares.put(0, null);
        squares.put(1, null);
        squares.put(2, Player.COMPUTER);
        squares.put(3, null);
        squares.put(4, null);
        squares.put(5, null);
        squares.put(6, null);
        squares.put(7, null);
        squares.put(8, null);

        System.out.println(getPrint(squares.get(0)) + " " + getPrint(squares.get(1)) + " " + getPrint(squares.get(2)));
        System.out.println(getPrint(squares.get(3)) + " " + getPrint(squares.get(4)) + " " + getPrint(squares.get(5)));
        System.out.println(getPrint(squares.get(6)) + " " + getPrint(squares.get(7)) + " " + getPrint(squares.get(8)));
        System.out.println("");

        while (true) {
            System.out.print("Enter Move X: ");
            int xInput = scanner.nextInt();

            System.out.print("Enter Move Y: ");
            int yInput = scanner.nextInt();

            if (xInput == 1) {
                if (yInput == 1) {
                    squares.put(0, Player.OPPONENT);
                } else if (yInput == 2) {
                    squares.put(1, Player.OPPONENT);
                } else if (yInput == 3) {
                    squares.put(2, Player.OPPONENT);
                }
            } else if (xInput == 2) {
                if (yInput == 1) {
                    squares.put(3, Player.OPPONENT);
                } else if (yInput == 2) {
                    squares.put(4, Player.OPPONENT);
                } else if (yInput == 3) {
                    squares.put(5, Player.OPPONENT);
                }
            } else if (xInput == 3) {
                if (yInput == 1) {
                    squares.put(6, Player.OPPONENT);
                } else if (yInput == 2) {
                    squares.put(7, Player.OPPONENT);
                } else if (yInput == 3) {
                    squares.put(8, Player.OPPONENT);
                }
            }

            System.out.println("");
            Tree<GamePosition, GameMove> tree = new Tree<>(new GamePosition(squares), Player.COMPUTER, 9);

            GameMove move = tree.calculateMove();

            if (move != null) {
                squares.put(move.getSquare(), move.getType());
            }

            System.out.println(getPrint(squares.get(0)) + " " + getPrint(squares.get(1)) + " " + getPrint(squares.get(2)));
            System.out.println(getPrint(squares.get(3)) + " " + getPrint(squares.get(4)) + " " + getPrint(squares.get(5)));
            System.out.println(getPrint(squares.get(6)) + " " + getPrint(squares.get(7)) + " " + getPrint(squares.get(8)));
            System.out.println("");

            if (new GamePosition(squares).getValue() == -1) {
                System.out.println("You Win!");
                return;
            } else if (new GamePosition(squares).getValue() == 0 && squares.values().stream().noneMatch(Objects::isNull)) {
                System.out.println("Draw!");
                return;
            } else if (new GamePosition(squares).getValue() == 1) {
                System.out.println("You Lose!");
                return;
            }

        }

    }

    private static String getPrint(Player type) {
        if (type == null) return "_";
        if (type.equals(Player.COMPUTER)) return "O";
        else return "X";
    }

}
