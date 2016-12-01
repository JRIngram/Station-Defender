package com.aston.group.stationdefender.utils.resources;

import com.aston.group.stationdefender.gamesetting.items.Item;
import com.aston.group.stationdefender.gamesetting.items.ItemBlank;
import com.aston.group.stationdefender.gamesetting.items.ItemCredit;
import com.aston.group.stationdefender.gamesetting.items.ItemTurret;

public class ItemFactory {

    public static Item getItem(Items items){
        switch (items){
            case CREDIT:
                return new ItemCredit();
            case TURRET:
                return new ItemTurret();
            default:
                return new ItemBlank();
        }
    }

    public static Item getRandomItem(){
        int rand = (int) (Math.random() * (Items.values().length));

        return ItemFactory.getItem(Items.values()[rand]);

    }

    public static Item getItemByChance(){
        int rand = (int) (Math.random() * 100);

        if(rand <= 25){
            return ItemFactory.getRandomItem();
        }else{
            return null;
        }
    }

}
