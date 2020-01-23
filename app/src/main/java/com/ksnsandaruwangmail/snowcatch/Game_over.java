package com.ksnsandaruwangmail.snowcatch;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.ksnsandaruwangmail.snowcatch.utils.DB;

public class Game_over extends AppCompatActivity {

    private TextView Retry;
    private TextView exit_gmae;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private TextView Score_view;
    private TextView Score_view_vale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        setToFullScreen();
        getWindow().setBackgroundDrawableResource(R.drawable.background);

        Typeface snowfont_cartoon = Typeface.createFromAsset(getAssets(), "fonts/Cartoon_Blocks_Christmas.otf");
        Typeface snowfont = Typeface.createFromAsset(getAssets(), "fonts/Snowfont.TTF");

        Score_view = (TextView)findViewById(R.id.score_label_Disply_id);
        Score_view_vale = (TextView)findViewById(R.id.score_label_id);
        Score_view.setTypeface(snowfont);
        Score_view_vale.setTypeface(snowfont);


        Retry = (TextView) findViewById(R.id.retry);
        exit_gmae = (TextView) findViewById(R.id.exit);

        Retry.setTypeface(snowfont_cartoon);
        exit_gmae.setTypeface(snowfont_cartoon);

        String v = getIntent().getStringExtra("Value");
        int score_plyer = Integer.parseInt(v);


        DB.createTables(getApplicationContext());

        DB.fetchData("CREATE TABLE IF NOT EXISTS Game_score(id INT,score int)");
     //   DB.fetchData("CREATE TABLE IF NOT EXISTS Game_path(id INT,path TEXT)");

        int i = DB.searchDataScore();

        if (i < score_plyer) {

            Score_view.setText("New High Score");
            Score_view_vale.setText(""+score_plyer);
            DB.fetchData("INSERT INTO Game_score(score) VALUES('" + score_plyer + "')");

//            if(Connection.checkConnection(getApplicationContext())){
//
//                try {
//                    HttpURLConnection ht= DB_connection.getConnection("Save_Score");
//                    ObjectOutputStream out=new ObjectOutputStream(ht.getOutputStream());
//                    ObjectInputStream input=new ObjectInputStream(ht.getInputStream());
////                    out.writeObject();
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//
//            }else {
//
//                DB.fetchData("INSERT INTO Game_path(path) VALUES('" + score_plyer + "')");
//
//            }

        } else {

            Score_view.setText("Score");
            Score_view_vale.setText(""+score_plyer);

        }


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void setToFullScreen() {

        ViewGroup rootLayout = (ViewGroup) findViewById(R.id.activity_game_over);
        rootLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }


    public void exit(View view) {
        finish();
    }

    public void retry(View view) {

        Intent i = new Intent(Game_over.this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Game_over Page") // TODO: Define a title for the content shown.
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

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
