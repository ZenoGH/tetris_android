package com.example.tetris.Game;

import static java.lang.Thread.sleep;

import android.widget.TableLayout;

import com.example.tetris.R;

public class TetrisGame {
    private final Renderer renderer = new Renderer();
    private final Field field = new Field(40, 10,this);
    private boolean isRunning = true;
    private int score = 0;

    public TableLayout tableLayout;

    private void tick() {
        field.tryToPlaceNewShape();
        field.processPhysics();
        field.checkLines();
        //renderer.consoleRender(field);
        renderer.tableLayoutRender(field, tableLayout);
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
}
