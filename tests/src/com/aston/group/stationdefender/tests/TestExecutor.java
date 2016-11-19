package com.aston.group.stationdefender.tests;


import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.utils.SoundManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.junit.runner.Computer;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.ArrayList;
import java.util.Arrays;

public class TestExecutor {

    private static final String TAG = "TestExecutor";

    private ArrayList<Class<?>> tests = new ArrayList<>();

    private Computer computer;
    private JUnitCore jUnitCore;


    public TestExecutor(ArrayList<Class<?>> tests) {
        this.tests.addAll(tests);
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = Constants.GAME_NAME;
        config.width = 200;
        config.height = 200;
        new LwjglApplication(new ApplicationAdapter() {
            @Override
            public void create() {
                super.create();
                new SoundManager();

                computer = new Computer();

                jUnitCore = new JUnitCore();

                performTest();

                Gdx.app.exit();
                System.exit(0);
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
        }, config);
    }

    public void addTest(Class<?> test) {
        tests.add(test);
    }

    public void addTests(Class<?>... test) {
        tests.addAll(Arrays.asList(test));
    }

    public void performTest() {

        for (Class<?> cl : tests) {
            Result result = jUnitCore.run(computer, cl);

            printResult(result);
        }

    }

    public void printResult(Result result) {
        if (result.getFailureCount() == 0) {
            System.out.println(printGreen("All tests passed"));
        } else {
            System.out.println("Failures: " + result.getFailureCount());
        }

        for (Failure failure : result.getFailures()
                ) {
            System.out.println(printRed(failure.getTrace()));
        }
    }

    private String printRed(String text) {
        return "\u001B[31m" + text + "\u001B[0m";
    }

    private String printGreen(String text) {
        return "\u001B[32m" + text + "\u001B[0m";
    }

}
