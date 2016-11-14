package com.aston.group.stationdefender.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class SoundManager {

    public static final int EXPLOSION_SOUND_ID = 1;

    public static SoundManager instance;

    public SoundManager() {
        instance = this;
    }

    public static SoundManager getInstance() {
        return instance;
    }

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
