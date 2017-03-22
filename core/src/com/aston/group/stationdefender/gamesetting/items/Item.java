package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Unit;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.engine.GameEngine;
import com.aston.group.stationdefender.utils.resources.Items;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Item class represents an in-game item that the player
 * can pick up, buy for credits, and use
 *
 * @author Mohammed Foysal
 */
public abstract class Item {
    private final SpriteBatch batch;
    Items sku;
    int id;
    boolean placeable;
    Texture texture;
    int cost;
    int value;
    private String name;
    private boolean usable;
    private boolean collected;
    private boolean justSpawned;
    private int x, y, width, height;
    private int flareX, flareY;

    /**
     * Construct a new Item with a name and states that can be collected
     * and used
     *
     * @param name   The name of the Item
     * @param usable Whether the Item is usable or not
     */
    Item(String name, boolean usable) {
        this.name = name;
        this.usable = usable;
        collected = false;

        width = 32;
        height = 32;
        cost = 0;
        value = 0;
        sku = Items.UNKNOWN;

        batch = GameEngine.getBatch();
    }

    /**
     * Render the Item.
     */
    public void render() {
        if (texture != null) {
            batch.begin();
            batch.draw(texture, x, y, width, height);
            batch.end();
        }
    }

    /**
     * Allows the player to use the Item
     *
     * @param itemCallback The ItemCallBack associated with the Item
     */
    public abstract void useItem(ItemCallback itemCallback);

    /**
     * Abstract method to return whether the Item can be placed on the Level
     *
     * @return A Unit that can be placed on the Level, null if the Unit cannot be placed on the Level
     */
    public abstract Unit getPlaceableUnit();

    /**
     * Changes the collected state of the Item to show it
     * has been collected by the player
     */
    public void collect() {
        collected = true;
    }

    /**
     * Changes the collected state of the item to show it
     * has been dropped by the player
     */
    public void drop() {
        collected = false;
    }

    /**
     * Returns the ID of the Item
     *
     * @return The ID of the item
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the Item ID to a given integer
     *
     * @param id The ID to give the Item
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the Item
     *
     * @return The name of the Item
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Item name to a given string
     *
     * @param name The name to give the Item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return whether the Item can be used by the player
     *
     * @return Whether the Item can be used
     */
    public boolean isUsable() {
        return usable;
    }

    /**
     * Sets whether the Item is usable or not
     *
     * @param usable Whether the Item is usable or not
     */
    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    /**
     * Returns whether the Item has been collected or not
     *
     * @return Whether the Item has been collected or not
     */
    public boolean isCollected() {
        return collected;
    }

    /**
     * Returns the texture of the Item
     *
     * @return The Texture of the Item
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     * Sets the Texture of the Item
     *
     * @param texture The Texture of the Item
     */
    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    /**
     * Returns whether the Item has just been created or not
     *
     * @return Whether the Item has just been created or not
     */
    public boolean isJustSpawned() {
        return justSpawned;
    }

    /**
     * Sets whether the Item has just been created or not
     */
    public void setJustSpawned() {
        this.justSpawned = true;
    }

    /**
     * Returns the X co-ordinate of the Item
     *
     * @return The X co-ordinate of the Item
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the X co-ordinate of the Item
     *
     * @param x The X co-ordinate of the Item
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the Y co-ordinate of the Item
     *
     * @return The Y co-ordinate of the Item
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the Y co-ordinate of the Item
     *
     * @param y The Y co-ordinate of the Item
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns the width of the Item
     *
     * @return The width of the Item
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the Item
     *
     * @param width The width of the Item
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Returns the height of the Item
     *
     * @return The height of the Item
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the Item
     *
     * @param height The height of the Item
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Returns whether the Item is placeable or not
     *
     * @return Whether the Item is placeable or not
     */
    public boolean isPlaceable() {
        return placeable;
    }

    /**
     * Sets whether the Item is placeable or not
     *
     * @param placeable Whether the Item is placeable or not
     */
    public void setPlaceable(boolean placeable) {
        this.placeable = placeable;
    }

    /**
     * Returns the cost of the Item
     *
     * @return The cost of the Item
     */
    public int getCost() {
        return cost;
    }

    /**
     * Returns the value of the Item
     *
     * @return The value of the Item
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the Item ID and Name
     *
     * @return The Item ID and Name
     */
    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name='" + name + '\'' + '}';
    }

    public Items getSku() {
        return sku;
    }

    public void setSku(Items sku) {
        this.sku = sku;
    }
}