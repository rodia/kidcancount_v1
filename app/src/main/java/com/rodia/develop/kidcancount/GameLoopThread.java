package com.rodia.develop.kidcancount;

import android.graphics.Canvas;

/**
 * This class manage the all scenarios that defined by user.
 */
public class GameLoopThread extends Thread {
    /**
     * Const for define the Frame per second for show.
     */
    static final long FPS = 10;

    /**
     * Parent of this class.
     */
    private GameView view;

    /**
     * Enable the run of this thread.
     */
    private boolean running = false;

    /**
     * Set the parent link.
     * @param view
     */
    public GameLoopThread(GameView view) {
        this.view = view;
    }

    /**
     * Set the behaviour for runnable.
     * @param run
     */
    public void setRunning(boolean run) {
        running = run;
    }

    /**
     * Develop the runnable actions for each sprite.
     */
    @Override
    public void run() {
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;
        while (running) {
            Canvas c = null;
            startTime = System.currentTimeMillis();
            try {
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder()) {
                    view.onDraw(c);
                }
            } finally {
                if (c != null) {
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }
            sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(10);
            } catch (Exception e) {}
        }
    }
}

