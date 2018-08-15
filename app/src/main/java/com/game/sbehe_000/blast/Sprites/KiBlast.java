package com.game.sbehe_000.blast.Sprites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.game.sbehe_000.blast.R;

/**
 * Created by sbehe_000 on 10/12/2017.
 */

public class KiBlast {
    private Bitmap kiblast;
    private Bitmap[] frames = new Bitmap[10];

    private float x;
    private float y;

    private boolean readytogo;

    private float maxY;
    private float minY;

    private int shootingcount;

    private Rect kiDetect;

    public KiBlast(Context context){
        //Set outside of the visibile field
        x = -250;
        y = -250;

        //Initialize the frames
        frames[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.kamehamha1);
        frames[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.kamehamha2);
        frames[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.editkiblast1);
        frames[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.editkiblast2);
        frames[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.editkiblast3);
        frames[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.editkiblast4);
        frames[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.editblast5);
        frames[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.editkiblast6);
        frames[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.kamehamha7);
        frames[9] = BitmapFactory.decodeResource(context.getResources(), R.drawable.kiblastholder);

        //Set the visibile bitmap
        kiblast = frames[9];

        kiDetect = new Rect((int)x ,(int)y, kiblast.getWidth(), kiblast.getHeight());
        shootingcount = 90;
        readytogo = false;
    }

    public void update(){
        if(readytogo){
            if(shootingcount <= 120){
                kiblast = frames[9];
                shootingcount++;
            }else if(shootingcount > 120 && shootingcount <= 130){
                kiblast = frames[0];
                shootingcount++;
            }else if(shootingcount > 130 && shootingcount <= 140){
                kiblast = frames[1];
                shootingcount++;
            }else if(shootingcount > 140 && shootingcount <= 150){
                kiblast = frames[2];
                shootingcount++;
            }else if(shootingcount > 150 && shootingcount <= 160){
                kiblast = frames[3];
                shootingcount++;
            }else if(shootingcount > 160 && shootingcount <= 170){
                kiblast = frames[4];
                shootingcount++;
            }else if(shootingcount > 170 && shootingcount <= 180){
                kiblast = frames[5];
                shootingcount++;
            }else if(shootingcount > 180 && shootingcount <= 190){
                kiblast = frames[6];
                shootingcount++;
            }else if(shootingcount > 200 && shootingcount <= 210){
                kiblast = frames[7];
                shootingcount++;
            }else if(shootingcount > 210 && shootingcount <= 220){
                kiblast = frames[8];
                shootingcount++;
            }
            else{
                kiblast = frames[9];
                shootingcount = 90;
                readytogo = false;
                x = -250;
                y = -250;
            }

            kiDetect.left = (int)x;
            kiDetect.top = (int)y;
            kiDetect.right = (int)x + kiblast.getWidth();
            kiDetect.bottom = (int)y + kiblast.getHeight();
        }
    }

    //Use the setters to make it visible at the time of the shot
    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setReadytogo(boolean readytogo){
        this.readytogo = readytogo;
    }

    public boolean getReadytogo(){
        return readytogo;
    }

    public Bitmap getKiblast() {
        return kiblast;
    }

    public Rect getKiDetect(){
        return kiDetect;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }



}
