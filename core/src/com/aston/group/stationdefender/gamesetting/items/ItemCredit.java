package com.aston.group.stationdefender.gamesetting.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ItemCredit extends Item {

    public ItemCredit() {
        id = 1;
        name = "Credits";
        itemTexture = new Texture(Gdx.files.internal("textures/item-credits.png"));
    }
}
