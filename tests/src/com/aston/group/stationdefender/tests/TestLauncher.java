package com.aston.group.stationdefender.tests;

import com.aston.group.stationdefender.tests.tests.*;
import com.aston.group.stationdefender.tests.utils.TestExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestLauncher {
    private static final List<Class<?>> tests = new ArrayList<>(Arrays.asList(
            AlienTest.class,
            ItemTest.class,
            PlayerTest.class,
            StandardTest.class,
            TextureTest.class,
            WeaponTest.class
    ));

    private TestLauncher() {
        new TestExecutor(tests);
    }

    public static void main(String[] args) {
        new TestLauncher();
    }
}