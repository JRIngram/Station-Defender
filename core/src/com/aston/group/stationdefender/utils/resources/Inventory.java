package com.aston.group.stationdefender.utils.resources;

import com.aston.group.stationdefender.gamesetting.items.Item;

/**
 * Inventory is a storage place for items
 * that the player can use.
 *
 * @author Mohammed Foysal
 */
public interface Inventory {

    /**
     * Adds an Item to the PlayerInventory
     *
     * @param item Item to add to the PlayerInventory
     */
    void addItem(Item item);
}