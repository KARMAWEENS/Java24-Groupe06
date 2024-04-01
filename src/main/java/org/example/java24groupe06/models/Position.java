package org.example.java24groupe06.models;

public class Position {
    private int x;
    private int y;

    public int getRow() {
        return x;
    }

    public int getColumn() {
        return y;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
