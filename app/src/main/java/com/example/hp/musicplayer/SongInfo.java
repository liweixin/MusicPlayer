package com.example.hp.musicplayer;

import android.util.Log;

import java.io.File;

/**
 * Created by HP on 2015/11/3.
 */
public class SongInfo {
    private int id;
    private String path;
    private String filename;
    private String artist;
    public SongInfo(){}
    public SongInfo(String path, String filename){
        this.path = path;
        this.filename = filename;
    }
    public static boolean isSong(File file){
        String st = file.getName();
        if(st.length()<4) return false;
        return (st.lastIndexOf(".mp3")==st.length()-4);
    }
    public String getFilename(){
        return filename;
    }
    @Override
    public String toString(){
        return path + ":" + filename + "\n";
    }
}
