package com.example.hp.musicplayer.datastructure;

import java.io.File;

/**
 * Created by HP on 2015/11/3.
 */
public class Lyric {
    private int id;
    private String path;
    private String filename;
    private String artist;
    public Lyric(){}
    public Lyric(String path, String filename){
        this.path = path;
        this.filename = filename;
    }
    public static boolean isLyric(File file){
        String st = file.getName();
        if(st.length()<4) return false;
        return (st.lastIndexOf(".lrc")==st.length()-4);
    }
    @Override
    public String toString(){
        return path + ":" + filename + "\n";
    }
}
