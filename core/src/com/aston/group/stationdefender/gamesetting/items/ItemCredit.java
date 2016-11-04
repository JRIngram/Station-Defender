package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Actor;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.gamesetting.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

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
        itemTexture = new Texture(Gdx.files.internal("textures/item-credits.png"));
        value = 10;
        placeable = true;
    }

    /**
     * Allows the player to use the Item
     *
     * @param player       The current Player of the game
     * @param itemCallback The ItemCallBack associated with the Item
     */
    @Override
    public void useItem(Player player, ItemCallback itemCallback) {
        player.setMoney(player.getMoney() + value);

        if (itemCallback != null)
            itemCallback.onUse(placeable);
    }

    /**
     * Returns whether the Item can be placed on the Board
     *
     * @return An Actor that can be placed on the Board, null if the Actor cannot be placed on the Board
     */
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