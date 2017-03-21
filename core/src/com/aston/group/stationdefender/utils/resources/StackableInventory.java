package com.aston.group.stationdefender.utils.resources;

import com.aston.group.stationdefender.gamesetting.items.Item;
import com.aston.group.stationdefender.gamesetting.items.ItemStack;
import com.badlogic.gdx.utils.Array;

public class StackableInventory implements Inventory {
    private final Array<ItemStack<Item>> itemStacks = new Array<>();

    @Override
    public void addItem(Item item) {
        ItemStack<Item> itemStack = findStack(item);
        if (itemStack != null) {
            //add the item to the found item stack
            itemStack.addItem(item);
        } else {
            //create a new item stack and add it to the array list
            ItemStack<Item> newStack = new ItemStack<>(item);
            itemStacks.add(newStack);
        }
    }

    @Override
    public void removeItem(Item item) {
        ItemStack<Item> itemStack = findStack(item);
        if (itemStack != null) {
            itemStack.removeItem(item);
        }
    }

    @Override
    public Item getItem(int index) {
        //todo change implementation
        return null;
    }

    @Override
    public Item getItemById(int id) {
        return null;
    }

    @Override
    public void removeItemById(int id) {
    }

    @Override
    public Array<Item> getAllItemsById(int id) {
        return null;
    }

    @Override
    public void removeAllItemsById(int id) {
    }

    @Override
    public Array<Item> getItems() {
        Array<Item> itemsArray = new Array<>();
        for (int i = 0; i < itemStacks.size; i++) {
            for (int j = 0; j < itemStacks.get(i).getCount(); j++) {
                itemsArray.add(itemStacks.get(i).getItem());
            }
        }
        return itemsArray;
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
            if (itemStack.getItem().getClass().equals(item.getClass()) && !itemStack.isFull()) {
                return itemStack;
            }
        }
        return null;
    }
}