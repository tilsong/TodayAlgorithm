package Boj.DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 꽃길 {
    static int n;
    static int[][] prices;
    static boolean[][] check;
    static int minPrice = 10000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        prices = new int[n+1][n+1];
        check = new boolean[n+1][n+1];
        
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                prices[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        search(0, 0);

        System.out.println(minPrice);
    }

    private static void search(int depth, int totalPrice) {
        if (depth == 3) {
            minPrice = Math.min(minPrice, totalPrice);
            return;
        }

        for (int i = 2; i <= n-1; i++) {
            for (int j = 2; j <= n-1; j++) {
                boolean can = true;

                int row = i;
                int col = j;

                if (!check[row][col]) {
                    for (int k = 0; k < 4; k++) {
                        int nRow = row + dRow[k];
                        int nCol = col + dCol[k];

                        if (nRow >= 1 && nCol >= 1 && nRow <= n && nCol <= n) {
                            if (check[nRow][nCol]) {
                               can = false;
                            }
                        } else {
                            can = false;
                        }
                    }
                } else {
                    can = false;
                }

                if (can) {
                    search(depth+1, totalPrice + changeState(row, col, true));
                    changeState(row, col, false);
                }
            }
        }
    }

    private static int changeState(int row, int col, boolean state) {
        int total = 0;
        for (int k = 0; k < 4; k++) {
            int nRow = row + dRow[k];
            int nCol = col + dCol[k];
            check[nRow][nCol] = state;
            total += prices[nRow][nCol];
        }
        check[row][col] = state;
        total += prices[row][col];

        return total;
    }

    static int[] dRow = {0, 1, 0, -1};
    static int[] dCol = {1, 0, -1, 0};
}
