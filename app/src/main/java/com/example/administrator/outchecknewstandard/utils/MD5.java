package com.example.administrator.outchecknewstandard.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    private static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 240) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 15]);
        }
        return sb.toString();
    }

    public static String MD5Encode(String origin) {
        try {
            String strfilename = "*D" + origin + "^?@^*";
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(strfilename.getBytes());
            return toHexString(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String MD5Encode1(String origin) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(origin.getBytes());
            return toHexString(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
