package com.example.harshul.fin.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.example.harshul.fin.R;
import com.example.harshul.fin.adapters.SongAdapter;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.util.ArrayList;

import com.example.harshul.fin.model.Song;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends android.app.Fragment implements android.widget.CompoundButton.OnCheckedChangeListener {

    public ListView lv;
    public ArrayList<Song> songList;
    public SongAdapter sAdapter;
    private Context c;

    public SongAdapter getsAdapter() {
        return sAdapter;
    }

    public void setsAdapter(SongAdapter sAdapter) {
        this.sAdapter = sAdapter;
    }

    public ListView getLv() {
        return lv;
    }

    public void setLv(ListView lv) {
        this.lv = lv;
    }

    public ArrayList<Song> getSongList() {

        return songList;
    }

    public void setSongList(ArrayList<Song> songList) {
        this.songList = songList;
    }



    public MainActivityFragment(){

    }

    @SuppressLint("ValidFragment") //Got to fix soon
    public MainActivityFragment(Context c){
        this.c = c;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        lv = (ListView) view.findViewById(R.id.listview);
        displayList();

        sAdapter = new SongAdapter(songList, c);
        lv.setAdapter(sAdapter);

        return view;
    }

    public void displayList() {


        songList = new ArrayList<Song>();
        FileDescriptor fd = null;
        FileInputStream fis = null;
        String audioPath = null;

        String path = Environment.getExternalStorageDirectory().toString()+"/music";
        File f = new File(path);
        File file[] = f.listFiles();
        for (int i=0; i < file.length; i++)
        {
            if(file[i].getName().endsWith(".mp3"))
            {
                MediaMetadataRetriever mmr = new MediaMetadataRetriever();
                String src = file[i].getAbsolutePath();
                Log.d("Files",src);
                mmr.setDataSource(src);
                String songArtist = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
                String songTitle = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
                if(songArtist == null) songArtist = "Unknown";
                if(songTitle == null) songTitle = file[i].getName();
                songList.add(new Song(songTitle,songArtist,src));
            }

        }


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        int pos = lv.getPositionForView(buttonView);
        if (pos != ListView.INVALID_POSITION) {
            Song s = songList.get(pos);
            s.setSelected(isChecked);
            /*Toast.makeText(
                    c,
                    "Clicked on : " + s.getTitle() + ". State: is "
                            + isChecked, Toast.LENGTH_SHORT).show();*/
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_plug:
                /*
                for(Song s: songList) {

                    if (s.isSelected())
                        Log.d("Files", s.getTitle());
                }*/


                FragmentManager fragmentManager = getFragmentManager();
                Fragment fragment = new RecommenderFragment(songList,c);
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

                break;

            default:
                break;
        }
        return true;
    }
}
