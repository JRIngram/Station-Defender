package com.aston.group.stationdefender.utils.resources;

import com.aston.group.stationdefender.gamesetting.items.Item;
import com.aston.group.stationdefender.gamesetting.items.ItemStack;
import com.badlogic.gdx.utils.Array;

/**
 * StackableInventory is an Inventory that allows Items to be stacked to allow more than one of the same type of item
 * per slot
 *
 * @author Mohammed Foysal
 */
public class StackableInventory implements Inventory {
    private final Array<ItemStack<Item>> itemStacks = new Array<>();

    @Override
    public void addItem(Item item) {
        ItemStack<Item> itemStack = findStack(item);
        if (itemStack != null) {
            itemStack.addItem(item);
        } else {
            ItemStack<Item> newStack = new ItemStack<>(item);
            itemStacks.add(newStack);
        }
    }

    /**
     * Remove an Item from the StackableInventory
     *
     * @param item The Item to be removed from the StackableInventory
     */
    public void removeItem(Item item) {
        ItemStack<Item> itemStack = findStack(item);
        if (itemStack != null) {
            itemStack.removeItem(item);
        }
    }

    /**
     * Returns the ItemStacks being used in the StackableInventory
     *
     * @return The ItemStacks being used in the StackableInventory
     */
    public Array<ItemStack<Item>> getItemStacks() {
        return itemStacks;
    }

    /**
     * Find the ItemStack for a specific Item
     *
     * @param item The Item to find the ItemStack of
     * @return The ItemStack that contains the Item
     */
    private ItemStack<Item> findStack(Item item) {
        for (ItemStack<Item> itemStack : itemStacks) {
            if (itemStack.getItem() != null) {
                if (itemStack.getItem().getClass().equals(item.getClass()) && itemStack.isNotFull()) {
                    return itemStack;
                }
            }
        }
        return null;
    }
}