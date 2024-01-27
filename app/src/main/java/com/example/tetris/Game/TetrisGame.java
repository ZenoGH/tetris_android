package com.example.tetris.Game;

import static java.lang.Thread.sleep;

import android.view.View;
import android.widget.GridView;

import com.example.tetris.Rendering.Renderer;

public class TetrisGame {
    private final Renderer renderer = new Renderer();
    private final Field field = new Field(25, 10, this);
    private boolean isRunning = true;
    private int score = 0;

    public View renderView;

    private void tick() {
        field.tryToPlaceNewShape();
        field.processPhysics();
        field.checkLines();
        //renderer.consoleRender(field);
        renderer.gridViewRender(field, (GridView) renderView);
    }

    protected void gameOver() {
        isRunning = false;
    }

    public void run() {
        while (isRunning) {
            try {
                sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            tick();
        }
    }

    protected void increaseScore(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }

    public Field getField() {
        return field;
    }

    public void processInput(Input.Action action) {
        field.processInput(action);
        renderer.gridViewRender(field, (GridView) renderView);
    }
}
