package com.example.administrator.outchecknewstandard.utils;

import java.util.Map;

public class MapValueUtils {
    public static String getMapValue(String key, Map<String, String> map) {
        String ret = "";
        if (!map.containsKey(key) || WebserviceInit.getURLDecoderString((String) map.get(key)).equals("")) {
            return ret;
        }
        return WebserviceInit.getURLDecoderString((String) map.get(key));
    }
}
