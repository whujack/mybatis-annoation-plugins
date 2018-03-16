package edu.whu.utils;

import java.util.regex.Pattern;

/**
 * @author Created By LiJie at 2018/3/16
 */
public class StringUtils {

    private final static String DEFAULT_STRING = "(#\\{})";

    public static String replace(String source, String... result) {
        if (source == null) {
            return null;
        }
        if (result == null) {
            return source;
        }
        for (int i = 0; i < result.length; i++) {
            source=source.replaceFirst(DEFAULT_STRING, result[i]);
        }
        return source;
    }

    public static void main(String[] args) {
        String res = replace("#{}#{}#{}#1234", "a", "b", "c");
        System.out.print(res);
    }
}
