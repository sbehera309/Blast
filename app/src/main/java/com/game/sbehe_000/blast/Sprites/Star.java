package com.game.sbehe_000.blast.Sprites;

import java.util.Random;

/**
 * Created by sbehe_000 on 10/11/2017.
 */

public class Star {
    private float x;
    private float y;
    private float speed;

    private float maxX;
    private float maxY;
    private float minX;
    private float minY;

    public Star(float screenX, float screenY) {
        maxX = screenX;
        maxY = screenY;
        minX = 0;
        minY = 0;
        Random generator = new Random();
        speed = generator.nextInt(10);

        //generating a random coordinate
        //but keeping the coordinate inside the screen size
        x = generator.nextInt((int)maxX);
        y = generator.nextInt((int)maxY);
    }

    public void update(float playerSpeed) {
        //animating the star horizontally left side
        //by decreasing x coordinate with player speed
        x -= playerSpeed;
        x -= speed;
        //if the star reached the left edge of the screen
        if (x < 0) {
            //again starting the star from right edge
            //this will give a infinite scrolling background effect
            x = maxX;
            Random generator = new Random();
            y = generator.nextInt((int)maxY);
            speed = generator.nextInt(15);
        }
    }

    public float getStarWidth() {
        //Making the star width random so that
        //it will give a real look
        float minX = 1.0f;
        float maxX = 4.0f;
        Random rand = new Random();
        float finalX = rand.nextFloat() * (maxX - minX) + minX;
        return finalX;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
