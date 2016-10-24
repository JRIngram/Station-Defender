package com.aston.group.stationdefender.utils.resources;

import com.aston.group.stationdefender.gamesetting.items.Item;

import java.util.ArrayList;

/**
 * Inventory is a storage place for items
 * that the player can use.
 */
public interface Inventory {

    /**
     * Adds an Item to the PlayerInventory
     * @param item Item to add to the PlayerInventory
     * @return The Item added
     */
    public boolean addItem(Item item);

    /**
     * Removes an Item from the Inventory
     * @param item Item to be removed from the Inventory
     * @return The Item removed
     */
    public boolean removeItem(Item item);

    /**
     * Adds all given Items from the Inventory
     * @param items ArrayList of Items to be added to the PlayerInventory
     */
    public void addAllItems(ArrayList<Item> items);

    /**
     * Removes all Items from the Inventory
     */
    public void removeAllItems();

    /**
     * Returns an Item at a specific number in the Inventory
     * @param index The number of the queue to get the Item from
     * @return The Item at the given queue index number
     */
    public Item getItem(int index);

    /**
     * Returns an Item at a specific id in the Inventory
     * @param id The id of the item to return
     * @return The Item at the given ID number
     */
    public Item getItemById(int id);

    /**
     * Removes an Item from the PlayerInventory by the Item ID
     * @param id The ID of the Item to be removed
     */
    public void removeItemById(int id);

    /**
     * Returns all Items from the Inventory by a specific ID
     * @param id The ID to get all items by
     * @return All Items that contain the given ID
     */
    public ArrayList<Item> getAllItemsById(int id);

    /**
     * Removes all items from the Inventory by a given ID
     * @param id The Item ID to remove all items by
     */
    public void removeAllItemsById(int id);
}