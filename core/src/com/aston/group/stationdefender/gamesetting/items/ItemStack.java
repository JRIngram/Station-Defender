package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.engine.GameEngine;
import com.aston.group.stationdefender.utils.FontManager;
import com.aston.group.stationdefender.utils.MouseInput;
import com.badlogic.gdx.graphics.Color;
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
    private final Class<? extends Item> itemClass;
    private int x;
    private int y;

    /**
     * Construct a new ItemStack with a specific Item
     *
     * @param item The specific Item to add to the ItemStack
     */
    public ItemStack(T item) {
        itemClass = item.getClass();
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
     * Remove an Item from the ItemStack
     *
     * @param item The Item to remove from the Stack
     */
    public void removeItem(T item) {
        Iterator<T> iterator = items.iterator();

        while (iterator.hasNext()){
            T chosenItem = iterator.next();

            if(chosenItem != null) {
                if (chosenItem.getClass().getName().equals(item.getClass().getName())) {
                    iterator.remove();
                    return;
                }
            }
        }
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
     * Return the Class of the Item the ItemStack holds
     *
     * @return The Class of the Item the ItemStack holds
     */
    public Class<? extends Item> getItemClass() {
        return itemClass;
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
            batch.begin();
            font.draw(batch, Integer.toString(items.size()), x + 20, y + 10);
            batch.end();
        }

        if (isColliding(MouseInput.getX(), MouseInput.getY())) {
            String name;
            if (getItem() == null) {
                name = "Empty";
            } else {
                name = getItem().getName();
            }
            batch.begin();
            font.setColor(Color.BLACK);
            font.draw(batch, name, x - 10, y + height + 19);
            font.setColor(Color.WHITE);
            font.draw(batch, name, x - 10, y + height + 20);
            batch.end();
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