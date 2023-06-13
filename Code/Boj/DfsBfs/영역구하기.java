package Boj.DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 영역구하기 {

    static int row;
    static int col;
    static int k;
    static boolean[][] rect;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken()); // row
        col = Integer.parseInt(st.nextToken()); // col
        k = Integer.parseInt(st.nextToken());

        rect = new boolean[row][col];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int dCol = Integer.parseInt(st.nextToken());
            int dRow = Integer.parseInt(st.nextToken());
            int uCol = Integer.parseInt(st.nextToken());
            int uRow = Integer.parseInt(st.nextToken());

            for (int j = dRow; j < uRow; j++) {
                for (int l = dCol; l < uCol; l++) {
                    rect[j][l] = true;
                }
            }
        }

        int areaCount = 0;
        List<Integer> areaSize = new ArrayList<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!rect[i][j]) {
                    curArea = 0;
                    search(i, j);
                    areaSize.add(curArea);
                    areaCount++;
                }
            }
        }

        Collections.sort(areaSize);

        System.out.println(areaCount);
        for (int i = 0; i < areaSize.size(); i++) {
            System.out.print(areaSize.get(i) + " ");
        }
    }

    static int curArea;
    static int[] dRow = {0, 1, 0, -1};
    static int[] dCol = {1, 0, -1, 0};
    private static void search(int r, int c) {
        if (rect[r][c]) return;

        curArea ++;
        rect[r][c] = true;

        for (int i = 0; i < 4; i++) {
            int nRow = dRow[i] + r;
            int nCol = dCol[i] + c;

            if (nCol >= 0 && nRow >=0 && nCol < col && nRow < row) {
                search(nRow, nCol);
            }
        }
    }
}
