package com.aston.group.stationdefender.utils.hud;

import com.badlogic.gdx.utils.Array;

/**
 * This class is responsible for the HUD
 *
 * @author Mohammad Foysal
 */
public class Hud {
    private static final Array<HudElement> hudElements = new Array<>();

    /**
     * Render the HudElements
     *
     * @param delta The time in seconds since the last render
     */
    public static void render(float delta) {
        for (HudElement hudElement : hudElements) {
            hudElement.render(delta);
        }
    }

    /**
     * Add a HudElement to the HudElements Array
     *
     * @param hudElement The HudElement to add to the HudElements Array
     */
    public static void addHudElement(HudElement hudElement) {
        hudElements.add(hudElement);
    }

    /**
     * Remove a HudElement from the HudElements Array
     *
     * @param hudElement The HudElement to be removed from the HudElements Array
     */
    public static void removeHudElement(HudElement hudElement) {
        hudElements.removeValue(hudElement, true);
    }

    /**
     * Checks whether a HudElement is colliding with the MouseInput
     *
     * @return true if the HudElement is not colliding with the MouseInput, false if it is
     */
    public static boolean isNotColliding() {
        for (HudElement hudElement : hudElements) {
            if (hudElement.isColliding())
                return false;
        }
        return true;
    }
}