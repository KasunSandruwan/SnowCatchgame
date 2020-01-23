package com.ksnsandaruwangmail.snowcatch;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Manu extends AppCompatActivity {

    private TextView startgame;
    private TextView howto;
    private TextView about_game;
    private TextView exit_gmae;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manu);
        setToFullScreen();
        getWindow().setBackgroundDrawableResource(R.drawable.background);
        Typeface snowfont_cartoon = Typeface.createFromAsset(getAssets(), "fonts/Cartoon_Blocks_Christmas.otf");


        startgame = (TextView) findViewById(R.id.Start_game);
        howto = (TextView) findViewById(R.id.h_Play);
        about_game = (TextView) findViewById(R.id.ab);
        exit_gmae = (TextView) findViewById(R.id.exit);

        startgame.setTypeface(snowfont_cartoon);
        howto.setTypeface(snowfont_cartoon);
        about_game.setTypeface(snowfont_cartoon);
        exit_gmae.setTypeface(snowfont_cartoon);






    }

    private void setToFullScreen() {

        ViewGroup rootLayout = (ViewGroup) findViewById(R.id.activity_manu);
        rootLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }


    public void Start(View view) {

        Intent start=new Intent(Manu.this,MainActivity.class);
        startActivity(start);

    }

    public void How_to_play(View view) {

        Intent hp=new Intent(Manu.this,How_to_play.class);
        startActivity(hp);


    }

    public void about(View view) {

        Intent ab=new Intent(Manu.this,About.class);
        startActivity(ab);

    }

    public void exit(View view) {

        finish();

    }
}
