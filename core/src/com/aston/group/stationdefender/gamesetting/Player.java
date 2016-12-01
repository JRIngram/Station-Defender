package com.aston.group.stationdefender.gamesetting;

import com.aston.group.stationdefender.callbacks.PlayerCallback;
import com.aston.group.stationdefender.callbacks.QuickSlotCallback;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.gamesetting.items.Item;
import com.aston.group.stationdefender.gamesetting.items.ItemBlank;
import com.aston.group.stationdefender.gamesetting.items.ItemCredit;
import com.aston.group.stationdefender.gamesetting.items.ItemTurret;
import com.aston.group.stationdefender.utils.MouseInput;
import com.aston.group.stationdefender.utils.indicators.IndicatorManager;
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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;

/**
 * Skeleton Player class
 *
 * @author Jonathon Fitch
 * @author Mohammed Foysal
 */
public class Player implements InputProcessor {
    private final Array<QuickSlot> quickSlots;
    private final QuickSlotCallback quickSlotCallback;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final Stage stage;
    private final TextButton menuButton;
    private Item currentItem;
    private Inventory inventory;
    private int score;
    private int money;
    private PlayerCallback playerCallback;
    private int selectedSlot = 0;
    private IndicatorManager moneyIndicator;

    /**
     * Construct a new Player
     */
    public Player() {
        batch = new SpriteBatch();
        inventory = new PlayerInventory();
        score = 0;
        money = Constants.START_MONEY;
        inventory.addItem(new ItemCredit());

        //Quick Slots
        quickSlots = new Array<>();
        int slotX = 0;
        for (int i = 0; i < 8; i++) {
            QuickSlot quickSlot = new QuickSlot(slotX, 0, 48, 48);
            if (i == 0) {
                quickSlot.setItem(new ItemTurret());
            } else {
                quickSlot.setItem(new ItemBlank());
            }
            quickSlots.add(quickSlot);
            slotX += 48;
        }
        quickSlotCallback = item -> currentItem = item;
        if (quickSlots.size > 0)
            currentItem = quickSlots.get(0).getItem();

        //Initialise Font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Roboto-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.size = 16;
        font = generator.generateFont(params);
        params.size = 22;
        BitmapFont buttonFont = generator.generateFont(params);
        generator.dispose();

        //Buttons
        stage = new Stage();
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = buttonFont;
        menuButton = new TextButton(Constants.MENU, textButtonStyle);
        menuButton.setColor(0, 0, 0, 0);
        menuButton.setWidth(400);
        menuButton.setHeight(50);
        stage.addActor(menuButton);
        menuButton.setPosition((Gdx.graphics.getWidth() / 2) + 200, Gdx.graphics.getHeight() - 80);

        moneyIndicator = new IndicatorManager();
    }

    /**
     * Render the Player.
     *
     * @param delta - The time in seconds since the last render.
     */
    public void render(float delta) {
        //Render Player's current item (if any)
        if (currentItem != null) {
            currentItem.setX(Gdx.input.getX() - (currentItem.getWidth() / 2));
            currentItem.setY((Gdx.graphics.getHeight() - Gdx.input.getY()) - currentItem.getHeight() / 2);
            currentItem.render(delta);
        }

        //Render Quick Slots
        for (int i = 0; i < quickSlots.size; i++) {

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
        menuButton.setColor(1, 1, 1, 1);
        font.setColor(Color.BLACK);
        font.draw(batch, "Score: " + score, Gdx.graphics.getWidth() - 99, 60);
        font.draw(batch, "Money: " + money, Gdx.graphics.getWidth() - 99, 30);
        font.setColor(Color.WHITE);
        font.draw(batch, "Score: " + score, Gdx.graphics.getWidth() - 100, 60);
        font.draw(batch, "Money: " + money, Gdx.graphics.getWidth() - 100, 30);
        batch.end();

        moneyIndicator.render(delta, Gdx.graphics.getWidth() - 50, 30);

        batch.begin();
        stage.draw();
        batch.end();
    }

    /**
     * Disposed of unused resources
     */
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
            case Input.Keys.ESCAPE:
                playerCallback.onPause();
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
            if (currentItem != null && money >= currentItem.getCost()) {
                currentItem.useItem(this, placeable -> {
                    if (playerCallback != null && placeable) {
                        playerCallback.placeActor(currentItem.getPlaceableActor(), MouseInput.getX(), MouseInput.getY());
                    }
                });
            }
            for (int i = 0; i < quickSlots.size; i++) {
                QuickSlot quickSlot = quickSlots.get(i);
                if (MouseInput.isColliding(quickSlot.getX(), quickSlot.getY(), quickSlot.getWidth(), quickSlot.getHeight())) {
                    selectedSlot = i;
                    quickSlotCallback.onSelectedItemChanged(quickSlots.get(selectedSlot).getItem());
                }
            }
            if (MouseInput.isColliding((int) menuButton.getX(), (int) menuButton.getY(), (int) menuButton.getWidth(), (int) menuButton.getHeight())) {
                playerCallback.onPause();
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
        if (selectedSlot > quickSlots.size - 1) {
            selectedSlot = 0;
        }
        if (selectedSlot < 0) {
            selectedSlot = quickSlots.size - 1;
        }
        quickSlotCallback.onSelectedItemChanged(quickSlots.get(selectedSlot).getItem());
        return true;
    }

    /**
     * Adds an Item to the Inventory
     *
     * @param item The Item to be added to the Inventory
     */
    public void collectItem(Item item) {
        inventory.addItem(item);
    }

    /**
     * Adds a list of Items to the Inventory
     *
     * @param items The Items to be added to the Inventory
     */
    public void collectItems(Array<Item> items) {
        inventory.addAllItems(items);
    }

    /**
     * Removes an Item from the Inventory
     *
     * @param item The Item to remove from the Inventory
     */
    public void dropItem(Item item) {
        inventory.removeItem(item);
    }

    /**
     * Returns the Player's Inventory
     *
     * @return The current Inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Sets the Player's Inventory
     *
     * @param inventory The Inventory to set to the Player
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Returns the Player's score
     *
     * @return The Player's score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the Player's score
     *
     * @param score The Player's score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Returns the amount of money the Player has
     *
     * @return The amount of money the Player has
     */
    public int getMoney() {
        return money;
    }

    /**
     * Sets the amount of money the Player has
     *
     * @param money The amount of money the Player has
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Adds a specific number to the Player's score total
     *
     * @param amount The amount of money to be added to the Player's score total
     */
    void addScore(int amount) {
        this.score += amount;
    }

    /**
     * Remove a specific number from the Player's score total
     *
     * @param amount The amount of money to be removed from the Player's score total
     */
    public void removeScore(int amount) {
        this.score -= amount;
    }

    /**
     * Add a specific amount of money to the Player's money total
     *
     * @param amount The amount of money to be added to the money Player's total
     */
    public void addMoney(int amount) {
        this.money += amount;
        moneyIndicator.addIndicator("+" + Integer.toString(amount), Color.YELLOW);
    }

    /**
     * Remove a specific amount of money from the Player's money total
     *
     * @param amount The amount of money to be removed from the Player's money total
     */
    public void removeMoney(int amount) {
        this.money -= amount;
    }

    /**
     * Sets the PlayerCallback for the Player
     *
     * @param playerCallback The PlayerCallback to set for the Player
     */
    public void setPlayerCallback(PlayerCallback playerCallback) {
        this.playerCallback = playerCallback;
    }
}