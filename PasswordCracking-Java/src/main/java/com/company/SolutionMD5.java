package com.company;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Developed by Mantas on 06/12/2016.
 *
 * A program that given a file with MD5 hashes of passwords
 * brute forces all five character passwords and outputs
 * the passwords found and the number of occurrences for each
 * of them.
 */

public class SolutionMD5 {

    private static final String SAMPLES_FILE = "rockyou-samples.md5.txt";

    private static final String MD5 = "MD5";

    private static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

    private static HashMap<String, Integer> dictionary;

    public SolutionMD5() {
        try {
            dictionary = buildRainbowTable();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, Integer> buildRainbowTable() throws FileNotFoundException, NoSuchAlgorithmException {
        HashMap<String, Integer> rainbowTable = new HashMap<>();

        File file = new File(SAMPLES_FILE);
        Scanner s = new Scanner(file);
        String temp;
        while (s.hasNext()) {
            temp = s.next();
            if (rainbowTable.containsKey(temp)) {
                Integer count = rainbowTable.get(temp);
                rainbowTable.put(temp, count+1);
            } else {
                rainbowTable.put(temp, 1);
            }
        }

        return rainbowTable;
    }

    public HashMap<String, Integer> crack() {
        HashMap<String, Integer> result = new HashMap<>();
        String guess;
        byte[] digest;
        String hash;
        try {
            MessageDigest md5 = MessageDigest.getInstance(MD5);
            for (char ch1 : ALPHABET) {
                for (char ch2 : ALPHABET) {
                    for (char ch3 : ALPHABET) {
                        for (char ch4 : ALPHABET) {
                            for (char ch5 : ALPHABET) {
                                guess = "" + ch1 + ch2 + ch3 + ch4 + ch5;
                                digest = md5.digest(guess.getBytes());
                                hash = DatatypeConverter.printHexBinary(digest).toLowerCase();
                                if (dictionary.containsKey(hash)) {
                                    result.put(guess, dictionary.get(hash));
                                    dictionary.remove(hash);
                                }
                                // if already have all hashes, then terminate
                                if (dictionary.size() == 0) return result;
                            }
                        }
                    }
                }
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}

