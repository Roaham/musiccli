package com.musiccl1v2.ui;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DataLoader {

    private static final String CLEAR_SCREEN_ANSI = "\033[H\033[2J";
    private static final String[] LOADING_FRAMES = { "|", "/", "-", "\\" };
    private static final String[] LOADING_FRAMES2 = { "  ◓  ", "  ◑  ", "  ◒  ", "  ◐  " };

    private static ScheduledExecutorService animationExecutor;
    private static volatile int frameIndex = 0;

    public static void clearConsole() {
        System.out.print(CLEAR_SCREEN_ANSI);
    }

    // cool animation
    public static void showLoadingAnimation() {
        clearConsole();

        int durationSeconds = 1;

        // current hour + future finish animation hour
        long endTime = System.currentTimeMillis() + (durationSeconds * 1000L);
        int currentFrameIndex = 0;

        while (System.currentTimeMillis() < endTime) {

            System.out.print(
                    "\r[" + LOADING_FRAMES[currentFrameIndex % LOADING_FRAMES.length] + "] Loading application...");
            currentFrameIndex++;

            // delay for the animetion (try-catch cuzz u can't declare a thread in a while)
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        clearConsole();
    }

    // i dont understand well, but...
    public static void startAnimation(String title) {
        frameIndex = 0;
        animationExecutor = Executors.newSingleThreadScheduledExecutor((runnable) -> {
            Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            return thread;
        });

        // Hide cursor
        System.out.print("\033[?25l");

        animationExecutor.scheduleAtFixedRate(() -> {
            System.out.print("\r\033[K" + "Playing: " + title + " [ " + LOADING_FRAMES2[frameIndex % 4] + " ] ");
            frameIndex++;
        }, 0, 150, TimeUnit.MILLISECONDS);
    }
}