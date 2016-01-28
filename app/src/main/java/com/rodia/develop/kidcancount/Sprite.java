package com.rodia.develop.kidcancount;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;

/**
 * This class manage the element for individual game.
 *
 * Each element can do independent movement with this conventions:
 *
 * direction = 0 up, 1 left, 2 down, 3 right,
 * animation = 3 back, 1 left, 0 front, 2 right
 */
public class Sprite {

    /**
     * Set for possible movement.
     * direction = 0 up, 1 left, 2 down, 3 right,
     * animation = 3 back, 1 left, 0 front, 2 right
     */
    int[] DIRECTION_TO_ANIMATION_MAP = { 3, 1, 0, 2 };

    /**
     * Const for total of rows.
     */
    private static final int BMP_ROWS = 4;

    /**
     * Constant for total columns.
     */
    private static final int BMP_COLUMNS = 3;

    /**
     * Const for max speed defined.
     */
    private static final int MAX_SPEED = 5;

    /**
     * Relationship for parent object. This is a compose link.
     */
    private GameView gameView;

    /**
     * Current image for show.
     */
    private Bitmap bmp;

    /**
     * Put the image in x coordinate.
     */
    private int x = 0;

    /**
     * Put the image in y coordinate.
     */
    private int y = 0;

    /**
     * X component speed.
     */
    private int xSpeed;

    /**
     * Y component speed.
     */
    private int ySpeed;

    /**
     * Current frame for show in the frame.
     */
    private int currentFrame = 0;

    /**
     * Width for one frame in the sprint.
     */
    private int width;

    /**
     * Height image in the sprint.
     */
    private int height;

    /**
     * Initialize the Sprite with a image sprite and init the values for speed and direction.
     * @param gameView
     * @param bmp
     */
    public Sprite(GameView gameView, Bitmap bmp) {
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
        this.gameView = gameView;
        this.bmp = bmp;

        Random rnd = new Random();
        x = rnd.nextInt(gameView.getWidth() - width);
        y = rnd.nextInt(gameView.getHeight() - height);
        xSpeed = rnd.nextInt(MAX_SPEED * 2) - MAX_SPEED;
        ySpeed = rnd.nextInt(MAX_SPEED * 2) - MAX_SPEED;
    }

    /**
     * Update the movement of animation in one sprint.
     */
    private void update() {
        if (x >= gameView.getWidth() - width - xSpeed || x + xSpeed <= 0) {
            xSpeed = -xSpeed;
        }
        x = x + xSpeed;
        if (y >= gameView.getHeight() - height - ySpeed || y + ySpeed <= 0) {
            ySpeed = -ySpeed;
        }
        y = y + ySpeed;
        currentFrame = ++currentFrame % BMP_COLUMNS;
    }

    /**
     * Update the values for the movement of one sprite.
     * @param canvas
     */
    public void onDraw(Canvas canvas) {
        update();
        int srcX = currentFrame * width;
        int srcY = getAnimationRow() * height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x + width, y + height);
        canvas.drawBitmap(bmp, src, dst, null);
    }

    /**
     * Return the current direction of one sprite.
     * @return
     */
    private int getAnimationRow() {
        double dirDouble = (Math.atan2(xSpeed, ySpeed) / (Math.PI / 2) + 2);
        int direction = (int) Math.round(dirDouble) % BMP_ROWS;
        return DIRECTION_TO_ANIMATION_MAP[direction];
    }

    /**
     * Check if have two or more images over one on a moment the game.
     * @param x2
     * @param y2
     * @return
     */
    public boolean isCollition(float x2, float y2) {
        return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
    }
}
