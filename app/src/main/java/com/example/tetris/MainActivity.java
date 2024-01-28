package com.example.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.tetris.Game.Input;
import com.example.tetris.Game.ScoreSystem;
import com.example.tetris.Game.TetrisGame;
import com.example.tetris.Rendering.Renderer;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Renderer renderer = new Renderer(findViewById(R.id.gridView));
        ScoreSystem scoreSystem = new ScoreSystem(findViewById(R.id.scoreTextView));
        TetrisGame game = new TetrisGame(renderer, scoreSystem);
        Thread thread = new Thread(game::run);
        thread.start();

        Button leftButton = findViewById(R.id.leftButton);
        Button rightButton = findViewById(R.id.rightButton);
        Button rotateButton = findViewById(R.id.rotateButton);
        Button downButton = findViewById(R.id.downButton);

        leftButton.setOnClickListener((view)-> {
            game.processInput(Input.Action.MOVE_LEFT);
        });
        rightButton.setOnClickListener((view)-> {
            game.processInput(Input.Action.MOVE_RIGHT);
        });
        rotateButton.setOnClickListener((view)-> {
            game.processInput(Input.Action.ROTATE);
        });
        downButton.setOnClickListener((view)-> {
            game.processInput(Input.Action.MOVE_DOWN);
        });

        //ISSUES:
        //Pieces flash in and out of rendering
        //Code breaks SOLID and other shit too probably
    }
}