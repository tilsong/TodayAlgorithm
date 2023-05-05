package Programmers.DfsBfs;
import java.util.*;

public class 전력망을둘로나누기 {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;

    public int solution(int n, int[][] wires) {
        int answer = (int) 1e9;

        for (int i = 0; i < wires.length + 2; i++) {
            graph.add(new ArrayList<>());
        }

        visited = new boolean[wires.length + 2];

        for (int i = 0; i < wires.length; i++) {
            int[] wire = wires[i];
            graph.get(wire[0]).add(wire[1]);
            graph.get(wire[1]).add(wire[0]);
        }

        for (int i = 0; i< wires.length; i++) {
            int [] wire = wires[i];
            // 현재 wire를 그래프에서 지우는 로직
            graph.get(wire[0]).removeIf(item -> item == wire[1]);
            graph.get(wire[1]).removeIf(item -> item == wire[0]);

            int v1 = search(wire[0]);
            visited = new boolean[wires.length + 2];
            int v2 = search(wire[1]);
            visited = new boolean[wires.length + 2];

            answer = Math.min(answer, Math.abs(v1-v2));

            graph.get(wire[0]).add(wire[1]);
            graph.get(wire[1]).add(wire[0]);
        }

        return answer;
    }

    public static int search (int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;
        int count = 1;

        while(!q.isEmpty()) {
            int now = q.poll();

            for (int i = 0; i < graph.get(now).size(); i++) {
                int node = graph.get(now).get(i);

                if (!visited[node]) {
                    q.offer(node);
                    visited[node] = true;
                    count ++;
                }
            }
        }

        return count;
    }
}
