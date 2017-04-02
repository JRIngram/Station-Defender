package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Unit;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.utils.TextureManager;

/**
 * ItemBandages represents a brick Item that can be used within the game
 *
 * @author Mohammed Foysal
 */
public class ItemBricks extends Item {

    /**
     * Construct a new Bricks Item
     */
    public ItemBricks() {
        super("Bricks");
        id = 6;
        cost = 3;
        //TODO Bricks texture needed
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
        //TODO create a blockade actor
        return null;
    }
}