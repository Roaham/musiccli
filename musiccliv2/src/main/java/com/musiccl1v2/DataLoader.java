package com.musiccl1v2;

public class DataLoader {

    // clear de console
    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    // cool animation
    public static void showLoadingAnimation() {
        clearConsole();

        // the array of the movement
        String[] animationFrames = { "|", "/", "-", "\\" };

        int durationSeconds = 1;

        // current hour + future finish animation hour
        long endTime = System.currentTimeMillis() + (durationSeconds * 1000);
        int frameIndex = 0;

        // while
        while (System.currentTimeMillis() < endTime) {

            System.out.print("\r[" + animationFrames[frameIndex % animationFrames.length] + "] Loading application...");

            frameIndex++;

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
}