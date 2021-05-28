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

    public HashMap<String, String> getMonuments() {
        return monuments;
    }

    public Game(ArrayList<Player> players, String owner, String turn) {
        monuments = new HashMap<>();
        monuments.put("Qutub Minar Delhi","available");
        monuments.put("Taj Mahal Agra","available");
        monuments.put("Hawa Mahal Jaipur Rajasthan","available");
        monuments.put("India Gate Delhi","available");
        monuments.put("Murudeshwar Temple Karnataka","available");
        monuments.put("City Palace Udaipur, Rajasthan","available");
        monuments.put("Bahai Temple (Lotus Temple), New Delhi","available");
        monuments.put("Mysore Palace, Mysore Karnataka","available");
        monuments.put("Gateway of India Mumbai Maharashtra","available");
        monuments.put("Konark temple Odisha","available");
        monuments.put("RED Fort Delhi","available");
        monuments.put("Charminar Hyderabad Telangana","available");
        monuments.put("Golden Temple Amritsar","available");
        monuments.put("Ajanta elora caves aurangabad","available");
        monuments.put("Jama Masjid, Delhi","available");
        monuments.put("Basilica of Bom Jesus","available");
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
