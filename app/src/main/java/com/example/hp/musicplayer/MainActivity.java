package com.example.hp.musicplayer;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button play, pause, stop, refresh;
    MusicScan scan;
    EditText path;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    public ArrayAdapter<SongInfo> adapter;

    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch (msg.what) {
                case 1:
                    Log.e("Create ListView:", ">.<");
                    ArrayAdapter<SongInfo> adapter = new ArrayAdapter<SongInfo>(MainActivity.this, android.R.layout.simple_list_item_1, scan.getSongList());
                    ListView musicList = (ListView) findViewById(R.id.music_list);
                    musicList.setAdapter(adapter);
            }
        }
    };

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.play:
                if(!mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();
                    mediaPlayerInit();
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
                    mediaPlayerInit();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonInit();
        mediaPlayerInit();
        new Thread(new Runnable() {
            @Override
            public void run() {
                scan = new MusicScan();
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

    private void mediaPlayerInit(){
        try{
           // File file = new File(Environment.getExternalStorageDirectory(), "music.mp3");
            mediaPlayer.setDataSource(path.getText().toString());
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
