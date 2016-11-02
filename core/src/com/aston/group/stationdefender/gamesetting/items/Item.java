package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Item class represents an in-game item that the player
 * can pick up, buy for credits, and use
 *
 * @author Mohammad Foysal
 */
public abstract class Item {

    //Item Properties
    protected int id;
    protected String name;
    protected boolean usable;
    protected boolean collected;
    protected boolean justSpawned;
    protected boolean placeable;

    //Rendering
    protected Texture itemTexture;
    protected int x, y, width, height;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private int flareX, flareY;

    /**
     * Construct a new Item
     */
    public Item() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        width = 32;
        height = 32;
    }

    /**
     * Construct a new Item with a name and states that can be collected
     * and used
     *
     * @param name      The name of the Item
     * @param usable    Whether the Item is usable or not
     * @param collected Whether the Item has been collected or not
     */
    public Item(String name, boolean usable, boolean collected) {
        this.name = name;
        this.usable = usable;
        this.collected = collected;

        width = 32;
        height = 32;

        batch = new SpriteBatch();
    }

    public void render(float delta) {
        //TODO: add specular effect
        if (itemTexture != null) {
            batch.begin();
            batch.draw(itemTexture, x, y, width, height);
            batch.end();
        }
    }

    /**
     * Allows the player to use the Item
     *
     * @param itemCallback The ItemCallBack associated with the Item
     */
    public void useItem(ItemCallback itemCallback) {
        itemCallback.onUse(placeable);
    }

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
     * Sets whether the Item has been collected or not
     *
     * @param collected Whether the Item has been collected or not
     */
    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    /**
     * Returns the texture of the Item
     *
     * @return The Texture of the Item
     */
    public Texture getItemTexture() {
        return itemTexture;
    }

    /**
     * Sets the Texture of the Item
     *
     * @param itemTexture The Texture of the Item
     */
    public void setItemTexture(Texture itemTexture) {
        this.itemTexture = itemTexture;
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
     *
     * @param justSpawned Whether the Item has just been created or not
     */
    public void setJustSpawned(boolean justSpawned) {
        this.justSpawned = justSpawned;
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
     * Returns the Item ID and Name
     *
     * @return The Item ID and Name
     */
    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}