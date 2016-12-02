package com.aston.group.stationdefender.utils.resources;

import com.aston.group.stationdefender.gamesetting.items.Item;
import com.aston.group.stationdefender.utils.FontManager;
import com.aston.group.stationdefender.utils.TextureManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * QuickSlot class allows the player inventory to
 * have a limited number of items and each item takes
 * up a slot.
 *
 * @author Mohammed Foysal
 */
public class QuickSlot {
    private final SpriteBatch batch;
    private final TextureRegion trQuickSlot;
    private final TextureRegion trQuickSlotHovered;
    private final BitmapFont font;
    private int x, y, width, height;
    private boolean isSelected;
    private Item item;

    /**
     * Construct a new QuickSlot
     *
     * @param x      The X co-ordinate of the QuickSlot
     * @param y      The Y co-ordinate of the QuickSlot
     * @param width  The width of the QuickSlot
     * @param height The height of the QuickSlot
     */
    public QuickSlot(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        batch = new SpriteBatch();
        Texture texture = TextureManager.INSTANCE.loadTexture(5);
        trQuickSlot = new TextureRegion(texture, 78, 29, 20, 20);
        trQuickSlotHovered = new TextureRegion(texture, 57, 29, 20, 20);
        font = FontManager.getFont(16);
    }

    /**
     * Render the QuickSlot.
     *
     * @param delta - The time in seconds since the last render.
     */
    public void render(float delta) {

        //Draw Hovered Batch
        if (isSelected) {
            batch.begin();
            batch.draw(trQuickSlotHovered, x, y, width, height);
            batch.end();
        } else {
            batch.begin();
            batch.draw(trQuickSlot, x, y, width, height);
            batch.end();
        }

        batch.begin();
        if (item != null) {
            item.setX(x + (width / 5));
            item.setY(y + (height / 6));
            item.render(delta);
        }
        batch.end();

        if (isColliding(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 1, 1)) {
            batch.begin();
            font.setColor(Color.BLACK);
            font.draw(batch, "Item Title", x, y + height + 20);
            batch.end();
        }
    }

    /**
     * Dispose of the resources created
     */
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    /**
     * Returns the X co-ordinate value of an QuickSlot
     *
     * @return The X co-ordinate value of the QuickSlot
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the X co-ordinate of the QuickSlot
     *
     * @param x The X co-ordinate of the QuickSlot
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the Y co-ordinate value of the item.
     *
     * @return The Y co-ordinate value of the QuickSlot
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the Y co-ordinate of the item
     *
     * @param y The Y co-ordinate of the item
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns the width of the QuickSlot
     *
     * @return The width of the QuickSlot
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the QuickSlot
     *
     * @param width The width of the QuickSlot
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Returns the height of the QuickSlot
     *
     * @return The height of the QuickSlot
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the QuickSlot
     *
     * @param height The height of the QuickSlot
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Returns whether a QuickSlot is selected or not
     *
     * @return whether a QuickSlot is selected or not
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * Sets whether a QuickSlot is selected or not
     *
     * @param selected Whether a QuickSlot is selected or not
     */
    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    /**
     * Returns the Item in the QuickSlot
     *
     * @return The Item in the QuickSlot
     */
    public Item getItem() {
        return item;
    }

    /**
     * Puts an item in a QuickSlot
     *
     * @param item The item to put in the QuickSlot
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Checks whether the params collides with the QuickSlot box
     *
     * @param x      The x to be compared with the QuickSlot
     * @param y      The y to be compared with the QuickSlot
     * @param width  The width to be compared with the QuickSlot
     * @param height The height to be compared with the QuickSlot
     * @return isColliding - returns true if params collide, false if not
     */
    private boolean isColliding(int x, int y, int width, int height) {
        return x + width > this.x && x < this.x + this.width && y + height > this.y && y < this.y + this.height;
    }
}
