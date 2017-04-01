package com.aston.group.stationdefender.utils.resources;

import com.aston.group.stationdefender.gamesetting.items.Item;
import com.aston.group.stationdefender.gamesetting.items.ItemStack;
import com.aston.group.stationdefender.utils.FontManager;
import com.aston.group.stationdefender.utils.TextureManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * QuickSlot class allows the player inventory to
 * have a limited number of items and each item takes
 * up a slot.
 *
 * @author Mohammed Foysal
 */
public class QuickSlot {
    private final SpriteBatch batch;
    private final TextureRegion trQuickSlot;
    private final TextureRegion trQuickSlotHovered;
    private final BitmapFont font;
    private final int x;
    private final int y = 0;
    private final int width = 48;
    private final int height = 48;
    private boolean isSelected;
    private Item item;
    private ItemStack<Item> itemStack;

    /**
     * Construct a new QuickSlot
     *
     * @param x The X co-ordinate of the QuickSlot
     */
    public QuickSlot(int x) {
        this.x = x;
        batch = new SpriteBatch();
        Texture texture = TextureManager.INSTANCE.loadTexture(5);
        trQuickSlot = new TextureRegion(texture, 78, 29, 20, 20);
        trQuickSlotHovered = new TextureRegion(texture, 57, 29, 20, 20);
        font = FontManager.getFont(16);
    }

    /**
     * Render the QuickSlot.
     */
    public void render() {

        //Draw Hovered Batch
        if (isSelected) {
            batch.begin();
            batch.draw(trQuickSlotHovered, x, y, width, height);
            batch.end();
        } else {
            batch.begin();
            batch.draw(trQuickSlot, x, y, width, height);
            batch.end();
        }
        if (itemStack != null) {
            itemStack.setX(x + (width / 5));
            itemStack.setY(y + (width / 6));
            itemStack.render();
        }
    }

    /**
     * Dispose of the resources created
     */
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    /**
     * Returns the X co-ordinate value of an QuickSlot
     *
     * @return The X co-ordinate value of the QuickSlot
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the Y co-ordinate value of the item.
     *
     * @return The Y co-ordinate value of the QuickSlot
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the width of the QuickSlot
     *
     * @return The width of the QuickSlot
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the QuickSlot
     *
     * @return The height of the QuickSlot
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets whether a QuickSlot is selected or not
     *
     * @param selected Whether a QuickSlot is selected or not
     */
    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    /**
     * Returns the Item in the QuickSlot
     *
     * @return The Item in the QuickSlot
     */
    public Item getItem() {
        return itemStack.getItem();
    }

    /**
     * Puts an item in a QuickSlot
     *
     * @param item The item to put in the QuickSlot
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Returns the ItemStack used in the QuickSlot Inventory
     *
     * @return The ItemStack used in the QuickSlot Inventory
     */
    public ItemStack<Item> getItemStack() {
        return itemStack;
    }

    /**
     * Sets the ItemStack used in the QuickSlot Inventory
     *
     * @param itemStack The ItemStack to be used in the QuickSlot Inventory
     */
    public void setItemStack(ItemStack<Item> itemStack) {
        this.itemStack = itemStack;
    }
}