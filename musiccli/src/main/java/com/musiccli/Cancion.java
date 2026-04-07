package com.musiccli;

import java.io.File;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

public class Cancion extends Multimedia {

    public Cancion(File archivo) {
        super();
        extraerMetadatos(archivo);
    }

    private void extraerMetadatos(File archivo) {
        try {
            // AudioFileIO requiere la importación explícita
            AudioFile audioFile = AudioFileIO.read(archivo);
            Tag tag = audioFile.getTag();

            if (tag != null) {
                setTitulo(tag.getFirst(FieldKey.TITLE));
                setArtista(tag.getFirst(FieldKey.ARTIST));
                setAlbum(tag.getFirst(FieldKey.ALBUM));
                setGenero(tag.getFirst(FieldKey.GENRE));
            }

            int durationInSeconds = audioFile.getAudioHeader().getTrackLength();
            setDuracionSegundos(durationInSeconds);

        } catch (org.jaudiotagger.audio.exceptions.CannotReadException e) {
            System.err.println("Error: Formato no soportado.");
        } catch (org.jaudiotagger.tag.TagException e) {
            System.err.println("Error: Fallo al procesar etiquetas de metadatos.");
        } catch (org.jaudiotagger.audio.exceptions.InvalidAudioFrameException e) {
            // Excepción adicional común en JAudioTagger
            System.err.println("Error: Archivo de audio corrupto o inválido.");
        } catch (java.io.IOException e) {
            System.err.println("Error: Problema de acceso al archivo.");
        } catch (Exception e) {
            System.err.println("Error Inesperado: " + e.getMessage());
        }
    }

    public void infoCancion() {
        // Lógica de visualización (Correcta)
        System.out.println("============== DETALLES ==============");
        System.out.println(" TITULO   : " + (getTitulo().isEmpty() ? "Desconocido" : getTitulo()));
        System.out.println(" ARTISTA  : " + (getArtista().isEmpty() ? "Desconocido" : getArtista()));
        System.out.println(" ALBUM    : " + (getAlbum().isEmpty() ? "Desconocido" : getAlbum()));
        System.out.println(" GENERO   : " + (getGenero().isEmpty() ? "Desconocido" : getGenero()));
        System.out.println(" DURACION : " + (getDuracionSegundos() <= 0 ? "Indeterminado"
                : (getDuracionSegundos() / 60 + ":" + String.format("%02d", getDuracionSegundos() % 60))));
        System.out.println("======================================");
    }
}