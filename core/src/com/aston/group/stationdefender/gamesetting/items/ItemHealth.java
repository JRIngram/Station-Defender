package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Unit;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.utils.TextureManager;

public class ItemHealth extends Item {

    /**
     * `
     * Construct a new Health Potion Item
     */
    public ItemHealth() {
        super("Wooden Blockade", true);
        id = 6;
        cost = 3;
        value = 25;
        //TODO health texture needed
        texture = TextureManager.INSTANCE.loadTexture(8);
        placeable = false;
    }

    @Override
    public void useItem(ItemCallback itemCallback) {
        if (itemCallback != null)
            itemCallback.onUse(placeable);

    }

    @Override
    public Unit getPlaceableUnit() {
        return null;
    }
}