package com.musiccli;

import java.io.File;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

public class Cancion extends Multimedia {

    public Cancion(File archivo) {
        super(archivo.getName(), "Desconocido", "Desconocido", "Varios", 0);
        extraerMetadatos(archivo);
    }

    private void extraerMetadatos(File archivo) {
        try {
            AudioFile f = AudioFileIO.read(archivo);
            Tag tag = f.getTag();
            
            setTitulo(tag.getFirst(FieldKey.TITLE));
            setArtista(tag.getFirst(FieldKey.ARTIST));
            setAlbum(tag.getFirst(FieldKey.ALBUM));
            setGenero(tag.getFirst(FieldKey.GENRE));
            setDuracionSegundos(f.getAudioHeader().getTrackLength());

        } 
        
        catch (Exception e) {
            setTitulo("Desconocido");
            setArtista("Desconocido");
            setAlbum("Desconocido");
            setGenero("Desconocido");
            setDuracionSegundos(0);
        }
    }

    public void infoCancion() {
        System.out.println("============== DETALLES ==============");
        System.out.println(" TITULO   : " + (getTitulo().isEmpty() ? "Desconocido" : getTitulo()));
        System.out.println(" ARTISTA  : " + (getArtista().isEmpty() ? "Desconocido" : getArtista()));
        System.out.println(" ALBUM    : " + (getAlbum().isEmpty() ? "Desconocido" : getAlbum()));
        System.out.println(" GENERO   : " + (getGenero().isEmpty() ? "Desconocido" : getGenero()));
        System.out.println(" DURACION : " + (getDuracionSegundos() <= 0 ? "Indeterminado" : ("Min: " + getDuracionSegundos() / 60 + " Seg: " + getDuracionSegundos() % 60)));
        System.out.println("======================================");
    }
}