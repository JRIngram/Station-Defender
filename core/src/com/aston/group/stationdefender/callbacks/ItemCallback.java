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
     */
    void onUse(boolean placeable);
}