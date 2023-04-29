package Boj.DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 45m (Success)
// 입력: 첫 번째 줄에는 지도의 크기 N(정사각형이므로 가로와 세로의 크기는 같으며 5≤N≤25)이 입력되고, 그 다음 N줄에는 각각 N개의 자료(0혹은 1)가 입력된다.
// 출력: 첫 번째 줄에는 총 단지수를 출력하시오. 그리고 각 단지내 집의 수를 오름차순으로 정렬하여 한 줄에 하나씩 출력하시오.
// 풀이 방향: 현재 위치가 단지(1)이면, 인접한 모든 단지를 0으로 만들고, 해당 단지를 구성하는 집의 수를 구하여 리스트에 저장한다. 이렇게 모든 위치에 대해 반복하고, 출력한다.
// 시간 복잡도: w와 h 이중 for 문, node 수, 4 방향 고려 => O(w * h * node * 8) , 약 50 * 50 * (25^2) * 4 = 50만

public class 단지번호붙이기 {
    static int n;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String[] split = st.nextToken().split("");

            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(split[j]);
            }
        }

        List<Integer> dangi = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) { // 단지이면
                    dangi.add(bfs(j, i));
                }
            }
        }

        Collections.sort(dangi);

        System.out.println(dangi.size());
        for (int i = 0; i < dangi.size(); i++) {
            System.out.println(dangi.get(i));
        }
    }

    static int bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        map[y][x] = 0;

        int count = 1;

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int gx = now.x + dx[i];
                int gy = now.y + dy[i];

                if (gx >= 0 && gx < n && gy >= 0 && gy < n) {
                    if (map[gy][gx] != 0) {
                        q.add(new Node(gx, gy));
                        map[gy][gx] = 0;
                        count ++;
                    }
                }
            }
        }
        return count;
    }

    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { 1, -1, 0, 0 };

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
