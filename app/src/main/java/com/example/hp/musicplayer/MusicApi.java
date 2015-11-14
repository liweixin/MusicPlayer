package com.example.hp.musicplayer;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by HP on 2015/11/14.
 */
public class MusicApi {
    String url2;
    RequestQueue mQueue;
    Context context;
    private String result = null;
    public MusicApi(){};
    public void setUrl(String name, Context context){
        this.context = context;
        start(name);//zusai
    }
    private void setResult(String result){
        this.result = result;
    }
    public String getUrl(){
        return result;
    }
    private void setSongId(String songId){
        url2 = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.song.getInfos&format=json&songid="
                + songId
                + "&ts=1408284347323&e=JoN56kTXnnbEpd9MVczkYJCSx%2FE1mkLx%2BPMIkTcOEu4%3D&nw=2&ucf=1&res=1";
        Toast.makeText(context, songId, Toast.LENGTH_LONG).show();
        Log.e("SONGID:", songId);
    }
    private void addUrl2(){
        JsonObjectRequest njsonObjectRequest = new JsonObjectRequest(url2, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Result", response.toString());
                        try{
                            JSONObject jsonObject = (JSONObject) response.getJSONObject("songurl").getJSONArray("url").get(0);
                            String downloadUrl = jsonObject.getString("file_link");
                            setResult(downloadUrl);
                            Log.e("TAG", downloadUrl);
                        } catch ( JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.getMessage(), error);
            }
        });
        mQueue.add(njsonObjectRequest);
    }
    private void start(String name){
        try{
            name = URLEncoder.encode(name, "utf-8");
        } catch ( Exception e){
            e.printStackTrace();
        }
        String url1 = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.search.common&format=json&query="
                + name
                + "&page_no=1&page_size=30";
        mQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url1, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Result", response.toString());
                        try{
                            JSONObject object=  (JSONObject) response.getJSONArray("song_list").get(0);
                            Log.e("Object:", object.toString());
                            setSongId(object.getString("song_id"));
                            addUrl2();
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.getMessage(), error);
            }
        });
        mQueue.add(jsonObjectRequest);
    }
}
