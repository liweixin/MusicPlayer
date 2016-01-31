package com.example.hp.musicplayer.fragment;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.musicplayer.API.Search;
import com.example.hp.musicplayer.MainActivity;
import com.example.hp.musicplayer.R;
import com.example.hp.musicplayer.RecyclerView.LocalListAdapter;
import com.example.hp.musicplayer.RecyclerView.LocalListDecoration;
import com.example.hp.musicplayer.RecyclerView.LocalListOnItemClickListener;
import com.example.hp.musicplayer.RecyclerView.SearchListAdapter;
import com.example.hp.musicplayer.RecyclerView.SearchListDecoration;
import com.example.hp.musicplayer.RecyclerView.SearchListOnItemClickListener;
import com.example.hp.musicplayer.Utils.Utils;
import com.example.hp.musicplayer.datastructure.SongInfo;
import com.example.hp.musicplayer.logic.MusicApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 2015/11/13.
 */
public class ThirdFragment extends Fragment implements View.OnClickListener{
    private View view;
    private EditText searchContent;
    private MainActivity mainActivity = (MainActivity) getActivity();
    private List<SongInfo> mSongList;
    private MusicApi mcallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        view = inflater.inflate(R.layout.third_fragment, container, false);
        return view;
    }
    @Override
    public void onStart(){
        super.onStart();
        mainActivity = (MainActivity) getActivity();
        initView();
        initRecyclerView(new ArrayList<SongInfo>());
        Log.e("onStart", "onStart");
    }
    private void initView() {
        searchContent = (EditText) view.findViewById(R.id.search_content);
        Button search = (Button) view.findViewById(R.id.search);
        search.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.search:
                mainActivity.setSongName(searchContent.getText().toString(), this);
                break;
        }
    }
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    SearchListAdapter adapter;
    public void initRecyclerView(final List<SongInfo> songList) {
        mSongList = songList;
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_search);
        if(recyclerView==null)
            Log.e("recyclerView", "" + (recyclerView == null));
        layoutManager = new LinearLayoutManager(mainActivity);
        //layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter = new SearchListAdapter(songList));
        adapter.setmOnItemClickListener(new SearchListOnItemClickListener() {
            @Override
            public void onItemClick(View view, SongInfo item) {
                Utils.getInstance().toast(item.getFilename() + " pressed.", Toast.LENGTH_SHORT);
                int i = songList.indexOf(item);
                mcallback.playOnlineMusic(i);
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SearchListDecoration(getActivity(), SearchListDecoration.VERTICAL_LIST));
    }
    public void setMusicApiCallBack(MusicApi callBack) {
        mcallback = callBack;
        for(int i=0; i<mSongList.size(); i++) {
            adapter.addItem(i, mSongList.get(i));
        }
    }
    public void updateAdapter(List<SongInfo> songList) {
        mSongList = songList;
    }
}
