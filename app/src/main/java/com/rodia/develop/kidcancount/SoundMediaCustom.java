package com.rodia.develop.kidcancount;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.util.Log;

import java.io.IOException;

/**
 * Create and manage sound
 */
public class SoundMediaCustom {

    /**
     * Save the current media.
     */
    private MediaPlayer mediaPlayer;

    /**
     * Save the current source for raw.
     */
    private int resource;

    /**
     * Set loop for resource.
     */
    private boolean loop;

    /**
     * Save to current context.
     */
    private Context context;

    /**
     * Define if the media is played.
     */
    private static boolean isEnable;

    /**
     * Set Parent for this sound media.
     */
    private Activity parent;

    /**
     * Start media Sound.
     * @param context
     */
    public SoundMediaCustom(Context context) {
        setDefault(context, true, true);
    }

    /**
     * Start media sound with parameters.
     * @param context
     * @param loop
     * @param isEnable
     */
    public SoundMediaCustom(Context context, boolean loop, boolean isEnable) {
        setDefault(context, loop, isEnable);
    }

    /**
     * Set default values for this object.
     * @param context
     * @param loop
     * @param isEnable
     */
    protected void setDefault(Context context, boolean loop, boolean isEnable) {
        setContext(context);
        setDefaultResource();
        setLoop(loop);
        setIsEnable(isEnable);
    }

    /**
     * Set media player.
     * @param mediaPlayer
     */
    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    /**
     * Get Media player source.
     * @return
     */
    public MediaPlayer getMediaPlayer() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, getResource());
        }
        return mediaPlayer;
    }

    /**
     * Play to current media setter.
     */
    public void play() {
        if (getPreferredForSound()) {
            mediaPlayer = getMediaPlayer();
            mediaPlayer.setLooping(isLoop());
            mediaPlayer.start();
        }
    }

    /**
     * Set default resource.
     */
    protected void setDefaultResource() {
        setResource(R.raw.maripopins);
    }

    /**
     * Get current resource, for start resource if possible setted by default value.
     * @return
     */
    public int getResource() {
        return resource;
    }

    /**
     * Set current source. You can set one source defined.
     * @param resource
     */
    public void setResource(int resource) {
        this.resource = resource;
    }

    /**
     * Get current context if possible that main context getter.
     * @return
     */
    public Context getContext() {
        return context;
    }

    /**
     * Set current context.
     * @param context
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Get current loop setter.
     * @return
     */
    public boolean isLoop() {
        return loop;
    }

    /**
     * Set loop for the current player.
     * @param loop
     */
    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    /**
     * Can play one resource located in the net. you need set the value myUrl and
     * @param myUri
     */
    public void playRemote(Uri myUri) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(getContext(), myUri);
            mediaPlayer.prepare();
        } catch (IOException io) {
            io.printStackTrace();
        } catch (IllegalArgumentException ia) {
            ia.printStackTrace();
        }

        mediaPlayer.start();
    }

    /**
     * Play a resource element with get element with string url.
     * @param url
     */
    public void playRemoteWithUrl(String url) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
        } catch (IOException io) {
            io.printStackTrace();
        } catch (IllegalArgumentException ia) {
            ia.printStackTrace();
        }

        mediaPlayer.start();
    }

    /**
     * Stop the current play.
     */
    public void stop() {
        if (getPreferredForSound()) {
            mediaPlayer.pause();
        }
    }

    /**
     * Pause the current play.
     */
    public void pause() {
        if (getPreferredForSound()) {
            mediaPlayer.pause();
        }
    }

    /**
     * Get if play can do sound.
     * @return
     */
    public boolean isEnable() {
        return isEnable;
    }

    /**
     * Set state for play the current source.
     * @param enable
     */
    public void setIsEnable(boolean enable) {
        isEnable = enable;
    }

    public void setParent(Activity parent) {
        this.parent = parent;
    }

    public Activity getParent() {
        return parent;
    }

    /**
     * Get the value for preferred sound setting.
     * @return
     */
    protected boolean getPreferredForSound() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean syncSoundPref = sharedPref.getBoolean(SettingsActivity.KEY_PREF_SOUND, true);
        return syncSoundPref;
    }
}
