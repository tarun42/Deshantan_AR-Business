package com.manet.deshatan.dataModels;

import java.util.ArrayList;

public class Game {
    ArrayList<Player> players;
    String owner,turn;

    public Game(ArrayList<Player> players, String owner, String turn) {
        this.players = players;
        this.owner = owner;
        this.turn = turn;
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
