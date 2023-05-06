package Boj.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 25m (Success)
// 입력: 첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 7)
// 출력: 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
//		수열은 사전 순으로 증가하는 순서로 출력해야 한다.
// 풀이 방향: 백트래킹 사용. 각 depth 별로 별도의 방문처리 배열을 만들어 사용했음.
// 시간 복잡도: O(n^m) => 최대 7^7
public class N과M_3 {
	static int n;
	static int m;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken()); // depth
		arr = new int [m];

		search(0);

		System.out.println(sb.toString());
	}

	static void search(int depth) {
		if (depth == m) {
			for (int i = 0; i < m; i++) {
				sb.append(arr[i]).append(' ');
			}
			sb.append('\n');

			return;
		}

		for (int i = 1; i <= n; i++) {
			arr[depth] = i;
			search(depth+1);
		}
	}
}