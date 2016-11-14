package com.aston.group.stationdefender.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Pool;

import java.util.ArrayList;
import java.util.HashMap;

public class SoundManager {

    public static final int EXPLOSION_SOUND_ID = 1;

    public static SoundManager instance;

    public SoundManager() {
        instance = this;
    }

    public void playExplosion(){
        Music music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Explosion.mp3"));
        music.setLooping(true);
        music.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music music) {
                music.dispose();
            }
        });
    }

    public static SoundManager getInstance() {
        return instance;
    }
}
