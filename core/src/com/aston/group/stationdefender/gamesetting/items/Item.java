package com.aston.group.stationdefender.gamesetting.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Item {

    //Item Properties
    protected int id;
    protected String name;
    protected boolean usable;
    protected boolean collected;
    protected boolean justSpawned;

    //Rendering
    protected Texture itemTexture;
    protected Sprite sprite;
    protected int x, y, width, height;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private int flareX, flareY;

    public Item() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        width = 32;
        height = 32;
    }

    public Item(String name, boolean usable, boolean collected) {
        this.name = name;
        this.usable = usable;
        this.collected = collected;

        width = 32;
        height = 32;

        batch = new SpriteBatch();
    }

    public void render(float delta) {
        //todo add specular effect
        batch.begin();
        batch.draw(itemTexture, x, y, width, height);
        batch.end();
    }

    public void collect() {
        collected = true;
    }

    public void drop() {
        collected = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isUsable() {
        return usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public Texture getItemTexture() {
        return itemTexture;
    }

    public void setItemTexture(Texture itemTexture) {
        this.itemTexture = itemTexture;
    }

    public boolean isJustSpawned() {
        return justSpawned;
    }

    public void setJustSpawned(boolean justSpawned) {
        this.justSpawned = justSpawned;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}