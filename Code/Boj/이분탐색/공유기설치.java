package Boj.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 공유기설치 {
	static int[] x;
	static int c;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		x = new int[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			x[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(x);

		System.out.println(binarySearch());
	}

	static int binarySearch() {
		int start = 1;
		int end = x[x.length-1] - x[0];
		int answer = 0;

		while(start <= end) {
			int mid = (start + end) / 2;

			int count = check(mid);

			if (count < c) {
				end = mid -1;
			} else {
				answer = mid;
				start = mid + 1;
			}
		}

		return answer;
	}

	static int check(int d) {
		int count = 0;

		int cur = 0;
		for (int i = 1; i < x.length; i++) {
			int xD = x[i] - x[cur];
			if (xD >= d) {
				count++;
				cur = i;
			}
		}

		return count + 1;
	}
}
