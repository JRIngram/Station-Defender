package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Unit;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.utils.TextureManager;
import com.aston.group.stationdefender.utils.resources.Items;

/**
 * ItemHealth represents a Health Item that can be used within the game to give the Tower
 * additional health
 *
 * @author Mohammed Foysal
 */
public class ItemHealth extends Item {

    /**
     * Construct a new Health Potion Item
     */
    public ItemHealth() {
        super("Health");
        id = 9;
        cost = 3;
        health = 25;
        texture = TextureManager.INSTANCE.loadTexture(24);
        placeable = true;
        sku = Items.HEALTH;
    }

    @Override
    public void useItem(ItemCallback itemCallback) {
        if (itemCallback != null)
            itemCallback.onUse(placeable, cost, value, health);
    }

    @Override
    public Unit getPlaceableUnit() {
        return null;
    }
}