package me.tskeff.gameintel;

public class Evaluation {

    private final int deepValue;
    private final int deepLayer;

    public Evaluation(int deepValue, int deepLayer) {
        this.deepValue = deepValue;
        this.deepLayer = deepLayer;
    }

    public int getValue() {
        return deepValue;
    }

    public int getLayer() {
        return deepLayer;
    }

}
