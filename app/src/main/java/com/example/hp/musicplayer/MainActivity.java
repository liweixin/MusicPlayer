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
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button play, pause, stop, refresh, next, previous, setMode;
    TextView len;
    MusicScan scan;
    EditText path;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    public ArrayAdapter<SongInfo> adapter;
    public MyDatabaseHelper dbHelper;
    final ArrayList<SongInfo> songList = new ArrayList<SongInfo>();
    PlayControl playControl;

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
                            playControl = new PlayControl(scan.getSongList().size(), PlayControl.TURNS);
                            playMusic(songInfo);
                        }
                    });
            }
        }
    };
    private String changeToTime(int milliseconds){
        milliseconds = milliseconds / 1000;
        int minute = milliseconds / 60;
        int second = milliseconds % 60;
        if(second<10){
            return minute + ":0" + second;
        } else {
            return minute + ":" + second;
        }
    }
    public void playMusic(SongInfo songInfo){
        if(songInfo==null){
            Log.e("In play music:", "Invalid songInfo.");
            return;
        }
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.reset();
        }
        mediaPlayer.reset();
        mediaPlayerInit(songInfo.getPath());
        mediaPlayer.start();
        int duration = mediaPlayer.getDuration();
        Toast.makeText(getApplicationContext(),String.valueOf(duration),Toast.LENGTH_LONG).show();
        len.setText(changeToTime(duration));
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.play:
              /* if(!mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();
                    mediaPlayerInit(path.getText().toString());
                    mediaPlayer.start();
                }*/
                playMusic(songList.get(playControl.getCurrentId()));
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
                searchFilesOnThread();
                break;
            case R.id.previous:
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();
                    mediaPlayerInit(path.getText().toString());
                }
                playMusic(songList.get(playControl.getPreviousSong()));
                break;
            case R.id.next:
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();
                    mediaPlayerInit(path.getText().toString());
                }
                playMusic(songList.get(playControl.getNextSong()));
                break;
            case R.id.set_mode:
                playControl.nextPlayMode();
                switch (playControl.getPlayMode()){
                    case PlayControl.TURNS:
                        setMode.setText("顺序播放");
                        break;
                    case PlayControl.RANDOM:
                        setMode.setText("随机播放");
                        break;
                    case PlayControl.SINGLE:
                        setMode.setText("单曲循环");
                        break;
                }
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
        refresh = (Button) findViewById(R.id.refresh);
        refresh.setOnClickListener(this);
        previous = (Button) findViewById(R.id.previous);
        previous.setOnClickListener(this);
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(this);
        setMode = (Button) findViewById(R.id.set_mode);
        setMode.setOnClickListener(this);

        path = (EditText) findViewById(R.id.path);
        len = (TextView) findViewById(R.id.len);
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
                playMusic(songList.get(playControl.findNextSong()));
            }
        });
        dbHelper = new MyDatabaseHelper(this, "MusicList.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] colomus = new String[] {"path", "fileName"};
        Cursor cursor = db.query("musicList", colomus, null, null, null, null, null);
        if(cursor.getCount()==0/*if database not exist*/){
            cursor.close();
            searchFilesOnThread();
        } else {
            //get listView.
            playControl = new PlayControl(cursor.getCount(), PlayControl.TURNS);
            Toast.makeText(getApplicationContext(), String.valueOf(cursor.getCount()), Toast.LENGTH_LONG).show();
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
                    playControl.setCurrentId(i);
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
