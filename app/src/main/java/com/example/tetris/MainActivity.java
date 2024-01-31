package com.example.tetris;

import static java.lang.Math.abs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Button;

import com.example.tetris.Game.Input;
import com.example.tetris.Game.ScoreSystem;
import com.example.tetris.Game.TetrisGame;
import com.example.tetris.Gestures.SimpleGestureListener;
import com.example.tetris.Rendering.Renderer;

public class MainActivity extends AppCompatActivity {
    private GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Renderer fieldRenderer = new Renderer(findViewById(R.id.tetrisLayout));
        Renderer nextShapeRenderer = new Renderer(findViewById(R.id.nextShapeLayout));
        ScoreSystem scoreSystem = new ScoreSystem(findViewById(R.id.scoreTextView));
        TetrisGame game = new TetrisGame(fieldRenderer, nextShapeRenderer, scoreSystem);
        Thread thread = new Thread(() -> {
            while (true) {
                game.run();
            }
        });
        thread.start();


        SimpleGestureListener gestureListener = new SimpleGestureListener() {
            @Override
            public void onSwipeUp() {
                game.processInput(Input.Action.ROTATE);
            }

            @Override
            public void onSwipeDown() {
                game.processInput(Input.Action.MOVE_DOWN);
            }

            @Override
            public void onSwipeLeft() {
                game.processInput(Input.Action.MOVE_LEFT);
            }

            @Override
            public void onSwipeRight() {
                game.processInput(Input.Action.MOVE_RIGHT);
            }
        };

        gestureDetector = new GestureDetector(this, gestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
    }
}

