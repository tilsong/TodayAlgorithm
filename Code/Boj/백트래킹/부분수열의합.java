package Boj.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 부분수열의합 {
	static int total = 0;
	static int dep = 0;
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
		arr = new int[n];
		visited = new boolean[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);

		for (int i = 1; i <= n ; i++) {
			dep++;
			search(0, nums[0]);
		}

		System.out.println(total);
	}

	static void search(int depth, int before) {
		if (depth == dep) {
			int cost = 0;
			for (int i = 0; i < dep; i++) {
				System.out.print(arr[i]);
				cost += arr[i];
			}
			System.out.println();

			if (cost == s) {
				total ++;
			}
			return;
		}

		for (int i = 0; i < n; i++) {
			if (!visited[i] && before <= arr[i]) {
				visited[i] = true;
				arr[depth] = nums[i];
				search(depth+1, arr[depth]);
				visited[i] = false;
			}
		}
	}
}
