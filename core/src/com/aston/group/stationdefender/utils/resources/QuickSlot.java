package com.aston.group.stationdefender.utils.resources;

import com.aston.group.stationdefender.gamesetting.items.Item;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
* QuickSlot class allows the player inventory to
* have a limited number of items and each item takes
* up a slot.
* @author Mohammad Foysal
*/
public class QuickSlot {
    private int x, y, width, height;
    private boolean isSelected;
    private Item item;
    private SpriteBatch batch;
    private TextureRegion trQuickSlot;

    /**
     * Construct a new QuickSlot
     * @param x The X co-ordinate of the QuickSlot
     * @param y The Y co-ordinate of the QuickSlot
     * @param width The width of the QuickSlot
     * @param height The height of the QuickSlot
     */
    public QuickSlot(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        batch = new SpriteBatch();

        Texture textureQuickSlot = new Texture(Gdx.files.internal("uiskin.png"));
        trQuickSlot = new TextureRegion(textureQuickSlot, 1, 20, 27, 20);
    }

    public void render(float delta){
        batch.begin();
        if(isSelected)
            batch.setColor(Color.YELLOW);
        batch.draw(trQuickSlot, x, y, width, height);

        batch.setColor(Color.WHITE);
        if(item != null) {
            item.setX(x);
            item.setX(y);
            item.render(delta);
        }
        batch.end();
    }

    public void dispose(){
        batch.dispose();
    }

	/**
     * Returns the X co-ordinate value of an QuickSlot
     * @return The X co-ordinate value of the QuickSlot
     */
    public int getX() {
        return x;
    }

	/**
     * Sets the X co-ordinate of the QuickSlot
     * @param x The X co-ordinate of the QuickSlot
     */
    public void setX(int x) {
        this.x = x;
    }

	/**
     * Returns the Y co-ordinate value of the item.
     * @return The Y co-ordinate value of the QuickSlot
     */
    public int getY() {
        return y;
    }

	/**
     * Sets the Y co-ordinate of the item
     * @param y The Y co-ordinate of the item
     */
    public void setY(int y) {
        this.y = y;
    }

	/**
     * Returns the width of the QuickSlot
     * @return The width of the QuickSlot
     */
    public int getWidth() {
        return width;
    }

	/**
     * Sets the width of the QuickSlot
     * @param width The width of the QuickSlot
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Returns the height of the QuickSlot
     * @return The height of the QuickSlot
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the Quickslot
     * @param height The height of the QuickSlot
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Returns whether a QuickSlot is selected or not
     * @return whether a QuickSlot is selected or not
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * Sets whether a QuickSlot is selected or not
     * @param selected Whether a QuickSlot is selected or not
     */
    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    /**
     * Returns the Item in the QuickSlot
     * @return The Item in the QuickSlot
     */
    public Item getItem() {
        return item;
    }

    /**
     * Puts an item in a QuickSlot
     * @param item The item to put in the QuickSlot
     */
    public void setItem(Item item) {
        this.item = item;
    }
}
