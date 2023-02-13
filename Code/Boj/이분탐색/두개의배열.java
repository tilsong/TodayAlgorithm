package Boj.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 두개의배열 {
	// Sample Input/Output
	// 3
	// 4 3
	// 20 5 14 9
	// 16 8 12
	// 3 4
	// 16 8 12
	// 20 5 14 9
	// 3 3
	// 1 2 3
	// 2 3 4

	// 50m (Success)
	// 입력: 첫 줄에 테스트 케이스의 수 T (1 <= T <= 10)가 주어진다.
	// 		각 테스트 케이스는 세 줄에 걸쳐서 주어진다.
	// 		첫 줄에는 n과 m이 공백으로 구분되어 주어진다 (1 <= n, m <= 10^6).
	// 		두 번째 줄에는 공백으로 구분된 n개의 정수가 주어지며, A[1] 부터 A[n]을 나타낸다 (각각의 값은 1이상 10^9 이하이다).
	// 		세 번째 줄에는 공백으로 구분된 m개의 정수가 주어지며, B[1] 부터 B[m]을 나타낸다 (각각의 값은 1이상 10^9 이하이다).
	// 		앞서 언급한대로, A와 B는 각각 서로 다른 양의 정수들을 포함한 배열들이다.
	// 출력: 각 테스트 케이스에 대해 배열 C를 구하고 해당 배열의 모든 원소 합을 한 줄에 출력하시오.
	// 풀이 방향: 조건에 맞게 이분 탐색할 것! mList의 모든 요소보다 작거나 큰 n 요소 나오면 예외처리해주었음.
	//			처음에 틀렸었는데, C배열의 합이 int의 범위를 벗어날 경우 오버플로우가 발생해서 오답 처리되는 것이었다.
	//			int를 long 으로 변경하니 문제가 되지 않았다.
	// 시간 복잡도: O(nlogn)

	static int[] mList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int t = Integer.parseInt(st.nextToken());

		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			int[] nList = new int[n];
			for (int j = 0; j < n; j++) {
				nList[j] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			mList = new int[m];
			for (int j = 0; j < m; j++) {
				mList[j] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(mList);

			long total = 0;
			for (int j = 0; j < n; j++) {
				int num = findNum(nList[j]);
				total += num;
			}

			System.out.println(total);
		}
	}

	static int findNum(int target) {
		int start = 0;
		int end = mList.length - 1;

		while (start <= end) {
			int mid = (start + end) / 2;

			if (target == mList[mid]) {
				return target;
			} else if (mList[mid] < target) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}

		if (start == mList.length) {
			return mList[mList.length -1];
		}
		if (end == -1) {
			return mList[0];
		}

		int sDistance = Math.abs(mList[start] - target);
		int eDistance = Math.abs(mList[end] - target);

		if (sDistance < eDistance) {
			return mList[start];
		}
		return mList[end];
	}
}
