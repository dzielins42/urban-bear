package pl.dzielins42.dmtools.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
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

        Reader r = null;
        try {
            Gson gson = new Gson();
            r = new InputStreamReader(new FileInputStream(jsonFile), Charset.forName("UTF-8"));
            return gson.fromJson(r, String[].class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (r != null) {
                try {
                    r.close();
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

        Reader r = null;
        Gson gson = new Gson();
        List<String> list = new LinkedList<String>();
        for (int i = 0; i < jsonFiles.length; i++) {
            try {
                r = new InputStreamReader(new FileInputStream(jsonFiles[i]), Charset.forName("UTF-8"));
                list.addAll(Arrays.asList(gson.fromJson(r, String[].class)));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (r != null) {
                    try {
                        r.close();
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