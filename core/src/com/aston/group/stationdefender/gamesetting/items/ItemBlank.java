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

    /**
     * Allows the player to use the Item
     *
     * @param player       The current Player of the game
     * @param itemCallback The ItemCallBack associated with the Item
     */
    @Override
    public void useItem(Player player, ItemCallback itemCallback) {
    }

    /**
     * Returns whether the Item can be placed on the Board
     *
     * @return An Actor that can be placed on the Board, null if the Actor cannot be placed on the Board
     */
    @Override
    public Actor getPlaceableActor() {
        return null;
    }
}