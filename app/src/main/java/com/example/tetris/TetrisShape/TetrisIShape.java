package com.example.tetris.TetrisShape;
import android.graphics.Color;

public class TetrisIShape extends AbstractTetrisShape {
    public TetrisIShape() {
        super();
        tetrisShapePiece.setColor(Color.CYAN);
        shapeArray = new TetrisShapePiece[][] {
                {tetrisShapePiece},
                {tetrisShapePiece},
                {tetrisShapePiece},
                {tetrisShapePiece}
        };
    }
}
