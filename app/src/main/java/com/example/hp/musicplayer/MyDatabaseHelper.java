package com.example.hp.musicplayer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by HP on 2015/11/5.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {
    public final static String CREAT_MUSIC_LIST = "create table musicList("
            + "path string primary key,"
            + "fileName string)";
    private Context mcontext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int version){
        super(context, name, cursorFactory, version);
        mcontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREAT_MUSIC_LIST);
       // Toast.makeText(mcontext, "Database created successfully.", Toast.LENGTH_SHORT).show();
        //refresh ui failed.
        // Can't create handler inside thread that has not called Looper.prepare()
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
