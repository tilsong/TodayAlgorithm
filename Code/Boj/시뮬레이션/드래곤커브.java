package Boj.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 드래곤커브 {
    static int[] dx = {1, 0, -1, 0}; // 문제에서 있는 그대로 넣기.
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] visit = new boolean[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < d; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            dCurv(x, y, dir, g);
        }

        // 사각형 포함되는지 확인하기
        int count = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (visit[i][j] && visit[i][j+1] && visit[i+1][j] && visit[i+1][j+1]) {
                    count ++;
                }
            }
        }

        System.out.println(count);
    }

    private static void dCurv (int x, int y, int d, int g) {
        List<Integer> dirList = new ArrayList<>();

        visit[y][x] = true;
        dirList.add(d);

        // 갈 방향 리스트 저장
        for (int i = 0; i < g; i++) {
            for (int j = dirList.size() - 1; j >= 0; j--) {
                dirList.add((dirList.get(j) + 1)%4);
            }
        }

        // 방향 리스트 따라 방문 처리
        for (int dir: dirList) {
            y += dy[dir];
            x += dx[dir];
            visit[y][x] = true;
        }
    }
}
