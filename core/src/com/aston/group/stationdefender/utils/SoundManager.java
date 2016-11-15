package com.aston.group.stationdefender.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class SoundManager {
    public static final int EXPLOSION_SOUND_ID = 1;
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
     * Play the explosion sound
     */
    public void playExplosion() {
        Music music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Explosion.mp3"));
        music.setLooping(true);
        music.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music music) {
                music.dispose();
            }
        });
    }
}