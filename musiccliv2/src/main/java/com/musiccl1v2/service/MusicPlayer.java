package com.musiccl1v2.service;

import java.io.FileInputStream;

import javazoom.jl.player.advanced.AdvancedPlayer;

public class MusicPlayer {

    private AdvancedPlayer player;

    public void playSong(String path) {
        // create player nd play the song
        try (FileInputStream fis = new FileInputStream(path)) {
            player = new AdvancedPlayer(fis);
            player.play();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            player.close();
        }
    }

    public void stopSong() {
        // TODO stop the song
    }
}