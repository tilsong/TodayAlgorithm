package Boj.최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 70m (Fail -> Success)
// 입력: 입력은 여러 개의 테스트 케이스로 이루어져 있다. 각 테스트 케이스의 첫째 줄에는 동굴의 크기를 나타내는 정수 N이 주어진다. (2 ≤ N ≤ 125) N = 0인 입력이 주어지면 전체 입력이 종료된다.
//      이어서 N개의 줄에 걸쳐 동굴의 각 칸에 있는 도둑루피의 크기가 공백으로 구분되어 차례대로 주어진다. 도둑루피의 크기가 k면 이 칸을 지나면 k루피를 잃는다는 뜻이다. 여기서 주어지는 모든 정수는 0 이상 9 이하인 한 자리 수다.
// 출력: 첫 번째 줄에 N명의 학생들 중 오고 가는데 가장 오래 걸리는 학생의 소요시간을 출력한다.
// 풀이 방향: 처음에는 배열로 입력 받은 뒤, 배열을 가지고 원래 했던 것처럼 graph를 만들어서 기존의 최단 거리 코드들을
//          적용하려고 했었다. 그런데 그렇게 하니까 너무 복잡해졌다.
//          아래의 풀이에서는 노드의 배열을 그대로 두고, 최단 거리 테이블도 동일한 크기의 배열로 만들었다.
//          결국 현재의 최단 거리가 구해진 노드를 기준으로, 이어진 간선들의 최단 거리를 추가적으로 구하는 방식이다.
//          배 웠 다 !.
// 시간 복잡도: 주어진 노드(N) * 방향 4개(4) => O(N) // 추가적인 계산은 하지 않음

public class 녹색옷을입은애가젤다지 {
    static int n;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int num = 1;
        while(true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            if (n == 0) break;

            int[][] map = new int[n][n];
            int[][] d = new int[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    d[i][j] = (int) 1e9;
                }
            }

            dijkstra(map, d);

            System.out.println("Problem " + num++ + ": " + d[n-1][n-1]);
        }
    }

    static void dijkstra(int[][] map, int[][] d) {
        Queue<Node> q = new PriorityQueue<>();
        d[0][0] = map[0][0];
        q.offer(new Node(0,0, map[0][0]));

        while (!q.isEmpty()) {
            Node poll = q.poll();
            int x = poll.x;
            int y = poll.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 | nx >= n | ny < 0 | ny >= n) continue;

                int cost = map[nx][ny] + d[x][y];
                if (cost < d[nx][ny]) {
                    d[nx][ny] = cost;
                    q.offer(new Node(nx, ny, cost));
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {
        int x;
        int y;
        int dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }
}
