package com.example.hp.musicplayer.logic;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.hp.musicplayer.Utils.MyDatabaseHelper;
import com.example.hp.musicplayer.datastructure.Lyric;
import com.example.hp.musicplayer.datastructure.SongInfo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by HP on 2015/11/3.
 */
public class MusicScan{
    private final String root = "sdcard/";
    //private final String root_extra1 = "/mnt/sdcard/extra_sd";
    private Queue<File> directoryQueue; //遍历的文件队列
    private List<String> songDirList;  //歌曲目录列表

    private boolean finished = false;
    private List<SongInfo> songList;  //歌曲列表
    private List<Lyric> lyricList;    //歌词列表
    private String lyricPath;
    private float leastSize = 200.0f;   //音乐文件最低大小 单位KB
    private boolean isCompleted = false;
    private MyDatabaseHelper dbHelper;


    public MusicScan(MyDatabaseHelper dbHelper){
        this.dbHelper = dbHelper;
        directoryQueue = new LinkedList<File>();
        songDirList = new ArrayList<String>();
        songList = new ArrayList<SongInfo>();
        lyricList = new ArrayList<Lyric>();
    }

    public void scan(String root) throws IOException {
        if(songList == null){
            songList = new ArrayList<SongInfo>();
        }else {
            songList.clear();
        }
        if(lyricList == null){
            lyricList = new ArrayList<Lyric>();
        }else {
            lyricList.clear();
        }
        //ReadMp3 read = new ReadMp3();  //解析歌曲文件对象 不需要解析TAG信息的话 就没必要这个对象
        File file=new File(root);    //根目录

        //file = Environment.getExternalStorageDirectory();
        directoryQueue.clear();      //清空队列
        directoryQueue.offer(file);  //入队列
        SongInfo si;

        while(!directoryQueue.isEmpty()){

            file = directoryQueue.poll();  //出队列
            boolean isSongDir = false;    //标识变量 标识当前文件是否直接为含歌曲目录
            File[] fileList=file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                //System.out.println("当前文件 "+i+" 路径:" + fileList[i].getAbsolutePath());
/*              if(!fileList[i].exists()){
                    System.out.println(fileList[i].getName()+" 不存在!");
                    return null;
                }*/
                if(fileList[i].isFile()){
                    //是文件
                    /*if(fileList[i].length()<leastSize*1024){
                        //小于最低大小
                        continue;
                    }*/
                    if(SongInfo.isSong(fileList[i])){
                        //是歌曲文件
                        if(!isSongDir){
                            //当前状态为 非直接含歌曲目录
                            //避免重复添加同一目录
                            String songDir = new String(fileList[i].getParent());
                            songDirList.add(songDir);
                            isSongDir = true;
                        }

                        if(!fileList[i].canRead()){ //////////////////这一句很重要，不然真机上面很多文件不能访问，会出现空指针情况
                            //非可读文件
                            continue;
                        }
                        si = new SongInfo(fileList[i].getAbsolutePath(), fileList[i].getName());
                        songList.add(si);  //添加进行歌曲列表songList
                        Log.e("SongList", fileList[i].getAbsolutePath() + "\n" + fileList[i].getName());
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put("path", si.getPath());
                        values.put("fileName", si.getFilename());
                        db.insert("musicList", null, values);

                    }else if (Lyric.isLyric(fileList[i])) {
                        //是歌词文件
                        System.out.println("是歌词文件" + fileList[i].getName());
                        Lyric lyric = new Lyric(fileList[i].getPath(),fileList[i].getName());
                        lyricList.add(lyric);
                    }
                }else //fileList[i].isDirectory() == true
                {
                //是目录
                //System.out.println(fileList[i].getName()+"是目录");
                    if(!fileList[i].canRead()) {
                        //非可读文件
                        continue;
                    }
                    directoryQueue.offer(fileList[i]);
                }
            }
        }
        Log.e("Scan finished!", "hahaha");
        finished = true;
    }
    public void scan() throws IOException{
        scan(root);
    }
    public List<SongInfo> getSongList(){
        return songList;
    }
}
