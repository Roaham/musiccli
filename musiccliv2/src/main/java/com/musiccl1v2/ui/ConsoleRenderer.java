package com.musiccl1v2.ui;

import java.util.List;

import com.musiccl1v2.core.Song;
import com.musiccl1v2.data.History;

public final class ConsoleRenderer {

    private static final String ANSI_RESET = "\033[0m";
    private static final String ANSI_CYAN = "\033[36m";
    private static final String ANSI_BOLD = "\u001B[1m";
    private static final String ANSI_WHITE = "\u001B[37m";

    // This prevents instantiation
    private ConsoleRenderer() {
        throw new AssertionError("Utility class - do not instantiate");
    }

    // Banner

    public static void printBanner() {
        System.out.println(ANSI_CYAN);
        System.out.println("  __  __ _    _  _____ _____ _____    _____ _      _____ ");
        System.out.println(" |  \\/  | |  | |/ ____|_   _/ ____|  / ____| |    |_   _|");
        System.out.println(" | \\  / | |  | | (___   | || |      | |    | |      | |  ");
        System.out.println(" | |\\/| | |  | |\\___ \\  | || |      | |    | |      | |  ");
        System.out.println(" | |  | | |__| |____) |_| || |____  | |____| |____ _| |_ ");
        System.out.println(" |_|  |_|\\____/|_____/|_____\\_____|  \\_____|______|_____|");
        System.out.println(ANSI_RESET);
        System.out.println("          > Music Player CLI v0.3 — Standard Edition <");
        System.out.println("          > Type  'commands'  to see available actions  <\n");
    }

    // Song list

    /**
     * Prints the list of songs in the library to the console.
     * If the library is empty, prints a message indicating that the library is
     * empty.
     * Otherwise, prints a header indicating the start of the library, followed by
     * a list of songs in the format "[id] > title".
     * 
     * @param songs the list of songs in the library
     */
    public static void printSongList(List<Song> songs) {
        if (songs.isEmpty()) {
            printError("  [ Library is empty — add MP3 files to the songs folder ]");
            return;
        }
        System.out.println("\n  ── Library ──────────────────────────────────────────");
        songs.forEach(song -> System.out.printf("  [%02d] > %s %n", song.getPosition(), song.getTitle()));
        System.out.println("  ─────────────────────────────────────────────────────");
    }

    // Song detail


    public static void displaySongInfo(Song song) {
        int seconds = song.getDurationSeconds();
        String formatedTime;

        // check the formated time
        if (seconds <= 0) {
            formatedTime = "Unknown";
        } else {
            formatedTime = formatDuration(seconds);
        }

        // Pretty Lines
        System.out.println("\n  " + ANSI_CYAN + "╔" + "═══════════════════════════════════════" + "╗" + ANSI_RESET);
        System.out.println("  " + ANSI_RESET + "║" + ANSI_RESET + ANSI_BOLD + ANSI_WHITE
                + "           TRACK INFORMATION           " + ANSI_RESET
                + ANSI_CYAN + "║" + ANSI_RESET);
        System.out.println("  " + ANSI_CYAN + "╠" + "═══════════════════════╦" + "═══════════════" + "╣" + ANSI_RESET);

        // Main formated attributes
        printFormattedField("TITLE", song.getTitle(), ANSI_CYAN, ANSI_RESET);
        printFormattedField("ARTIST", song.getArtist(), ANSI_CYAN, ANSI_RESET);
        printFormattedField("ALBUM", song.getAlbum(), ANSI_CYAN, ANSI_RESET);
        printFormattedField("GENRE", song.getGenre(), ANSI_CYAN, ANSI_RESET);
        printFormattedField("DURATION", formatedTime, ANSI_CYAN, ANSI_RESET);
        printFormattedField("POSITION", String.valueOf(song.getPosition()), ANSI_CYAN, ANSI_RESET);

        // Pretty Lines
        System.out.println(
                "  " + ANSI_CYAN + "╚" + "═══════════════════════╩" + "═══════════════" + "╝" + ANSI_RESET + "\n");
    }

    // format duration for the song btw
    private static String formatDuration(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%d:%02d", minutes, remainingSeconds);
    }

    // History

    /**
     * Prints the command history stored in the given History object.
     * The method first prints a header and then calls the read() method of the
     * History object to print the command history.
     * Finally, it prints a footer to complete the output.
     *
     * @param history the History object to read from
     */
    public static void printHistory(History history) {
        System.out.println("\n  ── Command History ──────────────────────────────────");
        history.read();
        System.out.println("  ─────────────────────────────────────────────────────");
    }

    // Errors nd prompts

    public static void printError(String message) {
        System.err.printf("  [ERROR] %s%n", message);
    }

    public static void printPrompt() {
        System.out.print("\n  Input > ");
    }

    // Helpers

    /**
     * Prints a formatted field with a label and value.
     * 
     * @param fieldName  the name of the field to be displayed
     * @param fieldValue the value of the field to be displayed
     * @param color      the color to be used for the label and value
     * @param reset      the reset string to be used to reset the color after
     *                   printing
     */
    public static void printFormattedField(String fieldName, String fieldValue, String color, String reset) {
        String safeValue = (fieldValue != null) ? fieldValue : "Unknown";

        // Truncate to 12 to leave room for padding within the 15-char column
        if (safeValue.length() > 12) {
            safeValue = safeValue.substring(0, 9) + "...";
        }

        String label = fieldName + ":";

        // %-21s + 2 spaces = 23
        // %-13s + 2 spaces = 15
        System.out.printf("  " + color + "║" + reset + " %-21s " + color + "║" + reset + " %-13s " + color + "║%n",
                label, safeValue);
    }
}
