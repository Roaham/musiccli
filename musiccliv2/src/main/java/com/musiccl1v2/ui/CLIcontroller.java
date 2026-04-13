package com.musiccl1v2.ui;

import java.util.Scanner;

import com.musiccl1v2.core.Song;
import com.musiccl1v2.data.History;
import com.musiccl1v2.service.MusicLibrary;

// * Controller class for the Command Line Interface.

public class CLIcontroller {
    private final MusicLibrary library;
    private final History history;

    public enum Command {
        COMMANDS, SHOW_SONGS, WATCH_METADATA, PLAY_SONG, HISTORY, EXIT
    }

    public CLIcontroller(MusicLibrary library, History history) {
        this.library = library;
        this.history = history;
    }

    // Overhere enter the input nd its double checked for no reason at all
    public void executeCommand(String input, Scanner scanner) {
        try {
            // Normalizing input (again) to match enum constants
            Command command = Command.valueOf(input.toUpperCase().trim());
            handleCommand(command, scanner);
        } catch (IllegalArgumentException e) {
            System.err.println("No command like this: " + input);
        }
    }

    // call a private method to improve experience (for me)
    private void handleCommand(Command command, Scanner scanner) {
        switch (command) {
            case COMMANDS -> showAvailableCommands();
            case SHOW_SONGS -> displayAllSongs();
            case WATCH_METADATA -> handleShowMetadata(scanner);
            case PLAY_SONG -> handlePlaySong(scanner);
            case HISTORY -> handleShowHistory();
            case EXIT -> System.out.println("Quitting...");
        }
    }

    private void showAvailableCommands() {
        DataLoader.clearConsole();
        System.out.println("\n--- COMMAND LIST ---");
        for (Command c : Command.values()) {
            System.out.println(" > " + c.name().toLowerCase());
        }
    }

    private void displayAllSongs() {
        DataLoader.showLoadingAnimation();
        if (library.getList().isEmpty()) {
            System.out.println("Empty Library");
        } else {
            library.getList()
                    .forEach(song -> System.out.printf(" [%02d] > %s %n", song.getPosition(), song.getTitle()));
        }
    }

    private void handleShowMetadata(Scanner scanner) {
        Integer pos = readPosition(scanner);
        if (pos == null)
            return;

        DataLoader.showLoadingAnimation();
        Song foundSong = library.findByPosition(pos);
        if (foundSong != null) {
            foundSong.displaySongInfo();
        } else {
            System.err.println("Error: Song with position " + pos + " not found.");
        }
    }

    private void handlePlaySong(Scanner scanner) {
        Integer pos = readPosition(scanner);
        if (pos == null)
            return;

        Song foundSong = library.findByPosition(pos);
        if (foundSong != null) {
            library.playSong(foundSong.getPath());
            DataLoader.startAnimation(foundSong.getTitle());
        } else {
            System.err.println("Error: Song with position " + pos + " not found.");
        }
    }

    private void handleShowHistory() {
        DataLoader.showLoadingAnimation();
        // ? idk if its better to put the history in the builder on in a try-catch here,
        // however it works the same
        history.read();
    }

    // position reader for undertand better the code
    private Integer readPosition(Scanner scanner) {
        try {
            System.out.print("Input the song position > ");
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("Error: Please enter a valid numerical position.");
            return null;
        }
    }
}