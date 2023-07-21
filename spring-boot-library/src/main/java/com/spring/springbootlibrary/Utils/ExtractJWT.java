package com.spring.springbootlibrary.Utils;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


public class ExtractJWT {

    public static String payloadJWTExtraction(String token) {

        token = token.replace("Bearer", "").trim();

        String[] chunks = token.split("\\.");
        if (chunks.length < 2) {
            // Invalid token format, return null or throw an exception as needed
            return null;
        }

        Base64.Decoder decoder = Base64.getUrlDecoder();

        String payload = new String(decoder.decode(chunks[1]));

        String[] entries = payload.split(",");

        Map<String, String> map = new HashMap<>();

        for (String entry : entries) {
            String[] keyValue = entry.split(":");
            if (keyValue[0].equals("\"sub\"")) {
                int remove = 1;
                if (keyValue[1].endsWith("}")) {
                    remove = 2;
                }
                keyValue[1] = keyValue[1].substring(0, keyValue[1].length() - remove);
                keyValue[1] = keyValue[1].substring(1);
                map.put(keyValue[0], keyValue[1]);
            }
        }

        if (map.containsKey("\"sub\"")) {
            return map.get("\"sub\"");
        }
        return null;
    }
}