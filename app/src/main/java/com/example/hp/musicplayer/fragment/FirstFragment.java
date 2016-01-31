package com.example.hp.musicplayer.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hp.musicplayer.MainActivity;
import com.example.hp.musicplayer.R;

/**
 * Created by HP on 2015/11/13.
 */
public class FirstFragment extends Fragment implements View.OnClickListener {
    private View view;
    private MainActivity mainActivity; //mainActivity在此处初始化会引起错误 null pointer exception
    private Button play, pause, stop, refresh, next, previous;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        view = inflater.inflate(R.layout.first_fragment, container, false);
        return view;
    }
    @Override
    public void onStart(){
        super.onStart();
        mainActivity = (MainActivity) getActivity();
        buttonInit();
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.play:
                mainActivity.play();
                break;
            case R.id.pause:
                mainActivity.pause();
                break;
            case R.id.stop:
                mainActivity.stop();
                break;
            case R.id.refresh:
                mainActivity.refresh();
                break;
            case R.id.previous:
                mainActivity.previous();
                break;
            case R.id.next:
                mainActivity.next();
                break;
            case R.id.set_mode:
                mainActivity.setMode();
                break;
        }
    }
    private void buttonInit() {
        play = (Button) view.findViewById(R.id.play);
        play.setOnClickListener(this);
        pause = (Button) view.findViewById(R.id.pause);
        pause.setOnClickListener(this);
        stop = (Button) view.findViewById(R.id.stop);
        stop.setOnClickListener(this);
        refresh = (Button) view.findViewById(R.id.refresh);
        refresh.setOnClickListener(this);
        previous = (Button) view.findViewById(R.id.previous);
        previous.setOnClickListener(this);
        next = (Button) view.findViewById(R.id.next);
        next.setOnClickListener(this);
    }
}
