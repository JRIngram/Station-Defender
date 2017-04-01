package com.aston.group.stationdefender.utils;

import com.aston.group.stationdefender.callbacks.LevelInfoCallback;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.gamesetting.items.Item;
import com.aston.group.stationdefender.utils.resources.ItemFactory;
import com.aston.group.stationdefender.utils.resources.Items;
import com.aston.group.stationdefender.utils.resources.StackableInventory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Array;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * FileUtils class is responsible for reading the configuration JSON files
 *
 * @author Mohammed Foysal
 */
public class FileUtils {

    /**
     * Save the Player's Level information
     *
     * @param score       The Player's score to save
     * @param money       The Player's amount of money to save
     * @param levelNumber The Level Number the Player got to
     * @param inventory   The Inventory the Player had
     */
    public static void saveLevel(int score, int money, int levelNumber, StackableInventory inventory) {

        //PLAYER PARAMS
        JsonObject playerObject = new JsonObject();
        playerObject.addProperty("score", score);
        playerObject.addProperty("money", money);

        JsonArray items = new JsonArray();
        for (int i = 0; i < inventory.getItemStacks().size; i++) {
            JsonObject stackObject = new JsonObject();
            stackObject.addProperty("id", inventory.getItemStacks().get(i).getItem().getId());
            stackObject.addProperty("name", inventory.getItemStacks().get(i).getItem().getName());
            stackObject.addProperty("sku", inventory.getItemStacks().get(i).getItem().getSku().toString());
            items.add(stackObject);
        }
        playerObject.add("items", items);

        //LEVEL PARAMS
        JsonObject levelObject = new JsonObject();
        levelObject.addProperty("number", levelNumber);
        JsonObject dataObject = new JsonObject();
        dataObject.add("level", levelObject);
        dataObject.add("player", playerObject);

        //Save
        Preferences prefs = Gdx.app.getPreferences(Constants.prefs);
        prefs.putString("level", dataObject.toString());
        prefs.flush();
    }

    /**
     * Load the Player's Level information
     *
     * @param levelInfoCallback The LevelInfoCallback to use
     */
    public static void loadLevel(LevelInfoCallback levelInfoCallback) {
        Gson gson = new Gson();
        Preferences prefs = Gdx.app.getPreferences(Constants.prefs);
        JsonElement element = gson.fromJson(prefs.getString("level", ""), JsonElement.class);

        //Parse data
        if (element != null && !element.isJsonNull() && element.isJsonObject()) {
            JsonObject dataObject = element.getAsJsonObject();
            JsonObject levelObject = dataObject.get("level").getAsJsonObject();
            JsonObject playerObject = dataObject.get("player").getAsJsonObject();
            int levelNumber = levelObject.get("number").getAsInt();
            int money = playerObject.get("money").getAsInt();
            int score = playerObject.get("score").getAsInt();
            Array<Item> items = new Array<>();
            JsonArray jsonItems = playerObject.get("items").getAsJsonArray();
            for (int i = 0; i < jsonItems.size(); i++) {
                JsonObject itemObject = jsonItems.get(i).getAsJsonObject();
                String skuText = itemObject.get("sku").getAsString();
                Items sku = Items.valueOf(skuText);
                Item item = ItemFactory.getItem(sku);
                items.add(item);
            }

            levelInfoCallback.onLoaded(score, money, levelNumber, items);
        }
    }

    /**
     * Remove the Level information of the Player
     */
    public static void deleteLevelInfo() {
        Preferences prefs = Gdx.app.getPreferences(Constants.prefs);
        prefs.remove("level");
        prefs.remove("player");
    }
}