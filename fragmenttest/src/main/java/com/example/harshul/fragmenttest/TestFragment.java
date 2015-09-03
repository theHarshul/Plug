package com.example.harshul.fragmenttest;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by Harshul on 8/18/2015.
 */
public class TestFragment extends android.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        View view = inflater.inflate(R.layout.toolbar_fragment, container, false);
        Button btnLoad = (Button) view.findViewById(R.id.button1);

        Log.d("files", "here");




        Thread downloadThread = new Thread() {
            public void run() {
                Document doc;
                try {
                    doc = Jsoup.connect("https://www.tastekid.com/music/like/Macklemore").get();

                    String text = doc.text();
                    String startChecker = "I recommend";
                    String endChecker = "More People who like";
                    String artists;
                    String ratings;
                    int start, end;
                    start = text.indexOf(startChecker) + 11;
                    end = text.indexOf(endChecker);
                    String unParsedArtists = text.substring(start,end);
                    unParsedArtists = unParsedArtists.replace("Music",",");
                    artists = unParsedArtists.replaceAll("[\\d.]", "");
                    ratings = unParsedArtists.replaceAll("[^,0-9]","");
                    ratings = ratings.substring(1);
                    Log.d("Files",artists);
                    Log.d("Files",ratings);



                    //Log.d("files",text);
                } catch (IOException e) {
                    Log.d("files","failure");
                    e.printStackTrace();
                }
            }
        };
        downloadThread.start();

        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                TestTwo fragment = new TestTwo();
                fragmentManager.beginTransaction().replace(R.id.fragment,fragment).commit();
            }
        };



        /*
        Document doc = null;

        try {
            doc = Jsoup.connect("http://example.com/").get();
        }catch(Exception e){
                Log.d("Files","error");
        }

        String title = doc.title();
        Log.d("Files",title);*/





        btnLoad.setOnClickListener(listener);
        return view;
    }
}
