package com.game.sbehe_000.blast;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.game.sbehe_000.blast.Extra.MusicService;

/**
 * Created by sbehe_000 on 10/10/2017.
 */

public class GameActivity extends AppCompatActivity implements View.OnClickListener{
    private GameView gameView;
    private int level;
    private boolean isGamePaused;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        FrameLayout game = new FrameLayout(this);

        startService(new Intent(this, MusicService.class));

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        level = getIntent().getExtras().getInt("levelnum");

        gameView = new GameView(this, size.x, size.y, level);


        //Adding pause button to the game programmatically instead of XML
        LinearLayout gameWidgets = new LinearLayout(this);

        TextView pauseButton = new TextView(this);
        pauseButton.setWidth(200);
        pauseButton.setGravity(Gravity.CENTER);
        pauseButton.setPadding(2, 2, 2, 2);
        pauseButton.setBackground(this.getResources().getDrawable(R.drawable.blankbutton));
        pauseButton.setText("Pause");
        pauseButton.setVisibility(View.INVISIBLE);

        gameWidgets.addView(pauseButton);
        gameWidgets.setGravity(Gravity.RIGHT);
        gameWidgets.setPadding(2,2,2,2);

        game.addView(gameView);
        game.addView(gameWidgets);

        isGamePaused = false;

        //setContentView(gameView); can also be used if there is no need for a button
        setContentView(game);
        pauseButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(isGamePaused){
            gameView.resume();
            startService(new Intent(this, MusicService.class));
        }else{
            stopService(new Intent(this, MusicService.class));
            gameView.pause();
            isGamePaused = true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
        stopService(new Intent(this, MusicService.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
        startService(new Intent(this,MusicService.class));
    }
    @Override
    public void onBackPressed() {
        gameView.pause();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        gameView.resume();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        stopService(new Intent(this, MusicService.class));
    }
}
