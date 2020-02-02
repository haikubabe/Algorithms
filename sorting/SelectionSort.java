package sorting;

public class SelectionSort {

    private static int[] sort(int[] a, int n) {
        for (int i=0;i<n-1;i++) {
            int min = a[i], minIdx = i;
            for (int j=i+1;j<n;j++) {
                if (a[j]<min) {
                    min = a[j];
                    minIdx = j;
                }
            }
            if (min != a[i]) {
                int t = a[i];
                a[i] = min;
                a[minIdx] = t;
            }
        }
        return a;
    }

    public static void main(String[] args) {
        int[] a = {6,2,8,0,1,4,3};
        int n = a.length;
        int[] b = sort(a, n);
        for (int i=0;i<n;i++) {
            System.out.printf("%d\t", b[i]);
        }
        System.out.println();
    }
}
