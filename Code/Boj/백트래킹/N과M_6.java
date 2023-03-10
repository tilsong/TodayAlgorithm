package Boj.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 10m (Success)
public class N과M_6 {
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

		search(0,0);

		System.out.println(sb.toString());
	}

	static void search(int depth, int before) {
		if (depth == m) {
			for (int i = 0; i < m; i++) {
				sb.append(nums[i]).append(' ');
			}
			sb.append("\n");

			return;
		}

		for (int i = 0; i < n; i++) {
			if (!visited[i] && before < arr[i]) {
				visited[i] = true;
				nums[depth] = arr[i];
				search(depth+1, arr[i]);
				visited[i] = false;
			}
		}
	}
}
