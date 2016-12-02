package com.aston.group.stationdefender.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * This enum manages fonts for the game
 *
 * @author Jonathon Fitch
 */
public enum FontManager {
    INSTANCE;
    private static final FreeTypeFontGenerator fontGenerator;
    private static final FreeTypeFontGenerator.FreeTypeFontParameter params;

    static {
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Roboto-Regular.ttf"));
        params = new FreeTypeFontGenerator.FreeTypeFontParameter();
    }

    /**
     * Returns a new BitmapFont with a specified size
     *
     * @param size The size of the font
     * @return The new BitmapFont generated
     */
    public static BitmapFont getFont(int size) {
        params.size = size;
        return fontGenerator.generateFont(params);
    }
}