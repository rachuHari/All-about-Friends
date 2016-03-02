package com.compassites.friends.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by admin on 01-03-2016.
 */
public class Util {

    public static String readFile(Context context) {
        String result = "";
        try {
            AssetManager assetManager = context.getAssets();
            InputStream is = assetManager.open("sample_response.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            result = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return result;
    }
}
