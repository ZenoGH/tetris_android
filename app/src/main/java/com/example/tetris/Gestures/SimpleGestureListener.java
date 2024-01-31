package com.example.tetris.Gestures;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class SimpleGestureListener extends GestureDetector.SimpleOnGestureListener {
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    public enum Direction {
        LEFT, RIGHT, UP, DOWN
    }
    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
        boolean result = false;
        float diffX = event2.getX() - event1.getX();
        float diffY = event2.getY() - event1.getY();
        if (Math.abs(diffX) > Math.abs(diffY)) {
            if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffX > 0) {
                    onSwipeRight();
                } else {
                    onSwipeLeft();
                }
                result = true;
            }
        } else {
            if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
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

