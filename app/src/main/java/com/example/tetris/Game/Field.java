package com.example.tetris.Game;

import com.example.tetris.TetrisShape.AbstractTetrisShape;
import com.example.tetris.TetrisShape.TetrisShapeFactory;
import com.example.tetris.TetrisShape.TetrisShapePiece;

public class Field {
    int rows;
    int columns;
    int centerColumn;
    private ScoreSystem scoreSystem;
    private TetrisGame game;
    public TetrisShapePiece[][] renderedFieldArray;
    public TetrisShapePiece[][] simulationFieldArray;
    private final TetrisShapeFactory shapeFactory = new TetrisShapeFactory();
    private final int[] targetCoords = new int[2];
    private AbstractTetrisShape currentShape;


    public Field(int rows, int columns, TetrisGame game) {
        this.rows = rows;
        this.columns = columns;
        this.game = game;
        this.scoreSystem = game.getScoreSystem();
        this.renderedFieldArray = new TetrisShapePiece[rows][columns];
        this.simulationFieldArray = new TetrisShapePiece[rows][columns];
        this.centerColumn = columns / 2 - 1;
    }

    public void tryToPlaceNewShape() {
        if (currentShape == null) {
            currentShape = shapeFactory.createRandomShape();
            //currentShape = shapeFactory.createShape(TetrisShapeFactory.Type.iShape);
            targetCoords[0] = 1;
            targetCoords[1] = centerColumn;
            if (!tryToPlaceShape(currentShape, renderedFieldArray, targetCoords[0], targetCoords[1])) {
                game.gameOver();
            }
        }
    }

    private boolean tryToPlaceShape(AbstractTetrisShape shape, TetrisShapePiece[][] fieldArray,
                                 int row, int column) {
        if (!isAreaFree(shape, row, column)) {
            return false;
        }
        else {
            placeShape(shape, fieldArray, row, column);
            return true;
        }
    }
    private void placeShape(AbstractTetrisShape shape, TetrisShapePiece[][] fieldArray,
                               int row, int column) {
        TetrisShapePiece[][] shapeArray = shape.getShapeArray();
        int rowLength = shapeArray.length - 1;
        int columnLength = shapeArray[0].length - 1;

        for (int shapeRow = 0; shapeRow <= rowLength; shapeRow++) {
            for (int shapeColumn = 0; shapeColumn <= columnLength; shapeColumn++) {
                if (shapeArray[shapeRow][shapeColumn] == null) {
                    continue;
                }
                fieldArray[shapeRow + row][shapeColumn + column] = shapeArray[shapeRow][shapeColumn];
            }
        }
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
                if (column < 0 || shapeRow + row >= rows || shapeColumn + column >= columns ||
                        (simulationFieldArray[shapeRow + row][shapeColumn + column] != null)) {
                    return false;
                }
            }
        }
        return true;
    }


    public void checkLines() {
        int scoreMultiplier = 1;
        for (int row = rows - 1; row >= 0; row--) {
            if (isLineComplete(row)) {
                scoreMultiplier *= 2;
                scoreSystem.increaseScore(100 * scoreMultiplier);
                clearLine(row);
                collapseLines(row);
            }
        }
    }

    private boolean isLineComplete(int line) {
        for (int column = 0; column < columns; column++) {
            if (simulationFieldArray[line][column] == null) {
                return false;
            }
        }
        return true;
    }

    private void clearLine(int line) {
        for (int column = 0; column < columns; column++) {
            simulationFieldArray[line][column] = null;
        }
    }

    public void collapseLines(int line) {
        for (int row = line; row > 0; row--) {
            for (int column = 0; column < columns; column++) {
                simulationFieldArray[row][column] = simulationFieldArray[row - 1][column];
            }
        }
    }

    public void processPhysics() {
        if (currentShape == null) {
            return;
        }
        doGravity();
    }

    protected void processAction(Input.Action action) {
        if (currentShape == null) {
            return;
        }
        switch (action) {
            case MOVE_LEFT:
                moveCurrentShapeSideways(-1);
                break;
            case MOVE_RIGHT:
                moveCurrentShapeSideways(1);
                break;
            case ROTATE:
                rotateCurrentShape();
                break;
            case MOVE_DOWN:
                dropCurrentShape();
                break;
        }
    }

    private void doGravity() {
        if (isAreaFree(currentShape, targetCoords[0] + 1, targetCoords[1])) {
            targetCoords[0]++; //lower target field
            clearRenderedShape();
            placeShape(currentShape, renderedFieldArray, targetCoords[0], targetCoords[1]);
        }
        else {
            placeShape(currentShape, simulationFieldArray, targetCoords[0], targetCoords[1]);
            currentShape = null; // reset current shape
        }

    }

    private void clearRenderedShape() {
        renderedFieldArray = cloneField(simulationFieldArray);
    }
    private void moveCurrentShapeSideways(int direction) {
        if (isAreaFree(currentShape, targetCoords[0], targetCoords[1] + direction)) {
            targetCoords[1] += direction;
            clearRenderedShape();
            placeShape(currentShape, renderedFieldArray, targetCoords[0], targetCoords[1]);
        }
    }

    private void rotateCurrentShape() {
        TetrisShapePiece[][] oldShapeArray = currentShape.getShapeArray();
        currentShape.rotateShapeArrayClockwise();
        if (isAreaFree(currentShape, targetCoords[0], targetCoords[1])) {
            clearRenderedShape();
            placeShape(currentShape, renderedFieldArray, targetCoords[0], targetCoords[1]);
        }
        else {
            currentShape.setShapeArray(oldShapeArray);
        }
    }

    private void dropCurrentShape() {
        while (currentShape != null) {
            doGravity();
        }
    }

    private TetrisShapePiece[][] cloneField(TetrisShapePiece[][] field) {
        TetrisShapePiece[][] newField = new TetrisShapePiece[field.length][];
        for (int i = 0; i < field.length; i++) {
            newField[i] = field[i].clone();
        }
        return newField;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}