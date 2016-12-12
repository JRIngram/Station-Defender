package com.aston.group.stationdefender.utils;

import com.aston.group.stationdefender.actors.Alien;
import com.aston.group.stationdefender.actors.Weapon;
import com.badlogic.gdx.utils.Json;

/**
 * ParseUtils class parses JSON information and puts it into the constructors of the relevant classes
 *
 * @author Mohammed Foysal
 */
class ParseUtils {

    /**
     * Parses a new Weapon from the given JSON
     *
     * @param weaponJsonObject The JSON string containing information to be fed to the Weapon constructor
     * @return A new Weapon object with the given constructor parameters
     */
    public static Weapon parseWeapon(String weaponJsonObject) {
        Json json = new Json();
        return json.fromJson(Weapon.class, weaponJsonObject);
    }

    /**
     * Parses a new Alien from the given JSON
     *
     * @param alienJsonObject The JSON string containing information to be fed to the Alien constructor
     * @return A new Alien object with the given constructor parameters
     */
    public static Alien parseAlien(String alienJsonObject) {
        Json json = new Json();
        return json.fromJson(Alien.class, alienJsonObject);
    }
}