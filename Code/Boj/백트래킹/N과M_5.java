package Boj.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 10m (Success)
// 입력: 첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)
//		둘째 줄에 N개의 수가 주어진다. 입력으로 주어지는 수는 10,000보다 작거나 같은 자연수이다.
// 출력: 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
//		수열은 사전 순으로 증가하는 순서로 출력해야 한다.
// 풀이 방향: 백트래킹 사용. E a s y
// 시간 복잡도: O(n^m + nlogn) => 최대 8^8

public class N과M_5 {
	static int n;
	static int m;
	static int[] arr;
	static int[] nums;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken()); // depth
		arr = new int[n];
		nums = new int[m];
		visited = new boolean[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		search(0, 0);

		System.out.println(sb.toString());
	}

	static void search(int depth, int before) {
		if (depth == m) {
			for (int i = 0; i < m; i++) {
				sb.append(nums[i]).append(' ');
			}
			sb.append('\n');

			return;
		}

		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				nums[depth] = arr[i];
				search(depth+1, arr[i]);
				visited[i] = false;
			}

		}
	}
}
