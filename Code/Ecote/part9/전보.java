package Ecote.part9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class 전보 {
	private static class Node implements Comparable<Node> {
		private int index;
		private int distance;

		public Node(int index, int distance) {
			this.index = index;
			this.distance = distance;
		}

		public int getIndex() {
			return index;
		}

		public int getDistance() {
			return distance;
		}

		// 거리(비용)가 짧은 것이 높은 우선순위를 가지도록 설정
		@Override
		public int compareTo(Node other) {
			if (this.distance < other.distance) return -1;
			return 1;
		}
	}

	public static ArrayList<ArrayList<Node>> graph = new ArrayList<>(); // 노드 간 최단 거리 정보
	public static int[] d; // 출발지 기준 최단 거리 테이블
	public static int INF = (int) 1e9; // 무한 의미하는 수

	public static void main(String[] args) {
		int n = 3; // 노드 개수
		int m = 2; // 간선 개수
		int c = 1; // 출발 노드 index

		mySolution(n, c);
	}

	// 40m (Success)
	// 입력: 노드 개수(int n), 간선 개수(int m), 간선 정보, 출발 노드(int c)
	// 출력: c에서 도달할 수 있는 노드의 수(int cities), 최대 도달 시간(int max)
	// 풀이 방향: 복잡하게 생각했다가, 다익스트라를 쓰면 가능한 모든 노드를 거칠 것이므로
	//			최단 거리가 무한이 아닌 노드로의 경로를 도달 가능한 노드의 수로 보고,
	//			도달 가능한 노드의 수에서 가장 긴 최대 도달 시간을 가진 노드의 시간을 최대 도달 시간으로 보면 되겠다는 결론을 내리게 되었다.
	// 시간 복잡도: O(Elog(N)
	public static void mySolution(int n, int start) {
		// 노드 간 최단 거리 리스트 초기화
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<Node>());
		}
		// 간선 정보 입력
		graph.get(1).add(new Node(2, 4));
		graph.get(1).add(new Node(3, 2));

		// 최단 거리 테이블 초기화 (출발지 기준)
		d = new int[n+1];
		Arrays.fill(d, INF);
		d[start] = 0;

		// 효율 다익스트라 시작
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start,0));

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int now = node.getIndex();
			int dist = node.getDistance();

			// 이미 처리된 최단 거리이면 무시
			if (d[now] < dist) continue;

			for (int i = 0; i < graph.get(now).size() ; i++) {
				int cost = d[now] + graph.get(now).get(i).getDistance();
				if (cost < d[graph.get(now).get(i).getIndex()]) {
					d[graph.get(now).get(i).getIndex()] = cost;
					pq.offer(new Node(graph.get(now).get(i).getIndex(), cost));
				}
			}
		}

		int max = 0;
		int cities = 0;

		for (int i = 1; i < d.length; i++) {
			if (d[i] < INF && i != start) {
				cities++; // 받는 도시 개수 ++
				if (d[i] > max) max = d[i]; // 최대 방문 갱신
			}
		}

		System.out.println("메시지를 받는 도시 개수: " + cities + ", 총 걸리는 시간: " + max);
	}
}
