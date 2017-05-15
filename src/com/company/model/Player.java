package com.company.model;

import javafx.fxml.Initializable;

import java.io.Serializable;
import java.net.URL;
import java.util.*;

/**
 * Created by Amine on 17/04/2017.
 */
public class Player extends Observable implements Serializable  {
    private String pseudonyme;
    private int meilleureScore;
    private  transient ArrayList<Integer> scores;
    private  transient int scoreActuel;
    private transient ArrayList<Observer> observers = new ArrayList<>();

    public int getMeilleureScore() {
        return meilleureScore;
    }

    public ArrayList<Integer> getScores() {
        return scores;
    }

    public int getScoreActuel() {
        return scoreActuel;
    }

    public Player(String pseudonyme) {
        this.pseudonyme = pseudonyme;
        initialize();
    }

    public String getPseudonyme() {
        return pseudonyme;
    }


    public void setScoreActuel(int scoreActuel) {
        this.scoreActuel += scoreActuel;
        notifyObservers();
    }
    public void ResetScore(){
        scoreActuel = 0 ;
    }
    public void AddScore(){
        if (scores == null) scores = new ArrayList<>();
        if (scoreActuel > meilleureScore) meilleureScore = scoreActuel;
        scores.add(scoreActuel);
    }

    @Override
    public synchronized void addObserver(Observer o) {
        if (observers == null) observers = new ArrayList<>();
        observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers){
            o.update((Observable) this,scoreActuel);
        }
    }

    public void initialize() {
        this.meilleureScore = 0;
        this.scores = new ArrayList<>() ;
    }
}
