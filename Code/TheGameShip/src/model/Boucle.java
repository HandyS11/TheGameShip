package model;

import javafx.application.Platform;
import static java.lang.Thread.sleep;

public class Boucle extends model.Observable implements Runnable {
    private final long millis;
    private boolean isRunning = true;

    public Boucle(long millis) {
        this.millis = millis;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                sleep(millis);
                Platform.runLater(this::notifier);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void StopBoucle(){ isRunning = false; }
    public void StartBoucle(){
        isRunning = true;
    }
}