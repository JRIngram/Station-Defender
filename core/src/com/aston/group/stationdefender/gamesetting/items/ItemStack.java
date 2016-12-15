package com.aston.group.stationdefender.gamesetting.items;

/**
 * This class allows the Items to be stacked into a QuickSlot Inventory
 *
 * @author Mohammed Foysal
 */
class ItemStack {
    private static final int maxItems = 64;
    private Item[] items = new Item[maxItems];

    /**
     * Construct a new ItemStack
     */
    public ItemStack() {
    }

    /**
     * Returns the maximum number of Items allowed in the ItemStack
     *
     * @return The maximum number of Items allowed in the ItemStack
     */
    public int getMaxItems() {
        return maxItems;
    }

    /**
     * Returns the Array of Items in the ItemStack
     *
     * @return The Array of Items in the ItemStack
     */
    public Item[] getItems() {
        return items;
    }

    /**
     * Sets the Array of Items to be in the ItemStack
     *
     * @param items The Array of Items to be set in the ItemStack
     */
    public void setItems(Item... items) {
        this.items = items;
    }

    /**
     * Returns the current number of Items in the ItemStack
     *
     * @return The current number of Items in the ItemStack
     */
    public int getCount() {
        return items.length;
    }

    /**
     * Adds an Item to the ItemStack if there are less than the max number
     * of Items already in the stack
     *
     * @param item The Item to add to the Stack
     * @return True if adding the Item was successful, false if not
     */
    public boolean addItem(Item item) {
        if (items.length < maxItems) {
            items[items.length] = item;
            return true;
        } else {
            return false;
        }
    }
}