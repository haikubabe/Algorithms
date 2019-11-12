package backtracking;

/**
 * calculate pow(x,n) = x^n
 * x^n = x * (x^n-1) if n is odd
 *     = (x^n/2) * (x^n/2) if n is even
 */
public class Exponential {
    //iterative approach
    //time complexity is O(n)
    private static int powI(int x, int n) {
        int res=1;
        for (int i=1;i<=n;i++) {
            res = res * x;
        }
        return res;
    }

    //recursive approach
    //time complexity is O(n)
    private static int powR(int x, int n) {
        if (n == 0) return 1;
        return x * powR(x, n-1);
    }

    //recursive approach
    //time complexity is O(logn)
    private static int powR1(int x, int n) {
        if (n == 0) return 1;
        if (n%2 == 0) {
            int y = powR1(x, n/2);
            return y*y;
        } else {
            return x * powR1(x, n-1);
        }
    }

    public static void main(String[] args) {
        System.out.println(powI(2,6));
        System.out.println(powR(4,8));
        System.out.println(powR1(4,8));
        System.out.println(powR1(4,5));
    }
}
