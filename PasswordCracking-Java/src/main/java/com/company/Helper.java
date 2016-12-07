package com.company;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Developed by Mantas on 06/12/2016.
 *
 * A helper to print out the frequencies of passwords stored
 * in a HashMap
 */
public class Helper {

    private static final String UTF_CHARSET = "UTF-8";

    public static void printFreqs(HashMap<String, Integer> dict, String outputFile) {
        try {
            PrintWriter w = new PrintWriter(outputFile, UTF_CHARSET);
            Iterator it = dict.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                w.println(pair.getValue() + "," + pair.getKey());
            }
            w.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
