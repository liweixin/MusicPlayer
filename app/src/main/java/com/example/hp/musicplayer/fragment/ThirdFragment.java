package com.example.hp.musicplayer.fragment;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.hp.musicplayer.MainActivity;
import com.example.hp.musicplayer.R;

/**
 * Created by HP on 2015/11/13.
 */
public class ThirdFragment extends Fragment implements View.OnClickListener{
    private View view;
    private EditText url;
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
        url = (EditText) view.findViewById(R.id.url);
        Button start = (Button) view.findViewById(R.id.url_start);
        start.setOnClickListener(this);
        Button set = (Button) view.findViewById(R.id.url_set);
        set.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.url_start:
                mainActivity.playSongName();
                break;
            case R.id.url_set:
                mainActivity.setSongName(url.getText().toString());
                break;
        }
    }
}
