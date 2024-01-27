package com.example.tetris.TetrisShape;

import android.graphics.Color;

public class TetrisShapePiece {

    private int color;
    private boolean frozen = false;
    public TetrisShapePiece(int color) {
        this.color = color;
    }

    public void freeze() {
        this.frozen = true;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
