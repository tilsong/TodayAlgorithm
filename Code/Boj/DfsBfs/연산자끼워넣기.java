package Boj.DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 연산자끼워넣기 {
	static int n;
	static int[] numbers;
	static int[] ops = new int[4];

	static int max = -2147483648;
	static int min = 2147483647;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 수열 길이 입력
		numbers = new int[n];

		st = new StringTokenizer(br.readLine()); // 수열 입력
		for (int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine()); // 각 연산자 개수 입력
		for (int i = 0; i < 4; i++) {
			ops[i] = Integer.parseInt(st.nextToken());
		}

		dfs(0, numbers[0]);

		System.out.println(max);
		System.out.println(min);
	}


	public static void dfs(int cur, int total) {
		if (cur == n-1) {
			if (total > max) max = total;
			if (total < min) min = total;
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (ops[i] > 0) {
				ops[i] --;
				int before = total;
				if (i == 0) {
					total += numbers[cur+1];
				} else if (i == 1) {
					total -= numbers[cur+1];
				} else if (i == 2) {
					total *= numbers[cur+1];
				} else if (i == 3) {
					if((total < 0 && numbers[cur+1] > 0) | (total > 0 && numbers[cur+1] < 0)) {
						total = Math.abs(total) / Math.abs(numbers[cur + 1]);
						total *= -1;
					} else {
						total /= numbers[cur+1];
					}
				}
				dfs(cur+1, total);
				total = before;
				ops[i] ++;
			}
		}

		return;
	}

}
