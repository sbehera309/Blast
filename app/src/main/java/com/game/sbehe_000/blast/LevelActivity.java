package com.game.sbehe_000.blast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

/**
 * Created by sbehe_000 on 10/10/2017.
 */

public class LevelActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton buttonEasy;
    private ImageButton buttonMedium;
    private ImageButton buttonHard;
    Animation leftoright, righttoleft, downtoup;
    public int level_mode; //Use level_mode as a reference to determine the level in Game_Activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_test);

        leftoright = AnimationUtils.loadAnimation(this, R.anim.lefttoright);
        righttoleft = AnimationUtils.loadAnimation(this,R.anim.righttoleft);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);

        //getting the button
        buttonEasy = (ImageButton) findViewById(R.id.buttonEasy);
        buttonEasy.setAnimation(leftoright);
        buttonMedium = (ImageButton) findViewById(R.id.buttonMedium);
        buttonMedium.setAnimation(downtoup);
        buttonHard = (ImageButton) findViewById(R.id.buttonHard);
        buttonHard.setAnimation(righttoleft);

        //adding a click listener
        buttonEasy.setOnClickListener(this);
        buttonMedium.setOnClickListener(this);
        buttonHard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //Set the level based on the button the user clicks and start the Game class.
        if ( v == buttonEasy){
            level_mode = 1;
            Intent intent = new Intent(LevelActivity.this, GameActivity.class);
            intent.putExtra("levelnum", 1);
            startActivity(intent);
        }
        if( v == buttonMedium){
            level_mode = 2;
            Intent intent =  new Intent(LevelActivity.this, GameActivity.class);
            intent.putExtra("levelnum", 2);
            startActivity(intent);
        }
        if(v == buttonHard){
            level_mode = 3;
            Intent intent = new Intent(LevelActivity.this,GameActivity.class);
            intent.putExtra("levelnum", 3);
            startActivity(intent);
        }
    }
}
