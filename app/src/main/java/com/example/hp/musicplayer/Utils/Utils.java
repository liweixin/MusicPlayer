package com.example.hp.musicplayer.Utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.example.hp.musicplayer.API.Search;
import com.example.hp.musicplayer.MainActivity;
import com.example.hp.musicplayer.datastructure.SongInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lwx on 2016/1/31.
 */
public class Utils {
    static Utils utils;
    Context context = null;
    MainActivity mainActivity;

    public static Utils getInstance(){
        if (utils==null){
            utils = new Utils();
        }
        return utils;
    }
    private Utils(){};
    public void init(Context context, MainActivity activity) {
        this.context = context;
        mainActivity = activity;
    }
    public void toast(String info) {
        if(context==null){
            throw new NullPointerException("Utils class should be init in mainactivity.");
        } else {
            Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
        }
    }
    public void toast(String info, int time) {
        if(context==null){
            throw new NullPointerException("Utils class should be init in mainactivity.");
        } else {
            Toast.makeText(context, info, time).show();
        }
    }
    public static List<SongInfo> getSongListFromSearch(Search searchList) {
        List<Search.SongEntity> songEntityList = searchList.getSong();
        int len = songEntityList.size();
        List<SongInfo> result = new ArrayList<>();
        for(int i=0; i<len; i++) {
            Search.SongEntity entity = songEntityList.get(i);
            result.add(new SongInfo(null, entity.getSongname(), entity.getArtistname()));
        }
        return result;
    }
    public String changeToTime(int milliseconds){
        milliseconds = milliseconds / 1000;
        int minute = milliseconds / 60;
        int second = milliseconds % 60;
        if(second<10){
            return minute + ":0" + second;
        } else {
            return minute + ":" + second;
        }
    }
    public MainActivity getMainActivity() {
        return mainActivity;
    }
    public Context getContext() {
        return context;
    }
}
