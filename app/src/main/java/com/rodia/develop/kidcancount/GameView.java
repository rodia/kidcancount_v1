package com.rodia.develop.kidcancount;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.rodia.develop.kidcancount.model.Counter;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contain the animation of play game.
 */
public class GameView extends SurfaceView {

    /**
     * Thread for game. Run the develop of game.
     */
    private GameLoopThread gameLoopThread;

    /**
     * List of element to develop the game. Usually are the animations elements.
     */
    private List<Sprite> sprites = new ArrayList<Sprite>();

    /**
     * Hold temporal element to develop the game.
     */
    private List<TempSprite> temps = new ArrayList<TempSprite>();

    /**
     * Hold the current last click action development.
     */
    private long lastClick;

    /**
     * Temporal image resource.
     */
    private Bitmap bmpBlood;

    /**
     * Construct for this object.
     * @param context
     */
    public GameView(Context context) {
        super(context);
        gameLoopThread = new GameLoopThread(this);
        getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry) {
                    try {
                        gameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e) {}
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                createSprites();
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });
        bmpBlood = BitmapFactory.decodeResource(getResources(), R.drawable.circle);
    }

    /**
     * Get all current sprites for the game.
     */
    private void createSprites() {
        sprites.add(createSprite(R.drawable.catrun));
//        sprites.add(createSprite(R.drawable.catrun2));
//        sprites.add(createSprite(R.drawable.catrun3));
//        sprites.add(createSprite(R.drawable.catrun4));
//        sprites.add(createSprite(R.drawable.catrun5));
//        sprites.add(createSprite(R.drawable.catrun6));
//        sprites.add(createSprite(R.drawable.catrun7));
//        sprites.add(createSprite(R.drawable.catrun8));
//        sprites.add(createSprite(R.drawable.catrun9));
//        sprites.add(createSprite(R.drawable.catrun10));
    }

    /**
     * Create current resource.
     * @param resouce
     * @return
     */
    private Sprite createSprite(int resouce) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resouce);
        return new Sprite(this, bmp);
    }

    /**
     * Draw the current element for the game.
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        if (canvas == null) {
            Counter.count = 0;
            return;
        }
        Bitmap bg =  BitmapFactory.decodeResource(getResources(), R.drawable.madejas);
        canvas.drawBitmap(bg, 0, 0, null);

        for (int i = temps.size() - 1; i >= 0; i--) {
            temps.get(i).onDraw(canvas);
        }
        for (Sprite sprite : sprites) {
            sprite.onDraw(canvas);
        }
    }

    /**
     * Set action for touch effect to develop the game.
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (System.currentTimeMillis() - lastClick > 300) {
            lastClick = System.currentTimeMillis();
            float x = event.getX();
            float y = event.getY();
            synchronized (getHolder()) {
                for (int i = sprites.size() - 1; i >= 0; i--) {
                    Sprite sprite = sprites.get(i);
                    if (sprite.isCollition(x, y)) {
                        sprites.remove(sprite);
                        TempSprite temp = new TempSprite(temps, this, x, y, bmpBlood);
                        temp.setCount(++Counter.count);
                        temps.add(temp);
                        break;
                    }
                }
            }
        }
        return true;
    }
}