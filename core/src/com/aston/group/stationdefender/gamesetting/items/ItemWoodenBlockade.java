package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Unit;
import com.aston.group.stationdefender.actors.Weapon;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.utils.TextureManager;

/**
 * ItemBandages represents a blockade formed of wood blocks that can be used within the game
 *
 * @author Mohammed Foysal
 */
public class ItemWoodenBlockade extends Item {

    /**
     * Construct a new Wooden Blockade Item
     */
    public ItemWoodenBlockade() {
        super("Wooden Blockade");
        id = 5;
        cost = 3;
        //TODO wooden blockade texture needed
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
        //TODO create a blockade actor
        return new Weapon(0, 0);
    }
}