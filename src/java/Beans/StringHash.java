/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author alex
 */
public class StringHash {
    public String getHash(String text) throws NoSuchAlgorithmException {
        MessageDigest  m = MessageDigest.getInstance("SHA-256");
        byte[] hash = m.digest(text.getBytes(StandardCharsets.UTF_8));
        BigInteger  bigInt = new BigInteger(1, hash);
        String hashText = bigInt.toString(16);
        while(hashText.length() < 64) hashText = "0" + hashText;
        return hashText;
    }
}
