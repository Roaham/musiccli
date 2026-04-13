package com.musiccl1v2;

import java.util.Scanner;

import com.musiccl1v2.data.History;
import com.musiccl1v2.service.MusicLibrary;
import com.musiccl1v2.ui.CLIcontroller;

public class Main {
    public static void main(String[] args) {
        try (History history = new History(); Scanner sc = new Scanner(System.in)) {
            // no logs by the jaudio
            java.util.logging.Logger.getLogger("org.jaudiotagger").setLevel(java.util.logging.Level.OFF);

            // load songs
            MusicLibrary library = new MusicLibrary();
            library.loadSongs();

            // cli print class
            CLIcontroller controller = new CLIcontroller(library, history);
            boolean exit = false;
            printBanner();

            while (!exit) {
                System.out.print("\nInput > ");

                // normalize input
                String input = sc.nextLine();

                if (input.equals("exit")) {
                    exit = true;
                }

                try {
                    controller.executeCommand(input, sc);
                } catch (Exception e) {
                    System.err.println("Command execution error: " + e.getMessage()); // double check btw
                } finally {
                    history.record(input); // save history to db
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
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