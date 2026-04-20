package com.musiccl1v2.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.musiccl1v2.core.Song;
import com.musiccl1v2.core.exception.LoadSongException;
import com.musiccl1v2.core.exception.MetadataExtractionException;

public class MusicLibrary {
    private static final String FOLDER_NAME = "canciones"; // ! 'canciones' must exist
    private static final String MP3_EXTENSION = ".mp3"; // this is in a variable cuzz its ez to change
    private final List<Song> songs = new ArrayList<>();
    private final Map<Integer, Song> songIndexByPosition = new HashMap<>();
    private final MusicPlayer musicPlayer;

    public MusicLibrary() {
        this.musicPlayer = new MusicPlayer();
    }

    // ! Testing
    // TODO: Handle the exception properly
    public void loadSongs() {
        // Clear the List
        songs.clear();
        // Clear the hashmap
        songIndexByPosition.clear();
        // Create the folder
        File folder = new File(FOLDER_NAME);
        if (!folder.exists() || !folder.isDirectory()) {
            return;
        }
        // Extract the songs from the folder to a file array
        File[] archives = folder.listFiles();

        try {
            if (archives != null) {
                for (File archive : archives) {
                    // if is mp3 and a file add to songs
                    if (archive.isFile() && archive.getName().toLowerCase().endsWith(MP3_EXTENSION)) {
                        Song song = new Song(archive);
                        songs.add(song);
                        songIndexByPosition.put(song.getPosition(), song);
                    }
                }
            }
        } catch (MetadataExtractionException e) {
            System.err.println("[ERROR] Failed to load song at potential position: " + (songs.size()));
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
}