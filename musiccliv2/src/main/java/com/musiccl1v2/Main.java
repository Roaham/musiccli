package com.musiccl1v2;

import java.util.Scanner;

import com.musiccl1v2.data.History;
import com.musiccl1v2.service.MusicLibrary;
import com.musiccl1v2.ui.CLIcontroller;

public class Main {
    public static void main(String[] args) {

        // no logs by the jaudio
        java.util.logging.Logger.getLogger("org.jaudiotagger").setLevel(java.util.logging.Level.OFF);

        MusicLibrary library = new MusicLibrary();
        library.loadSongs();

        History history = new History();
        CLIcontroller controller = new CLIcontroller(library);
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        printBanner();

        while (!exit) {
            System.out.print("\nInput > ");
            // clean input
            String input = sc.nextLine().toLowerCase().trim();
            if (input.equals("exit")) {
                exit = true;
            }
            try {
                controller.execCommand(input, sc);

            } catch (IllegalArgumentException e) {
                // double check for some reason
                System.err.println("Wth you do to see this???");
            } finally {
                history.record(input);
            }
        }
        sc.close();
    }

    private static void printBanner() {
        System.out.println("  __  __ _    _  _____ _____ _____    _____ _      _____ ");
        System.out.println(" |  \\/  | |  | |/ ____|_   _/ ____|  / ____| |    |_   _|");
        System.out.println(" | \\  / | |  | | (___   | || |      | |    | |      | |  ");
        System.out.println(" | |\\/| | |  | |\\___ \\  | || |      | |    | |      | |  ");
        System.out.println(" | |  | | |__| |____) |_| || |____  | |____| |____ _| |_ ");
        System.out.println(" |_|  |_|\\____/|_____/|_____\\_____|  \\_____|______|_____|");
        System.out.println("\n          > Music player CLI v2.0 <");
        System.out.println("          > To show commands input: \">Commands\" <\n");
    }
}