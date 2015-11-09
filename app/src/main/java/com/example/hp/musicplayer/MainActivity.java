package com.example.hp.musicplayer;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button play, pause, stop, refresh;
    MusicScan scan;
    EditText path;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    public ArrayAdapter<SongInfo> adapter;
    public MyDatabaseHelper dbHelper;
    int currentId;
    final ArrayList<SongInfo> songList = new ArrayList<SongInfo>();

    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch (msg.what) {
                case 1:
                    Log.e("Create ListView:", ">.<");
                    ArrayAdapter<SongInfo> adapter = new ArrayAdapter<SongInfo>(MainActivity.this, android.R.layout.simple_list_item_1, scan.getSongList());
                    ListView musicList = (ListView) findViewById(R.id.music_list);
                    musicList.setAdapter(adapter);
                    musicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            SongInfo songInfo = scan.getSongList().get(i);
                            playMusic(songInfo);
                        }
                    });
            }
        }
    };

    public void playMusic(SongInfo songInfo){
        if(songInfo==null){
            Log.e("In play music:", "Invalid songInfo.");
            return;
        }
        if(!mediaPlayer.isPlaying()) {
            mediaPlayer.reset();
            mediaPlayerInit(songInfo.getPath());
            mediaPlayer.start();
            Toast.makeText(getApplicationContext(),String.valueOf(mediaPlayer.getDuration()),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.play:
                if(!mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();
                    mediaPlayerInit(path.getText().toString());
                    mediaPlayer.start();
                }
                break;
            case R.id.pause:
                if(mediaPlayer.isPlaying())
                    mediaPlayer.pause();
                break;
            case R.id.stop:
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();
                    mediaPlayerInit(path.getText().toString());
                }
                break;
            case R.id.refresh:
                break;
        }
    }

    private void buttonInit(){
        play = (Button) findViewById(R.id.play);
        play.setOnClickListener(this);
        pause = (Button) findViewById(R.id.pause);
        pause.setOnClickListener(this);
        stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(this);
        path = (EditText) findViewById(R.id.path);
    }

    private void searchFilesOnThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                scan = new MusicScan(dbHelper);
                try{
                    scan.scan();
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonInit();
        mediaPlayerInit(path.getText().toString());
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(getApplicationContext(), "播放完毕", Toast.LENGTH_LONG).show();
                playMusic(songList.get(++currentId));
            }
        });
        dbHelper = new MyDatabaseHelper(this, "MusicList.db", null, 1);
        if(1==2/*if database not exist*/){
            searchFilesOnThread();
        } else {
            //get listView.
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            String[] colomus = new String[] {"path", "fileName"};
            Cursor cursor = db.query("musicList", colomus, null, null, null, null, null);
            SongInfo songInfo;
            if (cursor.moveToFirst()){
                do{
                    String path = cursor.getString(cursor.getColumnIndex("path"));
                    String fileName = cursor.getString(cursor.getColumnIndex("fileName"));
                    songInfo = new SongInfo(path, fileName);
                    songList.add(songInfo);
                } while(cursor.moveToNext());
            }
            cursor.close();
            ArrayAdapter<SongInfo> adapter = new ArrayAdapter<SongInfo>(MainActivity.this, android.R.layout.simple_list_item_1, songList);
            ListView musicList = (ListView) findViewById(R.id.music_list);
            musicList.setAdapter(adapter);
            musicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    currentId = i;
                    SongInfo songInfo = songList.get(i);  //should get songInfo List in the same place, or errors may occur.
                    playMusic(songInfo);
                }
            });
        }
    }

    private void mediaPlayerInit(String path){
        try{
           // File file = new File(Environment.getExternalStorageDirectory(), "music.mp3");
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
