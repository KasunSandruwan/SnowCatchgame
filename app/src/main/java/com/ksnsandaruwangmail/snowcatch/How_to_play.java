package com.ksnsandaruwangmail.snowcatch;

import android.animation.AnimatorSet;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class How_to_play extends AppCompatActivity {

    private String how_to_play_text;
    private ImageView newimage;
    private AnimatorSet.Builder Ion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);
        setToFullScreen();

        newimage=(ImageView)findViewById(R.id.imageView);
      // Ion.with(newimage).load( R.drawable.snow_catcher);

        howtoplaytext();





    }

    private void howtoplaytext() {

        how_to_play_text="Snowflakes will dropped on certain speed according to the level.Player must tap on dropping flakes to collect points.Collecting 10 points will cause level up.\n";

        TextView text= (TextView) findViewById(R.id.textView_how_to_play);
        text.setText(how_to_play_text);

    }

    private void setToFullScreen() {

        ViewGroup rootLayout = (ViewGroup) findViewById(R.id.activity_how_to_play);
        rootLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }

}
