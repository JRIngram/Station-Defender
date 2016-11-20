package com.aston.group.stationdefender.utils.indicators;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Interpolation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class DamageIndicatorManager {

    private int x, y;
    private SpriteBatch batch;
    private BitmapFont font;
    private float fadeElapsed = 0;
    private float yElapsed = 1;
    private ArrayList<Damage> damages = new ArrayList<>();
    private ArrayList<Integer> deadIndexes = new ArrayList<>();

    public DamageIndicatorManager() {
        batch = new SpriteBatch();

        //Initialise Font
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Roboto-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.size = 18;
        font = fontGenerator.generateFont(params);

    }

    public void addIndicator(int damage){
        damages.add(new Damage(damage, x, y, x, y + 100));
    }

    public void render(float delta, int x, int y){
        this.x = x;
        this.y = y;
        // fade in animation
        fadeElapsed += delta;
        yElapsed += delta;
        float moveInTime = .6f;
        float fadeInTime = .2f;
        float fade = Interpolation.fade.apply((fadeElapsed) / fadeInTime);
        float progress = Interpolation.fade.apply((fadeElapsed) / fadeInTime);

        //Damage Indicators
        font.setColor(1, 0.2f, 0.2f, fade);
//        for (int i = 0; i < damages.size(); i++) {
//            batch.begin();
//            fade = Interpolation.fade.apply((fadeElapsed - (fadeDelay + i + 1f)) / fadeInTime);
//            System.out.println(fade);
//            font.setColor(1, 1, 1, fade);
//            font.draw(batch, damages.get(i), x, y + (fade * 100));
//            batch.end();
//        }

            ListIterator<Damage> it = damages.listIterator();
            while(it.hasNext()){
                Damage damage = it.next();
                damage.setFadeElapsed(damage.getFadeElapsed() + delta);
                batch.begin();
                fade = Interpolation.fade.apply((fadeElapsed - 1f) / fadeInTime);
                progress = Interpolation.exp5In.apply((yElapsed - 1f) / moveInTime);
                damage.setX(x);
                damage.setY(y);
//                progress = Interpolation.exp5In.apply(damage.getY(), damage.getDestY(), moveInTime);
//                System.out.println(progress);
                font.setColor(1, 0.2f, 0.2f, 1  -fade);
                font.draw(batch, damage.getDamageText(), damage.getX(), damage.getY() + (progress * 50) );
                batch.end();

                if((fade) == 1)
                    it.remove();

            }
    }

    private class Damage{

        private int damage;
        private int x, y;
        private int destX, destY;
        private float fadeElapsed = 0;
        private float moveElapsed = 0;

        public Damage(int damage, int x, int y, int destX, int destY) {
            this.damage = damage;
            this.x = x;
            this.y = y;
            this.destX = destX;
            this.destY = destY;
        }

        public int getDamage() {
            return damage;
        }

        public void setDamage(int damage) {
            this.damage = damage;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getDestX() {
            return destX;
        }

        public void setDestX(int destX) {
            this.destX = destX;
        }

        public int getDestY() {
            return destY;
        }

        public void setDestY(int destY) {
            this.destY = destY;
        }

        public String getDamageText(){
            return "-" + damage;
        }

        public float getFadeElapsed() {
            return fadeElapsed;
        }

        public void setFadeElapsed(float fadeElapsed) {
            this.fadeElapsed = fadeElapsed;
        }
    }

    public void dispose(){

    }

}
