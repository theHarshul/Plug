package com.example.harshul.fin.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.harshul.fin.R;
import com.example.harshul.fin.adapters.ArrayAdapterItem;
import com.example.harshul.fin.model.Recomendation;
import com.example.harshul.fin.model.Song;
import com.example.harshul.fin.threads.DownloadThread;

import java.util.ArrayList;

/**
 * Created by Harshul on 8/20/2015.
 */
public class RecommenderFragment extends Fragment {

    ArrayList<Song> songList = new ArrayList<Song>();
    ArrayList<String> selectedArtists = new ArrayList<String>();
    ListView lv;
    ArrayAdapterItem adap;
    private Context c;
    String artists;
    String ratings;
    ArrayList<Recomendation> recommenderList;



    public RecommenderFragment(){
        recommenderList =  new ArrayList<Recomendation>();
    }

    @SuppressLint("ValidFragment") //Got to fix soon
    public RecommenderFragment(ArrayList<Song> songs, Context c){
        songList = songs;
        this.c = c;

        recommenderList = new ArrayList<Recomendation>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/

        View view = inflater.inflate(R.layout.fragment_recommendations, container, false);
        lv = (ListView) view.findViewById(R.id.songRecomendations);

        for(Song s: songList){
            if(s.isSelected()) selectedArtists.add(s.getArtist());

        }

        for(String str: selectedArtists){
            try {
                DownloadThread dt = new DownloadThread(str);
                dt.start();
                dt.join(); //wait for dt to finish
                recommenderList.addAll(dt.recommenderList); //add the reccomendations from each individual song

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        adap = new ArrayAdapterItem(recommenderList,c);
        lv.setAdapter(adap);

        //Log.d("files",selectedArtists.get(0));


        //DownloadThread dThread = new DownloadThread();
        //dThread.start();

        /*

        new Thread()  {
            public void run() {
                //Looper.prepare();
                ArrayList<Song> songReccomendations;
                final ArrayList<Recomendation> recommenderList;
                ArrayList<Document> documentList;
                Document doc1 = null;
                Document doc2;





                    /*
                    doc1 = Jsoup.connect("https://www.tastekid.com/music/like/Macklemore").get();

                    String text = doc1.text();
                    String startChecker = "I recommend";
                    String endChecker = "More People who like";
                    recommenderList = new ArrayList<Recomendation>();


                    int start, end;
                    start = text.indexOf(startChecker) + 11;
                    end = text.indexOf(endChecker);
                    String unParsedArtists = text.substring(start, end);
                    unParsedArtists = unParsedArtists.replace("Music", ",");
                    String artists = unParsedArtists.replaceAll("[\\d.]", "");
                    String ratings = unParsedArtists.replaceAll("[^,0-9]", "");
                    ratings = ratings.substring(1);
                    artists = artists.substring(0, artists.length() - 3);
                    //Log.d("Files", artists);
                    //Log.d("Files",ratings);

                    String[] individualArtists = artists.split(",");
                    String[] individualRatings = ratings.split(",");

                    for (int i = 0; i < individualArtists.length; ++i) {
                        Recomendation rec = new Recomendation(individualArtists[i].trim(), individualRatings[i].trim());
                        recommenderList.add(rec);
                        Log.d("files", recommenderList.get(i).getArtist());
                        //Log.d("files", individualArtists[i]);
                        //Log.d("files",individualRatings[i]);
                    } */

        /*

                    getActivity().runOnUiThread( new Runnable()
                    {

                        public void run()
                        {
                            //Log.d("Files","here");
                            ArrayList<Document> documentList = new ArrayList<Document>();
                            ArrayList<Recomendation>recommenderList = new ArrayList<Recomendation>();
                            /*

                            try {
                                Document d = Jsoup.connect("https://www.tastekid.com/music/like/Macklemore").get();
                                Log.d("files","worked");
                            } catch (IOException e) {
                                Log.d("files","failure");
                                e.printStackTrace();
                            }*/



/*

                            try{
                            for(String artist: selectedArtists){
                                Document d = Jsoup.connect("https://www.tastekid.com/music/like/" + artist).get();
                                documentList.add(d);
                                Log.d("files",artist);
                            }}catch (Exception e){
                                Log.d("files", "failure");
                                e.printStackTrace();
                            }

                            //doc1 = Jsoup.connect("https://www.tastekid.com/music/like/Macklemore").get();

                            for(Document d: documentList) {
                                String text = d.text();
                                String startChecker = "I recommend";
                                String endChecker = "More People who like";
                                int start, end;
                                start = text.indexOf(startChecker) + 11;
                                end = text.indexOf(endChecker);
                                String unParsedArtists = text.substring(start, end);
                                unParsedArtists = unParsedArtists.replace("Music", ",");
                                String artists = unParsedArtists.replaceAll("[\\d.]", "");
                                String ratings = unParsedArtists.replaceAll("[^,0-9]", "");
                                ratings = ratings.substring(1);
                                artists = artists.substring(0, artists.length() - 3);
                                //Log.d("Files", artists);
                                //Log.d("Files",ratings);

                                String[] individualArtists = artists.split(",");
                                String[] individualRatings = ratings.split(",");

                                for (int i = 0; i < individualArtists.length; ++i) {
                                    Recomendation rec = new Recomendation(individualArtists[i].trim(), individualRatings[i].trim());
                                    recommenderList.add(rec);
                                    //Log.d("files", recommenderList.get(i).getArtist());
                                    //Log.d("files", individualArtists[i]);
                                    //Log.d("files",individualRatings[i]);
                                }
                             }

                            for(Recomendation r: recommenderList){
                                Log.d("files",r.getArtist());

                            }



                            adap = new ArrayAdapterItem(recommenderList,c);
                            lv.setAdapter(adap);
                        }
                    });

            }
        }.start(); */








        //};



        //Log.d("files",recommenderList.get(0).getArtist());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Set title
        getActivity().setTitle("Curated Playlist");
    }
}
