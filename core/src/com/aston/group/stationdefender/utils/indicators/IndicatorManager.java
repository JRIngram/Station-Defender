package com.aston.group.stationdefender.utils.indicators;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

/**
 * This is a manager class for Indicators
 *
 * @author Mohammad Foysal
 */
public class IndicatorManager {
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final Array<Indicator> indicators = new Array<>();
    private int x, y;

    /**
     * Create a new IndicatorManager
     */
    public IndicatorManager() {
        batch = new SpriteBatch();

        //Initialise Font
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Roboto-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.size = 18;
        font = fontGenerator.generateFont(params);
    }

    /**
     * Add a new Indicator with a specified damage and colour
     *
     * @param damage The damage to be displayed on the Indicator
     * @param color  The colour the Indicator will appear in
     */
    public void addIndicator(int damage, Color color) {
        indicators.add(new Indicator(damage, x, y, x, y + 100, color));
    }

    /**
     * Render the Indicator on screen
     *
     * @param delta The time in seconds since the last render
     * @param x     The X co-ordinate of the Indicator
     * @param y     The Y co-ordinate of the Indicator
     */
    public void render(float delta, int x, int y) {
        this.x = x;
        this.y = y;

        Iterator<Indicator> it = indicators.iterator();
        while (it.hasNext()) {
            Indicator indicator = it.next();
            indicator.setXElapsed(indicator.getXElapsed() + delta);
            indicator.setYElapsed(indicator.getYElapsed() + delta);
            indicator.setFadeElapsed(indicator.getFadeElapsed() + delta);
            indicator.setX((int) Interpolation.linear.apply(indicator.getStartX(), indicator.getDestX(), MathUtils.clamp(indicator.getXElapsed(), 0, 1)));
            indicator.setY((int) Interpolation.linear.apply(indicator.getStartY(), indicator.getDestY(), MathUtils.clamp(indicator.getYElapsed(), 0, 1)));

            batch.begin();
            font.setColor(indicator.getColor());
            font.draw(batch, indicator.getDamageText(), x + indicator.getX(), y + indicator.getY());
            batch.end();

            if (System.currentTimeMillis() - indicator.getTimeDisplayed() > 5000 || indicator.getY() == indicator.getDestY())
                it.remove();
        }
    }

    /**
     * Returns the X co-ordinate of the Unit
     *
     * @return The X co-ordinate of the Unit
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the X co-ordinate of the Unit
     *
     * @param x The X co-ordinate of the Unit
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the Y co-ordinate of the Unit
     *
     * @return The Y co-ordinate of the Unit
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the Y co-ordinate of the Unit
     *
     * @param y The Y co-ordinate of the Unit
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Dispose of unneeded assets
     */
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    /**
     * This class is a private inner class that holds the Indicator object
     *
     * @author Mohammad Foysal
     */
    private class Indicator {
        private final int damage;
        private final int startX;
        private final int startY;
        private final long timeDisplayed;
        private int x, y;
        private int destX, destY;
        private float fadeElapsed = 0;
        private float yElapsed = 0;
        private float xElapsed = 0;
        private Color color;

        /**
         * Create a new Indicator object
         *
         * @param damage The damage to be displayed on the Indicator
         * @param x      The X co-ordinate of the Indicator
         * @param y      The Y co-ordinate of the Indicator
         * @param destX  The destination X co-ordinate of the Indicator
         * @param destY  The destination Y co-ordinate of the Indicator
         * @param color  The colour of the Indicator
         */
        Indicator(int damage, int x, int y, int destX, int destY, Color color) {
            this.damage = damage;
            this.color = color;
            this.x = x;
            this.y = y;
            this.destX = destX;
            this.destY = destY;
            startX = 0;
            startY = 0;
            this.destX = (int) (Math.random() * 50);
            this.destY = (int) (Math.random() * 50);
            this.destX *= Math.floor(Math.random() * 2) == 1 ? 1 : -1;
            this.destY *= Math.floor(Math.random() * 2) == 1 ? 1 : -1;
            timeDisplayed = System.currentTimeMillis();
        }

        /**
         * Returns the X co-ordinate of the Unit
         *
         * @return The X co-ordinate of the Unit
         */
        public int getX() {
            return x;
        }

        /**
         * Sets the X co-ordinate of the Unit
         *
         * @param x The X co-ordinate of the Unit
         */
        public void setX(int x) {
            this.x = x;
        }

        /**
         * Returns the Y co-ordinate of the Unit
         *
         * @return The Y co-ordinate of the Unit
         */
        public int getY() {
            return y;
        }

        /**
         * Sets the Y co-ordinate of the Unit
         *
         * @param y The Y co-ordinate of the Unit
         */
        public void setY(int y) {
            this.y = y;
        }

        /**
         * Returns the destination X co-ordinate of the Unit
         *
         * @return The destination X co-ordinate of the Unit
         */
        int getDestX() {
            return destX;
        }

        /**
         * Returns the destination Y co-ordinate of the Unit
         *
         * @return The destination Y co-ordinate of the Unit
         */
        int getDestY() {
            return destY;
        }

        /**
         * Returns the damage text of the Indicator
         *
         * @return The damage text of the Indicator
         */
        String getDamageText() {
            return "-" + damage;
        }

        /**
         * Returns the fade elapsed time
         *
         * @return The fade elapsed time
         */
        float getFadeElapsed() {
            return fadeElapsed;
        }

        /**
         * Sets the fade elapsed time
         *
         * @param fadeElapsed The fade elapsed time to be set
         */
        void setFadeElapsed(float fadeElapsed) {
            this.fadeElapsed = fadeElapsed;
        }

        /**
         * Returns the elapsed Y co-ordinate of the Indicator
         *
         * @return The elapsed Y co-ordinate of the Indicator
         */
        float getYElapsed() {
            return yElapsed;
        }

        /**
         * Sets the elapsed Y co-ordinate of the Indicator
         *
         * @param yElapsed The elapsed Y co-ordinate of the Indicator to be set
         */
        void setYElapsed(float yElapsed) {
            this.yElapsed = yElapsed;
        }

        /**
         * Returns the elapsed X co-ordinate of the Indicator
         *
         * @return The elapsed X co-ordinate of the Indicator
         */

        float getXElapsed() {
            return xElapsed;
        }

        /**
         * Sets the elapsed X co-ordinate of the Indicator
         *
         * @param xElapsed The elapsed X co-ordinate of the Indicator to be set
         */
        void setXElapsed(float xElapsed) {
            this.xElapsed = xElapsed;
        }

        /**
         * Returns the time that the Indicator was displayed
         *
         * @return The time that the Indicator was displayed
         */
        long getTimeDisplayed() {
            return timeDisplayed;
        }

        /**
         * Returns the start X co-ordinate of the Indicator
         *
         * @return The start X co-ordinate of the Indicator
         */
        int getStartX() {
            return startX;
        }

        /**
         * Returns the start Y co-ordinate of the Indicator
         *
         * @return The start Y co-ordinate of the Indicator
         */
        int getStartY() {
            return startY;
        }

        /**
         * Returns the colour of the Indicator
         *
         * @return The colour of the Indicator
         */
        Color getColor() {
            return color;
        }
    }
}