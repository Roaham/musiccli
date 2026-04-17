package com.musiccl1v2.core;

import java.io.File;
import java.io.IOException;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

// * Class representing a song

public class Song extends Multimedia {

    public Song(File file) {
        super(); // multimedia classes
        // ! this must be change, or could create cursed empty songs
        this.setPath(file.getAbsolutePath());
        extractMetadata(file);
    }

    // extract metadata from the file and assign to the class with set
    private void extractMetadata(File file) {
        try {
            AudioFile audioFile = AudioFileIO.read(file);
            Tag tag = audioFile.getTag();

            if (tag != null) {
                setTitle(tag.getFirst(FieldKey.TITLE));
                setArtist(tag.getFirst(FieldKey.ARTIST));
                setAlbum(tag.getFirst(FieldKey.ALBUM));
                setGenre(tag.getFirst(FieldKey.GENRE));
            }

            int trackLength = audioFile.getAudioHeader().getTrackLength();
            setDurationSeconds(trackLength);

        } catch (IOException | CannotReadException | InvalidAudioFrameException | ReadOnlyFileException
                | KeyNotFoundException | TagException e) {
            System.err.println("Error: Failed to process audio metadata. " + e.getMessage());
        }
    }
}