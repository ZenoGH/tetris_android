package com.example.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

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
                game.processInput(Direction.UP);
            }

            @Override
            public void onSwipeDown() {
                game.processInput(Direction.DOWN);
            }

            @Override
            public void onSwipeLeft() {
                game.processInput(Direction.LEFT);
            }

            @Override
            public void onSwipeRight() {
                game.processInput(Direction.RIGHT);
            }
        };

        gestureDetector = new GestureDetector(this, gestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
    }
}

