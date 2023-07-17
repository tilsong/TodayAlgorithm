package Boj.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 53m
//
// 풀이
// : 총 치킨집 중 m개만큼의 치킨집이 있을 때, 가장 작은 치킨 거리가 발생하는 경우를 구한다.
//   요소가 중복되지 않도록 백트래킹을 사용한다.

public class 치킨배달 {
    static int n;
    static int m;
    static List <int[]> houses;
    static List <int[]> chickens;
    static int[][] selectedChicks;

    static int min = (int) 1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        selectedChicks = new int[m][2];

        houses = new ArrayList<>();
        chickens = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int h = Integer.parseInt(st.nextToken());
                if (h == 1) { // 집이면
                    houses.add(new int[]{i, j});
                } else if (h == 2) { // 치킨 집이면
                    chickens.add(new int[]{i, j});
                }
            }
        }

        backtracking(0, -1);

        System.out.println(min);
    }

    private static void backtracking(int depth, int before) {
        if (depth == m) {
            // 주어진 치킨집 목록과 각 집 간의 치킨 거리의 합 구하기
            int sum = 0;

            for (int i = 0; i < houses.size(); i++) {
                int[] loc = houses.get(i);
                int dist = (int) 1e9;
                for (int j = 0; j < selectedChicks.length; j++) {
                    int temp = Math.abs(loc[0] - selectedChicks[j][0]) + Math.abs(loc[1] - selectedChicks[j][1]);
                    dist = Math.min(dist, temp);
                }
                sum += dist;
            }

            min = Math.min(min, sum);

            return;
        }

        for (int i = 0; i < chickens.size(); i++) {
            if (before < i) {
                // in
                selectedChicks[depth] = chickens.get(i);

                // backtracking
                backtracking(depth+1, i);

                // out
                selectedChicks[depth] = null;
            }
        }

    }
}
