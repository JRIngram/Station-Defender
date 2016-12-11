package com.aston.group.stationdefender.utils.hud;

import com.aston.group.stationdefender.actors.Weapon;

public class HudWeapon extends HudContainer{

    private Weapon weapon;

    public HudWeapon() {
        super();
        title = "Weapon";

        if(weapon != null){
            title = weapon.getName();
        }

        width = 150;
        height = 160;
    }

    public HudWeapon(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        batch.begin();
        font.draw(batch, "Damage: " + weapon.getDamage(), x + 5, (height + y) - 30);
        batch.end();

        batch.begin();
        font.draw(batch, "Cost: " + weapon.getCost(), x + 5, (height + y) - 50);
        batch.end();

        batch.begin();
        font.draw(batch, "Range: " + weapon.getRange(), x + 5, (height + y) - 70);
        batch.end();

        batch.begin();
        font.draw(batch, "ROF: " + weapon.getRateOfFire(), x + 5, (height + y) - 90);
        batch.end();
    }

    @Override
    public void setObject(Object object) {
        this.weapon = (Weapon) object;
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
