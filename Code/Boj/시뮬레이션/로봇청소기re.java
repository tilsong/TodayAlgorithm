package Boj.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 로봇청소기re {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[][] room = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dRow = {-1, 0, 1, 0}; // 북 동 남 서
        int[] dCol = {0, 1, 0, -1};
        while (true) {
            room[r][c] = 2;

            boolean goIf = false;
            for (int i = 0; i < 4; i++) { // 주변 4 방향 중 청소되지 않은 칸이 있으면 청소기 이동.
                d = (d + 3) % 4;
                int nRow = r + dRow[d];
                int nCol = c + dCol[d];

                if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m && room[nRow][nCol] == 0) {
                    r = nRow;
                    c = nCol;
                    goIf = true;
                    break;
                }
            }

            if (goIf) continue;

            // 4 방향 모두 청소되지 않은 칸이 없고 후진 가능하면 후진
            int back = (d + 2) % 4;
            int nRow = r + dRow[back];
            int nCol = c + dCol[back];
            if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m && room[nRow][nCol] != 1) {
                r = nRow;
                c = nCol;
            } else {
                break;
            }
        }

        // count
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (room[i][j] == 2) {
                    count ++;
                }
            }
        }

        System.out.println(count);
    }
}