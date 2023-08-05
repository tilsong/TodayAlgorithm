package Boj.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 컴백홈 {
    static boolean[][] map;
    static int r;
    static int c;
    static int k;
    static int[] dRow = {-1, 0, 1, 0};
    static int[] dCol = {0, 1, 0, -1};
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = line.charAt(j) == 'T';
            }
        }

        map[r-1][0] = true;
        search(0, r-1, 0);

        System.out.println(count);
    }

    private static void search(int depth, int curRow, int curCol) {
        if (depth == k-1) {
            if (curRow == 0 && curCol == c-1) {
                count ++;
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nRow = dRow[i] + curRow;
            int nCol = dCol[i] + curCol;

            if (nRow >= 0 && nRow < r && nCol >= 0 && nCol < c && !map[nRow][nCol]) {
                map[nRow][nCol] = true;
                search(depth+1, nRow, nCol);
                map[nRow][nCol] = false;
            }
        }
    }
}
