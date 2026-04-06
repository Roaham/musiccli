package com.musiccli;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SongLoader {

    private static final String CARPETA = "canciones";
    private File[] archivos;
    private List<Cancion> canciones;

    public SongLoader() {
        File carpeta = new File(CARPETA);
        this.archivos = carpeta.listFiles();
        this.canciones = new ArrayList<>();
    }

    public void cargarCanciones() {
        canciones.clear();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile() && archivo.getName().toLowerCase().endsWith(".mp3")) {
                    canciones.add(new Cancion(archivo));
                }
            }
        }
    }

    public void mostrarCanciones() {
        if (canciones.isEmpty()) {
            System.out.println("No hay canciones en la carpeta /canciones");
            return;
        }
        System.out.println("--- CANCIONES ALMACENADAS ---");
        for (Cancion c : canciones) {
            System.out.println(c.getPosicion() + " > " + c.getTitulo());
        }
        System.out.println("-----------------------------");
    }

    public Cancion getCancion(int numero) {
        if (numero > 0 && numero <= canciones.size()) {
            return canciones.get(numero - 1);
        }
        return null;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }
}