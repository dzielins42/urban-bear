package pl.dzielins42.dmtools.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

    public static String[] loadStringArrayFromFiles(String... jsonFilePaths) {
        if (jsonFilePaths == null || jsonFilePaths.length == 0) {
            throw new IllegalArgumentException();
        }

        List<String> list = new LinkedList<String>();
        String[] partial;
        for (int i = 0; i < jsonFilePaths.length; i++) {
            try {
                partial = loadStringArrayFromFile(ResourcesUtils.getResourceFile(jsonFilePaths[i]));
                if (partial != null) {
                    list.addAll(Arrays.asList(partial));
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        String[] result = null;
        if (list != null && !list.isEmpty()) {
            result = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                result[i] = list.get(i);
            }
        }

        return result;
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

    public static String[] loadStringArrayFromFiles(File... jsonFiles) {
        if (jsonFiles == null || jsonFiles.length == 0) {
            throw new IllegalArgumentException();
        }

        FileReader fr = null;
        Gson gson = new Gson();
        List<String> list = new LinkedList<String>();
        for (int i = 0; i < jsonFiles.length; i++) {
            try {
                fr = new FileReader(jsonFiles[i]);
                list.addAll(Arrays.asList(gson.fromJson(fr, String[].class)));
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
        }

        String[] result = null;
        if (list != null && !list.isEmpty()) {
            result = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                result[i] = list.get(i);
            }
        }

        return result;
    }

}