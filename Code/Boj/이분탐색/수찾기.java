package Boj.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 수찾기 {
	// Sample Input/Output
	// 5
	// 4 1 5 2 3
	// 5
	// 1 3 7 9 5

	// 10m (Success)
	// 입력: 첫째 줄에 자연수 N(1 ≤ N ≤ 100,000)이 주어진다. 다음 줄에는 N개의 정수 A[1], A[2], …, A[N]이 주어진다.
	// 		다음 줄에는 M(1 ≤ M ≤ 100,000)이 주어진다. 다음 줄에는 M개의 수들이 주어지는데,
	// 		이 수들이 A안에 존재하는지 알아내면 된다. 모든 정수의 범위는 -231 보다 크거나 같고 231보다 작다.
	// 출력: M개의 줄에 답을 출력한다. 존재하면 1을, 존재하지 않으면 0을 출력한다.
	// 풀이 방향: 먼저 입력 받는 N개의 요소는 이분탐색을 할 것이므로 정렬을 하고, 이후 입력 받은 m개의 데이터를 통해 이분 탐색하여
	//			1, 0 여부를 판단한다.
	// 시간 복잡도: 정렬 시 O(nlogn), 이분탐색 시 O(n * logn) ==> O(nlogn)

	static int n;
	static int[] nList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());

		nList = new int[n];
		for (int i = 0; i < n; i++) {
			nList[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nList);

		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			int mElement = Integer.parseInt(st.nextToken());
			System.out.println(check(mElement));
		}
	}

	static int check(int num) {
		int start = 0;
		int end = nList.length - 1;

		// 이분 탐색
		while (start <= end) {
			int mid = (start + end) / 2;

			if (num == nList[mid]) {
				return 1;
			} else if (num < nList[mid]){
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}

		return 0;
	}

}
