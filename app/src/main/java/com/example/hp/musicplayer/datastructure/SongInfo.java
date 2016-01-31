package com.example.hp.musicplayer.datastructure;

import android.util.Log;

import com.example.hp.musicplayer.constants.Constants;

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
        int len = st.length();
        if (len<4) {
            return false;
        } else {
            String fileNameExtation = st.substring(st.length()-3);
            return Constants.IS_SONG.contains(fileNameExtation);
        }
    }
    public String getFilename(){
        return filename;
    }
    @Override
    public String toString(){
        return path + ":" + filename + "\n";
    }

    public String getPath() { return path; }

    public String getArtist() {
        if (artist==null) {
            return "null";
        } else {
            return artist;
        }
    }
}
