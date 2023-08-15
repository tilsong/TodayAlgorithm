package Programmers.최단거리;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class 다익스트라 {
	// 최단거리 배열
	static int[] d = new int[100001]; // 10만
	static int INF = (int) 1e9;
	static ArrayList<ArrayList<Node>> node = new ArrayList<>();
	static int n; // node 개수
	static int m; // 간선 개수
	static int start = 1;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//노드의 개수 n을 입력 받는다.
		n = sc.nextInt();
		//간선의 개수 m을 입력 받는다.
		m = sc.nextInt();
		//시작 노드 번호를 입력 받는다.
		start = sc.nextInt();

		//그래프 초기화 - 인덱스를 노드 번호로 하고 싶기 때문에 n+1개 만큼 넣기.
		for (int i = 0; i <= n; i++) {
			node.add(new ArrayList<Node>());
		}

		//모든 간선 정보 입력. a 노드에서 b로 가는 비용 c
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			node.get(a).add(new Node(b, c));
		}

		dijkstra();
	}

	public static void dijkstra() {
		// 최단 거리 테이블 세팅
		Arrays.fill(d, INF);

		int start = 1;
		Queue<Node> q = new PriorityQueue<>();
		q.offer(new Node(start, 0));
		d[start] = 0;

		while (!q.isEmpty()) {
			Node now = q.poll();

			if (d[now.index] < now.distance) continue;

			for (int i = 0; i < node.get(now.index).size(); i++) {
				int cost = d[now.index] + node.get(now.index).get(i).distance;

				if (cost < d[node.get(now.index).get(i).index]) {
					d[node.get(now.index).get(i).index] = cost; // 최단거리 갱신
					q.offer(new Node(node.get(now.index).get(i).index, cost));
				}
			}
		}

		for (int i = 1; i <= 6; i++) {
			System.out.println(d[i]);
		}
	}

	private static class Node implements Comparable<Node> {
		int index;
		int distance;

		Node(int index, int distance) {
			this.index = index;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node o) {
			return this.distance - o.distance;
		}
	}
}
