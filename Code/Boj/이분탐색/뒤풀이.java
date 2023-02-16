package Boj.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 뒤풀이 {
	static int t;
	static int[] l;
	static int[] r;

	// 말렸다. 이건 진자 말렸다..ㅠ
	// 다음에 다시 풀어보기!
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		l = new int[n];
		int maxL = 0;
		r = new int[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			l[i] = Integer.parseInt(st.nextToken());
			if (maxL < l[i]) {
				maxL = l[i];
			}
			r[i] = Integer.parseInt(st.nextToken());
		}

		int start = maxL;
		int end = (int) 1e6;
		int ans = -1;
		while (start <= end) {
			int mid = (start + end) / 2;

			if (check(mid)) {
				ans = mid;
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}

		System.out.println(ans);
	}

	static boolean check(int mid) {
		int restT = t;

		for (int i = 0; i < l.length; i++) {
			restT -= l[i];
		}
		if (restT < 0) {
			return false;
		}

		for (int i = 0; i < r.length; i++) {
			restT -= (r[i] -l[i]);
		}

		if (restT > 0) {
			return false;
		}

		return true;
	}
}
