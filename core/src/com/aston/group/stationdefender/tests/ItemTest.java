package com.aston.group.stationdefender.tests;

import com.aston.group.stationdefender.gamesetting.items.ItemCredit;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author Jamie Ingram
 */
public class ItemTest extends Game {
    private SpriteBatch batch;
    private ItemCredit itemCredit;

    @Override
    public void create() {
        batch = new SpriteBatch();
        itemCredit = new ItemCredit();

        //Default items are set to 32x32, but for this test they're increased
        itemCredit.setWidth(256);
        itemCredit.setHeight(256);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        itemCredit.render(0.1f);
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
    }
}