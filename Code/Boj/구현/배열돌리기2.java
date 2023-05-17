package Boj.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 배열돌리기2 {
    static int[] dRow = {0, 1, 0, -1}; // 좌하우상
    static int[] dCol = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int group_count = Math.min(n, m) / 2;

        int[] rCounts = new int[group_count];

        int dm = m;
        int dn = n;

        for (int i = 0; i < rCounts.length; i++) {
            int needMoving = dm * 2 + dn * 2 - 4;

            rCounts[i] = r%needMoving;

            dm -= 2;
            dn -= 2;
        }

        for (int j = 0; j < group_count; j++) {
            for (int i = 0; i < rCounts[j]; i++) {
                int keepingvalue = arr[j][j];

                int row = j;
                int col = j;

                int idx = 0;
                while (idx < 4) {
                    int nRow = row + dRow[idx];
                    int nCol = col + dCol[idx];

                    if (nRow >= j && nCol >= j && nRow < n-j && nCol < m-j) {
                        arr[row][col] = arr[nRow][nCol];

                        row = nRow;
                        col = nCol;
                    } else {
                        idx++;
                    }
                }
                arr[j+1][j] = keepingvalue;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
