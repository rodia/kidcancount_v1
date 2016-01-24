package com.rodia.develop.kidcancount;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

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
     * Save to current context.
     */
    private Context context;

    /**
     * Start media Sound.
     * @param context
     */
    public SoundMediaCustom(Context context) {
        setContext(context);
        setDefaultResource();
        mediaPlayer = getMediaPlayer();
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
        mediaPlayer.start();
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
}