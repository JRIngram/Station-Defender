package com.aston.group.stationdefender.gamesetting;

import com.aston.group.stationdefender.gamesetting.items.Item;
import com.aston.group.stationdefender.gamesetting.items.ItemCredit;
import com.aston.group.stationdefender.utils.resources.Inventory;
import com.aston.group.stationdefender.utils.resources.PlayerInventory;
import com.aston.group.stationdefender.utils.resources.QuickSlot;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class Player implements InputProcessor{

    //Player Properties
    private Item currentItem;
    private Inventory inventory;
    private int score;
    private int money;

    //Quickslots
    private ArrayList<QuickSlot> quickSlots;
    private int selectedSlot = 0;

    //Graphics Variables
    private SpriteBatch batch;

    public Player() {
        batch = new SpriteBatch();

        inventory = new PlayerInventory();
        score = 0;
        money = 0;

        inventory.addItem(new ItemCredit());

        currentItem = new ItemCredit();

        //Quick Slots
        quickSlots = new ArrayList<QuickSlot>();
        int slotX = 0;
        for (int i = 0; i < 8; i++) {
            QuickSlot quickSlot = new QuickSlot(slotX, 0, 48, 48);
            quickSlot.setItem(new ItemCredit());

            quickSlots.add(quickSlot);
            slotX += 48;
        }
    }

    public void render(float delta){
        //Render Quick Slots
        for (int i = 0; i < quickSlots.size(); i++) {
            //Update Quickslot Items to inventory
//            if(inventory.getItem(i) != null){
//                quickSlots.get(i).setItem(inventory.getItem(i));
//            }

            //Update Selected
            if(selectedSlot == i){
                quickSlots.get(i).setSelected(true);
            }else{
                quickSlots.get(i).setSelected(false);
            }

            //Render
            quickSlots.get(i).render(delta);
        }

        //Render Player's current item (if any)
        if(currentItem != null) {
            currentItem.setX(Gdx.input.getX() - (currentItem.getWidth() / 2));
            currentItem.setY((Gdx.graphics.getHeight() - Gdx.input.getY()) - currentItem.getHeight() / 2);

            currentItem.render(delta);
        }
    }

    public void dispose(){
        batch.dispose();

        for (int i = 0; i < quickSlots.size(); i++) {
            quickSlots.get(i).dispose();
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode){
            case Input.Keys.NUM_1:
                selectedSlot = 0;
                break;
            case Input.Keys.NUM_2:
                selectedSlot = 1;
                break;
            case Input.Keys.NUM_3:
                selectedSlot = 2;
                break;
            case Input.Keys.NUM_4:
                selectedSlot = 3;
                break;
            case Input.Keys.NUM_5:
                selectedSlot = 4;
                break;
            case Input.Keys.NUM_6:
                selectedSlot = 5;
                break;
            case Input.Keys.NUM_7:
                selectedSlot = 6;
                break;
            case Input.Keys.NUM_8:
                selectedSlot = 7;
                break;


        }

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println("Touched Up");
        selectedSlot = 1;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public void collectItem(Item item) {
        inventory.addItem(item);
    }

    public void collectItems(ArrayList<Item> items) {
        inventory.addAllItems(items);
    }

    public void dropItem(Item item) {
        inventory.removeItem(item);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Item getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(Item currentItem) {
        this.currentItem = currentItem;
    }
}