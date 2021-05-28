package com.manet.deshatan.dataModels;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    ArrayList<Player> players;
    String owner,turn;
    Boolean startGame;
    HashMap<String,String> monuments;

    public Boolean getStartGame() {
        return startGame;
    }

    public Game(ArrayList<Player> players, String owner, String turn) {
        this.startGame = false;
        this.players = players;
        this.owner = owner;
        this.turn = turn;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public void setStartGame(Boolean startGame) {
        this.startGame = startGame;
    }

    public Game() {
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public String getOwner() {
        return owner;
    }

    public String getTurn() {
        return turn;
    }
}
