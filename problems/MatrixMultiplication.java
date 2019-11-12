import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MatrixMultiplication {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = bufferedReader.readLine().split("\\s+");
        int n = Integer.parseInt(line1[0]);
        int m = Integer.parseInt(line1[1]);
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] line = bufferedReader.readLine().split("\\s+");
            for (int j = 0; j < m; j++) {
                a[i][j] = Integer.parseInt(line[j]);
            }
        }
        String[] line2 = bufferedReader.readLine().split("\\s+");
        int q = Integer.parseInt(line2[0]);
        int p = Integer.parseInt(line2[1]);
        int[][] b = new int[q][p];
        for (int i = 0; i < q; i++) {
            String[] line = bufferedReader.readLine().split("\\s+");
            for (int j = 0; j < p; j++) {
                b[i][j] = Integer.parseInt(line[j]);
            }
        }
        if (m != q) {
            System.out.println("Invalid input");
            return;
        }
        int[][] c = new int[n][p];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                for (int k = 0; k < m; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                System.out.print(c[i][j] + " ");
            }
            System.out.println();
        }
    }
}
