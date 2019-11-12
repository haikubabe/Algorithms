package backtracking;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

    private static List<List<String>> result = new ArrayList<>();
    private static List<String> choices = new ArrayList<>();

    private static boolean isPalindrome(String s) {
        int n = s.length();
        for (int i = 0; i <= n / 2; i++) {
            if (s.charAt(i) != s.charAt(n - i - 1)) {
                return false;
            }
        }
        return true;
    }

    private static void partitionHelper(String s, int index) {
        if (s.length() == index) {
            result.add(new ArrayList<>(choices));
        } else {
            for (int i = index; i < s.length(); i++) {
                String str = s.substring(index, i + 1);
                if (isPalindrome(str)) {
                    choices.add(str);
                    partitionHelper(s, i + 1);
                    choices.remove(choices.size() - 1);
                }
            }
        }
    }

    private static List<List<String>> partition(String s) {
        partitionHelper(s, 0);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(partition("aab"));
    }
}
