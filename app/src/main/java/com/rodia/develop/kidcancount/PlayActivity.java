package com.rodia.develop.kidcancount;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * This Activity develop the activity for play.
 */
public class PlayActivity extends AppCompatActivity {

    /**
     * The object to produce the play game.
     */
    private GameView game;

    /**
     * Start to play scenery
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        game = new GameView(this);
        setContentView(game);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
    }
    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
    }
    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
    }
}
