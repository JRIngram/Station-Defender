package com.aston.group.stationdefender.utils.resources;

import com.aston.group.stationdefender.gamesetting.items.Item;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class QuickSlot {

    private int x, y, width, height;
    private boolean isSelected;

    private Item item;

    private SpriteBatch batch;
    private TextureRegion trQuickSlot;

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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
