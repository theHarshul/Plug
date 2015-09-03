package com.example.harshul.fin;

import java.util.UUID;

/**
 * Created by Harshul on 8/11/2015.
 */
public class Song {
    private String title, artist, path;
    private boolean selected = false;
    private UUID id;

    public Song(String title, String artist,String path){
        super();
        this.title = title;
        this.artist = artist;
        this.path = path;
        id = UUID.randomUUID();

    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }



}
