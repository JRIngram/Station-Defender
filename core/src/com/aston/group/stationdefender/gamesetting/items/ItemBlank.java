package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Unit;
import com.aston.group.stationdefender.callbacks.ItemCallback;

/**
 * ItemBlank represents a Blank Item with
 * an ID of '0' and a name of 'Unknown Item'
 *
 * @author Mohammed Foysal
 */
public class ItemBlank extends Item {

    /**
     * Construct a new ItemBlack
     */
    public ItemBlank() {
        super("Unknown Item", false, false);
        id = 0;
    }

    @Override
    public void useItem(ItemCallback itemCallback) {
    }

    @Override
    public Unit getPlaceableUnit() {
        return null;
    }
}