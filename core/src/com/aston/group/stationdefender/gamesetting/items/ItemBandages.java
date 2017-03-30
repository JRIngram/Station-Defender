package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Unit;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.utils.TextureManager;

public class ItemBandages extends Item {

    /**
     * Construct a new Bandages Item
     */
    public ItemBandages() {
        super("Bandages");
        id = 10;
        cost = 3;
        value = 2;
        //TODO bandages texture needed
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