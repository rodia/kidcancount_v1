package com.rodia.develop.kidcancount;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Class to temporal effect to current game.
 */
public class TempSprite {
    /**
     * Set the component in x.
     */
    private float x;

    /**
     * Set the component in y.
     */
    private float y;

    /**
     * Capture the resource for image.
     */
    private Bitmap bmp;

    /**
     * Time defined until hide this object.
     */
    private int life = 15;

    /**
     * All temporal elements for this sprite.
     */
    private List<TempSprite> temps;

    /**
     * This show the current number.
     */
    private int count = 0;

    /**
     * Init the temp object with all parameter and show it.
     * @param temps
     * @param gameView
     * @param x
     * @param y
     * @param bmp
     */
    public TempSprite(List<TempSprite> temps, GameView gameView, float x,
                      float y, Bitmap bmp) {
        this.x = Math.min(Math.max(x - bmp.getWidth() / 2, 0),
                gameView.getWidth() - bmp.getWidth());
        this.y = Math.min(Math.max(y - bmp.getHeight() / 2, 0),
                gameView.getHeight() - bmp.getHeight());
        this.bmp = bmp;
        this.temps = temps;
    }

    /**
     * Play the number in spanish for the moment.
     */
    public void speakNumber(Context context) {
        SoundMediaCustom media = new SoundMediaCustom(context);
        media.setResource(getResourceByName());
        media.setLoop(false);
        media.play();
    }

    /**
     * Draw the temporal image and show
     * @param canvas
     */
    public void onDraw(Canvas canvas) {
        update();
        canvas.drawBitmap(bmp, x, y, null);
        Paint paint = new Paint();

        paint.setColor(Color.RED);
        paint.setTextSize(50);
        canvas.drawText(getCountString(), x + 35, y + 65, paint);
    }

    /**
     * Set the name for this object, that is the number that is shown in the view.
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Get the name of this temporal object cast to string.
     * @return
     */
    public String getCountString() {
        return this.count  + "";
    }

    /**
     * Countdown for the live of this object.
     */
    private void update() {
        if (--life < 1) {
            temps.remove(this);
        }
    }

    /**
     * Get current resource by named for this object.
     * @return
     */
    private int getResourceByName() {
        int resource = 0;
        switch (count) {
            case 1: resource = R.raw.one; break;
            case 2: resource = R.raw.two; break;
            case 3: resource = R.raw.three; break;
            case 4: resource = R.raw.four; break;
            case 5: resource = R.raw.five; break;
            case 6: resource = R.raw.six; break;
            case 7: resource = R.raw.seven; break;
            case 8: resource = R.raw.eight; break;
            case 9: resource = R.raw.nine; break;
            case 10: resource = R.raw.ten; break;
        }
        return resource;
    }
}