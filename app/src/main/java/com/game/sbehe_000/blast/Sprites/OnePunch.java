package com.game.sbehe_000.blast.Sprites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.game.sbehe_000.blast.R;

/**
 * Created by sbehe_000 on 10/12/2017.
 */

public class OnePunch {

    private Bitmap bitmap;

    //coordinate variables
    private int x;
    private int y;

    //constructor
    public OnePunch(Context context) {
        //getting boom image from drawable resource
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.punch);

        //setting the coordinate outside the screen
        //so that it won't shown up in the screen
        //it will be only visible for a fraction of second
        //after collission
        x = -250;
        y = -250;
    }

    //setters for x and y to make it visible at the place of collision
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    //getters
    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
