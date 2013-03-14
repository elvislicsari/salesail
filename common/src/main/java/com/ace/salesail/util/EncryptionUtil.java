package com.ace.salesail.util;

import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

public class EncryptionUtil {

    public static String encrypt(String password) {
        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putString(password);
        String sellerEncryptionKey = hasher.hash().toString();
        return sellerEncryptionKey;
    }

    public static String getAppKey(String email) {
        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putString("4PpK3!_FOR_" + email);
        String sellerEncryptionKey = hasher.hash().toString();
        return sellerEncryptionKey;
    }

    public static void main(String[] args) {
        System.out.println(EncryptionUtil.encrypt("e"));
    }
}