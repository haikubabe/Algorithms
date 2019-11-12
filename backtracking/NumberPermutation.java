package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * find all the possible permutations of a collection
 * of integers
 * e.g. [1,2,3]
 * permutations are
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 */
public class NumberPermutation {

    private static List<Integer> chosen = new ArrayList<>();
    private static List<List<Integer>> result = new ArrayList<>();

    private static void permuteHelper(int[] nums) {
        if (chosen.size() == nums.length) {
            result.add(new ArrayList<>(chosen));
        } else {
            for (int i = 0; i < nums.length; i++) {
                int n = nums[i];
                if (!chosen.contains(n)) {
                    chosen.add(n);
                    permuteHelper(nums);
                    chosen.remove(chosen.size() - 1);
                }
            }
        }
    }

    private static List<List<Integer>> permute(int[] nums) {
        permuteHelper(nums);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 2, 3}));
    }
}
