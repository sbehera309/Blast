package com.game.sbehe_000.blast;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.game.sbehe_000.blast.Extra.ScoreSystem;
import com.game.sbehe_000.blast.Sprites.Goku;
import com.game.sbehe_000.blast.Sprites.KiBlast;
import com.game.sbehe_000.blast.Sprites.OnePunch;
import com.game.sbehe_000.blast.Sprites.Saitama;
import com.game.sbehe_000.blast.Sprites.Star;
import com.game.sbehe_000.blast.Sprites.TopTubes;

import java.util.ArrayList;

/**
 * Created by sbehe_000 on 10/10/2017.
 */

public class GameView extends SurfaceView implements Runnable {
    //Checks if the game is being played or not
    volatile boolean playing;
    private int game_level;
    private boolean isGameOver;
    Context context;

    private GestureDetectorCompat mDetector;


    //game thread
    private Thread gameThread = null;

    //player object
    private Goku goku;
    private TopTubes[] enemies;
    private Saitama[] saitamas;
    private OnePunch onePunch;
    private KiBlast[] kiBlasts;
    private ScoreSystem scoreSystem;

    //ground
    private Bitmap ground;
    private Rect groundDetect;

    //background stars
    private ArrayList<Star> stars = new
            ArrayList<Star>();
    private ArrayList<Star> yellowstars = new ArrayList<Star>();

    //Number of interactive objects
    private int tubeCount = 2; //Collectable ki Blast
    private int saitamaCount = 3;
    private int kiBlastcount = 2; //Kamehameha object
    private int kiBlaststorage = 0; //Count for how many ki blasts were collected

    //Declare the drawing objects
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    int tempscreenx, tempscreeny;

    //the score holder
    int score;

    //the high Scores Holder
    int highScore[] = new int[3];

    //Shared Prefernces to store the High Scores
    SharedPreferences sharedPreferences;

    public GameView(Context context,int screenX, int screenY , int level){
        super(context);

        this.context = context;
        isGameOver = false;

        //Initialize the Gesture class
        mDetector = new GestureDetectorCompat(context, new MyGestureListener());

        //Initialize the ground
        ground = BitmapFactory.decodeResource(context.getResources(), R.drawable.ground);

        //Initialize the stars
        int starNums = 100;
        for (int i = 0; i < starNums; i++) {
            Star s  = new Star(screenX, screenY);
            stars.add(s);
        }

        int yellowstarNums = 50;
        for( int i = 0; i < yellowstarNums ; i++){
            Star b = new Star(screenX + 1, screenY + 1);
            yellowstars.add(b);
        }

        game_level = level;
        //Initialize the player
        goku = new Goku(context, screenX, screenY);
        tempscreenx = screenX;
        tempscreeny = screenY;

        //Initialize the tubes
        enemies = new TopTubes[tubeCount];
        for(int i=0; i<tubeCount; i++){
            enemies[i] = new TopTubes(context, screenX, screenY);
        }

        //Initialize the Saitamas
        saitamaCount = saitamaCount + game_level -1;
        saitamas = new Saitama[saitamaCount];
        for(int i=0; i<saitamaCount; i++){
            saitamas[i] = new Saitama(context, screenX, screenY);
        }

        //Initialize the Collision animation
        onePunch = new OnePunch(context);

        //Initialize the kiBlasts
        kiBlasts = new KiBlast[kiBlastcount];
        for(int i = 0; i <kiBlastcount; i++){
            kiBlasts[i] = new KiBlast(context);
        }


        //setting the score to 0 initially
        score = 0;

        this.scoreSystem = new ScoreSystem(context);

        sharedPreferences = context.getSharedPreferences("SHAR_PREF_NAME",Context.MODE_PRIVATE);

        //initializing the array high scores with the previous values
        highScore[0] = sharedPreferences.getInt("score1",0);
        highScore[1] = sharedPreferences.getInt("score2",0);
        highScore[2] = sharedPreferences.getInt("score3",0);

        //Initialize the drawing objects
        surfaceHolder = getHolder();
        paint = new Paint();
    }

    @Override
    public void run(){
        while(playing){
            update();

            draw();

            control();
        }
    }

