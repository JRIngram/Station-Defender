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

    @Override
    public void useItem(Player player, ItemCallback itemCallback) {
        if (player != null)
            player.removeMoney(cost);

        if (itemCallback != null)
            itemCallback.onUse(placeable);
    }

    @Override
    public Actor getPlaceableActor() {
        return new Weapon();
    }
}