package com.example.hp.musicplayer;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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

    private Button tab1, tab2, tab3;
    private Button setMode;
    TextView len;
    MusicScan scan;
    EditText path;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    public ArrayAdapter<SongInfo> adapter;
    public MyDatabaseHelper dbHelper;
    final ArrayList<SongInfo> songList = new ArrayList<SongInfo>();
    PlayControl playControl;

    MusicApi musicApi;

    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch (msg.what) {
                case 1:
                    Log.e("Create ListView:", ">.<");
                    /*ArrayAdapter<SongInfo> adapter = new ArrayAdapter<SongInfo>(MainActivity.this, android.R.layout.simple_list_item_1, scan.getSongList());
                    ListView musicList = (ListView) findViewById(R.id.music_list);
                    musicList.setAdapter(adapter);
                    musicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            SongInfo songInfo = scan.getSongList().get(i);
                            playControl = new PlayControl(scan.getSongList().size(), PlayControl.TURNS);
                            playMusic(songInfo);
                        }
                    });*/
                    //setListView();
            }
        }
    };
    public void playSongName(){
        String url = musicApi.getUrl();
        Toast.makeText(getApplicationContext(), url, Toast.LENGTH_LONG).show();
        Log.e("URL:",url);
        mediaPlayer.reset();
        mediaPlayerInit(url);
        mediaPlayer.start();
    }
    public void setSongName(String s){
        musicApi = new MusicApi();
        musicApi.setUrl(s, getApplicationContext());
    }
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

    public void play(){
        playMusic(songList.get(playControl.getCurrentId()));
    }
    public void pause(){
        if(mediaPlayer.isPlaying())
            mediaPlayer.pause();
    }
    public void stop(){
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.reset();
            mediaPlayerInit(path.getText().toString());
        }
    }
    public void refresh(){
        searchFilesOnThread();
    }
    public void previous(){
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.reset();
            mediaPlayerInit(path.getText().toString());
        }
        playMusic(songList.get(playControl.getPreviousSong()));
    }
    public void next(){
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.reset();
            mediaPlayerInit(path.getText().toString());
        }
        playMusic(songList.get(playControl.getNextSong()));
    }
    public void setMode(){
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
    }
    @Override
    public void onClick(View view){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (view.getId()){
            case R.id.tab1:
                FirstFragment firstFragment = new FirstFragment();
                transaction.replace(R.id.fragment_layout, firstFragment);
                transaction.commit();
                Toast.makeText(getApplicationContext(), "Tab1 pressed", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tab2:
                SecondFragment secondFragment = new SecondFragment();
                transaction.replace(R.id.fragment_layout, secondFragment);
                transaction.commit();
                Toast.makeText(getApplicationContext(), "Tab2 pressed", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tab3:
                ThirdFragment thirdFragment = new ThirdFragment();
                transaction.replace(R.id.fragment_layout, thirdFragment);
                transaction.commit();
                Toast.makeText(getApplicationContext(), "Tab3 pressed", Toast.LENGTH_SHORT).show();
                break;
            case R.id.set_mode:
                setMode();
                break;
        }
    }

    private void buttonInit(){
        tab1 = (Button) findViewById(R.id.tab1);
        tab1.setOnClickListener(this);
        tab2 = (Button) findViewById(R.id.tab2);
        tab2.setOnClickListener(this);
        tab3 = (Button) findViewById(R.id.tab3);
        tab3.setOnClickListener(this);
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
            //setListView();
        }
    }

    public void setListView(){
        ArrayAdapter<SongInfo> adapter = new ArrayAdapter<SongInfo>(this, android.R.layout.simple_list_item_1, songList);
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
    public void onDestroy(){
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
