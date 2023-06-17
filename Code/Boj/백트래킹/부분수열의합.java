package Boj.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 부분수열의합 {
	static int count = 0;
	static int n;
	static int s;
	static int[] nums;
	static int[] arr;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		nums = new int[n];
		visited = new boolean[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);

		search(0, -1);

		System.out.println(count);
	}

	static void search(int total, int before) {
		if (total == s && before != -1) {
			count++;
		}

		for (int i = 0; i < n; i++) {
			if (!visited[i] && before < i) {
				visited[i] = true;
				search(total+nums[i], i);
				visited[i] = false;
			}
		}
	}
}


