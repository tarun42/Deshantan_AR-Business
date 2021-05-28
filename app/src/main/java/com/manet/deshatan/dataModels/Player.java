package com.manet.deshatan.dataModels;

import java.util.ArrayList;

public class Player {
    String balance,curPos, userName,id;
    ArrayList<String> monuments;

    public Player(String balance, String curPos, String userName, String id, ArrayList<String> monuments) {
        this.balance = balance;
        this.curPos = curPos;
        this.userName = userName;
        this.id = id;
        this.monuments = monuments;
    }


    public String getBalance() {
        return balance;
    }

    public Player() {
    }

    public String getCurPos() {
        return curPos;
    }

    public String getUserName() {
        return userName;
    }

    public ArrayList<String> getMonuments() {
        return monuments;
    }
}
