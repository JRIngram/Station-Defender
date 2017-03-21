package com.aston.group.stationdefender.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

/**
 * This enum manages texture loading for the game
 *
 * @author Jonathon Fitch
 */
public enum TextureManager {
    INSTANCE;
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
    private static final int MINE_WEAPON_TEXTURE = 11;
    private static final int RF_ALIEN_TEXTURE = 12;
    private static final int RF_WEAPON_TEXTURE = 13;
    private static final int KAMIKAZE_ALIEN_TEXTURE = 14;
    private static final int BOSS_ALIEN_1 = 15;
    private static final int BOSS_ALIEN_2 = 16;
    private static final int BOSS_ALIEN_3 = 17;
    private static final int BOSS_ALIEN_4 = 18;


    /**
     * Load the texture matching a given ID
     *
     * @param id The ID number of the texture to load
     * @return The texture matching the ID
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
            case MINE_WEAPON_TEXTURE:
                texture = new Texture(Gdx.files.internal("textures/landmine.png"));
                break;
            case RF_ALIEN_TEXTURE:
                texture = new Texture(Gdx.files.internal("textures/rf-enemy.png"));
                break;
            case RF_WEAPON_TEXTURE:
                texture = new Texture(Gdx.files.internal("textures/rf-turret.png"));
                break;
            case KAMIKAZE_ALIEN_TEXTURE:
                texture = new Texture(Gdx.files.internal("textures/rf-turret.png"));
                break;
            case BOSS_ALIEN_1:
                texture = new Texture(Gdx.files.internal("textures/boss1.png"));
                break;
            case BOSS_ALIEN_2:
                texture = new Texture(Gdx.files.internal("textures/boss2.png"));
                break;
            case BOSS_ALIEN_3:
                texture = new Texture(Gdx.files.internal("textures/boss3.png"));
                break;
            case BOSS_ALIEN_4:
                texture = new Texture(Gdx.files.internal("textures/boss4.png"));
                break;
        }
        return texture;
    }

    /**
     * Load the animation matching a given ID
     *
     * @return The animation matching the given ID
     */
    public ParticleEffect loadParticleEffect() {
        ParticleEffect particleEffect = new ParticleEffect();
        particleEffect.load(Gdx.files.internal("textures/explosion.animation"), Gdx.files.internal(""));
        return particleEffect;
    }
}