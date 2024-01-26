package com.example.tetris.TetrisShape;
import com.example.tetris.Color.Color;
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
