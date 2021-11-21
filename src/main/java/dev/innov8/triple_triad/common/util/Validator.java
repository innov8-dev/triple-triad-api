package dev.innov8.triple_triad.common.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Validator {

    private Validator() {
        super();
    }

    public static boolean isNull(Object o) {
        return o == null;
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().equals("");
    }

    public static boolean isValidUrl(String urlString) {

        try {
            new URL(urlString).openConnection().connect();
        } catch (Throwable t) {
            return false;
        }

        return true;
    }

}
