package Boj.최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 40m (Success)
// 입력: 첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 10,000), X가 공백으로 구분되어 입력된다. 두 번째 줄부터 M+1번째 줄까지 i번째 도로의 시작점, 끝점, 그리고 이 도로를 지나는데 필요한 소요시간 Ti가 들어온다. 시작점과 끝점이 같은 도로는 없으며, 시작점과 한 도시 A에서 다른 도시 B로 가는 도로의 개수는 최대 1개이다.
//       모든 학생들은 집에서 X에 갈수 있고, X에서 집으로 돌아올 수 있는 데이터만 입력으로 주어진다.
// 출력: 첫 번째 줄에 N명의 학생들 중 오고 가는데 가장 오래 걸리는 학생의 소요시간을 출력한다.
// 풀이 방향: 솔직히 시간 복잡도 생각하면 안되는게 맞는데 풀이 방법이 이것밖에 떠오르지 않았고.. 맞았다. 좋은 문제는 아니라고 생각한다.
// 시간 복잡도: O(2*E^2log(V)) //말도 안됨..

public class 파티 {
    static int n;
    static int m;
    static int x;

    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static int[] d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        d = new int[n+1]; // 각 노드에 대한 최단 거리 테이블
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>()); // 각 노드에 대한 그래프 생성
        }

        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(to, dist));
        }

        int max = 0;
        for (int i = 1; i <= n ; i++) {
            if (i != x) {
                Arrays.fill(d, (int) 1e9);

                dijkstra(i);
                int go = d[x];
                Arrays.fill(d, (int) 1e9);

                dijkstra(x);
                int back = d[i];
                System.out.println(i+": " + go +" "+ back);

                int gb = go + back;
                if (max < gb) {
                    max = gb;
                }
            }
        }

        System.out.println(max);
    }

    static void dijkstra (int start) {
        Queue<Node> q = new PriorityQueue<>();
        q.offer(new Node(start, 0));
        d[start] = 0;

        while (!q.isEmpty()) {
            Node now = q.poll();
            int dist = now.dist;
            int index = now.index;

            if (d[index] < dist) continue;

            for (int i = 0; i < graph.get(index).size(); i++) {
                int cost = d[index] + graph.get(index).get(i).dist;

                if (cost < d[graph.get(index).get(i).index]) {
                    d[graph.get(index).get(i).index] = cost;
                    q.offer(new Node(graph.get(index).get(i).index, cost));
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {
        int index;
        int dist;

        public Node(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node n) {
            return this.dist - n.dist;
        }
    }
}
