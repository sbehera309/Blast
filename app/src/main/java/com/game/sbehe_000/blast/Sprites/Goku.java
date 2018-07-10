package com.game.sbehe_000.blast.Sprites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.game.sbehe_000.blast.R;

/**
 * Created by sbehe_000 on 10/10/2017.
 */

public class Goku {
    private Bitmap bitmap;
    private Bitmap[] frames = new Bitmap[2];
    private Bitmap[] shootingframes = new Bitmap[14];

    private boolean isShooting;
    private int shootingcount;

    private int x;
    private int y;

    private int maxY;
    private int minY;

    private int speed = 10;
    private int gravity = 10;

    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;

    public int count;
    private int kicount;

    private Rect GokuDetect;

    public Goku(Context context, int screenX, int screenY){
        x = 55;
        y = 750;
        speed = 5;

        frames[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.player1);
        frames[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.player2);
        shootingframes[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.gokuattack);
        shootingframes[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.gokuattack1);
        shootingframes[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.gokuattack2);
        shootingframes[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.gokuattack3);
        shootingframes[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.gokuattack4);
        shootingframes[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.gokumegaattack5);
        shootingframes[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.gokumegaattack6);
        shootingframes[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.gokuattack7);
        shootingframes[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.gokuattack8);
        shootingframes[9] = BitmapFactory.decodeResource(context.getResources(), R.drawable.gokuattack9);
        shootingframes[10] = BitmapFactory.decodeResource(context.getResources(), R.drawable.gokuattack10);
        shootingframes[11] = BitmapFactory.decodeResource(context.getResources(), R.drawable.gokuattack11);
        shootingframes[12] = BitmapFactory.decodeResource(context.getResources(), R.drawable.gokumegaattack12);
        shootingframes[13] = BitmapFactory.decodeResource(context.getResources(), R.drawable.gokuattack13);
        bitmap = frames[0];

        maxY = screenY - bitmap.getHeight();
        minY = 0;

        GokuDetect = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());

        count = 0;
        isShooting = false;
        shootingcount = 0;
    }

    public void update(){
        //updating x coordinate

        if(isShooting == false){
            if(count <= 15){
                bitmap = frames[1];
                count++;
            }else if(count > 15 && count < 30){
                bitmap = frames[0];
                count++;
            }else{
                count = 0;
            }
            if(y < 0){
                y = 0;
            }
            if(y >= 0){
                y += gravity;
            }
            if(y >= maxY){
                y = maxY;
            }
        }else{
            if(shootingcount <= 5){
                bitmap = shootingframes[0];
                shootingcount++;
            }else if(shootingcount > 5 && shootingcount <= 10){
                bitmap = shootingframes[1];
                shootingcount++;
            }else if(shootingcount > 10 && shootingcount <= 15){
                bitmap = shootingframes[2];
                shootingcount++;
            }else if(shootingcount > 15 && shootingcount <= 20){
                bitmap = shootingframes[3];
                shootingcount++;
            }else if(shootingcount > 20 && shootingcount <= 25){
                bitmap = shootingframes[4];
                shootingcount++;
            }else if(shootingcount > 25 && shootingcount <= 30){
                bitmap = shootingframes[5];
                shootingcount++;
            }else if(shootingcount > 30 && shootingcount <= 35){
                bitmap = shootingframes[6];
                shootingcount++;
            }else if(shootingcount > 35 && shootingcount <= 40){
                bitmap = shootingframes[7];
                shootingcount++;
            }
            //Ki Blast starts from here
            else if(shootingcount > 40 && shootingcount <= 55){
                bitmap = shootingframes[8];
                shootingcount++;
            }else if(shootingcount > 55 && shootingcount <= 70){
                bitmap = shootingframes[9];
                shootingcount++;
            }else if(shootingcount > 70 && shootingcount <= 85){
                bitmap = shootingframes[10];
                shootingcount++;
            }else if(shootingcount > 85 && shootingcount <= 100){
                bitmap = shootingframes[11];
                shootingcount++;
            }else if(shootingcount > 100 && shootingcount <= 115){
                bitmap = shootingframes[12];
                shootingcount++;
            }else if(shootingcount > 115 && shootingcount <= 130){
                bitmap = shootingframes[13];
                shootingcount++;
            }else{
                shootingcount = 0;
                isShooting = false;
            }

        }
        GokuDetect.left = x;
        GokuDetect.top = y;
        GokuDetect.right = x + bitmap.getWidth();
        GokuDetect.bottom = y + bitmap.getHeight();
    }

    public void isFlying(){
        y -= 250;
    }

    public Rect getGokuDetect(){
        return GokuDetect;
    }

    public void gotKiBlast(){
        kicount++;
    }

    public void shootKiBlast(){
        kicount= kicount - 2;
        isShooting = true;
    }
    public boolean isFiring(){
        if(isShooting){
            return true;
        }else{
            return false;
        }
    }

    public Bitmap getBitmap() {
        return bitmap;
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
