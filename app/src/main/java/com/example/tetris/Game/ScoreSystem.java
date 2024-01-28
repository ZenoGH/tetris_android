package com.example.tetris.Game;

import android.app.Activity;
import android.widget.TextView;

public class ScoreSystem {
    private int score;
    private TextView scoreDisplay;
    public ScoreSystem(TextView scoreDisplay) {
        this.scoreDisplay = scoreDisplay;
    }
    protected void increaseScore(int score) {
        this.score += score;
        updateScoreDisplay();
    }

    private void updateScoreDisplay() {
        ((Activity) scoreDisplay.getContext()).runOnUiThread(() -> {
            scoreDisplay.setText(String.valueOf(score));
        });
    }

    public int getScore() {
        return score;
    }
}
