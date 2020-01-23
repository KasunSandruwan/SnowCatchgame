package com.ksnsandaruwangmail.snowcatch;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.ksnsandaruwangmail.snowcatch.utils.SoundHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements Snowflower.SnowListner {

    private ViewGroup mContentView;
    private int mScreenWidth, mScreenHeight;

    private static final int MIN_ANIMATION_DELAY = 500;
    private  static final int MAX_ANIMATION_DELAY = 1500;
    private static final int MIN_ANIMATION_DURATION = 1000;
    private static final int MAX_ANIMATION_DURATION = 8000;
    private static final int NUMBER_OF_LIFE=5;


    private int mLevel;
    private boolean mPlaying;
    private List<Snowflower> mSnow = new ArrayList<>();
    private List<ImageView> Life_image=new ArrayList<>();
    private int mScore;
    private int mLife;
    TextView mScoreDisplay, mLevelDisplay;
    private int mSnowspeed;
    private int mSnowPopped;
    private SoundHelper mSoundHelper;
    Handler hand;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private TextView sl;
    private TextView ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToFullScreen();
        Typeface snowfont = Typeface.createFromAsset(getAssets(), "fonts/Snowfont.TTF");

        sl = (TextView) findViewById(R.id.score_label);
        ll = (TextView) findViewById(R.id.level_label);

        sl.setTypeface(snowfont);
        ll.setTypeface(snowfont);


        getWindow().setBackgroundDrawableResource(R.drawable.background);

        mContentView = (ViewGroup) findViewById(R.id.activity_main);

        ViewTreeObserver viewTreeObserver = mContentView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    mContentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    mScreenWidth = mContentView.getWidth();
                    mScreenHeight = mContentView.getHeight();

                }
            });



        }

        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setToFullScreen();
            }
        });

        mScoreDisplay = (TextView) findViewById(R.id.score_display);
        mLevelDisplay = (TextView) findViewById(R.id.level_display);
        Life_image.add((ImageView) findViewById(R.id.Life_1));
        Life_image.add((ImageView) findViewById(R.id.Life_2));
        Life_image.add((ImageView) findViewById(R.id.Life_3));
        Life_image.add((ImageView) findViewById(R.id.Life_4));
        Life_image.add((ImageView) findViewById(R.id.Life_5));

        mScoreDisplay.setTypeface(snowfont);
        mLevelDisplay.setTypeface(snowfont);

        mSoundHelper = new SoundHelper(this);
        mSoundHelper.prepareMusicPlayer(this);

        hand=new Handler();
        Runnable rr = new Runnable() {
            @Override
            public void run() {
                startGame();
            }
        };
        hand.postDelayed(rr,2000);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void setToFullScreen() {

        ViewGroup rootLayout = (ViewGroup) findViewById(R.id.activity_main);
        rootLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setToFullScreen();
    }

    private void startGame() {

        mPlaying = true;
        mScore = 0;
        mLevel = 0;
        startLevel();
        mSoundHelper.playMusic();

    }

    private void startLevel() {

        updateDisplay();
        SnowLauncher launcher = new SnowLauncher();
        launcher.execute(mLevel);
        mSnowPopped = 0;

    }

    @Override
    public void popSnow(Snowflower snowflower, boolean userTuch) {

        mSnowspeed++;
        mContentView.removeView(snowflower);
        mSnow.remove(snowflower);
        if (userTuch) {
            mScore++;
            mSoundHelper.playSound();
            if(mScore % 10 == 0){
                mLevel++;
            }

        }else {

            mLife++;
            if(mLife <=Life_image.size()){

               Life_image.get(Life_image.size()-mLife).setImageResource(R.drawable.life_off);

            }if (mLife == NUMBER_OF_LIFE){

                gameOver(true);
                return;

            }

        }
        updateDisplay();

    }

    private void gameOver(boolean b) {

        for (Snowflower snowflower : mSnow){
                mContentView.removeView(snowflower);
                snowflower.setPopped(true);
        }
            mSnow.clear();
            mPlaying =false;
        mSoundHelper.pauseMusic();

        //........................................................................................./

        Intent over=new Intent(MainActivity.this,Game_over.class);
        over.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        over.putExtra("Value",mScoreDisplay.getText());
        startActivity(over);


    }


    private void updateDisplay() {

        mScoreDisplay.setText(String.valueOf(mScore));
        mLevelDisplay.setText(String.valueOf(mLevel));


    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        mPlaying =true;
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        mPlaying =false;
        mSoundHelper.pauseMusic();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPlaying =false;
        mSoundHelper.pauseMusic();

    }

    private class SnowLauncher extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected Void doInBackground(Integer... params) {


            if (params.length != 1) {

                throw new AssertionError(
                        "Expected 1 param for current level");

            }


            int level = params[0];

            int maxDelay = Math.max(MIN_ANIMATION_DELAY,
                    (MAX_ANIMATION_DELAY - ((level - 1) * 500)));
            int minDelay = maxDelay / 2;

            while (mPlaying) {


//              Get a random horizontal position for the next Snowflower............................
                Random random = new Random(new Date().getTime());
                int xPosition = random.nextInt(mScreenWidth - 200);
                publishProgress(xPosition);


//              Wait a random number of milliseconds before looping.................................
                int delay = random.nextInt(minDelay) + minDelay;
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

             return null;

        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            super.onProgressUpdate(values);
            int xPosition = values[0];
            launchSnow(xPosition);

        }

    }

    private void launchSnow(int x) {

        Snowflower snowflower = new Snowflower(this, 70);
        mSnow.add(snowflower);

//      Snow vertical position and dimensions, add to container.....................................
        snowflower.setX(x);
        snowflower.setY(mScreenHeight + snowflower.getHeight());
        mContentView.addView(snowflower);

//..................................................................................................
        int duration = Math.max(MIN_ANIMATION_DURATION, MAX_ANIMATION_DURATION - (mLevel * 1000));
        snowflower.rainSnow(mScreenHeight, duration);

    }

}