    private void update(){
        goku.update();

        onePunch.setX(-250);
        onePunch.setY(-250);

        if(this.scoreSystem.getZeroth() == 0){
            this.scoreSystem.setY0(-255);
        }
        if(this.scoreSystem.getFirst() == 0 || this.scoreSystem.getZeroth() == 0){
            this.scoreSystem.setY1(-255);
        }
        if(this.scoreSystem.getSecond() == 0 || this.scoreSystem.getFirst() == 0 || this.scoreSystem.getZeroth() == 0){
            this.scoreSystem.setY2(-255);
        }

        this.scoreSystem.splitScore(score);
        if(this.scoreSystem.getZeroth() > 0){
            this.scoreSystem.setY0(0);
        }
        if(this.scoreSystem.getFirst() > 0 || this.scoreSystem.getZeroth() > 0){
            this.scoreSystem.setY1(0);
        }
        if(this.scoreSystem.getSecond() > 0 || this.scoreSystem.getFirst() > 0 || this.scoreSystem.getZeroth() > 0){
            this.scoreSystem.setY2(0);
        }

        //Update the background extras
        for (Star s : stars) {
            s.update(goku.getSpeed());
        }

        for(Star b : yellowstars){
            b.update(goku.getSpeed());
        }

        for(int i=0; i<tubeCount; i++){
            enemies[i].update(goku.getSpeed());

            //Checks if the player meets the kiblast
            if(Rect.intersects(goku.getGokuDetect(),enemies[i].getCollisionDetect())){
                kiBlaststorage++;
                score++;
                enemies[i].repositionBlast(tempscreenx, tempscreeny);
                goku.gotKiBlast();
                this.scoreSystem.splitScore(score);
                if(this.scoreSystem.getZeroth() > 0){
                    this.scoreSystem.setY0(0);
                }
                if(this.scoreSystem.getFirst() > 0 || this.scoreSystem.getZeroth() > 0){
                    this.scoreSystem.setY1(0);
                }
                if(this.scoreSystem.getSecond() > 0 || this.scoreSystem.getFirst() > 0 || this.scoreSystem.getZeroth() > 0){
                    this.scoreSystem.setY2(0);
                }
            }

        }
        for (int i = 0; i < saitamaCount; i++){
            saitamas[i].update(goku.getSpeed());

            //Checks if saitama meets kiblast
            if(Rect.intersects(saitamas[i].getCollisionDetect(), enemies[0].getCollisionDetect())){
                enemies[0].repositionBlast(tempscreenx, tempscreeny);
            }
            if(Rect.intersects(saitamas[i].getCollisionDetect(), enemies[1].getCollisionDetect())){
                enemies[1].repositionBlast(tempscreenx, tempscreeny);
            }

            //Checks if goku meets saitama
            if(Rect.intersects(goku.getGokuDetect(), saitamas[i].getCollisionDetect())){

                if(!goku.isFiring()) {
                    onePunch.setX(goku.getX() + goku.getBitmap().getWidth());
                    onePunch.setY(goku.getY());
                    saitamas[i].repositionSaitama(tempscreenx, tempscreeny);
                    playing = false;
                    isGameOver = true;
                    for(int b = 0; b < 3; b++){
                        if(highScore[b]< score){
                            final int finalI = b;
                            highScore[b] = score;
                            break;
                        }
                    }
                    SharedPreferences.Editor e = sharedPreferences.edit();
                    for(int b = 0; b < 3; b++){
                        int a = b + 1;
                        e.putInt("score" + a, highScore[b]);
                    }
                    e.apply();
                }

            }
        }

        for(int i = 0 ; i < kiBlastcount; i++){
            kiBlasts[i].update();

            if(Rect.intersects(kiBlasts[i].getKiDetect(), saitamas[0].getCollisionDetect())){
                if(kiBlasts[i].getReadytogo()){
                    saitamas[0].repositionSaitama(tempscreenx, tempscreeny);
                    score = score + 4;
                    this.scoreSystem.splitScore(score);
                    if(this.scoreSystem.getZeroth() > 0){
                        this.scoreSystem.setY0(0);
                    }
                    if(this.scoreSystem.getFirst() > 0 || this.scoreSystem.getZeroth() > 0){
                        this.scoreSystem.setY1(0);
                    }
                    if(this.scoreSystem.getSecond() > 0 || this.scoreSystem.getFirst() > 0 || this.scoreSystem.getZeroth() > 0){
                        this.scoreSystem.setY2(0);
                    }
                }
            }
            if(Rect.intersects(kiBlasts[i].getKiDetect(), saitamas[1].getCollisionDetect())){
                if(kiBlasts[i].getReadytogo()) {
                    saitamas[1].repositionSaitama(tempscreenx, tempscreeny);
                    score = score + 4;
                    this.scoreSystem.splitScore(score);
                    if(this.scoreSystem.getZeroth() > 0){
                        this.scoreSystem.setY0(0);
                    }
                    if(this.scoreSystem.getFirst() > 0 || this.scoreSystem.getZeroth() > 0){
                        this.scoreSystem.setY1(0);
                    }
                    if(this.scoreSystem.getSecond() > 0 || this.scoreSystem.getFirst() > 0 || this.scoreSystem.getZeroth() > 0){
                        this.scoreSystem.setY2(0);
                    }
                }
            }
            if(Rect.intersects(kiBlasts[i].getKiDetect(), saitamas[2].getCollisionDetect())){
                if(kiBlasts[i].getReadytogo()){
                    saitamas[2].repositionSaitama(tempscreenx, tempscreeny);
                    score = score +4;
                    this.scoreSystem.splitScore(score);
                    if(this.scoreSystem.getZeroth() > 0){
                        this.scoreSystem.setY0(0);
                    }
                    if(this.scoreSystem.getFirst() > 0 || this.scoreSystem.getZeroth() > 0){
                        this.scoreSystem.setY1(0);
                    }
                    if(this.scoreSystem.getSecond() > 0 || this.scoreSystem.getFirst() > 0 || this.scoreSystem.getZeroth() > 0){
                        this.scoreSystem.setY2(0);
                    }
                }
            }
        }
    }

