package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Actor;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.utils.TextureManager;

/**
 * ItemCredit is the in-game currency used.
 *
 * @author Mohammed Foysal
 */
public class ItemCredit extends Item {

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
    public void useItem(ItemCallback itemCallback) {
        if (itemCallback != null)
            itemCallback.onUse(placeable);
    }

    @Override
    public Actor getPlaceableActor() {
        return null;
    }
}