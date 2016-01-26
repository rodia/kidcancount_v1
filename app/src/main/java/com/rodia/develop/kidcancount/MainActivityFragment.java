package com.rodia.develop.kidcancount;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Throw the start game for enjoin.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    /**
     * Develop the action for play the game.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Button button = (Button) rootView.findViewById(R.id.button_play);
        button.setOnClickListener(new View.OnClickListener() {

            /**
             * Start the play game.
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getActivity(), PlayActivity.class);
                startActivity(intent);
            }
        });
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
