package Boj.최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 35m (Success)
// 입력: 첫째 줄에 V와 E가 빈칸을 사이에 두고 주어진다. (2 ≤ V ≤ 400, 0 ≤ E ≤ V(V-1))
// 		다음 E개의 줄에는 각각 세 개의 정수 a, b, c가 주어진다.
// 		a번 마을에서 b번 마을로 가는 거리가 c인 도로가 있다는 의미이다. (a → b임에 주의) 거리는 10,000 이하의 자연수이다.
//		(a, b) 쌍이 같은 도로가 여러 번 주어지지 않는다.
// 출력: 첫째 줄에 최소 사이클의 도로 길이의 합을 출력한다. 운동 경로를 찾는 것이 불가능한 경우에는 -1을 출력한다.
// 풀이 방향: 명백한 플루이드 워셜 문제였다. 이런 문제만 나오면 얼마나 쉽게요.. 해설도 필요 없따
// 시간 복잡도: O(N^3), 약 64백만

public class 운동 {
	static int INF = (int) 1e9;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int v = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] d = new int[v+1][v+1];
		for (int i = 1; i <= v; i++) {
			Arrays.fill(d[i], INF);
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			d[from][to] = dist;
		}

		for (int i = 1; i <= v; i++) {
			for (int j = 1; j <= v; j++) {
				for (int k = 1; k <= v; k++) {
					if (d[j][i] + d[i][k] < d[j][k]) {
						d[j][k] =  d[j][i] + d[i][k];
					}
				}
			}
		}

		int min = INF;
		for (int i = 1; i < v; i++) {
			for (int j = 2; j <=v ; j++) {
				// 왕복 가능 여부
				if (d[i][j] != INF && d[j][i] != INF) {
					int cur = d[i][j] + d[j][i];
					min = Math.min(min, cur);
				}
			}
		}

		if (min != INF) {
			System.out.println(min);
		} else {
			System.out.println(-1);
		}
	}
}
