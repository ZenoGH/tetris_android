package com.example.tetris.TetrisShape;
import android.graphics.Color;
public class TetrisZShape extends AbstractTetrisShape {
    public TetrisZShape() {
        super();
        tetrisShapePiece.setColor(Color.RED);
        shapeArray = new TetrisShapePiece[][] {
                {tetrisShapePiece, tetrisShapePiece, null},
                {null, tetrisShapePiece, tetrisShapePiece}
        };
    }
}