    private void draw(){
        //checking if surface is valid
        if (surfaceHolder.getSurface().isValid()) {

            //locking the canvas
            canvas = surfaceHolder.lockCanvas();

            //drawing a background color for canvas
            canvas.drawColor(Color.BLACK);

            //setting the paint color to white to draw the stars
            paint.setColor(Color.WHITE);

            //drawing all stars
            for (Star s : stars) {
                paint.setStrokeWidth(s.getStarWidth());
                canvas.drawPoint(s.getX(), s.getY(), paint);
            }

            paint.setColor(Color.YELLOW);
            for (Star b : yellowstars){
                paint.setStrokeWidth(b.getStarWidth());
                canvas.drawPoint(b.getX(), b.getY(), paint);
            }

            //Drawing the ground
            canvas.drawBitmap(ground,
                    0,
                    2200,
                    paint);

            //Drawing the player
            canvas.drawBitmap(
                    goku.getBitmap(),
                    goku.getX(),
                    goku.getY(),
                    paint);

            //Draw the kiblasts
            for (int i = 0; i < tubeCount; i++) {
                canvas.drawBitmap(
                        enemies[i].getToptube_image(),
                        enemies[i].getX(),
                        enemies[i].getY(),
                        paint
                );
            }

            //Draw saitamas
            for (int i = 0; i < saitamaCount; i++){
                canvas.drawBitmap(
                        saitamas[i].getSaitama_image(),
                        saitamas[i].getX(),
                        saitamas[i].getY(),
                        paint
                );
            }

            //Draw collision Animation
            canvas.drawBitmap(
                    onePunch.getBitmap(),
                    onePunch.getX(),
                    onePunch.getY(),
                    paint);

            for(int i = 0; i < kiBlastcount; i++){
                canvas.drawBitmap(
                        kiBlasts[i].getKiblast(),
                        kiBlasts[i].getX(),
                        kiBlasts[i].getY(),
                        paint
                );
            }

            //Draw the GameOver sign
            if(isGameOver){
                paint.setTextSize(150);
                paint.setTextAlign(Paint.Align.CENTER);

                int yPos=(int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2));
                canvas.drawText("Game Over",canvas.getWidth()/2,yPos,paint);
            }

            //Draw the Score at the top
            canvas.drawBitmap(scoreSystem.zeroth(), scoreSystem.getX0(), scoreSystem.getY0(), paint);
            canvas.drawBitmap(scoreSystem.first(), scoreSystem.getX1(), scoreSystem.getY1(), paint);
            canvas.drawBitmap(scoreSystem.second(), scoreSystem.getX2(), scoreSystem.getY2(), paint);
            canvas.drawBitmap(scoreSystem.third(), scoreSystem.getX3(), scoreSystem.getY3(), paint);


            //Unlocking the canvas
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control(){
        try {
            gameThread.sleep(7);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        //when the game is paused, set the boolean to false
        playing = false;
        try {
            //stopping the thread
            gameThread.join();
        } catch (InterruptedException e) {
        }
    }

    public void resume() {
        //when the game is resumed, it starts the thread
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        this.mDetector.onTouchEvent(motionEvent);
        this.mDetector.setIsLongpressEnabled(true);
        return true;
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e1.getX() - e2.getX() > -400){
                //Toast.makeText(context, "Swiped right to left", Toast.LENGTH_LONG).show();

            }else{
                //float temp = e1.getX() -  e2.getX();
                //String tempval = Float.toString(temp);
                //Toast.makeText(context, tempval, Toast.LENGTH_LONG).show();
                if(kiBlaststorage >= 2){
                    goku.shootKiBlast();
                    kiBlasts[0].setX(goku.getX() + goku.getBitmap().getWidth() - 10);
                    kiBlasts[0].setY(goku.getY() + 40);
                    kiBlasts[0].setReadytogo(true);
                    kiBlaststorage = kiBlaststorage - 2;
                    score = score - 2;
                }
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            if(!goku.isFiring()){
                goku.isFlying();
            }
            //goku.isFlying();
            if(isGameOver){
                //context.startActivity(new Intent(context, MainActivity.class));
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want to go back to main menu").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                context.startActivity(new Intent(context, MainActivity.class));
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
            return true;
        }
    }
}
