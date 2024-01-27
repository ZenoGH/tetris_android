package com.example.tetris.TetrisShape;
import android.graphics.Color;
public class TetrisTShape extends AbstractTetrisShape {
    public TetrisTShape() {
        super();
        tetrisShapePiece.setColor(Color.GREEN); //Purple
        shapeArray = new TetrisShapePiece[][] {
                {tetrisShapePiece, tetrisShapePiece, tetrisShapePiece},
                {null, tetrisShapePiece, null},
                {null, tetrisShapePiece, null}
        };
    }
}
