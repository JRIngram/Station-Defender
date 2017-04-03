package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.CloseCombatWeapon;
import com.aston.group.stationdefender.actors.Unit;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.utils.TextureManager;
import com.aston.group.stationdefender.utils.resources.Items;

/**
 * ItemCloseCombatWeapon represents a CloseCombatWeapon Item that can be used within the game
 *
 * @author Mohammed Foysal
 */
public class ItemCloseCombatWeapon extends Item {

    /**
     * Construct a new CloseCombatWeapon Item
     */
    public ItemCloseCombatWeapon() {
        super("Close Combat Weapon");
        id = 4;
        cost = 15;
        texture = TextureManager.INSTANCE.loadTexture(25);
        placeable = true;
        sku = Items.CLOSE_COMBAT_WEAPON;
    }

    @Override
    public void useItem(ItemCallback itemCallback) {
        if (itemCallback != null)
            itemCallback.onUse(placeable, cost, value, health);
    }

    @Override
    public Unit getPlaceableUnit() {
        return new CloseCombatWeapon(0, 0);
    }
}