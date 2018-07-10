package com.game.sbehe_000.blast.Extra;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.game.sbehe_000.blast.R;

/**
 * Created by sbehe_000 on 10/13/2017.
 */

public class ScoreSystem {
    public SharedPreferences pref;
    private Bitmap[] numbers = new Bitmap[10];

    private int zeroth = 0, first = 0, second = 0, third = 0;

    private int x0, x1, x2, x3;
    private int y0,y1,y2, y3;

    public ScoreSystem(Context context){
        this.pref = context.getSharedPreferences("SHAR_PREF_NAME",Context.MODE_PRIVATE);
        numbers[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.zero);
        numbers[1] = BitmapFactory.decodeResource(context.getResources(),R.drawable.one);
        numbers[2] = BitmapFactory.decodeResource(context.getResources(),R.drawable.two);
        numbers[3] = BitmapFactory.decodeResource(context.getResources(),R.drawable.three);
        numbers[4] = BitmapFactory.decodeResource(context.getResources(),R.drawable.four);
        numbers[5] = BitmapFactory.decodeResource(context.getResources(),R.drawable.five);
        numbers[6] = BitmapFactory.decodeResource(context.getResources(),R.drawable.six);
        numbers[7] = BitmapFactory.decodeResource(context.getResources(),R.drawable.seven);
        numbers[8] = BitmapFactory.decodeResource(context.getResources(),R.drawable.eight);
        numbers[9] = BitmapFactory.decodeResource(context.getResources(),R.drawable.nine);

        x0= 500;
        x1 = 600;
        x2 = 700;
        x3 = 800;

        y0 = -400;
        y1 = -400;
        y2 = -400;
        y3 = 0;

    }

    public void splitScore(int score){
        int[] scorePartial = new int[4];
        int i = 3;
        int rem;
        int temp = score;

        while(temp != 0){
            rem = temp % 10;
            temp /= 10;
            scorePartial[i--] = rem;
        }
        zeroth = scorePartial[0];
        first = scorePartial[1];
        second = scorePartial[2];
        third = scorePartial[3];
    }

    public Bitmap zeroth(){
        return numbers[this.zeroth];
    }
    public Bitmap first(){
        return numbers[this.first];
    }
    public Bitmap second(){
        return numbers[this.second];
    }
    public Bitmap third(){
        return numbers[this.third];
    }
    public int getZeroth(){
        return zeroth;
    }
    public int getFirst() {
        return first;
    }
    public int getSecond() {
        return second;
    }

    public int getX0() {
        return x0;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getX3() {
        return x3;
    }

    public int getY0() {
        return y0;
    }

    public void setY0(int y0) {
        this.y0 = y0;
    }

    public int getY3() {
        return y3;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }
}
