package com.aston.group.stationdefender.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class SoundManager {
    private static final int BACKGROUND_MUSIC_ID = 1;
    private static final int GUN_SHOT_SOUND_ID = 2;
    private static final int EXPLOSION_SOUND_ID = 3;
    private static SoundManager instance;

    /**
     * Creates an instance of the SoundManager
     */
    public SoundManager() {
    }

    /**
     * Returns an instance of the SoundManager
     *
     * @return Returns an instance of the SoundManager
     */
    public static SoundManager getInstance() {
        if (instance == null)
            instance = new SoundManager();
        return instance;
    }

    /**
     * Play the sound matching a given ID
     *
     * @param id The id number of the sound to play
     */
    public void playSound(int id) {
        Music music = null;
        float volume = 0.0f;
        switch (id) {
            case BACKGROUND_MUSIC_ID:
                music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Background_Music.mp3"));
                music.setLooping(true);
                volume = 0.05f;
                break;
            case GUN_SHOT_SOUND_ID:
                music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Gun_Shot.mp3"));
                volume = 0.1f;
                break;
            case EXPLOSION_SOUND_ID:
                volume = 0.1f;
                music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Explosion.mp3"));
                break;
            default:
                break;
        }
        if (music != null) {
            music.setVolume(volume);
            music.play();
            music.setOnCompletionListener(Music::dispose);
        }
    }
}