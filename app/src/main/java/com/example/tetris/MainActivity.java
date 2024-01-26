package com.example.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tetris.Game.TetrisGame;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TetrisGame game = new TetrisGame();
        game.tableLayout = findViewById(R.id.tetrisField);
        game.run();
    }
}