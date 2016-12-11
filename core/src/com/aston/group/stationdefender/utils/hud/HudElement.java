package com.aston.group.stationdefender.utils.hud;

public abstract class HudElement {
    int x;
    int y;
    int width;
    int height;
    String title;

    public abstract void render(float delta);

    public abstract boolean isColliding();

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setObject(Object object) {
    }
}