package Boj.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 50m (Success)
// 입력: 첫째 줄에 지도의 크기 R과 C (1 ≤ R, C ≤ 10)가 주어진다. 다음 R개 줄에는 현재 지도가 주어진다.
// 출력: 50년 후의 지도를 출력한다.
// 풀이 방향: 시뮬레이션 문제로, 지도에서 먼저 바다가 될 수 있는 땅을 찾고, 이를 바다로 바꾼다. 남은 땅을 포함하는 최소한의 직사각형을 찾아 출력한다.
// 시간 복잡도: O(N) 에 수렴

public class 지구온난화 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        String[][] map = new String[r][c];
        List<Land> lands = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            String[] split = st.nextToken().split("");

            for (int j = 0; j < c; j++) {
                if (split[j].equals("X")) {
                    lands.add(new Land(i, j));
                }
                map[i][j] = split[j];
            }
        }

        List<Land> newSea = new ArrayList<>();

        for (int i = 0; i < lands.size(); i++) {
            Land land = lands.get(i);

            int count = 0;
            for (int j = 0; j < 4; j++) {
                int row = land.row + dx[j];
                int col = land.col + dy[j];

                if (row < 0 || row >= r || col < 0 || col >= c) {
                    count++;
                } else if (row >= 0 && row < r && col >= 0 && col < c){
                    if (map[row][col].equals(".")) {
                        count++;
                    }
                }
            }

            if (count == 3 || count == 4) {
                newSea.add(land);
            }
        }

        for (int i = 0; i < newSea.size(); i++) {
            Land land = newSea.get(i);
            lands.remove(land);
            map[land.row][land.col] = ".";
        }

        int minRow = 101;
        int maxRow = 0;
        int minCol = 101;
        int maxCol = 0;

        for (int i = 0; i < lands.size(); i++) {
            Land land = lands.get(i);

            minRow = Math.min(land.row, minRow);
            maxRow = Math.max(land.row, maxRow);
            minCol = Math.min(land.col, minCol);
            maxCol = Math.max(land.col, maxCol);
        }

        if (minRow == 101) {
            minRow = maxRow;
        }
        if (minCol == 101) {
            minCol = maxCol;
        }

        String[] answer = new String[maxRow - minRow + 1];

        int k = 0;
        for (int i = minRow; i <= maxRow; i++) {
            String row = "";
            for (int j = minCol; j <= maxCol; j++) {
                row += map[i][j];
            }
            answer[k] = row;
            k++;
        }

        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }
    }
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static class Land {
        int row;
        int col;

        public Land(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
