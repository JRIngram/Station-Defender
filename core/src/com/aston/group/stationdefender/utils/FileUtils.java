package com.aston.group.stationdefender.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class FileUtils {

    public static String openUnits(){
        FileHandle fileHandle = Gdx.files.internal("config/units.json");
        return fileHandle.readString();
    }

}
