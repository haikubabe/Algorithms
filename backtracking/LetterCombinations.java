package backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * find all possible letter combinations of phone numbers
 * e.g. digits = "23", its corresponding strings are
 * abc, def
 * [
 *  "ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"
 * ]
 */

public class LetterCombinations {

    private static Map<String, String> digitMap = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    private static StringBuilder choices = new StringBuilder();
    private static List<String> result = new ArrayList<>();

    private static void letterCombinationsHelper(String digits, int index) {
        if (digits.length() == 0) {
        } else if (choices.length() == digits.length()) {
            result.add(choices.toString());
        } else {
            String s = digitMap.get(Character.toString(digits.charAt(index)));
            for (int j = 0; j < s.length(); j++) {
                choices.append(s.charAt(j));
                letterCombinationsHelper(digits, index + 1);
                choices.deleteCharAt(choices.length() - 1);
            }
        }
    }

    private static List<String> letterCombinations(String digits) {
        letterCombinationsHelper(digits, 0);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("234"));
    }
}
