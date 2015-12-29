package com.example.harshul.fin.threads;

import android.util.Log;

import com.example.harshul.fin.model.Recomendation;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Harshul on 12/29/2015.
 */
public class DownloadThread extends Thread {

    public String artist;
    public ArrayList<Recomendation> recommenderList;

    public DownloadThread(String artist) {
        this.artist = artist;
        recommenderList = new ArrayList<Recomendation>();
    }

    @Override
    public void run() {
        Log.d("download", artist);

        try {
            Document d = Jsoup.connect("https://www.tastekid.com/music/like/" + artist).get();


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
                Log.d("download", "indiv: " + individualArtists[i]);
                //Log.d("files",individualRatings[i]);
            }
        } catch (IOException e1) {
            Log.d("error","IOException in DownloadThread");
            e1.printStackTrace();
        }
    }

}

