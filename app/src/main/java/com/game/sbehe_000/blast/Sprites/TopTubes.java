package com.game.sbehe_000.blast.Sprites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.shapes.OvalShape;

import com.game.sbehe_000.blast.R;

import java.util.Random;

import static android.R.attr.bitmap;
import static android.R.attr.top;

/**
 * Created by sbehe_000 on 10/10/2017.
 */

public class TopTubes {
    private Bitmap toptube_image;
    private Bitmap[] frames = new Bitmap[4];
    private int count;

    private int x;
    private int y;

    private int speed = 1;

    private int maxX;
    private int minX;

    private int maxY;
    private int minY;

    private Rect collisionDetect;

    public TopTubes(Context context, int screenX, int screenY){
        //getting bitmap from drawable resource
        frames[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.testki1);
        frames[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.testki2);
        frames[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.testki3);
        frames[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.testki4);
        toptube_image = frames[0];

        count = 0;

        //initializing min and max coordinates
        maxX = screenX;
        maxY = screenY;
        minX = 0;
        minY = 0;

        //generating a random coordinate to add enemy
        Random generator = new Random();
        speed = generator.nextInt(6) + 10;
        x = screenX + generator.nextInt(maxX);
        y = generator.nextInt(maxY) - toptube_image.getHeight();
        if(y < 0){
            y = 0 + toptube_image.getHeight();
        }

        //Initialize detection box
        collisionDetect = new Rect(x, y, toptube_image.getWidth(), toptube_image.getHeight());
    }

    public void update(int playerSpeed) {
        //decreasing x coordinate so that enemy will move right to left
        x -= playerSpeed;
        x -= speed;
        if(count <= 15){
            toptube_image = frames[1];
            count++;
        }else if(count > 15 && count <= 30){
            toptube_image = frames[2];
            count++;
        }else if(count > 30 && count <= 45){
            toptube_image = frames[3];
            count++;
        }else if(count > 45 && count < 60){
            toptube_image = frames[0];
            count++;
        }else{
            count = 0;
        }
        //if the enemy reaches the left edge
        if (x < minX - toptube_image.getWidth()) {
            //adding the enemy again to the right edge
            Random generator = new Random();
            speed = generator.nextInt(10) + 10;
            x = maxX;
            y = generator.nextInt(maxY) - toptube_image.getHeight();
            if(y < 0){
                y = 0 + toptube_image.getHeight();
            }
        }

        //Change detection boxes
        collisionDetect.left = x;
        collisionDetect.top = y;
        collisionDetect.right = x + toptube_image.getWidth();
        collisionDetect.bottom = y + toptube_image.getHeight();

    }

    public Bitmap getToptube_image() {
        return toptube_image;
    }

    public Rect getCollisionDetect() {
        return collisionDetect;
    }

    public void repositionBlast(int screenX, int screenY){
        //Reposition the actual image
        Random generator = new Random();
        speed = generator.nextInt(6) + 10;
        x = screenX + generator.nextInt(maxX);
        y = generator.nextInt(maxY) - toptube_image.getHeight();

        //Reposition the hit box according to the image
        collisionDetect.left = x;
        collisionDetect.top = y;
        collisionDetect.right = x + toptube_image.getWidth();
        collisionDetect.bottom = y + toptube_image.getHeight();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }
}
