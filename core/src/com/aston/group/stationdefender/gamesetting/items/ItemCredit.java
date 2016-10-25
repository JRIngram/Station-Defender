package com.aston.group.stationdefender.gamesetting.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * ItemCredit is the in-game currency used.
 * @author Mohammad Foysal
 */
public class ItemCredit extends Item {

    public ItemCredit() {
        id = 1;
        name = "Credits";
        itemTexture = new Texture(Gdx.files.internal("textures/item-credits.png"));
        sprite = new Sprite(itemTexture, x, y, width, height);
    }
}