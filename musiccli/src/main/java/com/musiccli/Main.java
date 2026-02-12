package com.musiccli;

import java.io.File;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {

        Logger.getLogger("org.jaudiotagger").setLevel(Level.OFF);

        File carpeta = new File("canciones");
        File[] archivos = carpeta.listFiles();
        loadData load = new loadData();
        boolean loop = false;

        System.out.println("  __  __ _    _  _____ _____ _____    _____ _      _____ ");
        System.out.println(" |  \\/  | |  | |/ ____|_   _/ ____|  / ____| |    |_   _|");
        System.out.println(" | \\  / | |  | | (___   | || |      | |    | |      | |  ");
        System.out.println(" | |\\/| | |  | |\\___ \\  | || |      | |    | |      | |  ");
        System.out.println(" | |  | | |__| |____) |_| || |____  | |____| |____ _| |_ ");
        System.out.println(" |_|  |_|\\____/|_____/|_____\\_____|  \\_____|______|_____|");
        System.out.println("\n          > Reproductor de Música CLI v0.1 <");
        System.out.println("          > Para ver comandos introduzca \">Comandos\" <");

        while (!loop) { 

            Scanner sc = new Scanner(System.in);

            System.out.print("          Introduzca acción a realizar > ");
            String input = sc.nextLine();

            try {

                comands command = comands.valueOf(input.toLowerCase().trim().replace(">", ""));

                switch (command) {
                    case comandos:
                        load.clean();
                        System.out.println("\n--- LISTA DE COMANDOS ---");
                        for (comands c : comands.values()) {
                            System.out.println(" > " + c);
                        }
                        System.out.println("-------------------------\n");
                        break;
                    case vercanciones:
                        load.loadData();
                        chargeSongs(archivos);
                        break;
                    case vermetadatos:
                        System.out.print("          Introduzca numero de cancion > ");
                        int songNum = sc.nextInt(); 
                        sc.nextLine();

                        load.loadData();

                        if (archivos != null && songNum > 0 && songNum <= archivos.length) {
                            File archivoElegido = archivos[songNum - 1];
                            Cancion c = new Cancion(archivoElegido); 
                            c.infoCancion(); 
                        } 
                        
                        else {
                            System.err.print("          Ese número de canción no existe >\n");
                        }

                        break;
                    case reproducircancion:
                        load.clean();
                        playSong();
                        break;
                    case exit:
                        load.clean();
                        System.out.print("          Saliendo... >");
                        Thread.sleep(2000);
                        loop = true;
                        break;
                    default:
                        throw new AssertionError();
                }
            }

            catch (Exception e) {
                System.err.print("          Ese comando no existe >\n");
            }
        }
    }

    public static void chargeSongs(File[] archivos) {
        System.out.println("--- CANCIONES ALMACENADAS ---");
        
        if (archivos != null) {
            for (File archivo : archivos) {
                Cancion c = new Cancion(archivo);
                if (archivo.isFile() && archivo.getName().toLowerCase().endsWith(".mp3")) {
                    System.out.println(c.getPosicion() + " > " + c.getTitulo());
                }
            }
            System.out.println("-----------------------------");
        } 
        
        else {
            System.out.println("No hay canciones en la carpeta /canciones");
        }
    }

    public static void playSong() {
        System.out.print("          Funcion en desarrollo... >\n");
    }

    enum comands {
        comandos,
        vercanciones,
        vermetadatos,
        reproducircancion,
        exit
    }
}