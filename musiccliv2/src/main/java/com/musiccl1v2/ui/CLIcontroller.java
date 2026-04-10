package com.musiccl1v2.ui;

import java.util.Scanner;

import com.musiccl1v2.core.Song;
import com.musiccl1v2.data.History;
import com.musiccl1v2.service.MusicLibrary;

public class CLIcontroller {
    private final MusicLibrary library;

    public enum comands {
        commands, showsongs, watchmetadata, playsong, history, exit
    }

    public CLIcontroller(MusicLibrary library) {
        this.library = library;
    }

    public void execCommand(String input, Scanner sc) {
        try {
            comands command = comands.valueOf(input.toLowerCase());
            handleCommand(command, sc);
        } catch (IllegalArgumentException e) {
            System.out.println("No command like this: " + input);
        }
    }

    // call a private method to improve experience (for me)
    private void handleCommand(comands command, Scanner sc) {
        switch (command) {
            case commands -> showAvailableCommands();
            case showsongs -> displayAllSongs();
            case watchmetadata -> handleShowMetadata(sc);
            case playsong -> handlePlaySong(sc);
            case history -> handleShowHistory();
        }
    }

    private void showAvailableCommands() {
        DataLoader.clearConsole();
        System.out.println("\n--- COMMAND LIST ---");
        for (comands c : comands.values()) {
            System.out.println(" > " + c);
        }
    }

    private void displayAllSongs() {
        DataLoader.showLoadingAnimation();
        if (library.getList().isEmpty()) {
            System.out.println("Empty Library");
        } else {
            for (Song c : library.getList()) {
                System.out.printf(" [%02d] > %s %n", c.getPosition(), c.getTitle());
            }
        }
    }

    private void handleShowMetadata(Scanner sc) {
        try {
            System.out.print("Input the song position > ");
            int pos = Integer.parseInt(sc.nextLine());
            DataLoader.showLoadingAnimation();
            Song foundSong = library.findByPosition(pos);
            if (foundSong != null) {
                foundSong.displaySongInfo();
            } else {
                System.err.println("Error: Song with position " + pos + " not found.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Please enter a valid numerical position.");
        }
    }

    private void handlePlaySong(Scanner sc) {
        try {
            System.out.print("Input the song position > ");
            int pos = Integer.parseInt(sc.nextLine());
            Song foundSong = library.findByPosition(pos);

            if (foundSong != null) {
                library.playSong(foundSong.getPath());
                DataLoader.startAnimation(foundSong.getTitle());

                System.out.println("\nInput> [s|exit]");
            } else {
                System.err.println("Error: Position not found.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Please enter a valid numerical position.");
        }
    }

    private void handleShowHistory() {
        History history = new History();
        DataLoader.showLoadingAnimation();
        history.read();
    }
}