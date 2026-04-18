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

    public void executeCommand(String input, Scanner scanner) {
        try {
            Command command = Command.valueOf(input.toUpperCase().trim());
            handleCommand(command, scanner);
        } catch (IllegalArgumentException e) {
            ConsoleRenderer.printError("Unknown command: " + input);
        } catch (Exception e) {
            // Catch any unexpected errors and display them nicely
            ConsoleRenderer.printError("An unexpected error occurred: " + e.getMessage());
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
            // ! This is useless but keeps the switch clean
            case EXIT -> System.out.println("Quitting...");
        }
    }

    /**
     * Displays a list of all available commands to the user.
     * The list is cleared and the user is presented with a formatted list of
     * commands.
     */
    private void showAvailableCommands() {
        Animations.clearConsole();
        System.out.println("\n--- COMMAND LIST ---");
        for (Command c : Command.values()) {
            System.out.println(" > " + c.name().toLowerCase());
        }
    }

    /**
     * Displays all the songs in the library, along with their positions.
     * If the library is empty, prints "Empty Library".
     */
    private void displayAllSongs() {
        Animations.showLoadingAnimation();
        ConsoleRenderer.printSongList(library.getList());
    }

    /**
     * Handle the WATCH_METADATA command. Prompts the user to enter a song position,
     * and displays the song's metadata.
     * 
     * @param scanner the scanner to read the user's input from
     */
    private void handleShowMetadata(Scanner scanner) {
        Integer pos = readPosition(scanner);
        if (pos == null)
            return;

        Animations.showLoadingAnimation();
        Song foundSong = library.findByPosition(pos);
        if (foundSong != null) {
            ConsoleRenderer.displaySongInfo(foundSong);
        } else {
            ConsoleRenderer.printError("Song with position " + pos + " not found.");
        }
    }

    /**
     * Handle the PLAY_SONG command. Prompts the user to enter a song position and
     * plays the song at that position if it exists. If the song does not exist,
     * prints an error message.
     *
     * @param scanner the scanner used to read user input
     */
    private void handlePlaySong(Scanner scanner) {
        Integer pos = readPosition(scanner);
        if (pos == null)
            return;

        Song foundSong = library.findByPosition(pos);
        if (foundSong != null) {
            library.playSong(foundSong.getPath());
            Animations.startAnimation(foundSong.getTitle());
        } else {
            ConsoleRenderer.printError("Song with position " + pos + " not found.");
        }
    }

    /**
     * Displays the history of the application. The history contains the commands
     * the user has entered, along with the time of execution.
     */
    private void handleShowHistory() {
        Animations.showLoadingAnimation();
        // ? idk if its better to put the history in the builder on in a try-catch here,
        // however it works the same
        ConsoleRenderer.printHistory(history);
    }

    // position reader for understand better the code
    private Integer readPosition(Scanner scanner) {
        try {
            System.out.print("Input the song position > ");
            return Integer.valueOf(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("Error: Please enter a valid numerical position.");
            return null;
        }
    }
}