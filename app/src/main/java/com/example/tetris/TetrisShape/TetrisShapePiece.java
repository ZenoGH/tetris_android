package com.example.tetris.TetrisShape;

import android.graphics.Color;

public class TetrisShapePiece {

    private int color;

    public TetrisShapePiece(int color) {
        this.color = color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
