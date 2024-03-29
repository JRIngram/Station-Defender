package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Unit;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.utils.TextureManager;

/**
 * ItemCement represents a cement Item that can be used within the game
 *
 * @author Mohammed Foysal
 */
public class ItemCement extends Item {

    /**
     * Construct a Cement Item
     */
    public ItemCement() {
        super("Cement");
        id = 8;
        cost = 3;
        // TODO: cement texture needed
        texture = TextureManager.INSTANCE.loadTexture(8);
        placeable = false;
    }

    @Override
    public void useItem(ItemCallback itemCallback) {
        if (itemCallback != null)
            itemCallback.onUse(placeable, cost, value, health);
    }

    @Override
    public Unit getPlaceableUnit() {
        // TODO: create a Cement actor
        return null;
    }
}