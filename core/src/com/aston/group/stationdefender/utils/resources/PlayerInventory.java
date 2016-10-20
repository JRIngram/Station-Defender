package com.aston.group.stationdefender.utils.resources;

import com.aston.group.stationdefender.gamesetting.items.Item;

import java.util.ArrayList;
import java.util.Iterator;

public class PlayerInventory implements Inventory {

    private ArrayList<Item> items;

    public PlayerInventory() {
        items = new ArrayList<Item>();
    }

    public PlayerInventory(ArrayList<Item> items) {
        this.items = items;
    }

    public boolean addItem(Item item) {
        return items.add(item);
    }

    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    public void addAllItems(ArrayList<Item> items) {
        this.items.addAll(items);
    }

    @Override
    public void removeAllItems() {
        items.clear();
    }

    @Override
    public void removeItemById(int id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) != null && items.get(i).getId() == id) {
                items.remove(i);

                break;
            }
        }
    }

    public Item getItemById(int id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) != null && items.get(i).getId() == id) {
                return items.get(i);
            }
        }

        return null;
    }

    @Override
    public ArrayList<Item> getAllItemsById(int id) {
        ArrayList<Item> items = new ArrayList<Item>();

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) != null && items.get(i).getId() == id) {
                items.add(items.get(i));
            }
        }

        return items;
    }

    @Override
    public void removeAllItemsById(int id) {
        for (Iterator<Item> iterator = items.iterator(); iterator.hasNext(); ) {
            Item item = iterator.next();

            if (item.getId() == id) {
                iterator.remove();
            }
        }
    }

    @Override
    public String toString() {
        return "PlayerInventory{" +
                "items=" + items +
                '}';
    }
}
