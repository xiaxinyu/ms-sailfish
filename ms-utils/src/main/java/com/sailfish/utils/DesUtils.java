package com.sailfish.utils;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;


/**
 *
 * @author XIAXINYU3
 * @date 2019.7.2
 */
public class DesUtils {



    public static String desDecrypt(String data, byte[] desKey, byte[] iv) throws Exception {
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(desKey, "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);

        byte[] pasByte = cipher.doFinal(Hex.decodeHex(data.toCharArray()));
        return new String(pasByte, "utf-8");
    }

    public static String desEncrypt(String data, byte[] desKey, byte[] iv) throws Exception {
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        DESKeySpec dks = new DESKeySpec(desKey);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);

        byte[] pasByte = cipher.doFinal(data.getBytes("utf-8"));
        return Hex.encodeHexString(pasByte);
    }
}
