package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.RapidFireWeapon;
import com.aston.group.stationdefender.actors.Unit;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.utils.TextureManager;
import com.aston.group.stationdefender.utils.resources.Items;

/**
 * ItemRapidFireTurret represents a RapidFireTurret Item that can be used within the game
 *
 * @author Mohammed Foysal
 */
public class ItemRapidFireTurret extends Item {

    /**
     * Construct a new Rapid Fire Turret Item
     */
    public ItemRapidFireTurret() {
        super("Rapid Fire Turret");
        id = 3;
        cost = 10;
        texture = TextureManager.INSTANCE.loadTexture(13);
        placeable = true;
        sku = Items.RAPID_FIRE_WEAPON;
    }

    @Override
    public void useItem(ItemCallback itemCallback) {
        if (itemCallback != null)
            itemCallback.onUse(placeable, cost, value);
    }

    @Override
    public Unit getPlaceableUnit() {
        return new RapidFireWeapon(0, 0);
    }
}