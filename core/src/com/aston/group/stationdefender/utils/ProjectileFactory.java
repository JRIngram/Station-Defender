package com.aston.group.stationdefender.utils;

import com.aston.group.stationdefender.gamesetting.helpers.Projectile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Pool;

import java.util.ArrayList;
import java.util.Iterator;

public class ProjectileFactory {

    private final Pool<Projectile> projectilePool = new Pool<Projectile>() {
        @Override
        protected Projectile newObject() {
            return new Projectile();
        }
    };
    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    private Sound sound;

    public ProjectileFactory() {
        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/Gun_Shot.mp3"));
    }

    public void shootBullet(int x, int y, int speed) {
        Projectile projectile = projectilePool.obtain();
        projectile.init(x, y, speed);
        projectiles.add(projectile);
        if(projectiles.size() < 20)
        sound.play(0.1f);
    }

    public void render(float delta) {
        for (Projectile projectile1 : projectiles) {
            projectile1.render(delta);
        }

        //Remove Dead Projectiles
        Iterator<Projectile> projectileIterator = projectiles.iterator();
        while (projectileIterator.hasNext()) {
            Projectile projectile = projectileIterator.next();

            if (!projectile.isAlive()) {
                projectileIterator.remove();
                projectilePool.free(projectile);
            }
        }
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    public void dispose(){
        sound.dispose();
    }
}