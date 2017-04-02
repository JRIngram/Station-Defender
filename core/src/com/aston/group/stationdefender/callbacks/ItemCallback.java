package com.aston.group.stationdefender.callbacks;

/**
 * ItemCallback is the callback class for Items
 *
 * @author Mohammed Foysal
 */
public interface ItemCallback {

    /**
     * The callback to use an Item
     *
     * @param placeable Whether the Item is placeable or not
     * @param cost The cost of the Item
     * @param value The value of the Item
     */
    void onUse(boolean placeable, int cost, int value);
}