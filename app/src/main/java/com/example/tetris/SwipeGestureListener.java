package com.example.tetris;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class SwipeGestureListener extends GestureDetector.SimpleOnGestureListener {
    // Minimum distance for a swipe gesture to be detected
    private static final int SWIPE_THRESHOLD = 100;

    // Minimum velocity for a swipe gesture to be detected
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    // Listener callback methods for gesture actions
    public interface SwipeListener {
        void onSwipeUp();

        void onSwipeDown();

        void onSwipeLeft();

        void onSwipeRight();
    }

    private SwipeListener swipeListener;

    public void setSwipeListener(SwipeListener swipeListener) {
        this.swipeListener = swipeListener;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
        if (event1 == null) {
            return false;
        }
        float diffX = event2.getX() - event1.getX();
        float diffY = event2.getY() - event1.getY();

        if (Math.abs(diffX) > Math.abs(diffY)) {
            if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffX > 0) {
                    if (swipeListener != null) {
                        swipeListener.onSwipeRight();
                    }
                } else {
                    if (swipeListener != null) {
                        swipeListener.onSwipeLeft();
                    }
                }
                return true;
            }
        } else {
            if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                    if (swipeListener != null) {
                        swipeListener.onSwipeDown();
                    }
                } else {
                    if (swipeListener != null) {
                        swipeListener.onSwipeUp();
                    }
                }
                return true;
            }
        }
        return false;
    }
}