package com.example.tetris.TetrisShape;
import com.example.tetris.Color.Color;

public class TetrisShapePiece {

    private Color color;
    private boolean frozen = false;
    public TetrisShapePiece(Color color) {
        this.color = color;
    }

    public void freeze() {
        this.frozen = true;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
