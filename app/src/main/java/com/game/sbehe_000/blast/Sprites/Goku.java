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
    private Bitmap[] frames = new Bitmap[6];
    private Bitmap[] shootingframes = new Bitmap[14];

    private boolean isShooting;
    private boolean isHit;
    private int shootingcount;

    private float x;
    private float y;

    private float maxY;
    private float minY;

    private float speed = 10;
    private float gravity = 10;

    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;

    public int count;
    private int kicount;

    private Rect GokuDetect;

    public Goku(Context context, float screenX, float screenY){
        x = 55;
        y = 750;
        speed = 5;

        frames[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.playeraura1);
        frames[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.playeraura4);
        frames[2] = BitmapFactory.decodeResource(context.getResources(),R.drawable.playeraura2);
        frames[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.playeraura5);
        frames[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.playeraura3);
        frames[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.playeraura6);
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

        GokuDetect = new Rect((int)x, (int)y, bitmap.getWidth() - 5, bitmap.getHeight()-5);

        count = 0;
        isShooting = false;
        isHit = false;
        shootingcount = 0;
    }

    public void update(){
        //updating x coordinate

        if(isHit){
            gravity = 0;
        }

        if(!isShooting){
            if(count <= 15){
                bitmap = frames[1];
                count++;
            }else if(count > 15 && count <= 30){
                bitmap = frames[2];
                count++;
            }else if(count > 30 && count <= 45){
                bitmap = frames[3];
                count++;
            }else if(count > 45 && count <= 60){
                bitmap = frames[4];
                count++;
            }else if(count > 60 && count < 75){
                bitmap = frames[5];
                count++;
            }else if(count >=75 && count < 90){
                bitmap = frames[0];
                count++;
            }
            else{
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
        GokuDetect.left = (int)x;
        GokuDetect.top = (int)y;
        GokuDetect.right = (int)x + bitmap.getWidth() - 5;
        GokuDetect.bottom = (int)y + bitmap.getHeight() - 5;
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
    public void setHit(){
        isHit = true;
    }
    public boolean isHit(){
        if(isHit){
            return true;
        }else{
            return false;
        }
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

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getSpeed() {
        return speed;
    }

}
