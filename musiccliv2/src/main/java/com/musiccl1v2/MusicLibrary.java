package com.musiccl1v2;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MusicLibrary {
    private static final String FOLDER_NAME = "canciones";
    private static final String MP3_EXTENSION = ".mp3";
    private final List<Song> songs = new ArrayList<>();
    private final Map<Integer, Song> songIndexByPosition = new HashMap<>();
    private final MusicPlayer musicPlayer;

    public MusicLibrary() {
        this.musicPlayer = new MusicPlayer();
    }

    public void loadSongs() {
        // Clear the arraylist
        songs.clear();
        // Clear the hashmap
        songIndexByPosition.clear();
        // Create the folder
        File folder = new File(FOLDER_NAME);
        // Extract the songs from the folder to a file array
        File[] archivos = folder.listFiles();

        if (archivos != null) {
            for (File archivo : archivos) {
                // if is mp3 and a file add to songs
                if (archivo.isFile() && archivo.getName().toLowerCase().endsWith(MP3_EXTENSION)) {
                    Song song = new Song(archivo);
                    songs.add(song);
                    songIndexByPosition.put(song.getPosition(), song);
                }
            }
        }
    }

    // getter
    public List<Song> getList() {
        return songs;
    }

    // find by position (id)
    public Song findByPosition(int pos) {
        return songIndexByPosition.get(pos);
    }

    public void playSong(String path) {
        musicPlayer.playSong(path);
    }

    public void stopSong() {
        musicPlayer.stopSong();
    }
}