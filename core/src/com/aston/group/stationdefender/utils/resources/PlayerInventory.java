package com.aston.group.stationdefender.utils.resources;

import com.aston.group.stationdefender.gamesetting.items.Item;
import com.badlogic.gdx.utils.Array;

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
     * Adds an Item to the PlayerInventory
     *
     * @param item Item to add to the PlayerInventory
     */
    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public String toString() {
        return "PlayerInventory{" + "items=" + items + "}";
    }
}