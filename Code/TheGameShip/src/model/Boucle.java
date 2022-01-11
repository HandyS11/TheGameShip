package model;

import static java.lang.Thread.sleep;

public class Boucle extends model.Observable implements Runnable {
    private boolean isRunning = true;
    private long millis;

    public Boucle() {
        this.millis = 50;
    }

    public Boucle(long millis) {
        this.millis = millis;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                sleep(millis);
                beep();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void StopBoucle(){
        isRunning = false;
    }

    public void StartBoucle(){
        isRunning = true;
    }

    public void beep(){
        notifier();
    }
}