package com.example.hp.musicplayer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by HP on 2015/11/13.
 */
public class ThirdFragment extends Fragment {
    private View view;
    private MainActivity mainActivity = (MainActivity) getActivity();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        view = inflater.inflate(R.layout.third_fragment, container, false);
        return view;
    }
    @Override
    public void onStart(){
        super.onStart();
        mainActivity = (MainActivity) getActivity();
    }
}
