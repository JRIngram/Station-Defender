package com.aston.group.stationdefender.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * FileUtils class is responsible for reading the configuration JSON files
 *
 * @author Mohammed Foysal
 */
class FileUtils {

    /**
     * Returns the String of Units in the units configuration JSON file
     *
     * @return The String of Units in the Units configuration JSON file
     */
    public static String openUnits() {
        FileHandle fileHandle = Gdx.files.internal("config/units.json");
        return fileHandle.readString();
    }
}