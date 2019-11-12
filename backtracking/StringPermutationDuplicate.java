package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * find all the possible permutations of a string
 * e.g. [Mary, Jane, Mary]
 * permutations are
 * [
 *  [Jane, Mary, Mary], [Mary, Jane, Mary], [Mary, Mary, Jane]
 * ]
 */
public class StringPermutationDuplicate {

    private static List<String> chosen = new ArrayList<>();
    private static List<List<String>> result = new ArrayList<>();

    private static void permuteHelper(String[] s, boolean[] used) {
        if (chosen.size() == s.length) {
            result.add(new ArrayList<>(chosen));
        } else {
            for (int i = 0; i < s.length; i++) {
                String str = s[i];
                if (used[i] || i > 0 && s[i - 1].equals(s[i]) && !used[i - 1]) continue;
                chosen.add(str);
                used[i] = true;
                permuteHelper(s, used);
                chosen.remove(chosen.size() - 1);
                used[i] = false;
            }
        }
    }

    private static List<List<String>> permuteDup(String[] s) {
        Arrays.sort(s);
        permuteHelper(s, new boolean[s.length]);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(permuteDup(new String[]{"Mary", "Jane", "Mary"}));
    }
}
