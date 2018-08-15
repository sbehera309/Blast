package com.game.sbehe_000.blast.Extra;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.game.sbehe_000.blast.GameActivity;
import com.game.sbehe_000.blast.MainActivity;
import com.game.sbehe_000.blast.R;


public class CustomDialogClass extends Dialog implements android.view.View.OnClickListener {
    public int game_level;
    boolean visibileNew = false;
    ImageButton playbtn, menubtn;
    TextView new_sign, high_score_text,high_score, score_text, score;
    Typeface font;
    String num_score, num_high_score;

    public CustomDialogClass(Context context, int level, int score_num, int high_score_num, boolean shownew) {
        super(context);
        game_level = level;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if(score_num > high_score_num){
            visibileNew = true;
        }
        num_score = Integer.toString(score_num);
        num_high_score = Integer.toString(high_score_num);
        visibileNew = shownew;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_dialog);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //Setting variables to their respective parts
        playbtn = (ImageButton)findViewById(R.id.try_again_button);
        menubtn = (ImageButton)findViewById(R.id.menu_button);
        new_sign = (TextView) findViewById(R.id.new_sign);
        high_score_text = (TextView) findViewById(R.id.high_score_text);
        high_score = (TextView) findViewById(R.id.high_score);
        score_text = (TextView) findViewById(R.id.score_text);
        score = (TextView) findViewById(R.id.score);

        //Set Text for the scores
        high_score.setText(num_high_score);
        score.setText(num_score);

        //Showing New or not
        if(visibileNew){
            new_sign.setVisibility(View.VISIBLE);
        }

        //Setting onClick on Button
        playbtn.setOnClickListener(this);
        menubtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == playbtn){
            Intent intent = new Intent(getContext(),GameActivity.class);
            intent.putExtra("levelnum", game_level);
            getContext().startActivity(intent);
        }
        if(v == menubtn){
            Intent intent = new Intent(getContext(), MainActivity.class);
            getContext().startActivity(intent);
        }
    }
}
