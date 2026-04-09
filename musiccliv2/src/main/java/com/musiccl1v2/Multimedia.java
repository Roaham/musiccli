package com.musiccl1v2;

public abstract class Multimedia {

    private static int idCounter = 0;

    // attributes
    private String path = DEFAULT_PATH;
    private String title = DEFAULT_TITLE;
    private String artist = DEFAULT_ARTIST;
    private String album = DEFAULT_ALBUM;
    private String genre = DEFAULT_GENRE;
    private int durationSeconds = DEFAULT_DURATION;
    private final int position;

    // default attributes
    private static final String DEFAULT_PATH = "Unknown";
    private static final String DEFAULT_TITLE = "Unknown";
    private static final String DEFAULT_ARTIST = "Unknown";
    private static final String DEFAULT_ALBUM = "Unknown";
    private static final String DEFAULT_GENRE = "Unknown";
    private static final int DEFAULT_DURATION = 0;

    public Multimedia() {
        // default builder
        this.position = idCounter++;
    }

    public Multimedia(String path, String title, String artist, String album, String genre, int durationSeconds) {
        this.position = idCounter++; // the position attribute functions as an id
        this.path = path;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.durationSeconds = durationSeconds;
    }

    // getters and setters
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(int durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public int getPosition() {
        return position;
    }
}