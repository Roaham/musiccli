package com.musiccl1v2;

import java.util.Scanner;

public class CLIcontroller {
    private final MusicLibrary library;

    public enum comands {
        commands, showsongs, watchmetadata, playsong, s, exit
    }

    public CLIcontroller(MusicLibrary library) {
        this.library = library;
    }

    public void execCommand(String input, Scanner sc) {
        switch (comands.valueOf(input)) {
            case commands -> {
                DataLoader.clearConsole();
                System.out.println("\n--- COMMAND LIST ---");
                for (comands c : comands.values()) {
                    System.out.println(" > " + c);
                }
            }
            case showsongs -> {
                DataLoader.showLoadingAnimation();
                if (library.getList().isEmpty()) {
                    System.out.println("Empty Library");
                } else {
                    for (Song c : library.getList()) {
                        System.out.printf(" [%02d] > %s %n", c.getPosition(), c.getTitle());
                    }
                }
            }
            case watchmetadata -> {
                System.out.print("Input the song position > ");
                try {
                    // String to int
                    int pos = Integer.parseInt(sc.nextLine());
                    DataLoader.showLoadingAnimation();
                    Song foundSong = library.findByPosition(pos);
                    if (foundSong != null) {
                        foundSong.displaySongInfo();
                    } else {
                        System.err.println("Error: Song with position " + pos + " not found.");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error: Please enter a valid position.");
                }
            }
            case playsong -> {
                System.out.print("Input the song position > ");
                try {
                    int pos = Integer.parseInt(sc.nextLine());
                    Song foundSong = library.findByPosition(pos);

                    if (foundSong != null) {
                        library.stopSong();

                        library.playSong(foundSong.getPath());
                        System.out.println("Playing: " + foundSong.getTitle());
                        System.out.println("Input> [s|exit]");
                    } else {
                        System.err.println("Error: Position not found.");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error: Invalid number.");
                }
            }
            case s -> {
                library.stopSong();
            }
            case exit -> System.out.println("Quitting...");
            default -> System.out.println("No command like this");
        }
    }
}