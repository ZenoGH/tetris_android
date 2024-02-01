package com.example.tetris.Game;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

public class ScoreSystem {
    private int score;
    private TextView scoreDisplay;

    private Context context;

    public ScoreSystem(TextView scoreDisplay) {
        this.scoreDisplay = scoreDisplay;
        this.context = scoreDisplay.getContext().getApplicationContext();
        updateScoreDisplay();
    }

    protected void increaseScore(int score) {
        this.score += score;
        updateHighScore();
        updateScoreDisplay();
    }

    private void updateScoreDisplay() {
        ((Activity) scoreDisplay.getContext()).runOnUiThread(() -> {
            String string = "Score: " + score + "\n" +
                    "High Score: " + getHighScore();
            scoreDisplay.setText(string);
        });
    }

    protected void resetScore() {
        score = 0;
        updateScoreDisplay();
    }

    private void updateHighScore() {
        if (score <= getHighScore()) {
            return;
        }
        SharedPreferences sharedPref =
                context.getSharedPreferences("tetrisHighScore", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("highScoreKey", score);
        editor.apply();
    }

    private int getHighScore() {
        SharedPreferences sharedPref =
                context.getSharedPreferences("tetrisHighScore", Context.MODE_PRIVATE);
        return sharedPref.getInt("highScoreKey", 0);
    }
}
