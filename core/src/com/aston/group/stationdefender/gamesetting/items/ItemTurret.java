package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Actor;
import com.aston.group.stationdefender.actors.Weapon;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.gamesetting.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ItemTurret extends Item {

    /**
     * Construct a new Turret Item
     */
    public ItemTurret() {
        id = 2;
        name = "Turret";
        cost = 10;
        itemTexture = new Texture(Gdx.files.internal("textures/turret.png"));
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
        if (player != null)
            player.removeMoney(cost);

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
        return new Weapon();
    }
}