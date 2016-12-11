package com.aston.group.stationdefender.utils.hud;

import com.badlogic.gdx.utils.Array;

public enum Hud {
    INSTANCE;
    private static final Array<HudElement> hudElements = new Array<>();

    public static void render(float delta) {
        for (HudElement hudElement : hudElements) {
            hudElement.render(delta);
        }
    }

    public static void addHudElement(HudElement hudElement) {
        hudElements.add(hudElement);
    }

    public static void removeHudElement(HudElement hudElement) {
        hudElements.removeValue(hudElement, true);
    }

    public static boolean isNotColliding() {
        for (HudElement hudElement : hudElements) {
            if (hudElement.isColliding())
                return false;
        }
        return true;
    }
}