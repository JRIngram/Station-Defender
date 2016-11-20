package com.aston.group.stationdefender.utils;

import com.badlogic.gdx.math.Vector2;

public class MouseInput {
    private static Vector2 position;

    public static Vector2 getPosition() {
        return position;
    }

    public static void setPosition(Vector2 position) {
        MouseInput.position = position;
    }

    public static int getX() {
        return (int) position.x;
    }

    public static int getY() {
        return (int) position.y;
    }
}