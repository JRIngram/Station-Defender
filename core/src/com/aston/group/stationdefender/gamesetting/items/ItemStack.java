package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.engine.GameEngine;
import com.aston.group.stationdefender.utils.FontManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class allows the Items to be stacked into a QuickSlot Inventory
 *
 * @author Mohammed Foysal
 */
public class ItemStack<T extends Item> implements Iterable<T> {
    private final int maxItems = 64;
    private final ArrayList<T> items = new ArrayList<>();
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final int width, height;
    private int x;
    private int y;

    /**
     * Construct a new ItemStack with a specific Item
     *
     * @param item The specific Item to add to the ItemStack
     */
    public ItemStack(T item) {
        addItem(item);
        batch = GameEngine.getBatch();
        font = FontManager.getFont(16);
        width = 32;
        height = 32;
    }

    /**
     * Adds an item to the ItemStack
     *
     * @param item The Item to add to the ItemStack
     */
    public void addItem(T item) {
        if (items.size() < maxItems)
            items.add(item);
    }

    /**
     * Gets an Item from the ItemStack
     *
     * @return The Item if it is in the ItemStack, null if it is not
     */
    public T getItem() {
        if (items.size() > 0)
            return items.get(items.size() - 1);
        else
            return null;
    }

    /**
     * Render the ItemStack
     */
    public void render() {
        if (!items.isEmpty() && items.get(0).getTexture() != null) {
            batch.begin();
            batch.draw((items.get(0)).getTexture(), x, y, width, height);
            batch.end();

            //Draw number of items text
            if (items.size() > 1) {
                batch.begin();
                font.draw(batch, Integer.toString(items.size()), x + 20, y + 10);
                batch.end();
            }
        }
    }

    /**
     * Sets the X co-ordinate of the ItemStack
     *
     * @param x The X co-ordinate of the ItemStack
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the Y co-ordinate of the ItemStack
     *
     * @param y The Y co-ordinate of the ItemStack
     */
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public Iterator<T> iterator() {
        return items.iterator();
    }

    /**
     * Returns whether the ItemStack is full or not
     *
     * @return Whether the ItemStack is full or not
     */
    public boolean isFull() {
        return items.size() >= maxItems;
    }
}