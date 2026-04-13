package com.musiccl1v2.core;

import java.io.File;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

public class Song extends Multimedia {

    public Song(File file) {
        super(); // multimedia classes
        // ! this must be change, or could create cursed empty songs
        extractMetadata(file);
    }

    // extract metadata from the file and assign to the class with set
    private void extractMetadata(File file) {
        try {
            AudioFile audioFile = AudioFileIO.read(file);
            Tag tag = audioFile.getTag();

            if (tag != null) {
                setPath(file.getAbsolutePath());
                setTitle(tag.getFirst(FieldKey.TITLE));
                setArtist(tag.getFirst(FieldKey.ARTIST));
                setAlbum(tag.getFirst(FieldKey.ALBUM));
                setGenre(tag.getFirst(FieldKey.GENRE));
            }

            int trackLength = audioFile.getAudioHeader().getTrackLength();
            setDurationSeconds(trackLength);

        } catch (Exception e) {
            System.err.println("Error: Failed to process audio metadata. " + e.getMessage());
        }
    }

    public void displaySongInfo() {
        int seconds = getDurationSeconds();
        String formattedTime;

        if (seconds <= 0) {
            formattedTime = "Unknown";
        } else {
            formattedTime = formatDuration(seconds);
        }

        System.out.println("============== DETAILS ==============");
        System.out.println(" TITLE    : " + getTitle());
        System.out.println(" ARTIST   : " + getArtist());
        System.out.println(" ALBUM    : " + getAlbum());
        System.out.println(" GENRE    : " + getGenre());
        System.out.println(" DURATION : " + formattedTime);
        System.out.println(" POSITION : " + getPosition());
        System.out.println("=====================================");
    }

    // format duration for the musisong btw
    private String formatDuration(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%d:%02d", minutes, remainingSeconds);
    }
}