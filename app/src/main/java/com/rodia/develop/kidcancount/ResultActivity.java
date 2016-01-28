package com.rodia.develop.kidcancount;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * This class have the responsibility for show the result of the game.
 */
public class ResultActivity extends AppCompatActivity {
    /**
     * Manage the media sound for this view.
     */
    private SoundMediaCustom media;

    /**
     * Start the view for show the result, and init the play sound.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

}
