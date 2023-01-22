package Ecote.part9;

import java.util.Arrays;
import java.util.Scanner;

public class 워셜플로이드 {
	public static final int INF = (int) 1e9; // 무한의 값을 의미
	// 노드의 개수(N), 간선의 개수(M)
	// 노드의 개수는 최대 500개라고 가정
	public static int n, m;
	// 2차원 배열(그래프 표현)을 만들기
	public static int[][] graph = new int[501][501];

	public static void main(String[] main) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		// 최단 거리 테이블을 모두 무한으로 초기화
		for (int i = 0; i < 501; i++) {
			Arrays.fill(graph[i], INF);
		}

		// 자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화
		for (int i = 1; i <= n; i++) {
			graph[i][i] = 0;
		}

		// 각 간선에 대한 정보를 입력 받아, 그 값으로 초기화
		for (int i = 0; i < m; i++) {
			// A에서 B로 가는 비용은 C라고 설정
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			graph[a][b] = c;
		}

		// 점화식에 따라 플로이드 워셜 알고리즘을 수행
		for (int k = 1; k <= n; k++) { // 중간 노드
			for (int a = 1; a <= n; a++) { // a-> k -> b
				for (int b = 1; b <= n; b++) {
					graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
				}
			}
		}

		// 수행된 결과를 출력
		for (int a = 1; a <= n; a++) {
			for (int b = 1; b <= n; b++) {
				// 도달할 수 없는 경우, 무한(INFINITY)이라고 출력
				if (graph[a][b] == INF) {
					System.out.print("INFINITY ");
				}
				// 도달할 수 있는 경우 거리를 출력
				else {
					System.out.print(graph[a][b] + " ");
				}
			}
			System.out.println();
		}

	}

}
