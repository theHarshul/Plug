package com.example.harshul.fin;

/**
 * Created by Harshul on 8/30/2015.
 */
public class Recomendation {
    private String artist,rating;

    public Recomendation(String artist, String rating) {
        this.artist = artist;
        this.rating = rating;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
