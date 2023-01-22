package Ecote.part9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 완전 탐색을 통해 최단 거리를 탐색
 * 0(V^2)의 시간 복잡도를 가짐. V는 노드의 수
 */
public class 간단다익스트라 {
	private static class Node {
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
	}

	public static final int INF = (int) 1e9; // 무한 의미, 10억
	// 노드의 개수(N), 간선의 개수(M), 시작 노드 번호(Start)
	// 노드의 개수는 최대 100,000개라고 가정
	public static int n, m, start;
	// 각 노드에 연결되어 있는 노드에 대한 정보를 담는 배열
	public static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	// 방문 체크 배열
	public static boolean[] visited = new boolean[100001];
	// 최단 거리 테이블 만들기
	public static int[] d = new int[100001];

	// 방문하지 않은 노드 중에서, 가장 최단 거리가 짧은 노드의 번호를 반환
	public static int getSmallestNode() {
		int min = INF;
		int idx = 0; // 가장 최단 거리가 짧은 노드(인덱스)
		for (int i = 1; i <= n; i++) {
			if(d[i] < min && !visited[i]) {
				min = d[i];
				idx = i;
			}
		}
		return idx;
	}

	public static void dijkstra(int start) {
		// 시작 노드 초기화
		d[start] = 0;
		visited[start] = true;
		for (int i = 0; i < graph.get(start).size(); i++) {
			d[graph.get(start).get(i).getIndex()] = graph.get(start).get(i).getDistance();
		}

		// 시작 노드를 제외한 전체 n-1개의 노드에 대해 반복
		for (int i = 0; i < n - 1; i++) {
			// 현재 최단 거리가 가장 짧은 노드를 꺼내서, 방문 처리
			int now = getSmallestNode();
			visited[now] = true;
			// 현재 노드와 연결된 다른 노드를 확인
			for (int j = 0; j < graph.get(now).size(); j++) {
				int cost = d[now] + graph.get(now).get(j).getDistance();
				// 현재 노드를 거쳐서 다른 노드로 이동하는 거리가 더 짧은 경우
				if (cost < d[graph.get(now).get(j).getIndex()]) {
					d[graph.get(now).get(j).getIndex()] = cost;
				}
			}
		}
	}


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();
		start = sc.nextInt();

		// 그래프 초기화
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<Node>());
		}

		// 모든 간선 정보를 입력받기
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			// a번 노드에서 b번 노드로 가는 비용이 c라는 의미
			graph.get(a).add(new Node(b, c));
		}

		// 최단 거리 테이블을 모두 무한으로 초기화
		Arrays.fill(d, INF);

		// 다익스트라 알고리즘 수행
		dijkstra(start);

		// 모든 노드로 가기 위한 최단 거리를 출력
		for (int i = 1; i <= n; i++) {
			// 도달할 수 없는 경우, 무한(INFINITY)이라고 출력
			if (d[i] == INF) {
				System.out.println("INFINITY");
			}
			// 도달할 수 있는 경우 거리를 출력
			else {
				System.out.println(d[i]);
			}
		}
	}
}
