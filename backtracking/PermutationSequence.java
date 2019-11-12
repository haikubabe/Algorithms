package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * find the permutation sequence
 * for e.g. n=3, k=3
 *
    1. "123"
    2. "132"
    3. "213"
    4. "231"
    5. "312"
    6. "321"

    ans is "213"
 */
public class PermutationSequence {

    private static StringBuilder choices = new StringBuilder();

    private static String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        for (int i=1;i<=n;i++) {
            nums.add(i);
        }

        int[] fact = new int[n];
        int f = 1;
        fact[0] = f;
        for (int i=1;i<n;i++) {
            fact[i] = i * fact[i-1];
        }

        k = k-1;
        for (int i=0;i<n;i++) {
            int index = k/fact[n-1-i];
            choices.append(nums.get(index));
            k = k - index * fact[n-1-i];
            nums.remove(index);
        }
        return choices.toString();
    }

    public static void main(String[] args) {
        System.out.println(getPermutation(4,14));
    }
}
