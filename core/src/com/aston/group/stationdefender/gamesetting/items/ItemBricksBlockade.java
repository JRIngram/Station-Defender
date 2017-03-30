package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Unit;
import com.aston.group.stationdefender.actors.Weapon;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.utils.TextureManager;

public class ItemBricksBlockade extends Item {

    /**
     * Construct a new Bricks Blockade Item
     */
    public ItemBricksBlockade() {
        super("Wooden Blockade");
        id = 8;
        cost = 3;
        //TODO bricks blockade texture needed
        texture = TextureManager.INSTANCE.loadTexture(8);
        placeable = true;
    }

    @Override
    public void useItem(ItemCallback itemCallback) {
        if (itemCallback != null)
            itemCallback.onUse(placeable);
    }

    @Override
    public Unit getPlaceableUnit() {
        //TODO create a Bricks blockade actor
        return new Weapon(0, 0);
    }
}