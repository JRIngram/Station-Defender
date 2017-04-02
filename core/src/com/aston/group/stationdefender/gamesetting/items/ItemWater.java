package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Unit;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.utils.TextureManager;

/**
 * ItemBandages represents water that can be used within the game
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
        //TODO water bottle item texture needed
        texture = TextureManager.INSTANCE.loadTexture(8);
        placeable = false;
    }

    @Override
    public void useItem(ItemCallback itemCallback) {
        if (itemCallback != null)
            itemCallback.onUse(placeable, cost, value);
    }

    @Override
    public Unit getPlaceableUnit() {
        return null;
    }
}