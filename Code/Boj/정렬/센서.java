package Boj.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 센서 {
	// Sample Input/Output
	// n: 10
	// k: 5
	// nList: 3 6 7 8 10 12 14 15 18 20

	// 60m (Success)
	// 입력: 첫째 줄에 센서의 개수 N(1 ≤ N ≤ 10,000), 둘째 줄에 집중국의 개수 K(1 ≤ K ≤ 1000)가 주어진다.
	// 		셋째 줄에는 N개의 센서의 좌표가 한 개의 정수로 N개 주어진다.
	// 		각 좌표 사이에는 빈 칸이 하나 있으며, 좌표의 절댓값은 1,000,000 이하이다.
	// 출력: 첫째 줄에 입력으로 주어진 수열을 빈도 정렬한 다음 출력한다.
	// 풀이 방향: 각 센서 사이의 간격과 각 간격의 총합을 구하고, 이를 내림차 순으로 정렬한 뒤, 큰 순서 대로 k-1만큼을 뺀다.
	//			RuntimeError가 났었는데, k가 n보다 클 때의 경우를 고려하지 못한 경우 였다. 이를 처리하면 완료.
	//			좌표 위의 정수라고 했으므로 음수 처리도 해주어야 했다 => Math.abs로 처리했다.
	// 시간 복잡도: O(2N+2nlog(n))
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());

		if (k >= n) {
			System.out.println(0);
			return;
		}

		st = new StringTokenizer(br.readLine());
		int [] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr); // 3 6 7 8 10 12 14 15 18 20

		long total = 0;
		int[] mids = new int[n-1];
		for (int i = 1; i < n; i++) {
			int length = Math.abs(arr[i] - arr[i - 1]); // -5 -(2)
			total += length;
			mids[i-1] = length;
		}

		Arrays.sort(mids);

		for (int i = 0; i < k-1; i++) {
			total -= mids[mids.length -1 -i];
		}

		System.out.println(total);
	}
}
