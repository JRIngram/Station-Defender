package com.aston.group.stationdefender.gamesetting;

import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.callbacks.PlayerCallback;
import com.aston.group.stationdefender.callbacks.QuickSlotCallback;
import com.aston.group.stationdefender.gamesetting.items.Item;
import com.aston.group.stationdefender.gamesetting.items.ItemBlank;
import com.aston.group.stationdefender.gamesetting.items.ItemCredit;
import com.aston.group.stationdefender.utils.resources.Inventory;
import com.aston.group.stationdefender.utils.resources.PlayerInventory;
import com.aston.group.stationdefender.utils.resources.QuickSlot;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.ArrayList;

/**
 * Skeleton Player class
 *
 * @author Jonathon Fitch
 * @author Mohammed Foysal
 */
public class Player implements InputProcessor {

    //Player Properties
    private Item currentItem;
    private Inventory inventory;
    private int score;
    private int money;
    private PlayerCallback playerCallback;

    //Quickslots
    private ArrayList<QuickSlot> quickSlots;
    private int selectedSlot = 0;
    private QuickSlotCallback quickSlotCallback;

    //Graphics Variables
    private SpriteBatch batch;
    private BitmapFont font;

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
            if (i % 2 == 0) {
                quickSlot.setItem(new ItemCredit());
            } else {
                quickSlot.setItem(new ItemBlank());
            }
            quickSlots.add(quickSlot);
            slotX += 48;
        }

        quickSlotCallback = new QuickSlotCallback() {
            @Override
            public void onSelectedItemChanged(Item item) {
                currentItem = item;
            }
        };

        //Font Init
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Roboto-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 16;
        font = generator.generateFont(parameter);
        generator.dispose();
    }

    public void render(float delta) {
        //Render Player's current item (if any)
        if (currentItem != null) {
            currentItem.setX(Gdx.input.getX() - (currentItem.getWidth() / 2));
            currentItem.setY((Gdx.graphics.getHeight() - Gdx.input.getY()) - currentItem.getHeight() / 2);
            currentItem.render(delta);
        }

        //Render Quick Slots
        for (int i = 0; i < quickSlots.size(); i++) {

            //Update Selected
            if (selectedSlot == i) {
                quickSlots.get(i).setSelected(true);
            } else {
                quickSlots.get(i).setSelected(false);
            }

            //Render
            quickSlots.get(i).render(delta);
        }

        //Render Player Stats
        batch.begin();
        font.setColor(Color.BLACK);
        font.draw(batch, "Score: " + score, Gdx.graphics.getWidth() - 100, 60);
        font.draw(batch, "Money: " + money, Gdx.graphics.getWidth() - 100, 30);
        batch.end();

    }

    public void dispose() {
        batch.dispose();
        for (QuickSlot quickSlot : quickSlots) {
            quickSlot.dispose();
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
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

        quickSlotCallback.onSelectedItemChanged(quickSlots.get(selectedSlot).getItem());

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
    public boolean touchUp(final int screenX, final int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            if (currentItem != null) {
                currentItem.useItem(this, new ItemCallback() {
                    @Override
                    public void onUse(boolean placeable) {
                        if (playerCallback != null && placeable)
                            playerCallback.placeActor(currentItem.getPlaceableActor(), screenX, screenY);
                    }
                });
            }
        }
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
        selectedSlot += amount;

        if (selectedSlot > quickSlots.size() - 1) {
            selectedSlot = 0;
        }

        if (selectedSlot < 0) {
            selectedSlot = quickSlots.size() - 1;
        }

        quickSlotCallback.onSelectedItemChanged(quickSlots.get(selectedSlot).getItem());

        return true;
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

    public PlayerCallback getPlayerCallback() {
        return playerCallback;
    }

    public void setPlayerCallback(PlayerCallback playerCallback) {
        this.playerCallback = playerCallback;
    }
}