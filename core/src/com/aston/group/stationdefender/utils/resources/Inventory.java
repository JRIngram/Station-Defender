package com.aston.group.stationdefender.utils.resources;

import com.aston.group.stationdefender.gamesetting.items.Item;

import java.util.ArrayList;

public interface Inventory {

    public boolean addItem(Item item);

    public boolean removeItem(Item item);

    public void addAllItems(ArrayList<Item> items);

    public void removeAllItems();

    public Item getItemById(int id);

    public void removeItemById(int id);

    public ArrayList<Item> getAllItemsById(int id);

    public void removeAllItemsById(int id);

}
