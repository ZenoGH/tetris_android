package com.example.tetris.TetrisShape;
import com.example.tetris.Color.Color;
public class TetrisTShape extends AbstractTetrisShape {
    public TetrisTShape() {
        super();
        tetrisShapePiece.setColor(Color.PURPLE);
        shapeArray = new TetrisShapePiece[][] {
                {tetrisShapePiece, tetrisShapePiece, tetrisShapePiece},
                {null, tetrisShapePiece, null},
                {null, tetrisShapePiece, null}
        };
    }
}
