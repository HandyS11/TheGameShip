package model.util.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.Date;

public class HighScore {

    private final ObservableList<String> highScore;

    public HighScore() { highScore = FXCollections.observableArrayList(new ArrayList<>()); }
    public HighScore(ArrayList<String> list) { highScore = FXCollections.observableArrayList(list); }

    public void addHighScore(int score) {
        highScore.add(String.valueOf(score) + " : " + new Date());
        if (highScore.contains("No Score Yet")){
            highScore.remove("No Score Yet");
        }
    }
    public ObservableList<String> getListScore() {
        /*
        if(highScore.isEmpty()){
            highScore.add("No Score Yet");
        }*/
        return highScore;
    }
    public void resetHighScore() { highScore.removeAll(highScore); }
    public void loadListe(ArrayList l) {
        resetHighScore();
        highScore.addAll(l);
    }

    @Override
    public String toString() {
        String str = "Scores :\n";
        for (String s : highScore) {
            str = str + s + "\n";
        }
        return str;
    }
}
