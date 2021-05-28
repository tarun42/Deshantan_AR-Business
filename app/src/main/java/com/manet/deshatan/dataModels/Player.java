package com.manet.deshatan.dataModels;

import java.util.ArrayList;

public class Player {
    public void setBalance(String balance) {
        this.balance = balance;
    }

    public void setCurPos(String curPos) {
        this.curPos = curPos;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMonuments(ArrayList<String> monuments) {
        this.monuments = monuments;
    }

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
