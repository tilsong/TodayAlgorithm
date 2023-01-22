package Programmers.최단거리;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class 배달 {
	// Input/Output Sample
	// N	road	K	result
	// 5	[[1,2,1],[2,3,3],[5,2,2],[1,4,2],[5,3,1],[5,4,2]]	3	4
	// 6	[[1,2,1],[1,3,2],[2,3,2],[3,4,3],[3,5,2],[3,5,3],[5,6,1]]	4	4

	// 40m (Success)
	// 입력: 노드 개수(int N), 노드간 연결 정보(int[][] road), 배달 가능 거리(int K)
	// 출력: 배달 가능한 마을의 최대 수(int max)
	// 풀이 방향: 기본 다익스트라 문제이므로, 우선순위 큐를 통해 접근. K 이하의 최단 거리를 가진 마을 수를 리턴함.
	// 시간 복잡도: O(Elog(N))

	final int INF = (int) 1e9;
	int[] d;
	ArrayList<ArrayList<Node>> graph = new ArrayList<>();

	public int solution(int N, int[][] road, int K) {
		// 최단 거리 배열 초기화
		d = new int[N+1];
		Arrays.fill(d, INF);
		d[1] = 0;

		// 그래프 만들기
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Node>());
		}
		// 간선 입력
		for (int i = 0; i < road.length; i++) {
			graph.get(road[i][0]).add(new Node(road[i][1], road[i][2]));
			graph.get(road[i][1]).add(new Node(road[i][0], road[i][2]));
		}

		// 다익스트라 수행
		// 큐 초기화
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1, 0));

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int now = node.getIndex();
			int dist = node.getDistance();

			// 현재 최단 거리가 이미 있으면 패스
			if (d[now] < dist) continue;

			for (int i = 0; i < graph.get(now).size(); i++) {
				int cost = d[now] + graph.get(now).get(i).getDistance();
				if (cost < d[graph.get(now).get(i).getIndex()]) {
					d[graph.get(now).get(i).getIndex()] = cost;
					pq.offer(new Node(graph.get(now).get(i).getIndex(), cost));
				}
			}
		}

		int max = 0;
		for (int i = 1; i < d.length ; i++) {
			if (d[i] <= K) max ++;
		}

		return max;
	}

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
}
