package com.example.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Button;

import com.example.tetris.Game.Field;
import com.example.tetris.Game.Input;
import com.example.tetris.Game.TetrisGame;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TetrisGame game = new TetrisGame();
        game.renderView = findViewById(R.id.gridView);
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
        //Doesn't render instantly when rotating piece
        //Freezes piece almost as soon as it touches the floor
        //Pieces flash in and out of existence
        //Lines don't clear properly
        //Code breaks SOLID and other shit too probably
    }
}