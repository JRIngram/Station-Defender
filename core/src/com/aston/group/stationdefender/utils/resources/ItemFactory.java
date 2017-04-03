package com.aston.group.stationdefender.utils.resources;

import com.aston.group.stationdefender.gamesetting.items.*;

/**
 * This class is responsible for handling what Items are created
 *
 * @author Mohammad Foysal
 */
public class ItemFactory {

    /**
     * Returns a new Item from the given list
     *
     * @param items The enum list of Items that can be created
     * @return The new Item to be placed within a Lane
     */
    public static Item getItem(Items items) {
        switch (items) {
            case CREDIT:
                return new ItemCredit();
            case TURRET:
                return new ItemTurret();
            case RAPID_FIRE_WEAPON:
                return new ItemRapidFireTurret();
            case HEALTH:
                return new ItemHealth();
            default:
                return new ItemBlank();
        }
    }

    /**
     * Returns a new Item at random
     *
     * @return The new Item to be placed within a Lane
     */
    public static Item getRandomItem() {
        int rand = (int) (Math.random() * (Items.values().length));
        return ItemFactory.getItem(Items.values()[rand]);
    }

    /**
     * Return either a new Item or null at random
     *
     * @return A new Item at random or null if no Item is to be returned
     */
    public static Item getItemByChance() {
        int rand = (int) (Math.random() * 100);
        if (rand <= 25) {
            return ItemFactory.getRandomItem();
        } else {
            return null;
        }
    }
}