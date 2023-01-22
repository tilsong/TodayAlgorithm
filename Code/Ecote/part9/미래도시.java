package Ecote.part9;

import java.util.Arrays;

// 30m (Success)
// 입력: 노드 개수(int n), 간선 개수(int m), 간선 정보, 목표 노드(int x), 거쳐야 하는 노드(int k)
// 출력: N(1) -> N(K) -> N(X)로 가는 최소 이동 시간 (int t, 도달할 수 없다면 -1)
// 풀이 방향: 노드의 최대 개수가 100이기 때문에, 플루이드 워셜을 써도 되겠다고 생각했다. 10^3 해도 백만이기 때문에..
//			먼저 모든 경로간 최단 거리를 구한 후에, a->i + i->b 를 구하면 된다.
//
//			만약, 다익스트라로 했다면 a->i, i->b로의 최단 거리를 구하는 다익스트라 알고리즘을 2번 수행했을 것이다.
//			그러면 시간 복잡도는 ElogN이었을 것이고 속도는 훨씬 빨랐을 것..
// 시간 복잡도: O(n^3) // 최대 백만
public class 미래도시 {
	static int[][] graph; // 최단 거리
	public static void main(String[] args) {
		int n = 5; // 노드의 개수
		int m = 7; // 간선 개수
		graph = new int[n+1][n+1];

		// 무한으로 채우기
		for (int i = 0; i < graph.length; i++) {
			Arrays.fill(graph[i], (int) 1e9);
		}
		// 간선 입력
		graph[1][2] = 1;
		graph[2][1] = 1;
		graph[1][3] = 1;
		graph[3][1] = 1;
		graph[1][4] = 1;
		graph[4][1] = 1;
		graph[2][4] = 1;
		graph[4][2] = 1;
		graph[3][4] = 1;
		graph[4][3] = 1;
		graph[3][5] = 1;
		graph[5][3] = 1;
		graph[4][5] = 1;
		graph[5][4] = 1;
		// 자기 자신과의 거리는 0
		for (int i = 1; i < graph.length; i++) {
			graph[i][i] = 0;
		}

		System.out.println(mySolution(n,m, 4,5));
	}

	public static int mySolution(int n, int m, int x, int k) {

		for (int i = 1; i <= n; i++) {
			for (int a = 1; a <= n; a++) {
				for (int b = 1; b <= n; b++) {
					// g(ab) = min(g(ab), g(ai) + g(ik))
					graph[a][b] = Math.min(graph[a][b], graph[a][i] + graph[i][b]);
				}
			}
		}

		// 구하고자 하는 것은 k를 거쳐 x로 가고자 하는 최단 거리
		// 즉, g(1k) + g(kx). 만약 한쪽이라도 값이 무한이면 -1 반환
		if (graph[1][k] == (int)1e9) return -1;
		if (graph[k][x] == (int)1e9) return -1;

		return graph[1][k] + graph[k][x];
	}
}
