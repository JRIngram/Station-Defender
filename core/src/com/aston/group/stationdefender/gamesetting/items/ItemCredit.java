package com.aston.group.stationdefender.gamesetting.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * ItemCredit is the in-game currency used.
 *
 * @author Mohammad Foysal
 */
public class ItemCredit extends Item {

    /**
     * Construct a new ItemCredit
     */
    public ItemCredit() {
        id = 1;
        name = "Credits";
        itemTexture = new Texture(Gdx.files.internal("textures/item-credits.png"));
    }
}