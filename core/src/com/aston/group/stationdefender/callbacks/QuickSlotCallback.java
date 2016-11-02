package com.aston.group.stationdefender.callbacks;

import com.aston.group.stationdefender.gamesetting.items.Item;

/**
 * QuickSlotCallback is the callback class for the
 * QuickSlot Inventory system
 *
 * @author Mohammad Foysal
 */
public interface QuickSlotCallback {
    void onSelectedItemChanged(Item item);
}