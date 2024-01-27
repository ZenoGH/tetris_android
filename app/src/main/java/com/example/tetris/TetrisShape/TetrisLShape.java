package com.example.tetris.TetrisShape;
import android.graphics.Color;

public class TetrisLShape extends AbstractTetrisShape {
    public TetrisLShape() {
        super();
        tetrisShapePiece.setColor(Color.GREEN); //Orange
        shapeArray = new TetrisShapePiece[][] {
                {tetrisShapePiece, null},
                {tetrisShapePiece, null},
                {tetrisShapePiece, tetrisShapePiece}
        };
    }
}
