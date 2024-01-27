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
        int rowLength = shapeArray.length;
        int columnLength = shapeArray[0].length;
        TetrisShapePiece[][] rotatedArray = new TetrisShapePiece[columnLength][rowLength];
        for (int row = 0; row < rowLength; row++) {
            for (int column = 0; column < columnLength; column++) {
                rotatedArray[column][rowLength - 1 - row] = shapeArray[row][column];
            }
        }
        shapeArray = rotatedArray;
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
