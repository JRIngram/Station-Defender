package com.aston.group.stationdefender.utils;

import com.aston.group.stationdefender.gamesetting.helpers.Projectile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

import java.util.Iterator;

/**
 * ProjectileFactory is the controller class for projectiles that can be fired
 *
 * @author Mohammed Foysal
 */
public class ProjectileFactory {
    private final Pool<Projectile> projectilePool = new Pool<Projectile>() {
        @Override
        protected Projectile newObject() {
            return new Projectile();
        }
    };
    private Array<Projectile> projectiles = new Array<Projectile>();
    private Sound sound;

    /**
     * Construct a new ProjectileFactory
     */
    public ProjectileFactory() {
        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/Gun_Shot.mp3"));
    }

    /**
     * Shoots a bullet
     *
     * @param x     The initial X co-ordinate of the bullet
     * @param y     The initial Y co-ordinate of the bullet
     * @param speed The speed of the bullet
     */
    public void shootBullet(int x, int y, int speed) {
        Projectile projectile = projectilePool.obtain();
        projectile.init(x, y, speed);
        projectiles.add(projectile);
        if (projectiles.size< 20)
            sound.play(0.1f);
    }

    /**
     * Render the Projectiles.
     *
     * @param delta - The time in seconds since the last render.
     */
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

    /**
     * Returns the projectiles currently in the projectiles array
     *
     * @return The Array or Projectiles
     */
    public Array<Projectile> getProjectiles() {
        return projectiles;
    }

    /**
     * Sets a new Array of Projectiles to be the current projectiles
     *
     * @param projectiles The Array of Projectiles to be used
     */
    public void setProjectiles(Array<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    /**
     * Dispose of unused resources
     */
    public void dispose(){
        sound.dispose();
    }
}