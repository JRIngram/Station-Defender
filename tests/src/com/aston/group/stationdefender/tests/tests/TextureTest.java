package com.aston.group.stationdefender.tests.tests;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TextureTest {

    @Test
    public void testTexture() {
        Texture texture = new Texture(512, 512, Pixmap.Format.RGB888);
        assertNotNull(texture);
    }
}