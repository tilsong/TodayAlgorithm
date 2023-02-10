package Boj.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 먹을것인가먹힐것인가 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int t = Integer.parseInt(st.nextToken());

		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			int[] aArr = new int[a];
			for (int j = 0; j < a; j++) {
				aArr[j] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			int[] bArr = new int[b];
			for (int j = 0; j < b; j++) {
				bArr[j] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(aArr);
			Arrays.sort(bArr);

			// 받기
			System.out.println(solution(aArr, bArr));
		}

	}

	static int solution(int[] a, int[] b) {
		int answer = 0;

		for (int i = 0; i < a.length; i++) {
			int bIndex = binarySearch(b, a[i]);
			answer += (bIndex + 1);
		}

		return answer;
	}

	// 해당하는 index 반환
	static int binarySearch(int[] b, int target) {
		int answer = -1;
		int start = 0;
		int end = b.length - 1;

		while (start <= end) {
			int mid = (start + end) / 2;

			if (b[mid] < target) {
				answer = mid;
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}

		return answer;
	}
}
