package com.aston.group.stationdefender.gamesetting;

import com.aston.group.stationdefender.gamesetting.items.Item;
import com.aston.group.stationdefender.gamesetting.items.ItemCredit;
import com.aston.group.stationdefender.utils.resources.Inventory;
import com.aston.group.stationdefender.utils.resources.PlayerInventory;

import java.util.ArrayList;

public class Player {

    private Inventory inventory;
    private int score;
    private int money;

    public Player() {
        inventory = new PlayerInventory();
        score = 0;
        money = 0;
    }

    public void collectItem(Item item){
        inventory.addItem(item);
    }

    public void collectItems(ArrayList<Item> items){
        inventory.addAllItems(items);
    }

    public void dropItem(Item item){
        inventory.removeItem(item);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}