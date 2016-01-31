package com.example.hp.musicplayer.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hp.musicplayer.MainActivity;
import com.example.hp.musicplayer.R;
import com.example.hp.musicplayer.RecyclerView.LocalListAdapter;;
import com.example.hp.musicplayer.RecyclerView.LocalListOnItemClickListener;
import com.example.hp.musicplayer.Utils.Utils;
import com.example.hp.musicplayer.datastructure.SongInfo;

import java.util.List;

/**
 * Created by HP on 2015/11/13.
 */
public class SecondFragment extends Fragment {
    private View view;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    LocalListAdapter adapter;
    List<SongInfo> songList;
    MainActivity mainActivity = Utils.getInstance().getMainActivity();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        view = inflater.inflate(R.layout.second_fragment, container, false);
        return view;
    }
    @Override
    public void onStart(){
        super.onStart();
        initRecyclerView();
    }
    public void initRecyclerView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);;
        layoutManager = new LinearLayoutManager(Utils.getInstance().getContext());
        //layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        setAdapter();
    }
    public void setAdapter() {
        if(mainActivity.scan==null) {
            songList = mainActivity.songList;
        } else {
            songList = mainActivity.scan.getSongList();
        }
        recyclerView.setAdapter(adapter = new LocalListAdapter(songList));
        adapter.setmOnItemClickListener(new LocalListOnItemClickListener() {
            @Override
            public void onItemClick(View view, SongInfo item) {
                Utils.getInstance().toast(item.getFilename() + " pressed.", Toast.LENGTH_SHORT);
                int i = songList.indexOf(item);
                mainActivity.playControl.setCurrentId(i); //should get songInfo List in the same place, or errors may occur.
                mainActivity.playMusic(item);
            }
        });
    }
}
