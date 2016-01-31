package com.example.hp.musicplayer.logic;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * Created by HP on 2015/11/10.
 */
public class PlayControl {
    public final static int TURNS = 0;
    public final static int RANDOM = 1;
    public final static int SINGLE = 2;
    private int playMode = 0;
    private int currentId = 0;
    private int size = 0;
    public final static int sumOfMode = 3;
    public Random random = new Random();
    Stack<Integer> randomNumber = new Stack<Integer>();
    public PlayControl( int size){
        this.size = size;
    }
    public PlayControl( int size, int playMode){
        this.size = size;
        this.playMode = playMode;
    }
    public PlayControl( int size, int playMode,int currentId){
        this.size = size;
        this.playMode = playMode;
        this.currentId = currentId;
    }
    public int getNextSong(){
        switch (playMode) {
            case TURNS:
                ++currentId;
                if(currentId==size){
                    currentId = 0;
                }
                break;
            case RANDOM:
                randomNumber.push(currentId);
                currentId =  random.nextInt(size);
                break;
            case SINGLE:
                break;
            default:
                Log.e("In class PlayControl", "Invalid case.");
        }
        return currentId;
    }
    public int getPreviousSong(){
        switch (playMode) {
            case TURNS:
                --currentId;
                if(currentId==-1){
                    currentId = size + currentId;
                }
                break;
            case RANDOM:
                if(!randomNumber.isEmpty()){
                    currentId = randomNumber.pop();
                }
                break;
            case SINGLE:
                --currentId;
                if(currentId==-1){
                    currentId = size + currentId;
                }
                break;
            default:
                Log.e("In class PlayControl", "Invalid case.");
        }
        return currentId;
    }
    public int findNextSong(){
        switch (playMode) {
            case TURNS:
                ++currentId;
                if(currentId==size){
                    currentId = 0;
                }
                break;
            case RANDOM:
                randomNumber.push(currentId);
                currentId =  random.nextInt(size);
                break;
            case SINGLE:
                ++currentId;
                if(currentId==size){
                    currentId = 0;
                }
                break;
            default:
                Log.e("In class PlayControl", "Invalid case.");
        }
        return currentId;
    }
    public int getPlayMode(){
        return playMode;
    }
    public void setPlayMode(int mode){
        playMode = mode;
    }
    public int getCurrentId(){
        return currentId;
    }
    public void setCurrentId(int id){
        currentId = id;
    }
    public void nextPlayMode(){
        ++playMode;
        if(playMode==sumOfMode) {
            playMode = 0;
        }
    }
}
