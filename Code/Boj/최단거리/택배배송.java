package Boj.최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 40m (Success)
// 입력: 첫째 줄에 N과 M이 공백을 사이에 두고 주어집니다.
// 		둘째 줄부터 M+1번째 줄까지 세 개의 정수 A_i, B_i, C_i가 주어집니다.
// 출력: 첫째 줄에 농부 현서가 가져가야 될 최소 여물을 출력합니다
// 풀이 방향: 다익스트라 그대로 사용하면 되는 문제^^
// 시간 복잡도: O(E * log V) ==> 약 5만.
public class 택배배송 {
    static final int INF = (int) 1e9;
    static int n;
    static int m;
    static ArrayList<ArrayList<Node>> graph;
    static int[] d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        d = new int[n+1];
        Arrays.fill(d, INF);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, d));
            graph.get(b).add(new Node(a, d));
        }

        dijkstra();

        System.out.println(d[n]);
    }

    static void dijkstra() {
        Queue<Node> q = new PriorityQueue<>();
        q.offer(new Node(1, 0));
        d[1] = 0;

        while(!q.isEmpty()) {
            Node poll = q.poll();
            int now = poll.index;

            for (int i = 0; i < graph.get(now).size(); i++) {
                int cost = d[now] + graph.get(now).get(i).distance;

                if (cost < d[graph.get(now).get(i).index]) {
                    d[graph.get(now).get(i).index] = cost;
                    q.add(new Node(graph.get(now).get(i).index, cost));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int index;
        int distance;

        public Node(int index,int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return this.distance - other.distance;
        }
    }
}
