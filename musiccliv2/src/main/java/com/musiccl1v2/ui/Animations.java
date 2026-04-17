package com.musiccl1v2.ui;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Utility class for console visual effects.
 * Uses both synchronous and asynchronous animation techniques.
 */
public class Animations {

    private static final String CLEAR_SCREEN_ANSI = "\033[H\033[2J";
    private static final String HIDE_CURSOR = "\033[?25l";
    private static final String SHOW_CURSOR = "\033[?25h";
    private static final String CLEAR_LINE = "\r\033[K";

    private static final String[] LOADING_FRAMES = { "|", "/", "-", "\\" };
    private static final String[] LOADING_FRAMES2 = { "  ◓  ", "  ◑  ", "  ◒  ", "  ◐  " };

    private static ScheduledExecutorService animationExecutor;
    private static volatile int frameIndex = 0;

    public static void clearConsole() {
        System.out.print(CLEAR_SCREEN_ANSI);
        System.out.flush();
    }

    /**
     * Synchronous loading animation.
     * Blocks the main thread for a fixed duration.
     */
    public static void showLoadingAnimation() {
        clearConsole();
        int durationSeconds = 1;
        long endTime = System.currentTimeMillis() + (durationSeconds * 1000L);
        int currentFrameIndex = 0;

        while (System.currentTimeMillis() < endTime) {
            System.out.print(
                    "\r[" + LOADING_FRAMES[currentFrameIndex % LOADING_FRAMES.length] + "] Loading application...");
            currentFrameIndex++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.print(CLEAR_LINE);
    }

    /**
     * Asynchronous playback animation.
     * Runs on a background daemon thread to allow the CLI to remain responsive.
     */
    public static void startAnimation(String title) {
        // Prevent multiple executors from running simultaneously
        stopAnimation();

        frameIndex = 0;
        animationExecutor = Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread thread = new Thread(runnable);
            thread.setDaemon(true); // Ensures thread closes when the app exits
            return thread;
        });

        System.out.print(HIDE_CURSOR);

        animationExecutor.scheduleAtFixedRate(() -> {
            System.out.print(CLEAR_LINE + "Playing: " + title + " [" + LOADING_FRAMES2[frameIndex % 4] + "]");
            frameIndex++;
        }, 0, 150, TimeUnit.MILLISECONDS);
    }

    /**
     * Safely stops the background animation and restores the console state.
     */
    public static void stopAnimation() {
        if (animationExecutor != null && !animationExecutor.isShutdown()) {
            animationExecutor.shutdownNow();
        }
        System.out.print(SHOW_CURSOR);
        System.out.print("\r");
    }
}