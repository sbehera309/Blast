package com.game.sbehe_000.blast.Sprites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.game.sbehe_000.blast.R;

import java.util.Random;

/**
 * Created by sbehe_000 on 10/11/2017.
 */

public class Saitama {
    private Bitmap saitama_image;
    private Bitmap[] frames = new Bitmap[2];
    int framecount;

    int x;
    int y;
    int maxX;
    int maxY;
    int minX;
    int minY;
    int speed = 1;

    private Rect collisionDetect;

    public Saitama(Context context, int screenX, int screenY){
        //Get the frames for the image
        frames[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.testonep1);
        frames[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.testonep2);
        saitama_image = frames[0];


        //Initialize the count for the frames
        framecount = 0;

        //initializing min and max coordinates
        maxX = screenX;
        maxY = screenY;
        minX = 0;
        minY = 0;

        //generating a random coordinate to add enemy
        Random generator = new Random();
        speed = generator.nextInt(6) + 10;
        x = screenX + generator.nextInt(maxX);
        y = generator.nextInt(maxY) - saitama_image.getHeight();
        if(y < 0){
            y = 0;
        }

        //Initialize the detection box
        collisionDetect = new Rect(x, y, saitama_image.getWidth(),saitama_image.getHeight() );
    }

    public void update(int playerSpeed){
        //Make the object move from right to left
        x -= playerSpeed;
        x -= speed;

        //update the bitmap to change according to the frames
        if(framecount <= 15){
            saitama_image = frames[1];
            framecount++;
        }else if(framecount > 15 && framecount < 30){
            saitama_image = frames[0];
            framecount++;
        }else{
            framecount = 0;
        }

        //if the enemy reaches the left edge
        if (x < minX - saitama_image.getWidth()) {
            //adding the enemy again to the right edge
            Random generator = new Random();
            speed = generator.nextInt(10) + 10;
            x = maxX;
            y = generator.nextInt(maxY) - saitama_image.getHeight();
            if(y < 0){
                y = 0;
            }
        }

        //Change detection boxes
        collisionDetect.left = x;
        collisionDetect.top = y;
        collisionDetect.right = x + saitama_image.getWidth();
        collisionDetect.bottom = y + saitama_image.getHeight();
    }

    public Bitmap getSaitama_image(){
        return saitama_image;
    }

    public Rect getCollisionDetect(){
        return collisionDetect;
    }

    public void repositionSaitama(int screenX, int screenY){
        Random generator = new Random();
        speed = generator.nextInt(10) + 10;
        x = maxX;
        y = generator.nextInt(maxY) - saitama_image.getHeight();
        if(y < 0){
            y = 0;
        }

        //Change detection boxes
        collisionDetect.left = x;
        collisionDetect.top = y;
        collisionDetect.right = x + saitama_image.getWidth();
        collisionDetect.bottom = y + saitama_image.getHeight();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
