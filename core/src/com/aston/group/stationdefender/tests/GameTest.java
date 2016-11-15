package com.aston.group.stationdefender.tests;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.mockito.Mockito;

public class GameTest {

    private static Application application;

    @BeforeClass
    public static void init(){
        HeadlessApplicationConfiguration headlessApplicationConfiguration = new HeadlessApplicationConfiguration();

        headlessApplicationConfiguration.renderInterval = 16;

        application = new HeadlessApplication(new ApplicationAdapter() {
            @Override
            public void create() {
                super.create();
            }

            @Override
            public void resize(int width, int height) {
                super.resize(width, height);
            }

            @Override
            public void render() {
                super.render();
            }

            @Override
            public void pause() {
                super.pause();
            }

            @Override
            public void resume() {
                super.resume();
            }

            @Override
            public void dispose() {
                super.dispose();
            }
        });



        Gdx.gl20 = Mockito.mock(GL20.class);
        Gdx.gl = Gdx.gl20;
    }

    @AfterClass
    public static void cleanUp(){
        application.exit();
        application = null;
    }

}
