package com.aston.group.stationdefender.utils.resources;

import com.aston.group.stationdefender.gamesetting.items.Item;
import com.aston.group.stationdefender.utils.FontManager;
import com.aston.group.stationdefender.utils.MouseInput;
import com.aston.group.stationdefender.utils.TextureManager;
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
    private final int x;
    private final int y = 0;
    private final int width = 48;
    private final int height = 48;
    private boolean isSelected;
    private Item item;

    /**
     * Construct a new QuickSlot
     *
     * @param x The X co-ordinate of the QuickSlot
     */
    public QuickSlot(int x) {
        this.x = x;
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

        if (isColliding(MouseInput.getX(), MouseInput.getY())) {
            batch.begin();
            font.setColor(Color.BLACK);
            font.draw(batch, item.getName(), x - 1, y + height + 20);
            font.setColor(Color.WHITE);
            font.draw(batch, item.getName(), x, y + height + 20);
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
     * Returns the Y co-ordinate value of the item.
     *
     * @return The Y co-ordinate value of the QuickSlot
     */
    public int getY() {
        return y;
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
     * Returns the height of the QuickSlot
     *
     * @return The height of the QuickSlot
     */
    public int getHeight() {
        return height;
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
     * @param x The x to be compared with the QuickSlot
     * @param y The y to be compared with the QuickSlot
     * @return isColliding - returns true if params collide, false if not
     */
    private boolean isColliding(int x, int y) {
        return x + 1 > this.x && x < this.x + this.width && y + 1 > this.y && y < this.y + this.height;
    }
}
