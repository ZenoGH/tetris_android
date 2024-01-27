package com.example.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.example.tetris.Game.TetrisGame;

public class MainActivity extends AppCompatActivity {
    private GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TetrisGame game = new TetrisGame();
        game.renderView = findViewById(R.id.gridView);
        Thread thread = new Thread(game::run);
        thread.start();
        SwipeGestureListener swipeGestureListener = new SwipeGestureListener();
        swipeGestureListener.setSwipeListener((SwipeGestureListener.SwipeListener) this);
        gestureDetector = new GestureDetector(this, swipeGestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


    public void onSwipeUp() {

    }


    public void onSwipeDown() {
        // Handle swipe down gesture
    }


    public void onSwipeLeft() {
        // Handle swipe left gesture
    }


    public void onSwipeRight() {
        // Handle swipe right gesture
    }
}