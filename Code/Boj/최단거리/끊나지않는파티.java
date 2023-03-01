package Boj.최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 끊나지않는파티 {
	// 20m (Success)
	// 입력: 입력의 첫 번째 줄에는 파티장의 크기 N(5 ≤ N ≤ 500)과 서비스를 요청한 손님의 수 M(1 ≤ M ≤ 10,000) 이 주어진다. 각각의 파티장은 1번부터 N번까지 번호가 붙여져 있다. 다음에는 N개의 줄에 걸쳐 각각 N개의 수가 주어진다. i번째 줄의 j번째 수 T(1 ≤ T ≤ 1,000,000)는 i번 파티장에서 j번 파티장으로 직접적으로 연결된 도로를 통해 이동하는 시간을 의미한다.
	// 		다음 M개의 줄에는 세개의 정수 A, B, C가 주어진다. A(1 ≤ A ≤ N) 는 서비스를 요청한 손님이 위치한 파티장의 번호, B(1 ≤ B ≤ N) 다음 파티가 열리는 파티장의 번호, C(1 ≤ C ≤ 1,000,000,000)는 지금으로부터 다음 파티가 열리는데 걸리는 시간을 의미한다.
	// 출력: M개의 줄에 걸쳐 서비스를 요청한 손님이 시간내에 다른 파티장에 도착할 수 있으면 “Enjoy other party”를, 시간내에 도착할 수 없으면 "Stay here”를 출력한다.
	// 풀이 방향: 대놓고 플로이드 워셜을 쓰도록 유도한 문제였다. 모든 노드 간의 단방향 거리를 주었으며, 경로를 통했을 때 더 빠를 수 있다는 힌트를 주었다.
	// 시간 복잡도: O(N^3)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] d = new int[n+1][n+1];

		// 노드 간 거리 입력
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n ; j++) {
				d[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				for (int k = 1; k <= n; k++) {
					if (d[j][k] > d[j][i] + d[i][k]) {
						d[j][k] = d[j][i] + d[i][k];
					}
				}
			}
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int limit = Integer.parseInt(st.nextToken());

			if (d[start][end] <= limit) {
				System.out.println("Enjoy other party");
			} else {
				System.out.println("Stay here");
			}
		}
	}
}
