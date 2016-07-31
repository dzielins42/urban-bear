package pl.dzielins42.dmtools.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

import com.google.gson.Gson;

public class JsonUtils {

    public static String[] loadStringArrayFromFile(String jsonFilePath) {
        try {
            return loadStringArrayFromFile(ResourcesUtils.getResourceFile(jsonFilePath));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String[] loadStringArrayFromFile(File jsonFile) {
        if (jsonFile == null || !jsonFile.exists()) {
            throw new IllegalArgumentException();
        }

        FileReader fr = null;
        try {
            Gson gson = new Gson();
            fr = new FileReader(jsonFile);
            return gson.fromJson(fr, String[].class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}