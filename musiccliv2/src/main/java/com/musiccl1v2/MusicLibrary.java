package com.musiccl1v2;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javazoom.jl.player.advanced.AdvancedPlayer;

public class MusicLibrary {
    private static final String FOLDER_NAME = "canciones";
    private final List<Song> songs = new ArrayList<>();

    public void loadSongs() {
        // Clear the arraylist
        songs.clear();
        // Create the folder
        File folder = new File(FOLDER_NAME);
        // Extract the songs from the folder to a file array
        File[] archivos = folder.listFiles();

        if (archivos != null) {
            for (File archivo : archivos) {
                // if is mp3 and a file add to songs
                if (archivo.isFile() && archivo.getName().toLowerCase().endsWith(".mp3")) {
                    songs.add(new Song(archivo));
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
        for (Song c : songs) {
            if (c.getPosition() == pos) {
                return c;
            }
        }
        return null;
    }

    // understand later
    private AdvancedPlayer player;
    private Thread playerThread;

    public void playSong(String path) {
        playerThread = new Thread(() -> {
            try (FileInputStream fis = new FileInputStream(path)) {
                player = new AdvancedPlayer(fis);
                player.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        playerThread.start();
    }

    public void stopSong() {
        if (player != null) {
            player.close();
        }
    }
}