package com.aston.group.stationdefender.utils.hud;

import com.aston.group.stationdefender.actors.Weapon;

/**
 * HudWeapon is a HudElement specific to the Weapon objects
 *
 * @author Mohammad Foysal
 */
public class HudWeapon extends HudContainer {
    private Weapon weapon;

    /**
     * Creates a new HudWeapon with default X and Y co-ordinates of '0'
     */
    public HudWeapon() {
        this(0, 0);
    }

    /**
     * Creates a new HudWeapon with given X and Y co-ordinates
     *
     * @param x The X co-ordinate of the HudWeapon
     * @param y The Y co-ordinate of the HudWeapon
     */
    private HudWeapon(int x, int y) {
        super(x, y);
        this.x = x;
        this.y = y;
        title = "Weapon";
        if (weapon != null) {
            title = weapon.getName();
        }
        width = 150;
        height = 160;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        font.draw(batch, "Damage: " + weapon.getDamage(), x + 5, (height + y) - 30);
        restartBatch();
        font.draw(batch, "Cost: " + weapon.getCost(), x + 5, (height + y) - 50);
        restartBatch();
        font.draw(batch, "Range: " + weapon.getRange(), x + 5, (height + y) - 70);
        restartBatch();
        font.draw(batch, "ROF: " + weapon.getRateOfFire(), x + 5, (height + y) - 90);
        batch.end();
    }

    @Override
    public void setObject(Object object) {
        this.weapon = (Weapon) object;
    }

    /**
     * Restarts the SpriteBatch to allow more objects to be drawn on the screen
     */
    private void restartBatch() {
        batch.end();
        batch.begin();
    }
}