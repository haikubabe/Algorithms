package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * find all the possible permutations of a collection
 * of integers
 * e.g. [1,2,2]
 * permutations are
 * [
 *  [1,2,2],
 *  [2,1,2],
 *  [2,2,1]
 * ]
 */
public class NumberPermutationDuplicate {

    private static List<Integer> chosen = new ArrayList<>();
    private static List<List<Integer>> result = new ArrayList<>();

    private static void permuteHelper(int[] nums, boolean[] used) {
        if (chosen.size() == nums.length) {
            result.add(new ArrayList<>(chosen));
        } else {
            for (int i = 0; i < nums.length; i++) {
                int n = nums[i];
                if (used[i] || i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) continue;
                chosen.add(n);
                used[i] = true;
                permuteHelper(nums, used);
                chosen.remove(chosen.size() - 1);
                used[i] = false;
            }
        }
    }

    private static List<List<Integer>> permuteDup(int[] nums) {
        Arrays.sort(nums);
        permuteHelper(nums, new boolean[nums.length]);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(permuteDup(new int[]{2, 1, 2}));
    }
}
