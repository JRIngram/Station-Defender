package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Mine;
import com.aston.group.stationdefender.actors.Unit;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.utils.TextureManager;

/**
 * ItemBandages represents a Mine Item that can be used within the game
 *
 * @author Mohammed Foysal
 */
public class ItemMine extends Item {

    /**
     * Construct a new Mine Item
     */
    public ItemMine() {
        super("Mine");
        id = 3;
        cost = 10;
        texture = TextureManager.INSTANCE.loadTexture(11);
        placeable = true;
    }

    @Override
    public void useItem(ItemCallback itemCallback) {
        if (itemCallback != null)
            itemCallback.onUse(placeable);
    }

    @Override
    public Unit getPlaceableUnit() {
        return new Mine(0, 0);
    }
}