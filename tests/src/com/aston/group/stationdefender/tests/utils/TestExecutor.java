package com.aston.group.stationdefender.tests.utils;

import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.tests.TestLauncher;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.junit.runner.Computer;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.List;

public class TestExecutor {
    private Computer computer;
    private JUnitCore jUnitCore;

    public TestExecutor() {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = Constants.GAME_NAME;
        config.width = 1;
        config.height = 1;
        config.x = 0;
        config.y = 0;
        new LwjglApplication(new ApplicationAdapter() {
            @Override
            public void create() {
                super.create();
                computer = new Computer();
                jUnitCore = new JUnitCore();
                List<Class<?>> tests = TestLauncher.getTests();
                for (Class<?> cl : tests) {
                    Result result = jUnitCore.run(computer, cl);
                    printResult(cl, result);
                }
                Gdx.app.exit();
            }
        }, config);
    }

    private void printResult(Class<?> cl, Result result) {
        if (result.getFailureCount() == 0) {
            System.out.println(printGreen(cl.getSimpleName() + " - All tests passed"));
        } else {
            System.out.println(cl.getSimpleName() + " - Failures: " + result.getFailureCount());
        }
        for (Failure failure : result.getFailures()) {
            System.out.println(printRed(cl.getSimpleName() + " - " + failure.getTrace()));
        }
    }

    private String printRed(String text) {
        return "\u001B[31m" + text + "\u001B[0m";
    }

    private String printGreen(String text) {
        return "\u001B[32m" + text + "\u001B[0m";
    }
}