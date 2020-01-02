package graph.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UVA260 {

    private static char[][] board;
    private static boolean[][] explored;

    private static boolean dfs(int i, int j, int N, char c) {
        explored[i][j] = true;
        if (i == N - 1) {
            return true;
        }
        if (i > 0 && j > 0 && !explored[i - 1][j - 1] && board[i - 1][j - 1] == c) {
            return dfs(i - 1, j - 1, N, c);
        } else if (i > 0 && !explored[i - 1][j] && board[i - 1][j] == c) {
            return dfs(i - 1, j, N, c);
        } else if (j > 0 && !explored[i][j - 1] && board[i][j - 1] == c) {
            return dfs(i, j - 1, N, c);
        } else if (j < N - 1 && !explored[i][j + 1] && board[i][j + 1] == c) {
            return dfs(i, j + 1, N, c);
        } else if (i < N - 1 && !explored[i + 1][j] && board[i + 1][j] == c) {
            return dfs(i + 1, j, N, c);
        } else if (i < N - 1 && j < N - 1 && !explored[i + 1][j + 1] && board[i + 1][j + 1] == c) {
            return dfs(i + 1, j + 1, N, c);
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N;
        int game = 1;
        while ((N = Integer.parseInt(bufferedReader.readLine().split("\\s+")[0])) != 0) {
            board = new char[N][N];
            explored = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                board[i] = bufferedReader.readLine().toCharArray();
            }

            boolean win = false;
            for (int j = 0; j < N; j++) {
                if (board[0][j] == 'b') {
                    if (dfs(0, j, N, 'b')) {
                        win = true;
                        break;
                    }
                }
            }

            if (win) {
                System.out.println(game + " B");
            } else {
                System.out.println(game + " W");
            }
            game++;
        }
    }
}
