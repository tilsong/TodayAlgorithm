package Boj.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사탕게임 {
    static int n;
    static char[][] board;
    static int max = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        board = new char[n][n];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        // 행에서 인접 요소 바꾸기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                swap(i, j, i, j+1);
                search();
                swap(i, j+1, i, j);
            }
        }


        // 열에서 인접 요소 바꾸기
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n; j++) {
                swap(i, j, i+1, j);
                search();
                swap(i+1, j, i, j);
            }
        }

        System.out.println(max);
    }

    private static void swap(int i, int j, int i2, int j2) {
        char temp = board[i][j];
        board[i][j] = board[i2][j2];
        board[i2][j2] = temp;
    }

    private static void search() {
        // 행 연속 검사
        for (int i = 0; i < n; i++) {
            int count = 1;

            for (int j = 0; j < n-1; j++) {
                if (board[i][j] == board[i][j+1]) {
                    count ++;
                    max = Math.max(count, max);
                } else {
                    count = 1;
                }
            }
        }

        // 열 연속 검사
        for (int i = 0; i < n; i++) {
            int count = 1;
            for (int j = 0; j < n-1; j++) {
                if (board[j][i] == board[j+1][i]) {
                    count ++;
                    max = Math.max(count, max);
                } else {
                    count = 1;
                }
            }
        }
    }
}
