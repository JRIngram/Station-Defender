package com.aston.group.stationdefender.utils;

import com.aston.group.stationdefender.actors.Alien;
import com.aston.group.stationdefender.actors.Weapon;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class ParseUtils {

    public static Weapon parseWeapon(String weaponJsonObject) {
        Json json = new Json();
        return json.fromJson(Weapon.class, weaponJsonObject);
    }

    public static Alien parseAlien(String alienJsonObject) {
        Json json = new Json();
        return json.fromJson(Alien.class, alienJsonObject);
    }

}
