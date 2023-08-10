package Boj.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 뱀 {
    static class Location {
        int row;
        int col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int[] dRow = { 0, 1, 0, -1 };
    static int[] dCol = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        boolean[][] map = new boolean[n][n];
        int k = Integer.parseInt(br.readLine());
        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            map[row][col] = true;
        }
        int l = Integer.parseInt(br.readLine());
        HashMap <Integer, Integer> directions = new HashMap<>();
        while (l-- > 0) {
            st = new StringTokenizer(br.readLine());
            int sec = Integer.parseInt(st.nextToken());
            int dir = st.nextToken().equals("D") ? 1 : -1;
            directions.put(sec, dir);
        }

        Deque<Location> snake = new LinkedList<>();
        snake.add(new Location(0, 0));

        int seq = 0;
        int d = 0;
        while (true) {
            seq++;

            Location head = snake.peekFirst();
            int nRow = head.row + dRow[d];
            int nCol = head.col + dCol[d];

            // 벽 체크
            if (nRow < 0 || nRow >= n || nCol < 0 || nCol >= n) {
                break;
            }

            // 몸 체크
            boolean bodyCheck = false;
            for (Location location : snake) {
                if (location.row == nRow && location.col == nCol) {
                    bodyCheck = true;
                }
            }
            if (bodyCheck) break;

            snake.addFirst(new Location(nRow, nCol));

            if (map[nRow][nCol]) { // 사과가 있으면
                map[nRow][nCol] = false;
            } else { // 사과가 없으면
                snake.pollLast();
            }

            // 방향 전환
            if (directions.containsKey(seq)) {
                d = (d + directions.get(seq) + 4) % 4;
            }
        }

        System.out.println(seq);
    }
}

