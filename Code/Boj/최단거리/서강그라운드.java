package Boj.최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 40m (Success)
// 입력: 첫째 줄에는 지역의 개수 n (1 ≤ n ≤ 100)과 예은이의 수색범위 m (1 ≤ m ≤ 15), 길의 개수 r (1 ≤ r ≤ 100)이 주어진다.
//      둘째 줄에는 n개의 숫자가 차례대로  각 구역에 있는 아이템의 수 t (1 ≤ t ≤ 30)를 알려준다.
//      세 번째 줄부터 r+2번째 줄 까지 길 양 끝에 존재하는 지역의 번호 a, b, 그리고 길의 길이 l (1 ≤ l ≤ 15)가 주어진다.
// 출력: 예은이가 얻을 수 있는 최대 아이템 개수를 출력한다.
// 풀이 방향: 각 지역에서 다른 거리로의 최단 거리를 구하고, 도달이 가능할 경우, 해당 값을 최대 아이템 개수와 비교하여 더 큰 값을 출력한다.
// 시간 복잡도: O(E*log(V) * E) => 약 6만 + a

public class 서강그라운드 {
    static int INF = (int) 1e9;
    static int n;
    static int m;
    static int r;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static int[] d;
    static int[] regionItemCountList;
    static int maxItemCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        d = new int[n+1];
        regionItemCountList = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            regionItemCountList[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, distance));
            graph.get(b).add(new Node(a, distance));
        }

        for (int i = 1; i <= n; i++) {
            Arrays.fill(d, INF);
            dijkstra(i);

            int itemCount = 0;
            for (int j = 1; j <= n; j++) {
                if (d[j] <= m) {
                    itemCount += regionItemCountList[j];
                }
            }
            maxItemCount = Math.max(itemCount, maxItemCount);
        }

        System.out.println(maxItemCount);
    }

    static void dijkstra(int start) {
        Queue<Node> q = new PriorityQueue<>();
        q.offer(new Node(start, 0));
        d[start] = 0;
        
        while(!q.isEmpty()) {
            Node poll = q.poll();
            int now = poll.index;

            for (int i = 0; i < graph.get(now).size(); i++) {
                int cost = d[now] + graph.get(now).get(i).distance;

                if (cost < d[graph.get(now).get(i).index]) {
                    d[graph.get(now).get(i).index] = cost;
                    q.offer(new Node(graph.get(now).get(i).index, cost));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int index;
        int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return this.distance - other.distance;
        }
    }
}
