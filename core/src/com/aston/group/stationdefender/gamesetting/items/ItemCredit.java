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

    @Override
    public void useItem(Player player, ItemCallback itemCallback) {
        player.setMoney(player.getMoney() + value);

        if (itemCallback != null)
            itemCallback.onUse(placeable);
    }

    @Override
    public Actor getPlaceableActor() {
        return null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}