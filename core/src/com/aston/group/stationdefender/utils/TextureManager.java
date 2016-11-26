package com.aston.group.stationdefender.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * This class manages texture loading for the game
 *
 * @author Jonathon Fitch
 */
public class TextureManager {
    private static final int BACKGROUND_TITLE_TEXTURE = 1;
    private static final int BACKGROUND_TEXTURE = 2;
    private static final int LEVEL_TEXTURE = 3;
    private static final int TILE_TEXTURE = 4;
    private static final int QUICK_SLOT_TEXTURE = 5;
    private static final int DEFAULT_TOWER_TEXTURE = 6;
    private static final int DEFAULT_ALIEN_TEXTURE = 7;
    private static final int DEFAULT_WEAPON_TEXTURE = 8;
    private static final int DEFAULT_PROJECTILE_TEXTURE = 9;
    private static final int ITEM_CREDIT_TEXTURE = 10;
    private static final int ITEM_TURRET_TEXTURE = 11;
    private static TextureManager instance;

    /**
     * Returns an instance of the TextureManager
     *
     * @return Returns an instance of the TextureManager
     */
    public static TextureManager getInstance() {
        if (instance == null)
            instance = new TextureManager();
        return instance;
    }

    /**
     * Load the texture matching a given ID
     *
     * @param id The id number of the texture to load
     */
    public Texture loadTexture(int id) {
        Texture texture = null;
        switch (id) {
            case BACKGROUND_TITLE_TEXTURE:
                texture = new Texture(Gdx.files.internal("textures/intro-back.jpg"));
                break;
            case BACKGROUND_TEXTURE:
                texture = new Texture(Gdx.files.internal("textures/back.jpg"));
                break;
            case LEVEL_TEXTURE:
                texture = new Texture(Gdx.files.internal("textures/level-background.png"));
                break;
            case TILE_TEXTURE:
                texture = new Texture(Gdx.files.internal("textures/tile.png"));
                break;
            case QUICK_SLOT_TEXTURE:
                texture = new Texture(Gdx.files.internal("data/uiskin.png"));
                break;
            case DEFAULT_TOWER_TEXTURE:
                texture = new Texture(Gdx.files.internal("textures/tower.png"));
                break;
            case DEFAULT_ALIEN_TEXTURE:
                texture = new Texture(Gdx.files.internal("textures/enemy.png"));
                break;
            case DEFAULT_WEAPON_TEXTURE:
                texture = new Texture(Gdx.files.internal("textures/turret.png"));
                break;
            case DEFAULT_PROJECTILE_TEXTURE:
                texture = new Texture(Gdx.files.internal("textures/projectile.png"));
                break;
            case ITEM_CREDIT_TEXTURE:
                texture = new Texture(Gdx.files.internal("textures/item-credits.png"));
                break;
            case ITEM_TURRET_TEXTURE:
                texture = new Texture(Gdx.files.internal("textures/turret.png"));
        }
        return texture;
    }
}