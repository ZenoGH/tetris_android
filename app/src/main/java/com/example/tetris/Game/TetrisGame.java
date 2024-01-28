package com.example.tetris.Game;

import static java.lang.Thread.sleep;

import com.example.tetris.Rendering.Renderer;

public class TetrisGame {
    private Renderer renderer;
    private ScoreSystem scoreSystem;
    private Field field;
    private boolean isRunning = true;

    public TetrisGame(Renderer renderer, ScoreSystem score) {
        this.renderer = renderer;
        this.scoreSystem = score;
        this.field = new Field(25, 10);
    }
    private void tick() {
        field.tryToPlaceNewShape();
        field.processPhysics();
        field.checkLines();
        //renderer.consoleRender(field);
        renderer.gridViewRender(field);
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

    public void processInput(Input.Action action) {
        field.processAction(action);
        renderer.gridViewRender(field);
    }
}
