package com.musiccl1v2;

import java.io.FileInputStream;

import javazoom.jl.player.advanced.AdvancedPlayer;

public class MusicPlayer {

    private AdvancedPlayer player;
    private Thread playerThread;
    private volatile boolean isPlaying = false;

    public void playSong(String path) {
        if (isPlaying) {
            stopSong();
        }
        isPlaying = true;

        playerThread = new Thread(() -> {
            try (FileInputStream fis = new FileInputStream(path)) {
                player = new AdvancedPlayer(fis);
                player.play();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                isPlaying = false;
            }
        });
        playerThread.start();
    }

    public void stopSong() {
        if (player != null) {
            player.close();
            isPlaying = false;
        }
    }
}