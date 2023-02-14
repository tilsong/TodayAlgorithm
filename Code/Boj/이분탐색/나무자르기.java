package Boj.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 나무자르기 {
	// Sample Input/Output
	// 4 7
	// 20 15 10 17

	// 25m (Success)
	// 입력: 첫째 줄에 나무의 수 N과 상근이가 집으로 가져가려고 하는 나무의 길이 M이 주어진다. (1 ≤ N ≤ 1,000,000, 1 ≤ M ≤ 2,000,000,000)
	// 출력: 적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값을 출력한다.
	// 풀이 방향: 최대 높이가 십억이므로 이분탐색하면 30 정도, 모든 나무의 수가 최대 백만이므로 충분히
	//			 반복문과 함께 풀이할 수 있다고 생각했다.
	// 시간 복잡도: O(N*logH) , N은 나무의 수, H는 최대 나무의 높이 ==> 최대 삼천만 정도의 시간 복잡도를 가지게 된다.

	static int[] treeArr;
	static int m;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		treeArr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			treeArr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(treeArr);

		int start = 0;
		int end = treeArr[treeArr.length-1];
		int answer = 0;

		while (start <= end) {
			int mid = (start + end) / 2;

			long tempM = check(mid);
			if (tempM == m) {
				answer = mid;
				break;
			} else if (tempM < m) {
				end = mid - 1;
			} else {
				start = mid + 1;
				answer = mid;
			}
		}

		System.out.println(answer);
	}

	static long check(int h) {
		long hCount = 0;
		for (int i = treeArr.length-1; i >= 0 ; i--) {
			if (treeArr[i] > h) {
				hCount += (treeArr[i] - h);
			} else { // 최적화
				return hCount;
			}
		}
		return hCount;
	}
}
