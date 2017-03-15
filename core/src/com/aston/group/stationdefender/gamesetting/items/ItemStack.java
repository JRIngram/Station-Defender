package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.engine.GameEngine;
import com.aston.group.stationdefender.utils.FontManager;
import com.badlogic.gdx.Gdx;
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

    public ArrayList<T> items = new ArrayList<>();

    private final int maxItems = 64;
    private int x, y, width, height;
    private SpriteBatch batch;
    private BitmapFont font;

    public ItemStack(T item) {
        addItem(item);

        init();
    }

    public ItemStack(List<T> items) {
        addItems(items);

        init();
    }

    private void init(){
        batch = GameEngine.getBatch();

        font = FontManager.getFont(16);

        width = 32;
        height = 32;
    }

    public boolean addItem(T item){
        if(items.size() < maxItems) {
            items.add(item);
            return true;
        }else{
            return false;
        }
    }

    public boolean canAddItems(List<T> items) {
        if(this.items.size() + items.size() <= maxItems) {
            return true;
        }else{
            return false;
        }
    }

    public boolean addItems(List<T> items){
        if(canAddItems(items)) {
            this.items.addAll(items);
            return true;
        }else{
            return false;
        }
    }

    public void removeItem(T item){
        items.remove(item);
    }

    public T getItem(){
        if(items.size() > 0)
            return items.get(items.size() - 1);
        else
            return null;
    }

    public boolean isEmpty(){
        if(items.size() == 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean isFull(){
        if(items.size() >= maxItems){
            return true;
        }else{
            return false;
        }
    }

    public boolean isSingle(){
        if(items.size() == 1){
            return true;
        }else{
            return false;
        }
    }

    public void render(float delta){
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

    public int getCount(){
        return items.size();
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
    public Iterator<T> iterator() {
        return items.iterator();
    }

    public int getMaxItems() {
        return maxItems;
    }
}