package com.example.tetris.TetrisShape;


public abstract class AbstractTetrisShape {
    protected TetrisShapePiece[][] shapeArray;
    protected TetrisShapePiece tetrisShapePiece;

    AbstractTetrisShape() {
        tetrisShapePiece = new TetrisShapePiece(0);
    }

    public void freeze() {
        tetrisShapePiece.freeze();
    }

    public boolean isFrozen() {
        return tetrisShapePiece.isFrozen();
    }

    public void rotateShapeArrayClockwise() {
        TetrisShapePiece[][] rotatedShapeArray = new TetrisShapePiece[shapeArray[0].length][shapeArray.length];
        for (int row = 0; row < shapeArray.length; row++) {
            for (int column = row; column < shapeArray[0].length; column++) {
                rotatedShapeArray[row][column] = shapeArray[column][row];
            }
        }
        shapeArray = rotatedShapeArray;
    }

    public TetrisShapePiece[][] getShapeArray() {
        return shapeArray;
    }

    public void setShapeArray(TetrisShapePiece[][] shapeArray) {
        this.shapeArray = shapeArray;
    }

    public TetrisShapePiece getTetrisShapePiece() {
        return tetrisShapePiece;
    }
}
