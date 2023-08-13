package Boj.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 테트로미노 {

    static int[] dRow = {-1, 0, 1, 0};
    static int[] dCol = {0, 1, 0, -1};
    static int n;
    static int m;
    static int[][] map;
    static boolean[][] visited;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                search(0, 0, i, j);

                visited[i][j] = true;
                subSearch(0, map[i][j], 0, i, j);
                visited[i][j] = false;
            }
        }

        System.out.println(max);
    }

    private static void subSearch(int depth, int sum, int start, int row, int col) {
        if (depth == 3) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = start; i < 4; i++) {
            int nRow = dRow[i] + row;
            int nCol = dCol[i] + col;

            if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m && !visited[nRow][nCol]) {
                visited[nRow][nCol] = true;
                subSearch(depth + 1, sum + map[nRow][nCol], start + 1, row, col);
                visited[nRow][nCol] = false;
            }
        }
    }

    private static void search(int depth, int sum, int row, int col) {
        if (depth == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nRow = dRow[i] + row;
            int nCol = dCol[i] + col;

            if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m && !visited[nRow][nCol]) {
                visited[nRow][nCol] = true;
                search(depth + 1, sum + map[nRow][nCol], nRow, nCol);
                visited[nRow][nCol] = false;
            }
        }
    }




// 첫 풀이.
//  - "감시" 문제에서 배웠던, 유형별 내용을 체크하고, 그대로 for문을 돌리는 방식을 사용하려 했는데, 6중 for문이 되었다.
//    무언가 잘못되었다고 느끼고 이 풀이를 그만두었다..ㅋ
//    static int[][][] go = {
//            {{0,0,0}}, // 1번
//            {{0,1,2}}, // 2번
//            {{0,0,1}, {0,0,3}}, // 3번
//            {{0,1,0}, {0,3,0}}  // 4번
//    };
//    static int[][] map;
//    static int n;
//    static int m;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        n = Integer.parseInt(st.nextToken());
//        m = Integer.parseInt(st.nextToken());
//        
//        map = new int[n][m];
//        for (int i = 0; i < n; i++) {
//            st = new StringTokenizer(br.readLine());
//            for (int j = 0; j < m; j++) {
//                map[i][j] = Integer.parseInt(st.nextToken());
//            }
//        }
//        
//        int max = 0;
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                int count = search(i, j);
//                max = Math.max(max, count);
//            }
//        }
//
//        System.out.println(max);
//    }
//
//    static int[] dRow = {-1, 0, 1, 0};
//    static int[] dCol = {0, 1, 0, -1};
//    private static int search(int i, int j) {
//        int max = 0;
//        for (int k = 0; k < go.length; k++) { // 각 도형별 테스트
//            for (int l = 0; l < go[k].length; l++) { // 대칭
//                int count = map[i][j];
//                int nI = i;
//                int nJ = j;
//                int[] dir = go[k][l];
//                boolean ifGo = true;
//                for (int m = 0; m < 3; m++) { // 방향
//                    int curD = dir[m];
//                    nI += dRow[curD];
//                    nJ += dCol[curD];
//
//                    if (nI >= 0 && nI < n && nJ >= 0 && nJ < m) {
//                        count += map[nI][nJ];
//                    } else {
//                        ifGo = false;
//                    }
//                }
//
//                if (ifGo) {
//                    max = Math.max(max, count);
//                }
//            }
//        }
//
//        return max;
//    }
}
