package me.tskeff.gameintel;

import java.util.ArrayList;
import java.util.List;

public class Layer<P extends Position, M extends Move> {

    private final List<Node<P, M>> nodes;

    public Layer() {
        this.nodes = new ArrayList<>();
    }

    public void addNode(Node<P, M> node) {
        nodes.add(node);
    }

    public List<Node<P, M>> getNodes() {
        return nodes;
    }

}
