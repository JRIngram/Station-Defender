package com.aston.group.stationdefender.utils.indicators;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.ListIterator;

public class IndicatorManager {

    private int x, y;
    private SpriteBatch batch;
    private BitmapFont font;
    private ArrayList<Indicator> indicators = new ArrayList<>();

    public IndicatorManager() {
        batch = new SpriteBatch();

        //Initialise Font
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Roboto-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.size = 18;
        font = fontGenerator.generateFont(params);
    }

    public void addIndicator(int damage){
        indicators.add(new Indicator(damage, x, y, x, y + 100, Color.WHITE));
    }

    public void addIndicator(int damage, Color color){
        indicators.add(new Indicator(damage, x, y, x, y + 100, color));
    }

    public void render(float delta, int x, int y){
        this.x = x;
        this.y = y;

        float fadeInTime = .2f;

        ListIterator<Indicator> it = indicators.listIterator();
        while(it.hasNext()){
            Indicator indicator = it.next();
            indicator.setXElapsed(indicator.getXElapsed() + delta);
            indicator.setYElapsed(indicator.getYElapsed() + delta);
            indicator.setFadeElapsed(indicator.getFadeElapsed() + delta);
//            fade = Interpolation.fade.apply((indicator.getFadeElapsed() - 1f) / fadeInTime);
            indicator.setX((int)Interpolation.linear.apply(indicator.getStartX(), indicator.getDestX(), MathUtils.clamp(indicator.getXElapsed() , 0, 1)));
            indicator.setY((int)Interpolation.linear.apply(indicator.getStartY(), indicator.getDestY(), MathUtils.clamp(indicator.getYElapsed() , 0, 1)));

            batch.begin();
            font.setColor(indicator.getColor());
            font.draw(batch, indicator.getDamageText(), x + indicator.getX(), y + indicator.getY() );
            batch.end();

            if(System.currentTimeMillis() - indicator.getTimeDisplayed() > 5000 || indicator.getY() == indicator.getDestY())
                it.remove();

        }

//        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
//            //todo DEBUG remove this
//            addIndicator(100);
//        }

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

    private class Indicator {

        private int damage;
        private int x, y;
        private int startX, startY;
        private int destX, destY;
        private float fadeElapsed = 0;
        private float yElapsed = 0;
        private float xElapsed = 0;
        private long timeDisplayed;
        private Color color;

        public Indicator(int damage, int x, int y, int destX, int destY, Color color) {
            this.damage = damage;
            this.color = color;
            this.x = 0;
            this.y = 0;
            this.destX = destX;
            this.destY = destY;
            startX = 0;
            startY = 0;
            this.destX = (int) (Math.random() * 50);
            this.destY = (int) (Math.random() * 50);
            this.destX *= Math.floor(Math.random()*2) == 1 ? 1 : -1;
            this.destY *= Math.floor(Math.random()*2) == 1 ? 1 : -1;
            timeDisplayed = System.currentTimeMillis();
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

        public float getYElapsed() {
            return yElapsed;
        }


        public void setYElapsed(float yElapsed) {
            this.yElapsed = yElapsed;
        }

        public float getXElapsed() {
            return xElapsed;
        }

        public void setXElapsed(float xElapsed) {
            this.xElapsed = xElapsed;
        }

        public long getTimeDisplayed() {
            return timeDisplayed;
        }

        public void setTimeDisplayed(long timeDisplayed) {
            this.timeDisplayed = timeDisplayed;
        }

        public int getStartX() {
            return startX;
        }

        public void setStartX(int startX) {
            this.startX = startX;
        }

        public int getStartY() {
            return startY;
        }

        public void setStartY(int startY) {
            this.startY = startY;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }
    }

    public void dispose(){

    }

}
