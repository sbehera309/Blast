package com.game.sbehe_000.blast;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by sbehe_000 on 10/16/2017.
 */

public class HighScoreActivity extends AppCompatActivity implements View.OnClickListener{

    TextView first, second, third, total_games, total_score, total_hits, total_blasts, average_score, average_blasts, average_hits;

    int int_total_game, int_total_score, int_total_hits, int_total_blasts;

    int int_average_score, int_average_hits, int_average_blasts;

    ImageButton menubtn;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        first = (TextView) findViewById(R.id.first);
        second = (TextView) findViewById(R.id.second);
        third = (TextView) findViewById(R.id.third);
        total_games = (TextView) findViewById(R.id.total_games);
        total_score = (TextView) findViewById(R.id.total_score);
        total_blasts = (TextView) findViewById(R.id.total_ki_blasts);
        total_hits = (TextView) findViewById(R.id.total_enemy);
        average_score = (TextView) findViewById(R.id.average_score);
        average_blasts = (TextView) findViewById(R.id.average_blasts);
        average_hits = (TextView) findViewById(R.id.average_hit);
        menubtn = (ImageButton) findViewById(R.id.high_menu_button);


        sharedPreferences = getSharedPreferences("SHAR_PREF_NAME", Context.MODE_PRIVATE);

        int_total_game = sharedPreferences.getInt("total_game", 0);
        int_total_score = sharedPreferences.getInt("total_score",0);
        int_total_blasts = sharedPreferences.getInt("total_blast",0);
        int_total_hits = sharedPreferences.getInt("total_enemy",0);

        //Calculating the Averages
        if(int_total_game != 0){
            int_average_score = int_total_score / int_total_game;
            int_average_blasts = int_total_blasts / int_total_game;
            int_average_hits = int_total_hits / int_total_game;
        } else{
            int_average_score = 0;
            int_average_blasts = 0;
            int_average_hits = 0;

        }

        //Setting High Scores
        first.setText(Integer.toString(sharedPreferences.getInt("score1",0)));
        second.setText(Integer.toString(sharedPreferences.getInt("score2",0)));
        third.setText(Integer.toString(sharedPreferences.getInt("score3",0)));

        //Setting Total Scores
        total_score.setText(Integer.toString(int_total_score));
        total_games.setText(Integer.toString(int_total_game));
        total_blasts.setText(Integer.toString(int_total_blasts));
        total_hits.setText(Integer.toString(int_total_hits));

        //Setting Average Scores
        average_score.setText(Integer.toString(int_average_score));
        average_hits.setText(Integer.toString(int_average_hits));
        average_blasts.setText(Integer.toString(int_average_blasts));

        menubtn.setOnClickListener(this);

    }@Override
    public void onClick(View v) {

        if(v == menubtn){
            Intent intent = new Intent(HighScoreActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

}
