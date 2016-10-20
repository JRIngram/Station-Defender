package com.aston.group.stationdefender.tests;

import com.aston.group.stationdefender.gamesetting.Player;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerTest extends Game{

    private SpriteBatch batch;
    private Player player;

    @Override
    public void create() {
        batch = new SpriteBatch();

        player = new Player();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    }

    @Override
    public void dispose() {
        super.dispose();

        batch.dispose();
    }

}
