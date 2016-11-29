package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Actor;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.gamesetting.Player;
import com.aston.group.stationdefender.utils.TextureManager;

/**
 * ItemCredit is the in-game currency used.
 *
 * @author Mohammed Foysal
 */
public class ItemCredit extends Item {
    private int value;

    /**
     * Construct a new ItemCredit
     */
    public ItemCredit() {
        id = 1;
        name = "Credits";
        texture = TextureManager.INSTANCE.loadTexture(10);
        value = 10;
        placeable = true;
    }

    @Override
    public void useItem(Player player, ItemCallback itemCallback) {
        player.addMoney(value);

        if (itemCallback != null)
            itemCallback.onUse(placeable);
    }

    @Override
    public Actor getPlaceableActor() {
        return null;
    }

    /**
     * Returns the value of the Item
     *
     * @return The value of the Item
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of the Item
     *
     * @param value The value of the Item
     */
    public void setValue(int value) {
        this.value = value;
    }
}