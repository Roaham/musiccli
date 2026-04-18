package com.musiccl1v2.core;

import java.io.File;
import java.io.IOException;

import com.musiccl1v2.core.exception.MetadataExtractionException;
import com.musiccl1v2.ui.ConsoleRenderer;
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

    public Song(File file) throws MetadataExtractionException {
        super(); // multimedia classes
        // ! this must be change, or could create cursed empty songs
        this.setPath(file.getAbsolutePath());
        extractMetadata(file);
    }

    /**
     * Extracts audio metadata from a specified file and updates the instance fields.
     * <p>
     * This method utilizes the {@code AudioFileIO} API to parse the file's tags and
     * audio header. It populates title, artist, album, genre, and duration.
     * If a tag is missing, the corresponding fields are not updated.
     * </p>
     *
     * @param file The {@link File} object representing the audio file to be processed.
     * @throws IOException If there is an error accessing the file.
     * @throws CannotReadException If the file format is not recognized or is corrupted.
     * @throws InvalidAudioFrameException If the audio frames cannot be parsed.
     * @throws ReadOnlyFileException If the file permissions prevent reading.
     * @throws TagException If the metadata tag structure is invalid.
     * @throws KeyNotFoundException If a requested field key does not exist in the tag.
     */
    private void extractMetadata(File file) throws MetadataExtractionException {
        try {
            AudioFile audioFile = AudioFileIO.read(file);
            Tag tag = audioFile.getTag();

            if (tag != null && !tag.isEmpty()) {
                String extractedTitle = tag.getFirst(FieldKey.TITLE);
                if (extractedTitle != null && !extractedTitle.isBlank()) {
                    setTitle(extractedTitle);
                }

                String extractedArtist = tag.getFirst(FieldKey.ARTIST);
                if (extractedArtist != null && !extractedArtist.isBlank()) {
                    setArtist(extractedArtist);
                }

                String extractedAlbum = tag.getFirst(FieldKey.ALBUM);
                if (extractedAlbum != null && !extractedAlbum.isBlank()) {
                    setAlbum(extractedAlbum);
                }

                String extractedGenre = tag.getFirst(FieldKey.GENRE);
                if (extractedGenre != null && !extractedGenre.isBlank()) {
                    setGenre(extractedGenre);
                }
            }

            int trackLength = audioFile.getAudioHeader().getTrackLength();
            setDurationSeconds(trackLength);

        } catch (Exception e) {
            throw new MetadataExtractionException("[ERROR] Failed to extract metadata", e);
        }
    }
}