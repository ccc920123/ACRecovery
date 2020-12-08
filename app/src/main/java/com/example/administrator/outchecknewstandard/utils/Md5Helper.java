package com.example.administrator.outchecknewstandard.utils;

import java.security.MessageDigest;

public class Md5Helper {
    private static final String[] hexDigits = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (byte byteToHexString : b) {
            resultSb.append(byteToHexString(byteToHexString));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < (byte) 0) {
            n += 256;
        }
        return hexDigits[n / 16] + hexDigits[n % 16];
    }

    public static String MD5Encode(String origin) {
        Exception ex;
        String resultString = null;
        try {
            String resultString2 = new String(origin);
            try {
                resultString = byteArrayToHexString(MessageDigest.getInstance("MD5").digest(resultString2.getBytes()));
            } catch (Exception e) {
                ex = e;
                resultString = resultString2;
                ex.printStackTrace();
                return resultString.toUpperCase();
            }
        } catch (Exception e2) {
            ex = e2;
            ex.printStackTrace();
            return resultString.toUpperCase();
        }
        return resultString.toUpperCase();
    }

    public static void main(String[] args) {
        System.out.println(MD5Encode("蓝辽B815X1".toString() + "|" + "2017-09-21 16:08:39".toString() + "DEATH64").substring(0, 8));
    }
}
