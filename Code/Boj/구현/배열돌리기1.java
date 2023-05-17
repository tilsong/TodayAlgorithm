package Boj.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 배열돌리기1 {
    static int[] dRow = {0, 1, 0, -1}; //우->좌, 하->상, 좌->우, 상->하
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
               arr[i][j] =  Integer.parseInt(st.nextToken());
            }
        }

        int groupLength = Math.min(n, m) / 2;

        int keepingValue = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < groupLength; j++) {// 그룹 개수만큼 돌리기
                keepingValue = arr[j][j]; // 유지되지 않으므로 keep

                int row = j;
                int col = j; // 현재 이동하는 위치

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
                arr[j+1][j] = keepingValue;
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