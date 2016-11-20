package com.aston.group.stationdefender.tests.tests;

import com.aston.group.stationdefender.gamesetting.items.ItemCredit;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author Jamie Ingram
 */
public class ItemTest {

    @Test
    public void testConstructor() {
        SpriteBatch batch = new SpriteBatch();
        ItemCredit itemCredit = new ItemCredit();
        assertNotNull(batch);
        assertNotNull(itemCredit);

        //Default items are set to 32x32, but for this test they're increased
        itemCredit.setWidth(256);
        itemCredit.setHeight(256);
    }
}