package Boj.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 40m (Success)
// 입력: 첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)
// 출력: 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
//		수열은 사전 순으로 증가하는 순서로 출력해야 한다.
// 풀이 방향: 백트래킹 사용. 이전 수열의 값보다 현재 값이 커야한다는 조건을 추가함
// 시간 복잡도: O(n^m) => 최대 64

public class N과M_2 {
	static int n;
	static int m;
	static boolean[] visited;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken()); // depth

		visited = new boolean[n+1];
		arr = new int[m];

		search(0, 0);

		System.out.println(sb.toString());
	}

	static void search(int depth, int before) {
		if (depth == m) {
			for (int i = 0; i < m; i++) {
				sb.append(arr[i]).append(' ');
			}
			sb.append("\n");

			return;
		}

		for (int i = 1; i <= n; i++) {
			if (!visited[i] && before < i) {
				visited[i] = true;
				arr[depth] = i;
				search(depth+1, i);
				visited[i] = false;
			}
		}
	}
}
