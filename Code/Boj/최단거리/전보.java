package Boj.최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 전보 {
    static int n;
    static int m;
    static int c;
    static int INF = (int) 1e9;
    static int[] d;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = new int[n+1];
        Arrays.fill(d, INF);

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        m = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(to, dist));
        }

        dijkstra();

        int count = 0;
        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (d[i] != INF && i != c) {
                count++;
                if (max < d[i]) {
                    max = d[i];
                }
            }
        }

        System.out.println(count + " " + max);
    }

    static void dijkstra() {
        Queue<Node> q = new PriorityQueue<>();
        q.offer(new Node(c, 0));
        d[c] = 0;

        while (!q.isEmpty()) {
            Node now = q.poll();
            int dist = now.dist;
            int index = now.index;

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
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }
}
