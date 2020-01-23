package com.ksnsandaruwangmail.snowcatch.utils;

/**
 * Created by welcome on 1/9/2017.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Locale;

public class DB  {

    private static SQLiteDatabase sqLiteDatabase;

    public static void createDatabase(Context context) {
        sqLiteDatabase = context.openOrCreateDatabase("Game_score", Context.MODE_PRIVATE, null);
        sqLiteDatabase.setVersion(1);
        sqLiteDatabase.setLocale(Locale.getDefault());
        sqLiteDatabase.setLockingEnabled(true);
    }

    public static void createTables(Context contextTable) {
        createDatabase(contextTable);
    }

    //to execute save , update , delete query
    public static void fetchData(String qry) {
        sqLiteDatabase.execSQL(qry);
    }

    public static int searchDataScore() {
        Cursor cr = sqLiteDatabase.rawQuery("SELECT * FROM Game_score", null);
        if (cr.moveToNext()) {
            int id = cr.getInt(0);
            int score = cr.getInt(1);
            return score;
        }
        return 0;
    }


}
