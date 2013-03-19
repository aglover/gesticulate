package com.b50.gesticulate;

import android.view.MotionEvent;

/**
 * Copyright (c) 2011 Andrew Glover
 * http://thediscoblog.com
 * https://twitter.com/aglover
 * <p/>
 * See Git repo for documentation, tests, and MIT license description.
 * https://github.com/aglover/gesticulate
 */

public class SwipeDetector {

    private MotionEvent eventOne;
    private MotionEvent eventTwo;
    private float velocityX;
    private float velocityY;
    private Gesticulate detector;

    public SwipeDetector(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        this.eventOne = e1;
        this.eventTwo = e2;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.detector = this.new Gesticulate();
    }

    public SwipeDetector(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY, int minDistance, int minVelocity) {
        this.eventOne = e1;
        this.eventTwo = e2;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.detector = this.new Gesticulate(minDistance, minVelocity);
    }

    public boolean isDownSwipe() {
        return detector.isDownSwipe(eventOne, eventTwo, velocityY);
    }

    public boolean isUpSwipe() {
        return detector.isUpSwipe(eventOne, eventTwo, velocityY);
    }

    public boolean isLeftSwipe() {
        return detector.isLeftSwipe(eventOne, eventTwo, velocityX);
    }

    public boolean isRightSwipe() {
        return detector.isRightSwipe(eventOne, eventTwo, velocityX);
    }

    private class Gesticulate {

        private int swipe_distance;
        private int swipe_velocity;
        private static final int DEFAULT_SWIPE_MIN_DISTANCE = 120;
        private static final int DEFAULT_SWIPE_THRESHOLD_VELOCITY = 200;

        public Gesticulate() {
            super();
            this.swipe_distance = DEFAULT_SWIPE_MIN_DISTANCE;
            this.swipe_velocity = DEFAULT_SWIPE_THRESHOLD_VELOCITY;
        }

        public Gesticulate(int distance, int velocity) {
            super();
            this.swipe_distance = distance;
            this.swipe_velocity = velocity;
        }

        public boolean isDownSwipe(MotionEvent e1, MotionEvent e2, float velocityY) {
            return isSwipe(e2.getY(), e1.getY(), velocityY);
        }

        public boolean isUpSwipe(MotionEvent e1, MotionEvent e2, float velocityY) {
            return isSwipe(e1.getY(), e2.getY(), velocityY);
        }

        public boolean isLeftSwipe(MotionEvent e1, MotionEvent e2, float velocityX) {
            return isSwipe(e1.getX(), e2.getX(), velocityX);
        }

        public boolean isRightSwipe(MotionEvent e1, MotionEvent e2, float velocityX) {
            return isSwipe(e2.getX(), e1.getX(), velocityX);
        }

        private boolean isSwipeDistance(float coordinateA, float coordinateB) {
            return (coordinateA - coordinateB) > this.swipe_distance;
        }

        private boolean isSwipeSpeed(float velocity) {
            return Math.abs(velocity) > this.swipe_velocity;
        }

        private boolean isSwipe(float coordinateA, float coordinateB, float velocity) {
            return isSwipeDistance(coordinateA, coordinateB)
                    && isSwipeSpeed(velocity);
        }
    }

}