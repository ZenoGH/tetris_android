package com.example.tetris.TetrisShape;
import android.graphics.Color;

public class TetrisSShape extends AbstractTetrisShape {
    public TetrisSShape() {
        super();
        tetrisShapePiece.setColor(Color.GREEN);
        shapeArray = new TetrisShapePiece[][] {
                {null, tetrisShapePiece, tetrisShapePiece},
                {tetrisShapePiece, tetrisShapePiece, null}
        };
    }
}
