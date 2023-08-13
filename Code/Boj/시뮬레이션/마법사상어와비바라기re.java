package Boj.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 마법사상어와비바라기re {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] basket = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                basket[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<Cloud> clouds = new ArrayList<>();
        clouds.add(new Cloud(n-2,0));
        clouds.add(new Cloud(n-2,1));
        clouds.add(new Cloud(n-1,0));
        clouds.add(new Cloud(n-1,1));

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken()) % n;

            // 구름 이동시키기, 위치에 물 + 1
            for (Cloud cloud : clouds) {
                cloud.row = (cloud.row + (dRow[d] * s) + n) % n;
                cloud.col = (cloud.col + (dCol[d] * s) + n) % n;

                basket[cloud.row][cloud.col] ++;
            }

            // 각 구름 위치 대각선에 물의 양이 있는지 확인하고 그 수만큼 물의 양 증가시키기
            for (Cloud cloud : clouds) {
                int count = 0;
                for (int i = 1; i <= 7; i+=2) {
                    int nRow = cloud.row + dRow[i];
                    int nCol = cloud.col + dCol[i];

                    if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < n && basket[nRow][nCol] > 0) {
                        count ++;
                    }
                }
                basket[cloud.row][cloud.col] += count;
            }

            boolean[][] beforeClouds = new boolean[n][n];
            for (Cloud cloud : clouds) {
                beforeClouds[cloud.row][cloud.col] = true;
            }

            clouds.clear();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!beforeClouds[i][j] && basket[i][j] >= 2) {
                        basket[i][j] -= 2;
                        clouds.add(new Cloud(i,j));
                    }
                }
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                count += basket[i][j];
            }
        }

        System.out.println(count);
    }

    static int[] dRow = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dCol = {-1, -1, 0, 1, 1, 1, 0, -1};

    static class Cloud {
        int row;
        int col;

        public Cloud(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}