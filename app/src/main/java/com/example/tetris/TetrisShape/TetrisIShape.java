package com.example.tetris.TetrisShape;

import com.example.tetris.Color.Color;

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
