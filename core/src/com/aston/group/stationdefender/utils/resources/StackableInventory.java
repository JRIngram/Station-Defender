package com.aston.group.stationdefender.utils.resources;

import com.aston.group.stationdefender.gamesetting.items.Item;
import com.aston.group.stationdefender.gamesetting.items.ItemStack;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class StackableInventory implements Inventory{

    private final ArrayList<ItemStack> itemStacks = new ArrayList<>();

    @Override
    public void addItem(Item item) {

        ItemStack itemStack = findStack(item);

        if(itemStack != null){
            //add the item to the found item stack
            itemStack.addItem(item);

        }else{
            //create a new item stack and add it to the array list
            ItemStack newStack = new ItemStack(item);

            itemStacks.add(newStack);

        }

    }

    @Override
    public void removeItem(Item item) {

        ItemStack itemStack = findStack(item);

        if(itemStack != null){
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


        for (int i = 0; i < itemStacks.size(); i++) {
            for (int j = 0; j < itemStacks.get(i).getCount(); j++) {
                itemsArray.add(itemStacks.get(i).getItem());
            }
        }

        return itemsArray;
    }

    public ArrayList<ItemStack> getItemStacks() {
        return itemStacks;
    }

    private ItemStack findStack(Item item){

        for (int i = 0; i < itemStacks.size(); i++) {

            if(itemStacks.get(i).getItem().getClass().equals(item.getClass()) && !itemStacks.get(i).isFull()){

                return itemStacks.get(i);

            }

        }

        return null;

    }
}
