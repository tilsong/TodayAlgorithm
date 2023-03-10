package Boj.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N과M_1 {
	static int m;
	static int n;
	static boolean[] visited;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken()); // depth

		visited = new boolean[n];
		arr = new int[m];

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

		for (int i = 0;  i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				arr[depth] = i + 1;
				search(depth+1);
				visited[i] = false;
			}
		}
	}
}
