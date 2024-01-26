package com.example.tetris.TetrisShape;

import com.example.tetris.Color.Color;

public class TetrisLShape extends AbstractTetrisShape {
    public TetrisLShape() {
        super();
        tetrisShapePiece.setColor(Color.ORANGE);
        shapeArray = new TetrisShapePiece[][] {
                {tetrisShapePiece, null},
                {tetrisShapePiece, null},
                {tetrisShapePiece, tetrisShapePiece}
        };
    }
}
