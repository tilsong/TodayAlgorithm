package Boj.DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Dfs와Bfs {
	static int[][] map;
	static int[] visited;

	// 60m (Success)
	// 입력: 첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다. 다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 입력으로 주어지는 간선은 양방향이다.
	// 출력: 첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다. V부터 방문된 점을 순서대로 출력하면 된다.
	// 풀이 방향
	// - 인접 리스트 활용했습니다. ArrayList 안 쓴 이유는 생각이 안 그랬습니다. 담에 풀 때는 ArrayList 쓰는 게 공간 효율 더 좋을 것 같습니다.
	// - 처음에 양방향 생각 못해서 틀렸습니다.
	// - Dfs는 재귀 함수를 이용한 스택 구조로, Bfs는 List를 활용한 큐 구조를 통해 풀었습니다.
	// - 그리고 지금 보니까 System.out.println을 방문 수만큼 쓰고 있는데, 모아서 한 번에 toString()으로 했으면 시간 효율이 더 좋았을 것 같습니다.
	// 시간 복잡도:
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());

		map = new int[n+1][n+1];
		visited = new int[n+1];

		// 간선 - 인접행렬
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int f = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[f][b] = 1;
			map[b][f] = 1;
		}

		dfs(v);
		Arrays.fill(visited, 0);
		bfs(v);
	}

	public static void dfs(int start) {
		if (visited[start] == 1) return;
		visited[start] = 1;
		System.out.println(start);

		for (int i = 1; i < map[start].length; i++) {
			if (map[start][i] == 1) dfs(i);
		}
	}

	public static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);

		while (!q.isEmpty()) {
			int p = q.poll();

			if (visited[p] == 1) continue;
			visited[p] = 1;
			System.out.println(p);

			for (int i = 1; i < map[p].length; i++) {
				if (map[p][i] == 1) q.offer(i);
			}
		}
	}
}

