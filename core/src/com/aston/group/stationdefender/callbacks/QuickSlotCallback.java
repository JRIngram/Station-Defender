package com.aston.group.stationdefender.callbacks;

import com.aston.group.stationdefender.gamesetting.items.Item;

/**
 * QuickSlotCallback is the callback class for the
 * QuickSlot Inventory system
 *
 * @author Mohammed Foysal
 */
public interface QuickSlotCallback {

    /**
     * The callback to change the selected Item
     *
     * @param item The Item to be selected
     */
    void onSelectedItemChanged(Item item);
}