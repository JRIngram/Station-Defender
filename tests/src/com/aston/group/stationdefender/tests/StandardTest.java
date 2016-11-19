package com.aston.group.stationdefender.tests;

import com.aston.group.stationdefender.actors.Weapon;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StandardTest {

    @Before
    public void setUp() {

    }

    @Test
    public void testSuccess() {
        int num = 5;
        Assert.assertEquals(num, 5);
    }

    @Test
    public void testGdxContext() {
        Weapon weapon = new Weapon();
        Assert.assertNotNull(weapon);
    }

    @Test
    public void testFails() {
        int num = 0;
        Assert.assertEquals(num, 5);
    }

}
