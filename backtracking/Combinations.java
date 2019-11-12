package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * find all possible combinations of k numbers out of 1 ... n
 * if n = 4 and k = 2 then
 * combinations are
 *
 * [
 *  [1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]
 * ]
 */
public class Combinations {

    private static List<Integer> choices = new ArrayList<>();
    private static List<List<Integer>> result = new ArrayList<>();

    private static void combineHelper(int n, int k, int index) {
        if (choices.size() == k) {
            result.add(new ArrayList<>(choices));
        } else {
            for (int i=index;i<=n;i++) {
                choices.add(i);
                combineHelper(n,k,i+1);
                choices.remove(choices.size()-1);
            }
        }
    }

    private static List<List<Integer>> combine(int n, int k) {
        combineHelper(n, k, 1);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(combine(4, 3));
    }
}
