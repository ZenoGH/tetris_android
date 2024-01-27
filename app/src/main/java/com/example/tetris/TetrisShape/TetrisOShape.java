package com.example.tetris.TetrisShape;
import android.graphics.Color;

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
