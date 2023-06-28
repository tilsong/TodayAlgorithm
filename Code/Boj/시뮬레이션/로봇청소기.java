package Boj.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 풀이
// 문제의 요구사항 대로 그대로 구현하는 게 관건인 문제.
// 최적화에 대해서도 생각해보면 좋을 듯.

public class 로봇청소기 {
    // 0 북 1 동 2 남 3 서
    static int[] dRow = { -1, 0, 1, 0 };
    static int[] dCol = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] room = new int[n][m];
        boolean[][] isCleaned = new boolean[n][m];

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;

        while (true) {
//      1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
            if (!isCleaned[r][c]) {
                count++;
                isCleaned[r][c] = true;
            }

            boolean canClean = false;
            for (int i = 0; i < 4; i++) {
                if (!isCleaned[r + dRow[i]][c + dCol[i]] && room[r + dRow[i]][c + dCol[i]] != 1 ) {
                    canClean = true;
                }
            }
//      2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우,
            if (!canClean) {
//          2.1 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
                if (d == 0) { // 북 후진할 수 있는지 여부
                    if (room[r+1][c] != 1) { // 벽인지 여부
                        r ++;
                        continue;
                    } else {
                        //          2.2 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
                        break;
                    }
                }
                if (d == 1) { // 동 후진할 수 있는지 여부
                    if (room[r][c-1] != 1) { // 벽인지 여부
                        c --;
                        continue;
                    } else {
                        break;
                    }
                }
                if (d == 2) { // 남 후진할 수 있는지 여부
                    if (room[r-1][c] != 1) { // 벽인지 여부
                        r --;
                        continue;
                    } else {
                        break;
                    }
                }
                if (d == 3) { // 서 후진할 수 있는지 여부
                    if (room[r][c+1] != 1) { // 벽인지 여부
                        c ++;
                    } else {
                        break;
                    }
                }
            } else { //      3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
//         3.1 반시계 방향으로 90도 회전한다.
                d --;
                if (d == -1) {
                    d = 3;
                }
//         3.2 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
                if (room[r + dRow[d]][c + dCol[d]] == 0 && !isCleaned[r + dRow[d]][c + dCol[d]]) {
                    r += dRow[d];
                    c += dCol[d];
                }
//         3.3 1번으로 돌아간다.
            }
        }

        System.out.println(count);
    }
}
