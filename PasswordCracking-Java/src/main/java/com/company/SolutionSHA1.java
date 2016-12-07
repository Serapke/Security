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
 * A program that tries the 25 most common passwords against the given list,
 * and reports how ofter each occurred.
 *
 * The format of each input line: $SHA1p$salt$hash
 */
public class SolutionSHA1 {

    private static final String SAMPLES_FILE = "rockyou-samples.sha1-salt.txt";

    private static final String SHA1 = "SHA1";
    private static final Integer DROP_CHAR_COUNT = 7;

    private static final String[] dictionary = {
            "123456", "12345", "123456789", "password", "iloveyou",
            "princess", "1234567", "rockyou", "12345678", "abc123",
            "nicole", "daniel", "babygirl", "monkey", "lovely",
            "jessica", "654321", "michael", "ashley", "qwerty",
            "111111", "iloveu", "000000", "michelle", "tigger",
    };

    public static HashMap<String, Integer> crack() {
        HashMap<String, Integer> freqDict = createFreqDictionary();

        File file = new File(SAMPLES_FILE);
        try (Scanner s = new Scanner(file)) {

            Integer freq;
            String temp;
            String salt;
            String hash;

            int i = 0;
            while (s.hasNext()) {
                temp = s.next().substring(DROP_CHAR_COUNT);   // drop '$SHA1p$' part
                salt = temp.substring(0, 10);
                hash = temp.substring(11);

                i++;
                for (String password : dictionary) {
                    if (isCorrectPassword(salt.concat(password), hash)) {
                        freq = freqDict.get(password);
                        freqDict.put(password, freq+1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return freqDict;
    }

    private static HashMap<String, Integer> createFreqDictionary() {
        HashMap<String, Integer> dict = new HashMap<>();
        for (String password : dictionary) {
            dict.put(password, 0);
        }
        return dict;
    }

    private static boolean isCorrectPassword(String password, String expectedPassword) throws NoSuchAlgorithmException {
        String potentialHash;
        MessageDigest sha1 = MessageDigest.getInstance(SHA1);

        potentialHash = DatatypeConverter.printHexBinary(sha1.digest(password.getBytes())).toLowerCase();

        return potentialHash.equals(expectedPassword);
    }
}
