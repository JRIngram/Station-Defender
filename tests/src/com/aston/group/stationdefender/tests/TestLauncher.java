package com.aston.group.stationdefender.tests;

import java.util.ArrayList;

public class TestLauncher {

    /**
     * MAKE SURE TO CHANGE WORKING DIRECTORY TO CORE/ASSETS
     **/

    public TestLauncher() {

        ArrayList<Class<?>> classes = new ArrayList<>();

        /** INSERT YOUR TEST CLASSES HERE**/
        classes.add(WeaponTest.class);

        new TestExecutor(classes);

    }

    public static void main(String[] args) {
        new TestLauncher();
    }
}
