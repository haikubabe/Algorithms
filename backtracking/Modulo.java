package backtracking;

/**
 * calculate mod(x,n,m) = (x^n)%m
 * (a * b)%m = {(a%m) * (b%m)}%m
 * (x^n)%m =
 *      (x * x^n-1)%m if n is odd
 *      {(x%m) * ((x^n-1)%m)}%m
 *      ((x^n/2) * (x^n/2))%m if n is even
 *      {((x^n/2)%m) * ((x^n/2)%m)}%m
 */
public class Modulo {

    //recursive approach
    //time complexity is O(logn)
    private static int mod(int x, int n, int m) {
        if (n == 0) return 1;
        if (n%2 == 0) {
            int y = mod(x,n/2, m);
            return (y*y)%m;
        } else {
            return ((x%m) * mod(x,n-1, m))%m;
        }
    }

    //recursive approach
    //time complexity is O(n)
    private static int mod1(int x, int n, int m) {
        if (n == 0) return 1;
        return ((x%m) * mod(x,n-1, m))%m;
    }

    public static void main(String[] args) {
        System.out.println(mod(5,4,7));
        System.out.println(mod1(5,4,7));
    }
}
