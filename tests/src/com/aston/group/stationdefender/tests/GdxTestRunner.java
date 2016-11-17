package com.aston.group.stationdefender.tests;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import static org.mockito.Mockito.mock;

public class GdxTestRunner {
    private static Application application;

    @BeforeClass
    public static void init() {
        Gdx.gl20 = mock(GL20.class);
        Gdx.gl = Gdx.gl20;
        HeadlessApplicationConfiguration conf = new HeadlessApplicationConfiguration();
        conf.renderInterval = 16;
        application = new HeadlessApplication(new ApplicationAdapter() {
        }, conf);
    }

    @AfterClass
    public static void cleanUp() {
        application.exit();
        application = null;
    }
}