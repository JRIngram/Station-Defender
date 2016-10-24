package com.aston.group.stationdefender.utils.resources;

import com.aston.group.stationdefender.gamesetting.items.Item;

import java.util.ArrayList;
import java.util.Iterator;

public class PlayerInventory implements Inventory {
    private ArrayList<Item> items;

    /**
     * Construct a new PlaayerInventory
     */
    public PlayerInventory() {
        items = new ArrayList<Item>();
    }

    /**
     * Construct a new PlayerInventory
     * @param items The Items to add to the inventory
     */
    public PlayerInventory(ArrayList<Item> items) {
        this.items = items;
    }

    /**
     * Adds an Item to the PlayerInventory
     * @param item Item to add to the PlayerInventory
     * @return The Item added
     */
    public boolean addItem(Item item) {
        return items.add(item);
    }

    /**
     * Removes an Item from the PlayerInventory
     * @param item Item to be removed from the PlayerInventory
     * @return The Item removed
     */
    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    /**
     * Adds all given Items from the PlayerInventory
     * @param items ArrayList of Items to be added to the PlayerInventory
     */
    public void addAllItems(ArrayList<Item> items) {
        this.items.addAll(items);
    }

    /**
     * Removes all Items from the PlayerInventory
     */
    @Override
    public void removeAllItems() {
        items.clear();
    }

    /**
     * Removes an Item from the PlayerInventory by the Item ID
     * @param id The ID of the Item to be removed
     */
    @Override
    public void removeItemById(int id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) != null && items.get(i).getId() == id) {
                items.remove(i);
                break;
            }
        }
    }

    /**
     * Returns an Item at a specific number in the PlayerInventory
     * @param index The number of the queue to get the Item from
     * @return The Item at the given queue index number
     */
    @Override
    public Item getItem(int index) {
        if(items.size() > index + 1 && items.get(index) != null){
            return items.get(index);
        }
        return null;
    }

    /**
     * Returns an Item at a specific id in the PlayerInventory
     * @param id The id of the item to return
     * @return The Item at the given ID number
     */
    public Item getItemById(int id) {
        for (Item item : items) {
            if (item != null && item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    /**
     * Returns all Items in the PlayerInventory by a specific ID
     * @param id The ID to get all items by
     * @return All Items that contain the given ID
     */
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

    /**
     * Removes all items from the PlayerInventory by a given ID
     * @param id The Item ID to remove all items by
     */
    @Override
    public void removeAllItemsById(int id) {
        for (Iterator<Item> iterator = items.iterator(); iterator.hasNext(); ) {
            Item item = iterator.next();
            if (item.getId() == id) {
                iterator.remove();
            }
        }
    }

    /**
     * Prints the Items in the PlayerInventory
     * @return The Items in the PlayerInventory
     */
    @Override
    public String toString() {
        return "PlayerInventory{" +
                "items=" + items +
                '}';
    }
}