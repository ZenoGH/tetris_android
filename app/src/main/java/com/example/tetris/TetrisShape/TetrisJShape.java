package com.example.tetris.TetrisShape;
import android.graphics.Color;

public class TetrisJShape extends AbstractTetrisShape {
    public TetrisJShape() {
        super();
        tetrisShapePiece.setColor(Color.BLUE);
        shapeArray = new TetrisShapePiece[][] {
                {null, tetrisShapePiece},
                {null, tetrisShapePiece},
                {tetrisShapePiece, tetrisShapePiece}
        };
    }
}
