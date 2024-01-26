package com.example.tetris.TetrisShape;

import com.example.tetris.Color.Color;

public class TetrisOShape extends AbstractTetrisShape {
    public TetrisOShape() {
        super();
        tetrisShapePiece.setColor(Color.YELLOW);
        shapeArray = new TetrisShapePiece[][] {
                {tetrisShapePiece, tetrisShapePiece},
                {tetrisShapePiece, tetrisShapePiece},
        };
    }
}
