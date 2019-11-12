package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * find all the possible permutations of a string
 * e.g. [Mary, Jane, Becky]
 * permutations are
 * [
 *  [Mary, Jane, Becky], [Mary, Becky, Jane], [Jane, Mary, Becky], [Jane, Becky, Mary], [Becky, Mary, Jane], [Becky, Jane, Mary]
 * ]
 */
public class StringPermutation {

    private static List<String> chosen = new ArrayList<>();
    private static List<List<String>> result = new ArrayList<>();

    private static void permuteHelper(String[] s) {
        if (chosen.size() == s.length) {
            result.add(new ArrayList<>(chosen));
        } else {
            for (int i = 0; i < s.length; i++) {
                if (!chosen.contains(s[i])) {
                    chosen.add(s[i]);
                    permuteHelper(s);
                    chosen.remove(chosen.size()-1);
                }
            }
        }
    }

    private static List<List<String>> permute(String[] s) {
        permuteHelper(s);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(permute(new String[]{"Mary", "Jane", "Becky"}));
    }
}
