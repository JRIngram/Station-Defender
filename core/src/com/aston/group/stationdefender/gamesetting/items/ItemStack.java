package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.engine.GameEngine;
import com.aston.group.stationdefender.utils.FontManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class allows the Items to be stacked into a QuickSlot Inventory
 *
 * @author Mohammed Foysal
 */
public class ItemStack<T extends Item> implements Iterable<T>{
    private final int maxItems = 64;
    private final ArrayList<T> items = new ArrayList<>();
    private final SpriteBatch batch;
    private final BitmapFont font;
    private int x, y, width, height;

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
     * Returns whether the Items in the list can be contained within the ItemStack
     *
     * @param items The Items to add to the ItemStack
     * @return True if the Items can fit in the ItemStack, false if not
     */
    private boolean canAddItems(List<T> items) {
        return this.items.size() + items.size() <= maxItems;
    }

    /**
     * Adds multiple items to the ItemStack
     *
     * @param items The list of Items to add to the ItemStack
     * @return True if the Items were successfully added to the ItemStack
     */
    public boolean addItems(List<T> items){
        if(canAddItems(items)) {
            this.items.addAll(items);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes an item from the ItemStack
     *
     * @param item The Item to remove from the ItemStack
     */
    public void removeItem(T item){
        items.remove(item);
    }

    /**
     * Gets an Item from the ItemStack
     *
     * @return The Item if it is in the ItemStack, null if it is not
     */
    public T getItem(){
        if(items.size() > 0)
            return items.get(items.size() - 1);
        else
            return null;
    }

    /**
     * Returns whether the ItemStack is full or not
     *
     * @return True if the ItemStack is full, false if it is not
     */
    public boolean isNotFull() {
        return items.size() <= maxItems;
    }

    /**
     * Render the ItemStack
     */
    public void render() {
        if(!items.isEmpty() && items.get(0).getTexture() != null){
            batch.begin();
            batch.draw((items.get(0)).getTexture(), x, y, width, height);
            batch.end();

            //Draw number of items text
            if(items.size() > 1){
                batch.begin();
                font.draw(batch, Integer.toString(items.size()), x + 20, y + 10);
                batch.end();
            }
        }
    }

    /**
     * Returns the number of Items in the ItemStack
     *
     * @return The number of Items in the ItemStack
     */
    public int getCount(){
        return items.size();
    }

    /**
     * Returns the X co-ordinate of the ItemStack
     *
     * @return The X co-ordinate of the ItemStack
     */
    public int getX() {
        return x;
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
     * Returns the Y co-ordinate of the ItemStack
     *
     * @return The Y co-ordinate of the ItemStack
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the Y co-ordinate of the ItemStack
     *
     * @param y The Y co-ordinate of the ItemStack
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns the width of the ItemStack
     *
     * @return The width of the ItemStack
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the ItemStack
     *
     * @param width The width of the ItemStack
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Returns the height of the ItemStack
     *
     * @return The height of the ItemStack
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the ItemStack
     *
     * @param height The height of the ItemStack
     */
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public Iterator<T> iterator() {
        return items.iterator();
    }

    /**
     * Returns the maximum number of Items the ItemStack can hold
     *
     * @return The maximum number of Items the ItemStack can hold
     */
    public int getMaxItems() {
        return maxItems;
    }
}