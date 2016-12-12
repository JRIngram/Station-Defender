package com.aston.group.stationdefender.utils.resources;

import com.aston.group.stationdefender.gamesetting.items.Item;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;
import java.util.stream.IntStream;

/**
 * PlayerInventory class is responsible for holding items in the Players' Inventory
 *
 * @author Mohammed Foysal
 */
public class PlayerInventory implements Inventory {
    private final Array<Item> items;

    /**
     * Construct a new PlayerInventory
     */
    public PlayerInventory() {
        items = new Array<>();
    }

    /**
     * Construct a new PlayerInventory
     *
     * @param items The Items to add to the inventory
     */
    public PlayerInventory(Array<Item> items) {
        this.items = items;
    }

    /**
     * Adds an Item to the PlayerInventory
     *
     * @param item Item to add to the PlayerInventory
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Removes an Item from the PlayerInventory
     *
     * @param item Item to be removed from the PlayerInventory
     */
    public void removeItem(Item item) {
        items.removeValue(item, true);
    }

    /**
     * Adds all given Items from the PlayerInventory
     *
     * @param items Array of Items to be added to the PlayerInventory
     */
    public void addAllItems(Array<Item> items) {
        this.items.addAll(items);
    }

    @Override
    public void removeItemById(int id) {
        IntStream.range(0, items.size).filter(i -> items.get(i) != null && items.get(i).getId() == id).findFirst().ifPresent(items::removeIndex);
    }

    @Override
    public Item getItem(int index) {
        if (items.size > index + 1 && items.get(index) != null) {
            return items.get(index);
        }
        return null;
    }

    /**
     * Returns an Item at a specific id in the PlayerInventory
     *
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

    @Override
    public Array<Item> getAllItemsById(int id) {
        Array<Item> items = new Array<>();
        for (int i = 0; i < items.size; i++) {
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
        return "PlayerInventory{" + "items=" + items + "}";
    }
}