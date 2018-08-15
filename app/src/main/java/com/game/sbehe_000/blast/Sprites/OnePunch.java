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
    private float x;
    private float y;
    private boolean isActive;
    private Bitmap[] frames = new Bitmap[7];
    private int count;

    //constructor
    public OnePunch(Context context) {
        //getting boom image from drawable resource
        frames[0]= BitmapFactory.decodeResource(context.getResources(),R.drawable.punchtest);
        frames[1]= BitmapFactory.decodeResource(context.getResources(), R.drawable.punchanime1);
        frames[2]= BitmapFactory.decodeResource(context.getResources(), R.drawable.punchanime2);
        frames[3]= BitmapFactory.decodeResource(context.getResources(), R.drawable.punchanime3);
        frames[4]= BitmapFactory.decodeResource(context.getResources(), R.drawable.punchanime4);
        frames[5]= BitmapFactory.decodeResource(context.getResources(), R.drawable.punchanime5);
        frames[6]= BitmapFactory.decodeResource(context.getResources(), R.drawable.punchanime6);
        bitmap = frames[0];

        //setting the coordinate outside the screen
        //so that it won't shown up in the screen
        //it will be only visible for a fraction of second
        //after collission
        isActive = false;
        x = -250;
        y = -250;
        count = 0;
    }

    public void update(){
        if(isActive){
            if(count <= 10){
                bitmap = frames[1];
                count++;
            }else if(count > 10 && count <= 20){
                bitmap = frames[2];
                count++;
            }else if(count > 20 && count <= 30){
                bitmap = frames[3];
                count++;
            }else if(count > 30 && count <= 40){
                bitmap = frames[4];
                count++;
            }else if(count > 40 && count <= 50){
                bitmap = frames[5];
                count++;
            }else if(count > 50 && count <= 60){
                bitmap = frames[6];
                count++;
            }else if(count > 60 && count <= 70){
                bitmap = frames[0];
                count++;
            }
            else {
                bitmap = frames[0];
                count = 100;
            }
        }
    }

    //setters for x and y to make it visible at the place of collision
    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void isActive(){
        isActive = true;
    }
    public boolean isAnimeDone(){
        if(count == 100){
            return true;
        }
        else{
            return false;
        }
    }

    //getters
    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
