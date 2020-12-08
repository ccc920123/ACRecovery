package com.example.administrator.outchecknewstandard.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class CheckMachineCode {
    public static String checkLicence(String licence) {
        String ret = "";
        if (licence != null && licence.length() > 8) {
            try {
                ret = decrypt(licence.substring(8), licence.substring(0, 8));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public static String decrypt(String message, String key) throws Exception {
        byte[] bytesrc = convertHexString(message);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(key.getBytes("UTF-8"))), new IvParameterSpec(key.getBytes("UTF-8")));
        return new String(cipher.doFinal(bytesrc));
    }

    private static byte[] convertHexString(String ss) {
        byte[] digest = new byte[(ss.length() / 2)];
        for (int i = 0; i < digest.length; i++) {
            digest[i] = (byte) Integer.parseInt(ss.substring(i * 2, (i * 2) + 2), 16);
        }
        return digest;
    }
}
