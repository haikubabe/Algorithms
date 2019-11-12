package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * find all the possible subset of a list of string
 * if you have n items the the total no. of subsets
 * will be 2^n
 * e.g [Mary, Jane, Becky]
 * subsets are
 * [
 *  [Mary, Jane, Becky], [Mary, Jane], [Mary, Becky], [Mary], [Jane, Becky], [Jane], [Becky], []
 * ]
 */
public class StringSubset {

    private static List<String> chosen = new ArrayList<>();
    private static List<List<String>> result = new ArrayList<>();

    private static void subsetHelper(String[] str, int left,  int right) {
        if (left == right - 1) {
            chosen.add(str[left]);
            result.add(new ArrayList<>(chosen));
            chosen.remove(chosen.size()-1);
            result.add(new ArrayList<>(chosen));
        } else {
            chosen.add(str[left]);
            subsetHelper(str, left + 1, right);
            chosen.remove(chosen.size()-1);
            subsetHelper(str, left + 1, right);
        }
    }

    private static List<List<String>> subset(String[] s) {
        subsetHelper(s, 0, s.length);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(subset(new String[] {"Mary", "Jane", "Becky"}));
    }
}
