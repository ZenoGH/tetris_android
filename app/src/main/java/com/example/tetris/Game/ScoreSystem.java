package com.example.tetris.Game;

import static java.lang.Thread.sleep;

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

    protected void resetScore() {
        score = 0;
        updateScoreDisplay();
    }
//    protected void highlightScore() {
//        ((Activity) scoreDisplay.getContext()).runOnUiThread(() -> {
//            scoreDisplay.setTextSize(textSize * 2);
//            try {
//                sleep(2000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            scoreDisplay.setTextSize(textSize);
//        });
//    }


}
