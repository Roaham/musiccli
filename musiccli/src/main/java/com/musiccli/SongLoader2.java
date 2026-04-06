package com.musiccli;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SongLoader2 {
    private File[] archivos;
    private List<Cancion> canciones;

    public SongLoader2(File[] archivos, List<Cancion> canciones) {
        File carpeta = new File("canciones");
        this.archivos = carpeta.listFiles();
        this.canciones = new ArrayList<>();
    }

    public void cargarCnaciones() {
        canciones.clear();
        if (archivos != null) {
            for (File archivo : archivos)
        }
    }

}
