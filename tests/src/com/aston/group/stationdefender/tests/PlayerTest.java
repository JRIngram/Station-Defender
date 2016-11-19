package com.aston.group.stationdefender.tests;

import com.aston.group.stationdefender.gamesetting.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author Jamie Ingram
 */
public class PlayerTest {

    @Test
    public void testConstructors() {
        SpriteBatch batch = new SpriteBatch();
        Player player = new Player();
        assertNotNull(batch);
        assertNotNull(player);
    }
}