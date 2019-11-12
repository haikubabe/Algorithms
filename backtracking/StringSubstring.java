package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * find the substring of a string
 * e.g. aab
 * possible substrings are
 * [a, aa, aab, a, ab, b]
 */
public class StringSubstring {

    private static List<String> result = new ArrayList<>();

    private static List<String> substr(String str) {
        for (int i=0;i<str.length();i++) {
            for (int j=i;j<str.length();j++) {
                result.add(str.substring(i,j+1));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(substr("abcd"));
    }
}
