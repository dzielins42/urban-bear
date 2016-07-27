package pl.dzielins42.dmtools.util;

import java.io.File;
import java.net.URISyntaxException;

import com.google.common.io.Resources;

public class ResourcesUtils {

    public static File getResourceFile(String resourcePath) throws URISyntaxException, IllegalArgumentException {
        return new File(Resources.getResource(resourcePath).toURI());
    }

}