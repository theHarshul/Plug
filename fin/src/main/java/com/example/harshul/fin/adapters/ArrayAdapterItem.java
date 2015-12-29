package com.example.harshul.fin.adapters;

/**
 * Created by Harshul on 8/30/2015.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.harshul.fin.R;

import java.util.List;

import com.example.harshul.fin.model.Recomendation;

// here's our beautiful adapter
public class ArrayAdapterItem extends ArrayAdapter<Recomendation> {

    Context mContext;
    int layoutResourceId;
    List<Recomendation> songList;

    public ArrayAdapterItem(List<Recomendation> songList, Context context) {
        super(context, R.layout.list_view_row_item, songList);
        this.songList = songList;
        this.mContext = context;
    }

    private static class SongHolder {
        public TextView title;
        public TextView artist;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        SongHolder holder = new SongHolder();

        if(convertView == null) {

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_view_row_item, null);

            holder.title = (TextView) v.findViewById(R.id.recTitle);
            holder.artist = (TextView) v.findViewById(R.id.recArtist);



        } else {
            holder = (SongHolder) v.getTag();
        }

        Recomendation r = songList.get(position);
        if(holder == null){

        }
        else {
            holder.title.setText("" + r.getArtist());
            holder.artist.setText("" + r.getRating());
        }

        return v;
    }

}
