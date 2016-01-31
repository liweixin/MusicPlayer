package com.example.hp.musicplayer.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by lwx on 2016/1/31.
 */
public class Utils {
    static Utils utils;
    Context context = null;

    public static Utils getInstance(){
        if (utils==null){
            utils = new Utils();
        }
        return utils;
    }
    private Utils(){};
    public void init(Context context) {
        this.context = context;
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
}
