package com.rodia.develop.kidcancount;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Main Activity for show start game. Also can you heard the initial sound for started.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Hold the current media sound.
     */
    private SoundMediaCustom media;

    /**
     * This method create the main activity and try to hold the "scenarios".
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        media = new SoundMediaCustom(getApplicationContext());
        media.play();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        media.stop();
    }

    /**
     * Cicle of life for on pause state
     */
    @Override
    protected void onPause() {
        super.onPause();
        media.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        media.play();
    }

    @Override
    protected void onStart() {
        super.onStart();
        media.play();
    }

    @Override
    protected void onStop() {
        super.onStop();
        media.pause();
    }

    /**
     * Setup the menu options.
     * @param menu
     * @return boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Manage the action for menu.
     * @param item
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
