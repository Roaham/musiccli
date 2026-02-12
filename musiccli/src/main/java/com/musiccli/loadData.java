package com.musiccli;

public class loadData {

    public static void clean() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void loadData() {
        clean();
        String[] frames = {"|", "/", "-", "\\"};
        int duracionSegundos = 5;
        long tiempoFinal = System.currentTimeMillis() + (duracionSegundos * 1000);
        int i = 0;

        while (System.currentTimeMillis() < tiempoFinal) {
            System.out.print("\r[" + frames[i % frames.length] + "] Loading...");
            
            i++;
            
            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        clean();
    }
}
