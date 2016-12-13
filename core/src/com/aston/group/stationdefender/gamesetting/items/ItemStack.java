package com.aston.group.stationdefender.gamesetting.items;

public class ItemStack {

    public final int maxItems = 64;
    public Item[] items = new Item[maxItems];

    public ItemStack() {

    }

    public int getMaxItems() {
        return maxItems;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public int getCount(){
        return items.length;
    }

    public boolean addItem(Item item){
        if(items.length < 64){
            items[items.length] = item;
            return true;
        }else{
            return false;
        }
    }


}
