package com.example.tetris.Gestures;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class SimpleGestureListener extends GestureDetector.SimpleOnGestureListener {
    private static final int swipeThreshold = 100;
    private static final int swipeVelocityThreshold = 100;

    public enum Direction {
        LEFT, RIGHT, UP, DOWN
    }
    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
        boolean result = false;
        float diffX = event2.getX() - event1.getX();
        float diffY = event2.getY() - event1.getY();
        if (Math.abs(diffX) > Math.abs(diffY)) {
            if (Math.abs(diffX) > swipeThreshold && Math.abs(velocityX) > swipeVelocityThreshold) {
                if (diffX > 0) {
                    onSwipeRight();
                } else {
                    onSwipeLeft();
                }
                result = true;
            }
        } else {
            if (Math.abs(diffY) > swipeThreshold && Math.abs(velocityY) > swipeVelocityThreshold) {
                if (diffY > 0) {
                    onSwipeDown();
                } else {
                    onSwipeUp();
                }
                result = true;
            }
        }

        return result;
    }

    public void onSwipeUp() {
    }

    public void onSwipeDown() {
        // To be implemented by the calling class
    }

    public void onSwipeLeft() {
        // To be implemented by the calling class
    }

    public void onSwipeRight() {
        // To be implemented by the calling class
    }
}

