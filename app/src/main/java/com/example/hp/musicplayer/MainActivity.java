package com.example.hp.musicplayer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.musicplayer.logic.MusicApi;
import com.example.hp.musicplayer.Utils.MyDatabaseHelper;
import com.example.hp.musicplayer.Utils.Utils;
import com.example.hp.musicplayer.datastructure.SongInfo;
import com.example.hp.musicplayer.fragment.FirstFragment;
import com.example.hp.musicplayer.fragment.SecondFragment;
import com.example.hp.musicplayer.fragment.ThirdFragment;
import com.example.hp.musicplayer.logic.MusicScan;
import com.example.hp.musicplayer.logic.PlayControl;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button tab1, tab2, tab3;
    private Button setMode;
    TextView len;
    public MusicScan scan;
    EditText path;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    public MyDatabaseHelper dbHelper;
    public ArrayList<SongInfo> songList = new ArrayList<SongInfo>();
    public PlayControl playControl;

    MusicApi musicApi;

    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch (msg.what) {
                case 1:
                    Log.e("Create ListView:", ">.<");
            }
        }
    };
    public void playSongName(){
        String url = musicApi.getUrl();
        Utils.getInstance().toast("歌曲网址：" + url);
        Log.e("URL:", url);
        mediaPlayer.reset();
        mediaPlayerInit(url);
        mediaPlayer.start();
    }
    public void setSongName(String s, ThirdFragment fragment){
        musicApi = new MusicApi();
        musicApi.setUrl(s, getApplicationContext(), fragment);
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
        Utils.getInstance().toast("歌曲长度：" + String.valueOf(Utils.getInstance().changeToTime(duration)));
        len.setText(changeToTime(duration));
    }
    public void playOnlineMusic(String path) {
        if (path==null){
            Log.e("In play online music:", "Invalid path.");
            return;
        }
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.reset();
        }
        mediaPlayer.reset();
        mediaPlayerInit(path);
        mediaPlayer.start();
        int duration = mediaPlayer.getDuration();
        Utils.getInstance().toast("歌曲长度：" + String.valueOf(Utils.getInstance().changeToTime(duration)));
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab1:
                setTabSelection(TAB_1);
                break;
            case R.id.tab2:
                setTabSelection(TAB_2);
                break;
            case R.id.tab3:
                setTabSelection(TAB_3);
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
                    initSongList();
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
        playControl = new PlayControl(0, PlayControl.TURNS);
        Utils.getInstance().init(this, this);
        buttonInit();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Utils.getInstance().toast("播放完毕");
                playMusic(songList.get(playControl.findNextSong()));
            }
        });
        initSongList();
    }

    private void initSongList() {
        dbHelper = new MyDatabaseHelper(this, "MusicList.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] colomus = new String[] {"path", "fileName"};
        Cursor cursor = db.query("musicList", colomus, null, null, null, null, null);
        if(cursor.getCount()==0){
            cursor.close();
            searchFilesOnThread();
        } else {
            //get listView.
            playControl.setSize(cursor.getCount());
            Log.e("歌曲总数：", String.valueOf(cursor.getCount()));
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
            if (secondFragment!=null) {
                ((SecondFragment)secondFragment).setAdapter();
            }
        }
    }

    private void mediaPlayerInit(String path){
        try{
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

    public static final int TAB_1 = 1;
    public static final int TAB_2 = 2;
    public static final int TAB_3 = 3;
    int position;
    Fragment firstFragment, secondFragment, thirdFragment;
    private void setTabSelection(int position) {
        //记录position
        this.position = position;
        //更改底部导航栏按钮状态
        //changeButtonStatus(position);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragment(transaction);
        switch (position) {
            case TAB_1:
                tab1.setSelected(true);
                tab2.setSelected(false);
                tab3.setSelected(false);
                if (firstFragment == null) {
                    firstFragment = new FirstFragment();
                    transaction.add(R.id.fragment_layout, firstFragment);
                } else {
                    transaction.show(firstFragment);
                }
                break;
            case TAB_2:
                tab1.setSelected(false);
                tab2.setSelected(true);
                tab3.setSelected(false);
                if (secondFragment == null) {
                    secondFragment = new SecondFragment();
                    transaction.add(R.id.fragment_layout, secondFragment);
                } else {
                    transaction.show(secondFragment);
                }
                break;
            case TAB_3:
                tab1.setSelected(false);
                tab2.setSelected(false);
                tab3.setSelected(true);
                if (thirdFragment == null) {
                    thirdFragment = new ThirdFragment();
                    transaction.add(R.id.fragment_layout, thirdFragment);
                } else {
                    transaction.show(thirdFragment);
                }
                break;
        }
        transaction.commitAllowingStateLoss();
    }
    private void hideFragment(FragmentTransaction transaction) {
        if(firstFragment!=null) {
            transaction.hide(firstFragment);
        }
        if(secondFragment!=null) {
            transaction.hide(secondFragment);
        }
        if(thirdFragment!=null) {
            transaction.hide(thirdFragment);
        }
    }
}
