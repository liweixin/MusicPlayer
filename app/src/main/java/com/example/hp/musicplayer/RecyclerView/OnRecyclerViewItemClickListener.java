package com.example.hp.musicplayer.RecyclerView;

import android.view.View;

import com.example.hp.musicplayer.SongInfo;

/**
 * Created by lwx on 2016/1/31.
 */
public interface OnRecyclerViewItemClickListener {
    void onItemClick(View view, SongInfo songInfo);
}
