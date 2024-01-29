package com.example.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.tetris.Game.Input;
import com.example.tetris.Game.ScoreSystem;
import com.example.tetris.Game.TetrisGame;
import com.example.tetris.Rendering.Renderer;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView = findViewById(R.id.gridView);
        Renderer renderer = new Renderer(gridView);
        gridView.setVerticalScrollBarEnabled(false);
        gridView.setOnTouchListener((View view, MotionEvent motionEvent) -> {
                return true;
        });
        ScoreSystem scoreSystem = new ScoreSystem(findViewById(R.id.scoreTextView));
        TetrisGame game = new TetrisGame(renderer, scoreSystem);
        Thread thread = new Thread(() -> {
            while (true) {
                game.run();
            }
        });
        thread.start();

        Button leftButton = findViewById(R.id.leftButton);
        Button rightButton = findViewById(R.id.rightButton);
        Button rotateButton = findViewById(R.id.rotateButton);
        Button downButton = findViewById(R.id.downButton);

        leftButton.setOnClickListener((view)-> game.processInput(Input.Action.MOVE_LEFT));
        rightButton.setOnClickListener((view)-> game.processInput(Input.Action.MOVE_RIGHT));
        rotateButton.setOnClickListener((view)-> game.processInput(Input.Action.ROTATE));
        downButton.setOnClickListener((view)-> game.processInput(Input.Action.MOVE_DOWN));

        //ISSUES:
        //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        //game over doesn't restart game
    }
}