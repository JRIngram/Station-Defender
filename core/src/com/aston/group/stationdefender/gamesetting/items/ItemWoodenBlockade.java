package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Unit;
import com.aston.group.stationdefender.actors.Weapon;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.utils.TextureManager;

/**
 * ItemWoodenBlockade represents a blockade formed of wood blocks that can be used within the game
 *
 * @author Mohammed Foysal
 */
public class ItemWoodenBlockade extends Item {

    /**
     * Construct a new Wooden Blockade Item
     */
    public ItemWoodenBlockade() {
        super("Wooden Blockade");
        id = 11;
        cost = 3;
        // TODO: Wooden Blockade texture needed
        texture = TextureManager.INSTANCE.loadTexture(8);
        placeable = true;
    }

    @Override
    public void useItem(ItemCallback itemCallback) {
        if (itemCallback != null)
            itemCallback.onUse(placeable, cost, value, health);
    }

    @Override
    public Unit getPlaceableUnit() {
        // TODO: create a Wooden Blockade actor
        return new Weapon(0, 0);
    }
}