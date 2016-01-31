package com.example.hp.musicplayer.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.musicplayer.R;
import com.example.hp.musicplayer.Utils.Utils;
import com.example.hp.musicplayer.datastructure.SongInfo;

import java.util.List;

/**
 * Created by lwx on 2016/1/31.
 */
public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder> implements View.OnClickListener {

    private SearchListOnItemClickListener mOnItemClickListener = null;
    public void setmOnItemClickListener(SearchListOnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }

    public List<SongInfo> datas;
    public SearchListAdapter(List<SongInfo> datas){
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        view.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SongInfo songInfo = datas.get(position);
        holder.filename.setText(songInfo.getFilename());
        holder.artist.setText(songInfo.getArtist());
        holder.itemView.setTag(songInfo);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onClick(View v){
        if (mOnItemClickListener!=null) {
            mOnItemClickListener.onItemClick(v, (SongInfo) v.getTag());
        }
    }

    public void addItem(int position, SongInfo item){
        datas.add(position, item);
        notifyItemInserted(position);
    }

    public void removeItem(SongInfo item){
        int position = datas.indexOf(item);
        if(position>=0) {
            datas.remove(position);
            notifyItemRemoved(position);
        } else {
            Utils.getInstance().toast("列表中不存在" + item.getFilename() +  "了哟" + "(´・∀・｀)", Toast.LENGTH_SHORT);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView filename;
        public TextView artist;
        public ViewHolder (View view){
            super(view);
            filename = (TextView) view.findViewById(R.id.filename);
            artist = (TextView) view.findViewById(R.id.artist);
        }
    }
}