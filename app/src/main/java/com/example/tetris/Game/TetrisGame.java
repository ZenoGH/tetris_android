package com.example.tetris.Game;

import static java.lang.Thread.sleep;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.tetris.Gestures.SimpleGestureListener;
import com.example.tetris.Rendering.Renderer;
import com.example.tetris.TetrisShape.TetrisShapePiece;

public class TetrisGame {
    private Renderer FieldRenderer;
    private Renderer nextShapeRenderer;
    private Field field;
    private ScoreSystem scoreSystem;
    private boolean isRunning;

    private final int TickDelayMs = 500;
    private final int fieldHeight = 25;
    private final int fieldWidth = 10;

    public TetrisGame(Renderer fieldRenderer, Renderer nextShapeRenderer, ScoreSystem scoreSystem) {
        this.scoreSystem = scoreSystem;
        this.FieldRenderer = fieldRenderer;
        this.nextShapeRenderer = nextShapeRenderer;
    }

    private void tick() {
        field.tryToPlaceNewShape();
        field.processPhysics();
        field.checkLines();
        //renderer.consoleRender(field);
        renderGameField();
        renderNextShape();
    }

    protected void gameOver() {
        isRunning = false;
    }

    public void run() {
        this.field = new Field(fieldHeight, fieldWidth, this);
        isRunning = true;
        while (isRunning) {
            try {
                sleep(TickDelayMs);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            tick();
        }
        scoreSystem.resetScore();
        //scoreSystem.highlightScore();
    }

    public void processInput(SimpleGestureListener.Direction action) {
        field.processAction(action);
        renderGameField();
    }

    public ScoreSystem getScoreSystem() {
        return scoreSystem;
    }

    private void renderGameField() {
        TetrisShapePiece[][] newArray =
                cutOffRows(field.getRenderedFieldArray(), fieldHeight / 5);
        FieldRenderer.renderTetrisPieceArray(newArray,
                (float) getScreenWidth() / newArray[0].length * 0.7f);
    }

    private void renderNextShape() {
        TetrisShapePiece[][] newArray = field.getNextShape().getShapeArray();
        nextShapeRenderer.renderTetrisPieceArray(newArray,
                dpToPx(nextShapeRenderer.getView().getContext(), 30));
    }

    public static TetrisShapePiece[][] cutOffRows(TetrisShapePiece[][] array, int n) {
        int newRowSize = array.length - n;
        TetrisShapePiece[][] newArray = new TetrisShapePiece[newRowSize][array[0].length];
        if (array.length - n >= 0) System.arraycopy(array, n, newArray, 0, array.length - n);
        return newArray;
    }

    private int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    private int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
