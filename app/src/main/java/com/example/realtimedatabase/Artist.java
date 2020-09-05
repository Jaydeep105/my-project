package com.example.realtimedatabase;

public class Artist {
    String artistId;
    String artistName;
    String artistGenre;
    public Artist(String id, String name, String genre){

    }

    public Artist(String artistId, String artistName, String artistGenre ,String artistname) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.artistGenre = artistGenre;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getArtistGenre() {
        return artistGenre;
    }
}
