package com.rodia.develop.kidcancount;

import android.content.Intent;
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
     * Hold the current media sound.
     */
    private SoundMediaCustom media;

    /**
     * Start to play scenery
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        game = new GameView(this);
        setContentView(game);

        media = new SoundMediaCustom(getApplicationContext());
        media.setResource(R.raw.thegame);
        media.play();
    }

    /**
     * Resume the sound for the game if is enable.
     */
    @Override
    protected void onStart() {
        super.onStart();
        media.play();
    }

    /**
     * Resume the sound for the game if is enable.
     */
    @Override
    protected void onResume() {
        super.onResume();
        media.play();
    }

    /**
     * Pause the sound the play game.
     */
    @Override
    protected void onPause() {
        super.onPause();
        media.pause();
    }

    /**
     * Stop the play sound when the app is stopped.
     */
    @Override
    protected void onStop() {
        super.onStop();
        media.stop();
    }

    /**
     * Stop the play sound when the app is destroyed.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        media.stop();
    }

    /**
     * Show the Result Activity for the game over.
     */
    public void showResultActivity() {
        Intent intent;
        intent = new Intent(getApplicationContext(), ResultActivity.class);
        startActivity(intent);
    }
}
