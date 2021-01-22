package me.tskeff.gameintel;

import java.util.ArrayList;
import java.util.List;

public class Node<P extends Position, M extends Move> {

    private final P position;
    private final M move;
    private final List<Node<P, M>> children;
    private final int layer;

    public Node(P position, M move, int layer) {
        this.position = position;
        this.move = move;
        this.layer = layer;
        this.children = new ArrayList<>();
    }

    public void addChild(Node<P, M> node) {
        children.add(node);
    }

    public Evaluation getEvaluation() {
        if (children.isEmpty()) {
            return new Evaluation(position.getValue(), layer);
        }

        int value = children.get(0).getEvaluation().getValue();
        int layer = children.get(0).getEvaluation().getLayer();

        for (Node<P, M> child : children) {
            Evaluation evaluation = child.getEvaluation();
            int childValue = evaluation.getValue();
            int childLayer = evaluation.getLayer();

            if (this.layer % 2 == 0) {
                if (childValue > value) {
                    value = childValue;
                    layer = childLayer;
                } else if (childValue == value) {
                    if (value > 0) {
                        if (childLayer < layer) {
                            layer = childLayer;
                        }
                    } else {
                        if (childLayer > layer) {
                            layer = childLayer;
                        }
                    }
                }

            } else {
                if (childValue < value) {
                    value = childValue;
                    layer = childLayer;
                } else if (childValue == value) {
                    if (value < 0) {
                        if (childLayer < layer) {
                            layer = childLayer;
                        }
                    } else {
                        if (childLayer > layer) {
                            layer = childLayer;
                        }
                    }
                }
            }
        }

        return new Evaluation(value, layer);
    }

    public M getMove() {
        return move;
    }

    public P getPosition() {
        return position;
    }

}
