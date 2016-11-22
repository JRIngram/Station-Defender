package com.aston.group.stationdefender.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class SoundManager {
    private static final int EXPLOSION_SOUND_ID = 1;
    private static SoundManager instance;

    /**
     * Creates an instance of the SoundManager
     */
    public SoundManager() {
        instance = this;
    }

    /**
     * Returns an instance of the SoundManager
     *
     * @return Returns an instance of the SoundManager
     */
    public static SoundManager getInstance() {
        return instance;
    }

    /**
     * Play the sound matching a given ID
     *
     * @param id The id number of the sound to play
     */
    public void playSound(int id) {
        Music music = null;
        switch (id) {
            case EXPLOSION_SOUND_ID:
                music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Explosion.mp3"));
                break;
            default:
                break;
        }
        if (music != null) {
            music.setLooping(true);
            music.setOnCompletionListener(Music::dispose);
        }
    }
}