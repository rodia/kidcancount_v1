package com.rodia.develop.kidcancount;

import android.content.Context;
import android.content.Intent;
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
     * Parent to this class. This link help to maintain the control of game.
     */
    private Context context;

    /**
     * Define the total counter for this game.
     */
    private int untilCounter;

    /**
     * Construct for this object.
     * @param context
     */
    public GameView(Context context) {
        super(context);
        this.context = context;
        gameLoopThread = new GameLoopThread(this);
        getHolder().addCallback(new SurfaceHolder.Callback() {

            /**
             * Manage the event when the view is destroyed.
             * @param holder
             */
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry) {
                    try {
                        gameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }

            /**
             * Prepare the game initialized the thread and run the loop game.
             * @param holder
             */
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                createSprites();
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }

            /**
             * @param holder
             * @param format
             * @param width
             * @param height
             */
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
        setUntilCounter(10);
        sprites.add(createSprite(R.drawable.catrun));
        sprites.add(createSprite(R.drawable.catrun2));
        sprites.add(createSprite(R.drawable.catrun3));
        sprites.add(createSprite(R.drawable.catrun4));
        sprites.add(createSprite(R.drawable.catrun5));
        sprites.add(createSprite(R.drawable.catrun6));
        sprites.add(createSprite(R.drawable.catrun7));
        sprites.add(createSprite(R.drawable.catrun8));
        sprites.add(createSprite(R.drawable.catrun9));
        sprites.add(createSprite(R.drawable.catrun10));
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
     * Check if is game over.
     * @return
     */
    protected boolean isFinishTheGame() {
        if (Counter.count >= getUntilCounter()) {
            return true;
        }
        return false;
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
                        //temp.speakNumber(getPlayActivity());
                        temps.add(temp);
                        break;
                    }
                }
                if (isFinishTheGame()) {
                    getPlayActivity().showResultActivity();
                }
            }
        }
        return true;
    }

    /**
     * Get current parent for this object.
     * @return
     */
    public PlayActivity getPlayActivity() {
        return (PlayActivity)context;
    }

    /**
     * Get Until when. Define the total of element showed.
     * @return
     */
    public int getUntilCounter() {
        return untilCounter;
    }

    /**
     * Set the total elements for this game.
     * @param untilCounter
     */
    public void setUntilCounter(int untilCounter) {
        this.untilCounter = untilCounter;
    }
}