package com.aston.group.stationdefender.tests;

import com.aston.group.stationdefender.gamesetting.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author Jamie Ingram
 */
public class PlayerTest extends GdxTestRunner {

    @Test
    public void testConstructors() {
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                SpriteBatch batch = new SpriteBatch();
                Player player = new Player();
                assertNotNull(batch);
                assertNotNull(player);
            }
        });
    }
}