package me.tskeff.gameintel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Tree<P extends Position, M extends Move> {

    private final List<Layer<P, M>> layers;
    private final P position;
    private final Player turn;

    public Tree(P position, Player turn, int depth) {
        this.layers = new ArrayList<>();
        this.position = position;
        this.turn = turn;

        createFirst();
        for (int i = 0; i < depth; i++) {
            createLayer();
        }
    }

    public M calculateMove() {
        if (layers.get(1).getNodes().isEmpty()) return null;

        Evaluation value = layers.get(0).getNodes().get(0).getEvaluation();
        int score = value.getValue();
        int layer = value.getLayer();

        List<Node<P, M>> nodes = layers.get(1).getNodes().stream().filter(pmNode -> pmNode.getEvaluation().getValue() == score && pmNode.getEvaluation().getLayer() == layer).collect(Collectors.toList());

        Random random = new Random();
        return nodes.get(random.nextInt(nodes.size())).getMove();
    }

    private void createFirst() {
        Layer<P, M> layer = new Layer<>();
        layer.addNode(new Node<>(position, null, 0));
        layers.add(layer);
    }

    private void createLayer() {
        Layer<P, M> layer = new Layer<>();
        layers.add(layer);

        if (layers.indexOf(layer) % 2 == 1) {
            for (Node<P, M> node : layers.get(layers.indexOf(layer) - 1).getNodes()) {
                if (node.getPosition().getValue() == 1 || node.getPosition().getValue() == -1) continue;
                for (Move move : node.getPosition().getMoves(turn)) {
                    Node<P, M> newNode = new Node<>((P) move.getNewPosition(node.getPosition()), (M) move, layers.indexOf(layer));
                    node.addChild(newNode);
                    layer.addNode(newNode);
                }
            }
        } else {
            for (Node<P, M> node : layers.get(layers.indexOf(layer) - 1).getNodes()) {
                if (node.getPosition().getValue() == 1 || node.getPosition().getValue() == -1) continue;
                for (Move move : node.getPosition().getMoves(Player.getOpposite(turn))) {
                    Node<P, M> newNode = new Node<>((P) move.getNewPosition(node.getPosition()), (M) move, layers.indexOf(layer));
                    node.addChild(newNode);
                    layer.addNode(newNode);
                }

            }
        }

    }

}
