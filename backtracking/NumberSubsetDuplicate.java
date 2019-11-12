package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * find all the possible subset of a list of integers
 * if you have n items the the total no. of subsets
 * will be 2^n
 * e.g [1, 2, 2]
 * subsets are
 * <p>
 * [
 *  [1, 2, 2], [1, 2], [1], [2, 2], [2], []
 * ]
 */
public class NumberSubsetDuplicate {

    private static List<Integer> choices = new ArrayList<>();
    private static List<List<Integer>> result = new ArrayList<>();

    private static void subsetHelper(int[] num, int left, int right) {
        if (left == right - 1) {
            choices.add(num[left]);
            if (!result.contains(choices)) {
                result.add(new ArrayList<>(choices));
            }
            choices.remove(choices.size() - 1);
            if (!result.contains(choices)) {
                result.add(new ArrayList<>(choices));
            }
        } else {
            choices.add(num[left]);
            subsetHelper(num, left + 1, right);
            choices.remove(choices.size() - 1);
            subsetHelper(num, left + 1, right);
        }
    }

    private static List<List<Integer>> subsetDup(int[] num) {
        subsetHelper(num, 0, num.length);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(subsetDup(new int[]{4, 4, 4, 1, 4}));
    }
}
