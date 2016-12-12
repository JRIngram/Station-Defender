package com.aston.group.stationdefender.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * This enum manages sound playing for the game
 *
 * @author Mohammed Foysal
 */
public enum SoundManager {
    INSTANCE;
    private static final int BACKGROUND_MUSIC_ID = 1;
    private static final int GUN_SHOT_SOUND_ID = 2;
    private static final int EXPLOSION_SOUND_ID = 3;

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
                music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Background_Music.ogg"));
                music.setLooping(true);
                volume = 0.1f;
                break;
            case GUN_SHOT_SOUND_ID:
                music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Gun_Shot.mp3"));
                volume = 0.05f;
                break;
            case EXPLOSION_SOUND_ID:
                music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Explosion.mp3"));
                volume = 0.07f;
                break;
        }
        if (music != null) {
            music.setVolume(volume);
            music.play();
            music.setOnCompletionListener(Music::dispose);
        }
    }
}