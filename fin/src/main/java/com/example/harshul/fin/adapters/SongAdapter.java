package com.example.harshul.fin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.harshul.fin.activities.MainActivity;
import com.example.harshul.fin.R;
import com.example.harshul.fin.model.Song;

import java.util.List;

/**
 * Created by Harshul on 8/11/2015.
 */
public class SongAdapter extends ArrayAdapter<Song>{

    private List<Song> songList;
    private Context context;

    public SongAdapter(List<Song> songList, Context context) {
        super(context, R.layout.single_listview_item, songList);
        this.songList = songList;
        this.context = context;
    }

    private static class SongHolder {
        public TextView title;
        public TextView artist;
        public CheckBox chkBox;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        SongHolder holder = new SongHolder();

        if(convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.single_listview_item, null);

            holder.title = (TextView) v.findViewById(R.id.title);
            holder.artist = (TextView) v.findViewById(R.id.artist);
            holder.chkBox = (CheckBox) v.findViewById(R.id.chk_box);

            holder.chkBox.setOnCheckedChangeListener((MainActivity) context);

        } else {
            holder = (SongHolder) v.getTag();
        }

        Song s = songList.get(position);
        if(holder == null){

        }
        else {
            holder.title.setText(s.getTitle());
            holder.artist.setText("" + s.getArtist());
            holder.chkBox.setChecked(s.isSelected());
            holder.chkBox.setTag(s);
        }

        return v;
    }
}


