package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Unit;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.utils.TextureManager;

/**
 * ItemWater represents water that can be used within the game
 *
 * @author Mohammed Foysal
 */
public class ItemWater extends Item {

    /**
     * Construct a new Water Bottle Item
     */
    public ItemWater() {
        super("Water bottle");
        id = 10;
        cost = 3;
        value = 2;
        // TODO: Water Bottle item texture needed
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
        // TODO: create a Water Bottle actor
        return null;
    }
}