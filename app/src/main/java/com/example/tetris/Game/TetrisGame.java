package com.example.tetris.Game;

import static java.lang.Thread.sleep;

import com.example.tetris.Rendering.Renderer;

public class TetrisGame {
    private Renderer renderer;
    private Field field;
    private ScoreSystem scoreSystem;
    private boolean isRunning;

    public TetrisGame(Renderer renderer, ScoreSystem scoreSystem) {
        this.scoreSystem = scoreSystem;
        this.renderer = renderer;
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
        this.field = new Field(25, 10, this);
        isRunning = true;
        while (isRunning) {
            try {
                sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            tick();
        }
        scoreSystem.resetScore();
        //scoreSystem.highlightScore();
    }

    public void processInput(Input.Action action) {
        field.processAction(action);
        renderer.gridViewRender(field);
    }

    public ScoreSystem getScoreSystem() {
        return scoreSystem;
    }
}
