package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Actor;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.gamesetting.Player;

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
        id = 0;
        name = "Unknown Item";
    }

    @Override
    public void useItem(Player player, ItemCallback itemCallback) {

    }

    @Override
    public Actor getPlaceableActor() {
        return null;
    }
}