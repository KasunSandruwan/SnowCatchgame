package com.ksnsandaruwangmail.snowcatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class About extends AppCompatActivity {

    private String about_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setToFullScreen();
        aboutText();

    }

    private void aboutText() {

        about_text="Version 1.0\n" +
                "Date published :2017-01-12" +
                "Website:??\n" +
                "Game Description\n" +
                "Snow Catcher is a game where the player's objective is to tap on the white snowflake.\n" +
                "Missing FOUR snowflakes will cause to game over.Each level will up when the player collect TEN snowflake while increasing the speed dropping snow.\n" +
                "\n" +
                "Developer\n" +
                "Kasun Sadaruwan is young undergradute researcher who is very keen on creating games and implemeting new algorithums on games.\n" +
                "Contact:+94770310025\n" +
                "Email:ksnsandaruwan@gmail.com\n";

       TextView text= (TextView) findViewById(R.id.textView_about);
        text.setText(about_text);



    }

    private void setToFullScreen() {

        ViewGroup rootLayout = (ViewGroup) findViewById(R.id.activity_about);
        rootLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }



}
