package com.game.sbehe_000.blast;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.game.sbehe_000.blast.Extra.MusicService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout l2;
    private ImageButton buttonPlay, buttonEasy, buttonMedium, buttonHard;
    private TextView buttonHighScore;
    Animation up_to_down, left_to_right, right_to_left,down_to_up;
    public int level_mode;
    ViewGroup transitionContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //getting the button

        transitionContainer = (ViewGroup) findViewById(R.id.transitions_container);
        buttonPlay = (ImageButton) transitionContainer.findViewById(R.id.buttonPlay);
        buttonEasy = (ImageButton) transitionContainer.findViewById(R.id.buttonEasy);
        buttonMedium = (ImageButton) transitionContainer.findViewById(R.id.buttonMedium);
        buttonHard = (ImageButton) transitionContainer.findViewById(R.id.buttonHard);
        buttonHighScore = (TextView) findViewById(R.id.buttonhighscore);

        //adding a click listener
        buttonPlay.setOnClickListener(this);
        buttonEasy.setOnClickListener(this);
        buttonMedium.setOnClickListener(this);
        buttonHard.setOnClickListener(this);
        buttonHighScore.setOnClickListener(this);

        //setting Animations
        up_to_down = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        down_to_up = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        left_to_right = AnimationUtils.loadAnimation(this, R.anim.lefttoright);
        right_to_left = AnimationUtils.loadAnimation(this, R.anim.righttoleft);

        //getting layout
        l2 = (LinearLayout) findViewById(R.id.l2);

        //Doing the animation
        l2.setAnimation(down_to_up);
        buttonPlay.setAnimation(down_to_up);
        buttonHighScore.setAnimation(left_to_right);


    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(startMain);
                        finish();
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

    @Override
    public void onClick(View v) {
/*
        switch(v.getId()){
            case R.id.network_connection:
                System.out.println("check the connection");
                startActivity(new Intent(this, Network_Script.class));
                break;
            case R.id.button_sound:
                if(playing_sound){
                    //mServ.pauseMusic();
                    System.out.println("sound is suposed to be paused");
                }else{
                    //mServ.resumeMusic();
                    System.out.println("sound is suposed to be continue");
                }
                break;
            default:
                break;

        } */
        //starting game activity
        //startActivity(new Intent(this, LevelActivity.class));

        if(v == buttonPlay){

            TransitionManager.beginDelayedTransition(transitionContainer);
            slideButtonsDown(buttonPlay);
            //buttonEasy.clearAnimation();
            buttonEasy.setVisibility(View.VISIBLE);
            buttonEasy.setAnimation(left_to_right);
            buttonEasy.setClickable(true);
            buttonMedium.setVisibility(View.VISIBLE);
            buttonMedium.setAnimation(down_to_up);
            buttonMedium.setClickable(true);
            buttonHard.setVisibility(View.VISIBLE);
            buttonHard.setAnimation(right_to_left);
            buttonHard.setClickable(true);
        }
        if(v == buttonHighScore){
            Intent intent = new Intent(MainActivity.this, HighScoreActivity.class);
            startActivity(intent);
        }

        if(v == buttonEasy){
            level_mode = 1;
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            intent.putExtra("levelnum", 1);
            startActivity(intent);
        }

        if(v == buttonMedium){
            level_mode = 2;
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            intent.putExtra("levelnum", 2);
            startActivity(intent);
        }

        if(v == buttonHard){
            level_mode = 3;
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            intent.putExtra("levelnum", 3);
            startActivity(intent);
        }


    }

    private void slideButtonsDown(ImageButton button){
        button.setAnimation(down_to_up);
        button.setClickable(false);
        button.setVisibility(View.GONE);
    }
}
