package com.musiccli;

public class Multimedia {

    // contador de posicion
    private static int counter = 0;

    // variables
    private String titulo = TITULO_DEFECTO;
    private String artista = ARTISTA_DEFECTO;
    private String album = ALBUN_DEFECTO;
    private String genero = GENERO_DEFECTO;
    private int duracionSegundos = DURACION_DEFECTO;
    public int posicion;

    // variables por defecto
    private static final String TITULO_DEFECTO = "Desconocido";
    private static final String ARTISTA_DEFECTO = "Desconocido";
    private static final String ALBUN_DEFECTO = "Desconocido";
    private static final String GENERO_DEFECTO = "Desconocido";
    private static final int DURACION_DEFECTO = 0;

    public Multimedia(String titulo, String artista, String album, String genero, int duracionSegundos) {
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.genero = genero;
        this.duracionSegundos = duracionSegundos;
        this.posicion = counter++; // esto funciona como un id
    }

    public Multimedia() {
        // Contructor con valores por defecto
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuracionSegundos() {
        return duracionSegundos;
    }

    public void setDuracionSegundos(int duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }

    public int getPosicion() {
        return posicion;
    }
}