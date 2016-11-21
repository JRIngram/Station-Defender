package com.aston.group.stationdefender.gamesetting;

import com.aston.group.stationdefender.actors.Alien;
import com.aston.group.stationdefender.callbacks.LevelCallback;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class TestLevel extends Level {

    private Alien alien;

    public TestLevel(Player player, LevelCallback levelCallback) {
        super(player, levelCallback);
        init();
    }

    public TestLevel(Player player, LevelCallback levelCallback, int levelNumber) {
        super(player, levelCallback, levelNumber);
        init();
    }

    private void init(){
        alien = new Alien();
        alien.setX(400);
        alien.setY(400);
    }

    @Override
    public void render(float delta) {
//        super.render(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        alien.render(delta);
        alien.setX(400);
        alien.setY(200);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
