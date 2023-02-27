package Boj.최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 이미 많이 다루어진 로직이므로 상세 설명은 추가하지 않음.
public class 최단거리 {
	static int v;
	static int e;
	static int start;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	static int[] d;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 노드 정보 초기화
		v = Integer.parseInt(st.nextToken());
		for (int i = 0; i <= v; i++) { // 1부터 시작이므로 <= 으로 설정
			graph.add(new ArrayList<>());
		}

		// 최단 거리 테이블 초기화
		e = Integer.parseInt(st.nextToken());
		d = new int[v+1];
		Arrays.fill(d, (int) 1e9);

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		d[start] = 0;

		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			graph.get(from).add(new Node(to, dist));
		}

		dijkstra();

		for (int i = 1; i <= v; i++) {

			if (d[i] == (int) 1e9) {
				System.out.println("INF");
			} else {
				System.out.println(d[i]);
			}
		}
	}

	static void dijkstra () {
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.offer(new Node(start, 0));

		while (!q.isEmpty()) {
			Node now = q.poll();
			int dist = now.distance;
			int index = now.index;

			if (d[index] < dist) continue;

			for (int i = 0; i < graph.get(index).size(); i++) {
				int cost = graph.get(index).get(i).distance + d[index];

				if (cost < d[graph.get(index).get(i).index]) {
					d[graph.get(index).get(i).index] = cost;
					q.offer(new Node(graph.get(index).get(i).index, cost));
				}
			}
		}
	}

	private static class Node implements Comparable<Node> {
		int index;
		int distance;

		public Node(int index, int distance) {
			this.index = index;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node o) {
			return this.distance - o.distance;
		}
	}
}
