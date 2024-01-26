package com.example.tetris.Game;

import com.example.tetris.TetrisShape.AbstractTetrisShape;
import com.example.tetris.TetrisShape.TetrisShapeFactory;
import com.example.tetris.TetrisShape.TetrisShapePiece;

public class Field {
    int size = 16;
    int centerColumn;
    public TetrisShapePiece[][] simulationField;
    public TetrisShapePiece[][] worldField;
    private final TetrisShapeFactory shapeFactory = new TetrisShapeFactory();
    private int[] targetFieldCenter = new int[2];
    private AbstractTetrisShape currentShape;

    private TetrisGame game;

    public Field(int rows, int columns, TetrisGame game) {
        this.size = rows;
        this.simulationField = new TetrisShapePiece[rows][columns];
        this.worldField = new TetrisShapePiece[rows][columns];
        this.centerColumn = columns / 2 - 1;
        this.game = game;
    }

    public void tryToPlaceNewShape() {
        if (currentShape == null) {
            currentShape = shapeFactory.createRandomShape();
            targetFieldCenter[0] = 1;
            targetFieldCenter[1] = centerColumn;
            placeShape(currentShape, targetFieldCenter[0], targetFieldCenter[1]);
        }
    }

    private boolean placeShape(AbstractTetrisShape shape, int row, int column) {
        if (!isAreaFree(shape, row, column)) {
            return false;
        }
        TetrisShapePiece[][] shapeArray = shape.getShapeArray();
        int rowLength = shapeArray.length - 1;
        int columnLength = shapeArray[0].length - 1;

        for (int shapeRow = 0; shapeRow <= rowLength; shapeRow++) {
            for (int shapeColumn = 0; shapeColumn <= columnLength; shapeColumn++) {
                if (shapeArray[shapeRow][shapeColumn] == null) {
                    continue;
                }
                simulationField[shapeRow + row][shapeColumn + column] = shapeArray[shapeRow][shapeColumn];
            }
        }
        return true;
    }

    private boolean isAreaFree(AbstractTetrisShape shape, int row, int column) {
        TetrisShapePiece[][] shapeArray = shape.getShapeArray();
        int rowLength = shapeArray.length - 1;
        int columnLength = shapeArray[0].length - 1;

        for (int shapeRow = 0; shapeRow <= rowLength; shapeRow++) {
            for (int shapeColumn = 0; shapeColumn <= columnLength; shapeColumn++) {
                if (shapeArray[shapeRow][shapeColumn] == null) {
                    continue;
                }
                if (shapeRow + row >= size || shapeColumn + column >= size ||
                        (simulationField[shapeRow + row][shapeColumn + column] != null &&
                                !simulationField[shapeRow + row][shapeColumn + column]
                                        .equals(shape.getTetrisShapePiece()))) {
                    return false;
                }
            }
        }
        return true;
    }


    public void checkLines() {
        int power = 1;
        for (int row = size - 1; row >= 0; row--) {
            if (isLineComplete(row)) {
                power *= 2;
                clearLine(row);
            }
        }
        game.increaseScore(100 * power);
    }

    public boolean isLineComplete(int line) {
        for (int column = 0; column < size; column++) {
            if (simulationField[line][column] == null) {
                return false;
            }
        }
        return true;
    }

    public void clearLine(int line) {
        for (int column = 0; column < size; column++) {
            simulationField[line][column] = null;
        }
        for (int row = line; row <= 1; row--) {
            simulationField[row] = simulationField[row - 1];
        }
    }

    public void processPhysics() {
        if (currentShape == null) {
            return;
        }
        doGravity();
    }

    public void processInput(boolean rotate, int directionColumn) {
        moveCurrentShapeSideways(directionColumn);
        if (rotate) {
            rotateCurrentShape();
        }
        processPhysics();
    }

    private void doGravity() {
        simulationField = cloneField(worldField);
        if (placeShape(currentShape, targetFieldCenter[0], targetFieldCenter[1])) {
            targetFieldCenter[0]++;
            if (!isAreaFree(currentShape, targetFieldCenter[0], targetFieldCenter[1])) {
                currentShape.freeze();
                currentShape = null;
                worldField = cloneField(simulationField);
            }
        } else {
            game.gameOver();
        }
    }

    private void moveCurrentShapeSideways(int direction) {
        targetFieldCenter[1] += direction;
        simulationField = cloneField(worldField);
        if (placeShape(currentShape, targetFieldCenter[0], targetFieldCenter[1])) {
            targetFieldCenter[0]++;
            if (!isAreaFree(currentShape, targetFieldCenter[0], targetFieldCenter[1])) {
                currentShape.freeze();
                currentShape = null;
                worldField = cloneField(simulationField);
            }
        }

    }

    private void rotateCurrentShape() {
        TetrisShapePiece[][] oldShapeArray = currentShape.getShapeArray();
        currentShape.rotateShapeArrayClockwise();
        if (!isAreaFree(currentShape, targetFieldCenter[0], targetFieldCenter[1])) {
            currentShape.setShapeArray(oldShapeArray);
        }
    }

    private TetrisShapePiece[][] cloneField(TetrisShapePiece[][] field) {
        TetrisShapePiece[][] newField = new TetrisShapePiece[field.length][];
        for (int i = 0; i < field.length; i++) {
            newField[i] = field[i].clone();
        }
        return newField;
    }
}