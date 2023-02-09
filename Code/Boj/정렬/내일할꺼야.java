package Boj.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 내일할꺼야 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());

		work[] arr = new work[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			int d = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			arr[i] = new work(d,t);
		}

		Arrays.sort(arr, (o1, o2) -> {
			return o2.t - o1.t;
		});

		int now = arr[0].t;
		for (int i = 0; i < arr.length; i++) {
			now = now - arr[i].d; // 12
			if (i < arr.length -1 && now > arr[i+1].t) {
				now = arr[i+1].t;
			}
		}

		System.out.println(now);
	}

	private static class work {
		int d;
		int t;

		public work(int d, int t) {
			this.d = d;
			this.t = t;
		}
	}
}
