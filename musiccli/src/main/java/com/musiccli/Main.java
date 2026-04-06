package com.musiccli;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        java.util.logging.Logger.getLogger("org.jaudiotagger")
                .setLevel(java.util.logging.Level.OFF);

        SongLoader songLoader = new SongLoader();
        songLoader.cargarCanciones();
        loadData load = new loadData();
        Scanner sc = new Scanner(System.in);
        boolean loop = false;

        printBanner();

        while (!loop) {

            System.out.print("          Introduzca acción a realizar > ");
            String input = sc.nextLine();

            try {
                comands command = comands.valueOf(
                        input.toLowerCase().trim().replace(">", ""));

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
                        songLoader.mostrarCanciones();
                        break;

                    case vermetadatos:
                        System.out.print("          Introduzca numero de cancion > ");
                        int songNum = sc.nextInt();
                        sc.nextLine();
                        load.loadData();

                        Cancion cancion = songLoader.getCancion(songNum);
                        if (cancion != null) {
                            cancion.infoCancion();
                        } else {
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
                        sc.close();
                        loop = true;
                        break;

                    default:
                        throw new AssertionError();
                }

            } catch (IllegalArgumentException e) {
                System.err.print("          Ese comando no existe >\n");
            } catch (Exception e) {
                System.err.print("          Error inesperado: " + e.getMessage() + "\n");
            }
        }
    }

    private static void printBanner() {
        System.out.println("  __  __ _    _  _____ _____ _____    _____ _      _____ ");
        System.out.println(" |  \\/  | |  | |/ ____|_   _/ ____|  / ____| |    |_   _|");
        System.out.println(" | \\  / | |  | | (___   | || |      | |    | |      | |  ");
        System.out.println(" | |\\/| | |  | |\\___ \\  | || |      | |    | |      | |  ");
        System.out.println(" | |  | | |__| |____) |_| || |____  | |____| |____ _| |_ ");
        System.out.println(" |_|  |_|\\____/|_____/|_____\\_____|  \\_____|______|_____|");
        System.out.println("\n          > Reproductor de Música CLI v0.1 <");
        System.out.println("          > Para ver comandos introduzca \">Comandos\" <\n");
    }

    private static void playSong() {
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