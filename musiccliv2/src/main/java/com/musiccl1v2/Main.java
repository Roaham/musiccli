package com.musiccl1v2;

import java.util.Scanner;

import com.musiccl1v2.data.History;
import com.musiccl1v2.service.MusicLibrary;
import com.musiccl1v2.ui.CLIcontroller;
import com.musiccl1v2.ui.ConsoleRenderer;

public class Main {
    public static void main(String[] args) {
        try (History history = new History(); Scanner sc = new Scanner(System.in)) {
            // no logs by the audio
            java.util.logging.Logger.getLogger("org.jaudiotagger").setLevel(java.util.logging.Level.OFF);

            // TODO Convert this to a new command
            MusicLibrary library = new MusicLibrary();
            library.loadSongs();

            // cli print class
            CLIcontroller controller = new CLIcontroller(library, history);
            boolean exit = false;
            ConsoleRenderer.printBanner();

            while (!exit) {
                ConsoleRenderer.printPrompt();

                // normalize input
                String input = sc.nextLine();

                if (input.equals("exit")) {
                    exit = true;
                }

                try {
                    controller.executeCommand(input, sc);
                } catch (Exception e) {
                    ConsoleRenderer.printError(e.getMessage()); // double check btw
                } finally {
                    history.record(input); // save history to db
                }
            }
        } catch (Exception e) {
            ConsoleRenderer.printError(e.getMessage());
        }
    }
}